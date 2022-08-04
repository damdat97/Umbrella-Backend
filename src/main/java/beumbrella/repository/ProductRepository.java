package beumbrella.repository;

import beumbrella.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    Iterable<Product> findAllByNameContaining(String name);



    @Query(value = "select * from products p  where(:name is null or p.name like :name ) and (:description is null or p.description like :description) and (:from is null or p.price >=:from) and(:to is null or p.price<=:to)", nativeQuery = true)
    Iterable<Product> find(@Param("name") String name,
                                      @Param("description") String description,
                                      @Param("from") Integer from,
                                      @Param("to") Integer to);
    @Query(value = "select * from products where user_id = :id", nativeQuery = true)
    Iterable<Product> findProductByUserId(@Param("id") Long id);
}
