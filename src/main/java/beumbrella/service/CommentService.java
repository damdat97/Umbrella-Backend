package beumbrella.service;
import beumbrella.model.Comment;
import beumbrella.model.Product;

public interface CommentService extends GeneralService<Comment>{

    Iterable<Product> findNewProduct();

}
