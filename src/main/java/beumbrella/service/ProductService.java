package beumbrella.service;

import beumbrella.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ProductService extends GeneralService<Product> {
    Iterable<Product> findNewProduct();

    Iterable<Product> findAllByNameContaining(String name);

    //Page<Product> findAllPageProduct(Pageable pageable);
    Iterable<Product> findAllBySearch(String name, Long category_id );
}
