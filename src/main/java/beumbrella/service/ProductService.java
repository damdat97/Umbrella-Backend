package beumbrella.service;

import beumbrella.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ProductService extends GeneralService<Product> {
    Iterable<Product> findNewProduct();

    Iterable<Product> findAllByNameContaining(String name);

    Iterable<Product> findAllByUserIdNot(@Param("id") Long id);

    Iterable<Product> find(String name,String description, Integer from, Integer to);

    Iterable<Product> findProductByCate(long id);

    Iterable<Product> findProductByUserId(Long id);

    Iterable<Product> findProductByCategoryAndUserIdNot(Long categoryId, Long userId);

    Iterable<Product> findProductsByCustomerId(Long customerId, Long userId);
}


