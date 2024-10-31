package project.com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.categoryModel.Category;
import project.categoryModel.Product;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
   List<Product> findByCategoryOrderByPriceAsc(Category category);

    List<Product> findByProductNameLikeIgnoreCase(String s);
}
