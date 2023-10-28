package com.example.demo.controller;

import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<Image> addImage(@RequestBody Image image){
        return ResponseEntity.ok(imageService.addImage(image));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Long id){
        Optional<Image> imagenBuscada = imageService.getImageById(id);
        if (imagenBuscada.isPresent()){
            return ResponseEntity.ok(imagenBuscada.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages(){
        return ResponseEntity.ok(imageService.getAllImages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateImage(@RequestBody Image image) throws ResourceNotFoundException{
        Optional<Image> imagenBuscada = imageService.getImageById(image.getId());
        if (imagenBuscada.isPresent()){
            imageService.updateImage(image);
            return ResponseEntity.ok("Image was Updated-" +image.getName());
        }else {
            throw new ResourceNotFoundException("Image was not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id){
        imageService.deleteImage(id);
        return ResponseEntity.ok("The image with de ID was deleted "+id);
    }

}
