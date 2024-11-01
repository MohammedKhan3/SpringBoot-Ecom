package project.service.categoryService;

import project.Payload.ProcductDTO;
import project.Payload.ProdouctResponse;
import project.categoryModel.Product;

public interface ProductService {
    ProcductDTO addProduct(Long categoryId, ProcductDTO procductDTO);

    ProdouctResponse getAll();

    ProdouctResponse searchByCategory(Long categoryId);

    ProdouctResponse searchByKeyword(String keyword);

    ProcductDTO updateProduct(Long product_id,ProcductDTO procductDTO);

    ProcductDTO deleteProduct(Long productId);
}
