package beumbrella.service;

import beumbrella.model.CartItem;

import java.util.Optional;

public interface CartService extends GeneralService<CartItem>{
    Iterable<CartItem> findByUserId(Long id);
}
