package project.service.categoryService;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import project.MyGlobalException.APIExceptions;
import project.MyGlobalException.ResourceNotFoundException;
import project.Payload.CategoryDTO;
import project.Payload.CategoryResponse;
import project.categoryModel.Category;
import project.com.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {
//make update here too.
    private  List<Category> categoryList = new ArrayList<>();
    private Long nextId = 1L;
    @Autowired
    private CategoryRepo categoryRepo;

/**
 * Then go to serviceimplementation
 *  create
 *  @Autowired
 *  private modelMapper
 *
 * Challenege:
 * then create a list of categoryDto call it categoryDTS and use stream to map
 * then map category to categoryDTO and toList()
 *
 * create categoryResponse and new categoryResponse andsetcontent to categoryDTO and return it.
 */
    @Autowired
    private ModelMapper modelMapper;
    public CategoryResponse getCategoryList(Integer pageNumber, Integer pageSize,String sortBy,String sortOrder) {
        //that will implement PageRequest.of and pass pagenumber and pagesize
        //                    and get page<Category>categoryPage = CategoryRepository.findall(pageDetails)
        //
        Sort sorByAndOrder = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sorByAndOrder);
        Page<Category> page= categoryRepo.findAll(pageDetails);

        List<Category>categories = page.getContent();
        if(categories.isEmpty()) {
            throw new APIExceptions("Category List is empty!");
        }else{
            /*
            List<CategoryDTO> categoryDTO = categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).toList();

categories.stream(): This converts the categories list (a collection of Category objects) into a stream, which allows for functional operations like map.
.map(category -> modelMapper.map(category, CategoryDTO.class)): The map function iterates over each category in the stream and uses modelMapper.map() to convert each Category object into a CategoryDTO object. modelMapper is likely a utility to automatically map fields between the Category and CategoryDTO classes.
.toList(): This converts the result back into a list of CategoryDTO objects.
             */
            List<CategoryDTO> categoryDTO = categories.stream().map(category -> modelMapper.map(category,CategoryDTO.class)).toList();
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setContent(categoryDTO);
            categoryResponse.setPageNumber(page.getNumber());
            categoryResponse.setPageSize(page.getSize());
            categoryResponse.setTotalElements(page.getTotalElements());
            categoryResponse.setTotalPages(page.getTotalPages());
            categoryResponse.setLastPage(page.isLast());
            return categoryResponse;
        }


    }
/*

CategoryResponse categoryResponse = new CategoryResponse();
use modelMapper and convert savedcategory to categoryresponse and return

CategoryResponse savedCategory = new CategoryResponse();
savedCategory = modelMap.map(savedCategory,CategoryDTO.class);

 */
    @Override
    public CategoryDTO createCategory(CategoryDTO category) {
        Category category1 = modelMapper.map(category,Category.class);
        Category categoryFromDB= categoryRepo.findByCategoryName(category.getCategoryName());
        if( categoryFromDB!=null){
            throw new APIExceptions(category.getCategoryName()+"already exists!");
        }
        Category savedCategory = categoryRepo.save(category1);
        CategoryDTO savedCategoryDTO = modelMapper.map(savedCategory,CategoryDTO.class);
        return savedCategoryDTO;
      //  category.setId(nextId++);
    }
    @Override
    public CategoryDTO deleteCategory(Long id){
       // List<Category> catList = categoryRepo.findAll();
        //resource name, filed, and field name
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id,"Category","Category"));
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        categoryRepo.delete(category);
        return categoryDTO;

    }

    /**
     * @param categoryDTO
     * @param id
     * @return
     */
    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) {

        Optional<Category> categoryFromDb = categoryRepo.findById(id);

        if (categoryFromDb.isPresent()) {
            // Map the incoming DTO to a Category entity
            Category category = modelMapper.map(categoryDTO, Category.class);

            // Set the ID to ensure the correct Category is updated
            category.setId(id);

            // Save the updated category
            Category savedCategory = categoryRepo.save(category);

            // Return the updated category as a DTO
            return modelMapper.map(savedCategory, CategoryDTO.class);
        } else {
            // Handle the case where the category with the given ID does not exist
            throw new ResourceNotFoundException(id, "Category", "Category");
        }
    }


}


