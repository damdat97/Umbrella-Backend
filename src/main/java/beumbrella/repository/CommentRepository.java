package beumbrella.repository;

import beumbrella.model.Category;
import beumbrella.model.Comment;
import beumbrella.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select * from comments order by id desc limit 4", nativeQuery = true)
    Iterable<Product> findNewProduct();
}
