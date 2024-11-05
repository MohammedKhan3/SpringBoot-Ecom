package project.com.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.Models.Category;
import project.Models.Product;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
   Page<Product> findByCategoryOrderByPriceAsc(Pageable pageDetails, Category category);

 Page<Product> findByProductNameLikeIgnoreCase(String s, Pageable pageDetails);
}
