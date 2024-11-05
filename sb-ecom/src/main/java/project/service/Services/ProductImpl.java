package project.service.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.MyGlobalException.APIExceptions;
import project.MyGlobalException.ResourceNotFoundException;
import project.Payload.ProcductDTO;
import project.Payload.ProdouctResponse;
import project.Models.Category;
import project.Models.Product;
import project.com.repo.CategoryRepo;
import project.com.repo.ProductRepo;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
//AWS SPIRNGBOOT
@Service
public class ProductImpl implements ProductService {
    @Autowired
    CategoryRepo categoryRepository;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;
    @Override
    public ProcductDTO addProduct(Long categoryId, ProcductDTO procductDTO) {
        //check if product already present or not

        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(categoryId,"categoryId","Category"));
        boolean isProductNotPresent =true;
        List<Product>products = category.getProducts();
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getProductName().equals(procductDTO.getProductName())){
                isProductNotPresent=false;
                break;
            }
        }
        if(isProductNotPresent){
            Product product = modelMapper.map(procductDTO,Product.class);
            product.setCategory(category);
            double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01 )* product.getPrice());
            product.setImage("default.png");
            product.setSpecial_price(specialPrice);

            Product savedProduct = productRepo.save(product);


            return modelMapper.map(savedProduct,ProcductDTO.class);
        }else{
            throw new APIExceptions("Product already exists");
        }

    }

    /**
     Service:
     return list of all Products
     List ProductDTO = products.stream().map(p->modelMapper.map(prodcut,productDto.class)).collect(Collector.toList());
     //list of productDTO
     ProductResponse instance
     productResponse.setContent(productDTOS);
     return productResponse
     * @return
     */

    @Override
    public ProdouctResponse getAll(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        //check if product size is 0
        /**
         * Sort sorByAndOrder = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
         *         Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sorByAndOrder);
         *         Page<Category> page= categoryRepo.findAll(pageDetails);
         */
        Sort sortByOrder = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByOrder);
        Page<Product> productList=productRepo.findAll(pageDetails);
        List<Product>pageProducts = productList.getContent();
//        List<Product>productList = productRepo.findAll();
        List<ProcductDTO>products;
        if(productList.isEmpty()){
           throw new APIExceptions("Product list is empty!");
        }
        products = productList.stream().map(p->modelMapper.map(p,ProcductDTO.class)).collect(Collectors.toList());
        ProdouctResponse prodouctResponse = new ProdouctResponse();
        prodouctResponse.setContent(products);
        prodouctResponse.setPageNumber(productList.getNumber());
        prodouctResponse.setLastPage(productList.isLast());
        prodouctResponse.setTotalPages(productList.getTotalPages());
        prodouctResponse.setTotalElements(productList.getTotalElements());
        prodouctResponse.setPageSize(productList.getSize());

        return prodouctResponse;

    }
    /*
Service:
similar to productResponse bnut first
get Category byId
List<Product>products = productRepo findeByCategeoryOrderByPriceASC(Category category);
then convert into ProductResponse using DTOS

and TEST!!!

     */
    @Override
    public ProdouctResponse searchByCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder,Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(categoryId,"categoryId","Category") );
        Sort sortByOrder = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByOrder);
        Page<Product> productList=  productRepo.findByCategoryOrderByPriceAsc(pageDetails,category);
        List<Product>pageProducts = productList.getContent();
       // List<Product> product =  productRepo.findByCategoryOrderByPriceAsc(category);
       List<ProcductDTO> dto = productList.stream().map(p->modelMapper.map(p,ProcductDTO.class)).collect(Collectors.toList());
        ProdouctResponse prodouctResponse = new ProdouctResponse();
        prodouctResponse.setContent(dto);
        prodouctResponse.setPageNumber(productList.getNumber());
        prodouctResponse.setLastPage(productList.isLast());
        prodouctResponse.setTotalPages(productList.getTotalPages());
        prodouctResponse.setTotalElements(productList.getTotalElements());
        prodouctResponse.setPageSize(productList.getSize());
        return prodouctResponse;
    }

    /*
 in service
create method it is very similar to getCategoryById
findByProductNameLikeIgnoreCase('%'+keyword+'%')
Test it!

     */
    @Override
    public ProdouctResponse searchByKeyword(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder,String keyword) {
        Sort sortByOrder = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByOrder);
        Page<Product>productList= productRepo.findByProductNameLikeIgnoreCase('%'+keyword+'%',pageDetails);
        List<Product>pageProducts = productList.getContent();
        List<ProcductDTO> procductDTOS = pageProducts.stream().map(p->modelMapper.map(p,ProcductDTO.class)).collect(Collectors.toList());
        ProdouctResponse prodouctResponse = new ProdouctResponse();
        prodouctResponse.setContent(procductDTOS);
    prodouctResponse.setPageNumber(productList.getNumber());
    prodouctResponse.setLastPage(productList.isLast());
      prodouctResponse.setTotalPages(productList.getTotalPages());
       prodouctResponse.setTotalElements(productList.getTotalElements());
      prodouctResponse.setPageSize(productList.getSize());
        return prodouctResponse;
    }

    @Override
    public ProcductDTO updateProduct(Long product_id,ProcductDTO procductDTO) {

        //find exitisng product, then update the product, and save in database. change into DTO and return
        Product productFromdatabase = productRepo.findById(product_id).orElseThrow(()->new ResourceNotFoundException(product_id,"productId","product"));
//            productFromdatabase.setProductId(product.getProductId());
//            productFromdatabase.setProductName(product.getProductName());
//            productFromdatabase.setCategory(product.getCategory());
//            productFromdatabase.setSpecial_price(product.getSpecial_price());
//            productFromdatabase.setImage(product.getImage());
       Product product = modelMapper.map(procductDTO,Product.class);
        BeanUtils.copyProperties(product,productFromdatabase,"productId");
        Product updatedProduct = productRepo.save(productFromdatabase);
        return modelMapper.map(updatedProduct,ProcductDTO.class);
    }

    @Override
    public ProcductDTO deleteProduct(Long productId) {
        /**
         * we find the product from productRepo, if not found throw an error
         *then save in dto
         * delete and return
         */
        Product product = productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException(productId,"ProductID","Product"));
        Product save = product;
        productRepo.delete(product);

        return modelMapper.map(save,ProcductDTO.class);
    }

    @Override
    public ProcductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
        /**
         * Get product form DB
         * upload image to server
         * Get the file name of uploaded image
         * updating new file name to the product
         * return dto after mapping top dto
         */
        Product productFromDb = productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException(productId,"ProductID","Product"));

        String fileName = fileService.uploadImage(path,image);
        productFromDb.setImage(fileName);
        Product updatedProducrt = productRepo.save(productFromDb);

        return modelMapper.map(updatedProducrt,ProcductDTO.class);
    }


}
