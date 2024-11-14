package ecommerce_flutter.repository;

import ecommerce_flutter.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p WHERE p.deleted = false ")
    List<Product> findAllNotDeleted();
}
