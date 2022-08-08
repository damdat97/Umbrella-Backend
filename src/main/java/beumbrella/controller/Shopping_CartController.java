package beumbrella.controller;

import beumbrella.model.CartItem;
import beumbrella.model.Product;
import beumbrella.model.User;
import beumbrella.service.impl.CartServiceImpl;
import beumbrella.service.impl.ProductServiceImpl;
import beumbrella.service.impl.UserServiceImpl;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        Iterable<CartItem> result = cartService.findAll();
        // 1. loại bỏ những cartItem có status là đã thanh toán
        // code

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping({"/cart/{id}"})
    public ResponseEntity<Optional<CartItem>> findCartItem(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CartItem> addToShoppingCart(@RequestBody CartItem cartItem) {
         User currentUser = userService.getCurrentUser();
        cartItem.setUser(currentUser);
        Iterable<CartItem> listCart = cartService.findByUserId(cartItem.getUser().getId());
        Optional<Product> product = productService.findById(cartItem.getProduct().getId());
        for (CartItem item : listCart) {
            if (item.getProduct().getId().equals(cartItem.getProduct().getId())) {
                if (item.getQuantity() < item.getProduct().getQuantity()) {
                    item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                    item.getProduct().setQuantity(item.getProduct().getQuantity() - cartItem.getQuantity());
                    cartItem.setDate(LocalDate.now());
                    cartItem.setStatus(0);
                    cartService.save(item);
                    return new ResponseEntity<>(item, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
        }
        product.get().setQuantity(product.get().getQuantity() - cartItem.getQuantity());
        cartItem.setDate(LocalDate.now());
        cartItem.setStatus(0);
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

    @GetMapping("/find-all-carts-by-userId/{id}")
    public ResponseEntity<Iterable<CartItem>> findAllCartItem(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.findAllCartByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/find-all-carts-by-ownerId/{id}")
    public ResponseEntity<Iterable<CartItem>> findAllCartItemByOwnerId(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.findAllCartByProductAndUserId(id), HttpStatus.OK);
    }
}
