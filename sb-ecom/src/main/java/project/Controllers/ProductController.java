package project.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.AppConfig.APPConstants;
import project.Payload.ProcductDTO;
import project.Payload.ProdouctResponse;
import project.service.Services.ProductService;

import java.io.IOException;

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

 */
@RestController
@RequestMapping("/api")

public class ProductController {
    @Autowired
   private ProductService productService;
    @PostMapping("/admin/categories/{category_id}/product")
    public ResponseEntity<ProcductDTO> addProduct(@Valid @RequestBody ProcductDTO productDTO, @PathVariable Long category_id){

        ProcductDTO savedProduct =  productService.addProduct(category_id,productDTO);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    @GetMapping("/public/products")
   public ResponseEntity<ProdouctResponse>getProducts(@RequestParam(name ="pageNumber", defaultValue = APPConstants.PAGE_NUMBER,required = false)Integer pageNumber,@RequestParam(name ="pageSize",defaultValue = APPConstants.PAGE_SIZE,required = false)Integer pageSize,@RequestParam(name="sortBy",defaultValue = APPConstants.SORTBY_PRODUCID,required = false)String sortBy,
                                                      @RequestParam(name="sortOrder",
                                                              defaultValue = APPConstants.SORTDIR,required = false)String sortOrder){

        ProdouctResponse response = productService.getAll(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(response,HttpStatus.OK);
   }
    @GetMapping("public/categories/{categoryId}/products")
    public ResponseEntity<ProdouctResponse>getProductsByCategory(@RequestParam(name ="pageNumber", defaultValue = APPConstants.PAGE_NUMBER,required = false)Integer pageNumber,@RequestParam(name ="pageSize",defaultValue = APPConstants.PAGE_SIZE,required = false)Integer pageSize,@RequestParam(name="sortBy",defaultValue = APPConstants.SORTBY_PRODUCID,required = false)String sortBy,
                                                                 @RequestParam(name="sortOrder",
                                                                         defaultValue = APPConstants.SORTDIR,required = false)String sortOrder,@PathVariable Long categoryId){
        ProdouctResponse prodouctResponse =  productService.searchByCategory(pageNumber,pageSize,sortBy,sortOrder,categoryId);
        return new ResponseEntity<>(prodouctResponse,HttpStatus.OK);
    }
@GetMapping("public/categories/search/{keyword}/products")
    public ResponseEntity<ProdouctResponse>getProductsByKeyword(@RequestParam(name ="pageNumber", defaultValue = APPConstants.PAGE_NUMBER,required = false)Integer pageNumber,@RequestParam(name ="pageSize",defaultValue = APPConstants.PAGE_SIZE,required = false)Integer pageSize,@RequestParam(name="sortBy",defaultValue = APPConstants.SORTBY_PRODUCID,required = false)String sortBy,
                                                                @RequestParam(name="sortOrder",defaultValue = APPConstants.SORTDIR,required = false)String sortOrder,@PathVariable String keyword){
        ProdouctResponse prodouctResponse = productService.searchByKeyword(pageNumber,  pageSize,  sortBy,  sortOrder,keyword);
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
    @PutMapping("product/{productId}/image")
    public ResponseEntity<ProcductDTO>updateProductImage(@PathVariable Long productId, @RequestParam("image")MultipartFile image) throws IOException {

        ProcductDTO updateProduct = productService.updateProductImage(productId,image);
        return new ResponseEntity<>(updateProduct,HttpStatus.OK);

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

