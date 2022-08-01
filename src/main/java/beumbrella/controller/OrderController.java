package beumbrella.controller;

import beumbrella.model.Order;
import beumbrella.model.OrderDetail;
import beumbrella.model.Product;
import beumbrella.service.OrderDetailService;
import beumbrella.service.OrderService;
import beumbrella.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<Iterable<Order>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Order>> findById(@PathVariable long id) {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Iterable<Order>> add(@RequestBody Order order,
                                               @RequestBody OrderDetail orderDetail) {
//        Iterable<Order> listCart = orderService.findByUserId(order.getUser().getId());
//        Optional<Product> product = productService.findById(orderDetail.getProduct().getId());
//        for (CartItem item : listCart) {
//            if (item.getProduct().getId().equals(cartItem.getProduct().getId())) {
//                if (item.getQuantity() < item.getProduct().getQuantity()) {
//                    item.setQuantity(item.getQuantity() + cartItem.getQuantity());
//                    item.getProduct().setQuantity(item.getProduct().getQuantity() - cartItem.getQuantity());
//                    shoppingCartService.save(item);
//                    return new ResponseEntity<>(item, HttpStatus.OK);
//                } else {
//                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//                }
//            }
//        }
//        if (product.isPresent()) {
//            if (product.get().getQuantity() >= cartItem.getQuantity()) {
//                product.get().setQuantity(product.get().getQuantity() - cartItem.getQuantity());
//                shoppingCartService.save(cartItem);
//                return new ResponseEntity<>(cartItem, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> edit(@PathVariable long id, @RequestBody Order Order) {
        Optional<Order> Order1 = orderService.findById(id);
        if (!Order1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Order.setId(Order1.get().getId());
        orderService.save(Order);
        return new ResponseEntity<>(Order, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable long id) {
        orderService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
