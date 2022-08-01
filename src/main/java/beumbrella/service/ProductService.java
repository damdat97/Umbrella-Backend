package beumbrella.service;

import beumbrella.model.Product;

public interface ProductService extends GeneralService<Product>{
    Iterable<Product> findNewProduct();
    Iterable<Product> findAllByNameContaining(String name);

}
