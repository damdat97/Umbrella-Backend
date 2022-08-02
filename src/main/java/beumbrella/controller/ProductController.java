package beumbrella.controller;

import beumbrella.model.Product;
import beumbrella.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/products")
@CrossOrigin("*")

public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Iterable<Product>> add(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> edit(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> product1 = productService.findById(id);
        if (!product1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(product1.get().getId());
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id) {
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-new-products")
    public ResponseEntity<Iterable<Product>> findNewProduct() {
        return new ResponseEntity<>(productService.findNewProduct(), HttpStatus.OK);
    }

    @GetMapping("/find-products-by-category/{id}")
    public ResponseEntity<Iterable<Product>> findProductByCategories(@PathVariable long id) {
        return new ResponseEntity<>(productService.findProductByCate(id), HttpStatus.OK);
    }

//    @GetMapping("/sort-products-by-quantity/{id}")
//    public ResponseEntity<Iterable<Product>> sortProductByQuantity(@PathVariable Long id) {
//        return new ResponseEntity<>(productService.sortProductByQuantity(id), HttpStatus.OK);
//    }
//    @GetMapping("/sort-products-by-price/{id}")
//    public ResponseEntity<Iterable<Product>> sortProductByPrice(@PathVariable Long id) {
//        return new ResponseEntity<>(productService.sortProductByPrice(id), HttpStatus.OK);
//    }
}
