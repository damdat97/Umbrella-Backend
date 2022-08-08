package beumbrella.repository;

import beumbrella.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartItem,Long> {
    Iterable<CartItem> findByUserId(Long id);

    @Query(value = "select *\n" +
            "from cart_item join user_table ut on cart_item.user_id = ut.id\n" +
            "where cart_item.user_id = :id ;", nativeQuery = true)
    Iterable<CartItem> findAllCartByUserId(@Param("id") Long id);
}
