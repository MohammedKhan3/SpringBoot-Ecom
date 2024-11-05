package project.service.Services;

import project.Payload.CategoryDTO;
import project.Payload.CategoryResponse;

public interface CategoryService {
//instead of list use categoryResponse
    CategoryResponse getCategoryList(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder);
   CategoryDTO createCategory(CategoryDTO category);
    CategoryDTO deleteCategory(Long id);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);

}
