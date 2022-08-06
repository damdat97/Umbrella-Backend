package beumbrella.controller;

import beumbrella.model.CartItem;
import beumbrella.model.Product;
import beumbrella.service.impl.CartServiceImpl;
import beumbrella.service.impl.ProductServiceImpl;
import beumbrella.service.impl.UserServiceImpl;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/shopping_carts")

public class Shopping_CartController {
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    UserServiceImpl userService;
    @GetMapping
    public ResponseEntity<Iterable<CartItem>> findAll() {
        return new ResponseEntity<>(cartService.findAll(), HttpStatus.OK);
    }
    @GetMapping({"/cart/{id}"})
    public ResponseEntity<Optional<CartItem>> findCartItem(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public <userId> ResponseEntity<CartItem> addToShoppingCart(@RequestBody CartItem cartItem) {
        var curentUser = userService.getCurrentUser();
        cartItem.setUser(curentUser);
        Iterable<CartItem> listCart = cartService.findByUserId(cartItem.getUser().getId());
        Optional<Product> product = productService.findById(cartItem.getProduct().getId());
        for (CartItem item : listCart) {
            if (item.getProduct().getId().equals(cartItem.getProduct().getId())) {
                if (item.getQuantity() < item.getProduct().getQuantity()) {
                    item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                    item.getProduct().setQuantity(item.getProduct().getQuantity() - cartItem.getQuantity());
                    cartService.save(item);
                    return new ResponseEntity<>(item, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
        }
        product.get().setQuantity(product.get().getQuantity() - cartItem.getQuantity());
       cartService.save(cartItem);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }



    @PutMapping({"/cart/{id}"})
    public ResponseEntity<CartItem> updateShoppingCart(@PathVariable Long id, @RequestBody CartItem cartItem) {
        Optional<CartItem> cartItemOptional = cartService.findById(id);
        if (!cartItemOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartItem.setId(cartItemOptional.get().getId());
        cartItemOptional.get().getProduct().setQuantity(cartItemOptional.get().getProduct().getQuantity() - (cartItem.getQuantity()-cartItemOptional.get().getQuantity()));
        cartService.save(cartItem);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping({"/cart/{id}"})
    public ResponseEntity<CartItem> deleteShoppingCart(@PathVariable Long id) {
        Optional<CartItem> cartItemOptional = cartService.findById(id);
        cartItemOptional.get().getProduct().setQuantity(cartItemOptional.get().getProduct().getQuantity() + cartItemOptional.get().getQuantity());
       cartService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
