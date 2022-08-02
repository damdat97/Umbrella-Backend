package beumbrella.service;

import beumbrella.model.Product;

public interface ProductService extends GeneralService<Product>{
    Iterable<Product> findNewProduct();

    Iterable<Product> findProductByCate(long id);

//    Iterable<Product> sortProductByQuantity(Long id);
//
//    Iterable<Product> sortProductByPrice(Long id);
}
