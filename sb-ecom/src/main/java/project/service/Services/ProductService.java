package project.service.Services;

import org.springframework.web.multipart.MultipartFile;
import project.Payload.ProcductDTO;
import project.Payload.ProdouctResponse;

import java.io.IOException;

public interface ProductService {
    ProcductDTO addProduct(Long categoryId, ProcductDTO procductDTO);

    ProdouctResponse getAll(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProdouctResponse searchByCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder,Long categoryId);

    ProdouctResponse searchByKeyword(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder,String keyword);

    ProcductDTO updateProduct(Long product_id,ProcductDTO procductDTO);

    ProcductDTO deleteProduct(Long productId);

    ProcductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
}
