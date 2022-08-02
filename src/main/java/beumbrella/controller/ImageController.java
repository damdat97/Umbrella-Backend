package beumbrella.controller;

import beumbrella.model.Image;
import beumbrella.service.impl.ImgServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Controller
@RequestMapping("/images")
public class ImageController {
    @Autowired
    ImgServiceImpl imageService;

    @GetMapping()
    public ResponseEntity<Iterable<Image>> findAllImg(){
        return new ResponseEntity<>(imageService.findAllImg(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Image> save(@RequestBody Image image) {
        imageService.save(image);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Iterable<Image>> findAllBuProductId(@PathVariable Long productId){
        return new ResponseEntity<>(imageService.findAllByProductId(productId),HttpStatus.OK);
    }
}
