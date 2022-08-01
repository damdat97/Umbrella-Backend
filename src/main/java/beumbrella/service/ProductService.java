package beumbrella.service;

import beumbrella.model.Product;

public interface ProductService extends IGeneralService<Product>{
    Iterable<Product> findNewProduct();

}
