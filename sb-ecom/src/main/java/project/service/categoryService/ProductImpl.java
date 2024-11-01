package project.service.categoryService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.MyGlobalException.ResourceNotFoundException;
import project.Payload.ProcductDTO;
import project.Payload.ProdouctResponse;
import project.categoryModel.Category;
import project.categoryModel.Product;
import project.com.repo.CategoryRepo;
import project.com.repo.ProductRepo;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    CategoryRepo categoryRepository;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProcductDTO addProduct(Long categoryId, ProcductDTO procductDTO) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(categoryId,"categoryId","Category"));
        Product product = modelMapper.map(procductDTO,Product.class);
        product.setCategory(category);
        double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01 )* product.getPrice());
        product.setImage("default.png");
        product.setSpecial_price(specialPrice);

        Product savedProduct = productRepo.save(product);


        return modelMapper.map(savedProduct,ProcductDTO.class);
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
    public ProdouctResponse getAll() {
        List<Product>productList = productRepo.findAll();
        List<ProcductDTO>products;
        products = productList.stream().map(p->modelMapper.map(p,ProcductDTO.class)).collect(Collectors.toList());
        ProdouctResponse prodouctResponse = new ProdouctResponse();
        prodouctResponse.setContent(products);
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
    public ProdouctResponse searchByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(categoryId,"categoryId","Category") );
       List<Product> product =  productRepo.findByCategoryOrderByPriceAsc(category);
       List<ProcductDTO> dto = product.stream().map(p->modelMapper.map(p,ProcductDTO.class)).collect(Collectors.toList());
        ProdouctResponse prodouctResponse = new ProdouctResponse();
        prodouctResponse.setContent(dto);
        return prodouctResponse;
    }

    /*
 in service
create method it is very similar to getCategoryById
findByProductNameLikeIgnoreCase('%'+keyword+'%')
Test it!

     */
    @Override
    public ProdouctResponse searchByKeyword(String keyword) {
        List<Product>products = productRepo.findByProductNameLikeIgnoreCase('%'+keyword+'%');
        List<ProcductDTO> procductDTOS = products.stream().map(p->modelMapper.map(p,ProcductDTO.class)).collect(Collectors.toList());
        ProdouctResponse prodouctResponse = new ProdouctResponse();
        prodouctResponse.setContent(procductDTOS);
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
}
