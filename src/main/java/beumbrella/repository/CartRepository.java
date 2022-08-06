package beumbrella.repository;

import beumbrella.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartItem,Long> {
    Iterable<CartItem> findByUserId(Long id);
}
