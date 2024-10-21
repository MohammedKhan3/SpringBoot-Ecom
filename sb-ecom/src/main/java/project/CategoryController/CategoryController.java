package project.CategoryController;

import project.AppConfig.APPConstants;
import project.Payload.CategoryDTO;
import project.Payload.CategoryResponse;
import project.categoryModel.Category;
import project.service.categoryService.CategoryServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired

    private CategoryServiceImp categoryServiceImp;
  @GetMapping("/public/categories")

  public ResponseEntity<CategoryResponse> getCategoryList(@RequestParam(name ="pageNumber", defaultValue = APPConstants.PAGE_NUMBER,required = false) Integer pageNumber,@RequestParam(name = "pageSize",defaultValue = APPConstants.PAGE_SIZE,required = false)Integer pageSize,@RequestParam(name ="sortBy", defaultValue = APPConstants.SORTBY,required = false) String sortBy,@RequestParam(name ="sortOrder",
          defaultValue = APPConstants.SORTDIR,required = false) String sortOrder){
      CategoryResponse categoryResponse = categoryServiceImp.getCategoryList(pageNumber,pageSize,sortBy,sortOrder);
      return new ResponseEntity<>(categoryResponse,HttpStatus.FOUND);
  }
  @GetMapping("/echo")
  public ResponseEntity<String>echoMessage(@RequestParam(name = "message",required = false) String message){
      return  new ResponseEntity<>("Echoed message : " + message,HttpStatus.OK);

  }

  @PostMapping("/public/addCategory")
  public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO category){

      CategoryDTO categoryDTO = categoryServiceImp.createCategory(category);
      //return "Category added!";
    return new ResponseEntity<>(categoryDTO,HttpStatus.CREATED);
  }

  @DeleteMapping("/admin/category/{id}")
  public ResponseEntity <CategoryDTO> deleteCategory(@PathVariable Long id){
        CategoryDTO deleteCategory = categoryServiceImp.deleteCategory(id);
        return new ResponseEntity<>(deleteCategory,HttpStatus.OK);

  }

  @PutMapping("/public/category/{id}")
   public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id){

         CategoryDTO savedCategoryDTO = categoryServiceImp.updateCategory(categoryDTO,id);
         return new ResponseEntity<>(savedCategoryDTO,HttpStatus.OK);

  }
}
