package beumbrella.service;

import beumbrella.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ProductService extends GeneralService<Product> {
    Iterable<Product> findNewProduct();

    Iterable<Product> findAllByNameContaining(String name);

    //Page<Product> findAllPageProduct(Pageable pageable);
    Iterable<Product> findAllBySearch(String name, Long category_id , Long user_id,Integer from,Integer to);
    Iterable<Product> find(String name,Long category_id, Integer from, Integer to);
    Iterable<Product> findAllBySearch(String name, Long category_id );

    Iterable<Product> findProductByCate(long id);

    Iterable<Product> sortProductByQuantity(Long id);

    Iterable<Product> sortProductByPrice(Long id);

    Iterable<Product> findProductByUserId(Long id);
}
