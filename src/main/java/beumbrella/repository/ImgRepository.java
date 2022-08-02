package beumbrella.repository;

import beumbrella.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepository extends JpaRepository<Image,Long> {
    Iterable<Image> findAllByProductId(Long id);
}
