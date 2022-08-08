package beumbrella.service;

import beumbrella.model.CartItem;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartService extends GeneralService<CartItem>{
    Iterable<CartItem> findByUserId(Long id);

    Iterable<CartItem> findAllCartByUserId( Long id);

    Iterable<CartItem> findAllCartByProductAndUserId(Long id);


}
