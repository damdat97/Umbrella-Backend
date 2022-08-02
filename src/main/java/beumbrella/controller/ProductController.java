package beumbrella.controller;


import beumbrella.model.Product;
import beumbrella.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
//    @GetMapping("/page")
//    public ResponseEntity<Iterable<Product>> findAllPage(Pageable pageable) {
//        return new ResponseEntity<>(productService.findAllPageProduct(pageable), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Iterable<Product>> add(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> edit(@PathVariable long id, @RequestBody Product product) {
        Optional<Product> product1 = productService.findById(id);
        if (!product1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(product1.get().getId());
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable long id) {
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-new-product")
    public ResponseEntity<Iterable<Product>> findNewProduct() {
        return new ResponseEntity<>(productService.findNewProduct(), HttpStatus.OK);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<Iterable<Product>> findAllByNameContaining(@RequestParam String name) {
        List<Product> products = (List<Product>) productService.findAllByNameContaining(name);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/find-by")
    public ResponseEntity<Iterable<Product>> findAllBySearch(@RequestParam String name, @RequestParam Long category_id) {
        return new ResponseEntity<>(productService.findAllBySearch('%'+name+'%', category_id), HttpStatus.OK);

    }
}
