package project.service.categoryService;

import project.Payload.ProcductDTO;
import project.categoryModel.Product;

public interface ProductService {
    ProcductDTO addProduct(Long categoryId, Product product);
}
