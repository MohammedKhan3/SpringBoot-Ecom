package project.service.categoryService;

import project.Payload.CategoryDTO;
import project.Payload.CategoryResponse;
import project.categoryModel.Category;

import java.util.List;

public interface CategoryService {
//instead of list use categoryResponse
    CategoryResponse getCategoryList(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder);
   CategoryDTO createCategory(CategoryDTO category);
    CategoryDTO deleteCategory(Long id);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);

}
