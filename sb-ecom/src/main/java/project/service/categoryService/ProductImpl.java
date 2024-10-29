package project.service.categoryService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.MyGlobalException.ResourceNotFoundException;
import project.Payload.ProcductDTO;
import project.categoryModel.Category;
import project.categoryModel.Product;
import project.com.repo.CategoryRepo;
import project.com.repo.ProductRepo;

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
}
