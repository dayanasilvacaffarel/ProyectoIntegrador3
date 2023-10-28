package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image addImage(Image image){
        return imageRepository.save(image);
    }
    public Optional<Image> getImageById(Long id){
        return imageRepository.findById(id);
    }

    public List<Image> getAllImages(){
        return imageRepository.findAll();
    }

    public void updateImage(Image image){
        imageRepository.save(image);
    }
    public void deleteImage(Long id){
        imageRepository.deleteById(id);
    }

}
