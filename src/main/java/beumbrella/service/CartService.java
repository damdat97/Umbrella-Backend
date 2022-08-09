package beumbrella.service;

import beumbrella.model.CartItem;
import org.springframework.data.repository.query.Param;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface CartService extends GeneralService<CartItem>{
    List<CartItem> findAllCartByUserId(Long id);

    boolean checkout(Long userId);

    Iterable<CartItem> findAllCartByProductAndUserId(Long id);

    Iterable<CartItem> findDetailCart(Long id, Long cart_id);

}
