package beumbrella.service.impl;

import beumbrella.model.CartItem;
import beumbrella.model.Product;
import beumbrella.repository.CartRepository;
import beumbrella.repository.UserRepository;
import beumbrella.service.CartService;
import beumbrella.service.CategoryService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@SessionScope
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    Map<Integer, Product> maps = new HashMap<>();
    @Autowired
    UserRepository userRepository;

    // lấy tất cả cart item có trạng thái là 0 - chưa thanh toán
    @Override
    public Iterable<CartItem> findAll() {
        var items = cartRepository.findAll();
        if(items.isEmpty()) return items;
        for(var item : items){
            if(item.getStatus() != 0){
                items.remove(item);
            }
        }
        return items;
    }

    @Override
    public boolean checkout(Long userId) {
        try {
            var items = cartRepository.findAllCartByUserId(userId);
            for (CartItem item : items){
                if(item.getStatus() == 0){
                    item.setStatus(1);
                    cartRepository.save(item);
                }
            }
            return true;
        }catch(Exception ex) {
            return false;
        }
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
    public List<CartItem> findAllCartByUserId(Long id) {
        var result = new ArrayList<CartItem>();
        var items = cartRepository.findAllCartByUserId(id);
        if(items == null) return result;
        for(CartItem item : items){
            if(item.getStatus() == 0){
                result.add((item));
            }
        }
        return result;
    }

    @Override
    public Iterable<CartItem> findAllCartByProductAndUserId(Long id) {
        return cartRepository.findAllCartByProductAndUserId(id);
    }

    @Override
    public Iterable<CartItem> findDetailCart(Long id, Long cart_id) {
        return cartRepository.findDetailCart(id, cart_id);
    }

    @Override
    public List<CartItem> findAllCartByShopIdAndCustomerId(Long shopId, Long customerId) {
        return cartRepository.findAllCartByShopIdAndCustomerId(shopId, customerId);
    }

    @Override
    public List<CartItem> findBillStatusEqualsZero(Long userId) {
        return cartRepository.findBillStatusEqualsZero(userId);
    }

    @Override
    public List<CartItem> findBillStatusEqualsOne(Long userId) {
        return cartRepository.findBillStatusEqualsOne(userId);
    }

    @Override
    public List<CartItem> findBillStatusEqualsTwo(Long userId) {
        return cartRepository.findBillStatusEqualsTwo(userId);
    }

    @Override
    public List<CartItem> findBillStatusEqualsThree(Long userId) {
        return cartRepository.findBillStatusEqualsThree(userId);
    }
}
