package project.com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.categoryModel.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
}
