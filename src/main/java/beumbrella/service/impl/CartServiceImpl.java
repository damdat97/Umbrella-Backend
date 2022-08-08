package beumbrella.service.impl;

import beumbrella.model.CartItem;
import beumbrella.model.Product;
import beumbrella.repository.CartRepository;
import beumbrella.repository.UserRepository;
import beumbrella.service.CartService;
import beumbrella.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SessionScope
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    Map<Integer, Product> maps = new HashMap<>();
    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<CartItem> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void save(CartItem cartItem) {
        cartRepository.save(cartItem);

        //    CartItem cartItem1=maps.get(cartItem.getProduct().getId());
//    if(cartItem1==null){
//     maps.put(Math.toIntExact(cartItem.getProduct().getId()),cartItem);
//    }else {
//        cartItem1.setQuantity(cartItem1.getQuantity()+1);
//    }
    }

    @Override
    public void remove(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Iterable<CartItem> findByUserId(Long id) {
        return cartRepository.findByUserId(id);
    }

    @Override
    public Iterable<CartItem> findAllCartByUserId(Long id) {
        return cartRepository.findAllCartByUserId(id);
    }

    @Override
    public Iterable<CartItem> findAllCartByProductAndUserId(Long id) {
        return cartRepository.findAllCartByProductAndUserId(id);
    }
}
