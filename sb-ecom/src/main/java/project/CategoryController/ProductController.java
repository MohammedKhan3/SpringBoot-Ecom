package project.CategoryController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.Payload.ProcductDTO;
import project.Payload.ProdouctResponse;
import project.categoryModel.Category;
import project.categoryModel.Product;
import project.service.categoryService.ProductService;

/**
 *
 * write logic to get all products
 *
 *  @GetMapping(/public/products/)
 *  public ResponseEntity<ProductResponse> getAllProducts(){
 *
 *  ProductResponse = productservice.getAll()
 *  return ResponseEnity
 *  }
 * SKHAN89 - email
 */
@RestController
@RequestMapping("/api")

public class ProductController {
    @Autowired
   private ProductService productService;
    @PostMapping("/admin/categories/{category_id}/product")
    public ResponseEntity<ProcductDTO> addProduct(@RequestBody Product product, @PathVariable Long category_id){

        ProcductDTO productDTO =  productService.addProduct(category_id,product);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }
    @GetMapping("/public/products")
   public ResponseEntity<ProdouctResponse>getProducts(){
        ProdouctResponse response = productService.getAll();
        return new ResponseEntity<>(response,HttpStatus.OK);
   }
    @GetMapping("public/categories/{categoryId}/products")
    public ResponseEntity<ProdouctResponse>getProductsByCategory(@PathVariable Long categoryId){
        ProdouctResponse prodouctResponse =  productService.searchByCategory(categoryId);
        return new ResponseEntity<>(prodouctResponse,HttpStatus.OK);
    }
@GetMapping("public/categories/search/{keyword}/products")
    public ResponseEntity<ProdouctResponse>getProductsByKeyword(@PathVariable String keyword){
        ProdouctResponse prodouctResponse = productService.searchByKeyword(keyword);
        return new ResponseEntity<>(prodouctResponse,HttpStatus.OK);

    }
   }//end of main method

