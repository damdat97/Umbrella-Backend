package beumbrella.repository;

import beumbrella.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from products order by id desc limit 4", nativeQuery = true)
    Iterable<Product> findNewProduct();

    Iterable<Product> findAllByNameContaining(String name);

//    @Query(value = "select * from products p inner join category c on c.id=p.category_id where" +
//            "(:name is null or p.name like : name)" + "and" +
//            "(:from is null or p.price>=:from)" + "and" +
//            "(:to is null or p.price_to<=:to)" + "and" +
//            "(:category_id is null or c.id=:category_id)", nativeQuery = true)

//    Page<Product> findAllPageProduct(Pageable pageable);


    @Query(value = "select * from products p inner join category c on c.id = p.category_id inner join user_role ur on p.user_id = ur.user_id where(:name is null or p.name like :name ) and (:category_id is null or c.id = :category_id) and (:user_id is null or ur.id = :user_id) and (:from is null or p.price >=:from) and(:to is null or p.price<=:to)", nativeQuery = true)
    Iterable<Product> findAllBySearch(@Param("name") String name,
                                      @Param("category_id") Long category_id,
                                      @Param("user_id") Long user_id,
                                      @Param("from") Integer from,
                                      @Param("to") Integer to);

    @Query(value = "select * from products p inner join category c on c.id = p.category_id where(:name is null or p.name like :name ) and (:category_id is null or c.id = :category_id) and (:from is null or p.price >=:from) and(:to is null or p.price<=:to)", nativeQuery = true)
    Iterable<Product> find(@Param("name") String name,
                                      @Param("category_id") Long category_id,
                                      @Param("from") Integer from,
                                      @Param("to") Integer to);
}
