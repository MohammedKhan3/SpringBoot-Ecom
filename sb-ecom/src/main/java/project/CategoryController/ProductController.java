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
    public ResponseEntity<ProcductDTO> addProduct(@RequestBody ProcductDTO productDTO, @PathVariable Long category_id){

        ProcductDTO savedProduct =  productService.addProduct(category_id,productDTO);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
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
    /**
     * Update Product
     *
     * takes a request body as json
     * and productId as pathvariable, response with productDTO httpstatus ok
     * /api/products/{productID}
     */
    @PutMapping("admin/products/{product_id}")
    public ResponseEntity<ProcductDTO>updateProduct(@RequestBody ProcductDTO productDTO,@PathVariable Long product_id){
        ProcductDTO dto = productService.updateProduct(product_id,productDTO);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
    /*
    Create a mappings that takes a path variable in and deletes the product using the productID
    lets call the deletingmethod - deleteProduct

     */
    @DeleteMapping("admin/products/deleteProducts/{productId}")
    public ResponseEntity<ProcductDTO>deleteProduct(@PathVariable Long productId){
        ProcductDTO deletingProduct = productService.deleteProduct(productId);

        return new ResponseEntity<>(deletingProduct,HttpStatus.OK);
    }
   }//end of main method

