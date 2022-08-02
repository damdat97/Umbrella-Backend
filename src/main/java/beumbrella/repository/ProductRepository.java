package beumbrella.repository;

import beumbrella.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from products order by id desc limit 4", nativeQuery = true)
    Iterable<Product> findNewProduct();

    @Query(value = "select * from products join category c on products.category_id = c.id where c.id = :id", nativeQuery = true)
    Iterable<Product> findProductByCategory(@Param("id") Long id);

//    @Query(value = "select *\n" +
//            "from products\n" +
//            "         join category c on c.id = products.category_id\n" +
//            "where c.id = :id\n" +
//            "order by price desc", nativeQuery = true)
//    Iterable<Product> sortProductByPrice(@Param("id") Long id);
//
//    @Query(value = "select *\n" +
//            "from products\n" +
//            "         join category c on c.id = products.category_id\n" +
//            "where c.id = :id\n" +
//            "order by quantity desc", nativeQuery = true)
//    Iterable<Product> sortProductByQuantity(@Param("id") Long id);
}
