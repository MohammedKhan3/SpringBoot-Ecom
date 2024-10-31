package project.service.categoryService;

import org.modelmapper.ModelMapper;
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
    public ProcductDTO addProduct(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(categoryId,"categoryId","Category"));

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
}
