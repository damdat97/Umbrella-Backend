package beumbrella.service.impl;

import beumbrella.model.Product;
import beumbrella.repository.ProductRepository;
import beumbrella.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

//    @Override
//    public Page<Product> findAllPageProduct(Pageable pageable) {
//        return productRepository.findAllPageProduct(pageable);
//    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
    productRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findNewProduct() {
        return productRepository.findNewProduct();
    }

    @Override
    public Iterable<Product> findAllByNameContaining(String name) {
        return productRepository.findAllByNameContaining(name);
    }

    @Override
    public Iterable<Product> findAllBySearch(String name, Long category_id) {
        return productRepository.findAllBySearch(name, category_id);
    }


}
