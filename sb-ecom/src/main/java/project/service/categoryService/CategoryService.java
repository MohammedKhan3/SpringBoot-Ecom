package project.service.categoryService;

import project.Payload.CategoryDTO;
import project.Payload.CategoryResponse;
import project.categoryModel.Category;

import java.util.List;

public interface CategoryService {
//instead of list use categoryResponse
    CategoryResponse getCategoryList();
   CategoryDTO createCategory(CategoryDTO category);
    CategoryDTO deleteCategory(Long id);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);

}
