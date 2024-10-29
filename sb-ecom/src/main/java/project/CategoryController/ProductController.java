package project.CategoryController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.Payload.ProcductDTO;
import project.categoryModel.Category;
import project.categoryModel.Product;
import project.service.categoryService.ProductService;


@RestController
@RequestMapping("/api")

public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/admin/categories/{category_id}/product")
    public ResponseEntity<ProcductDTO> addProduct(@RequestBody Product product, @PathVariable Long category_id){

        ProcductDTO productDTO =  productService.addProduct(category_id,product);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

}
