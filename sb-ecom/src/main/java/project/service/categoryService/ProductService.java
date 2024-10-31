package project.service.categoryService;

import project.Payload.ProcductDTO;
import project.Payload.ProdouctResponse;
import project.categoryModel.Product;

public interface ProductService {
    ProcductDTO addProduct(Long categoryId, Product product);

    ProdouctResponse getAll();

    ProdouctResponse searchByCategory(Long categoryId);

    ProdouctResponse searchByKeyword(String keyword);
}
