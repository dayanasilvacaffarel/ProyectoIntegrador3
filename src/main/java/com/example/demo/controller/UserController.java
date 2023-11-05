package com.example.demo.controller;

import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userBuscado = userService.getUserById(id);
        if (userBuscado.isPresent()) {
            return ResponseEntity.ok(userBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user) throws ResourceNotFoundException {
        Optional<User> userBuscado = userService.getUserById(user.getId());
        if (userBuscado.isPresent()) {
            userService.updateUser(user);
            return ResponseEntity.ok("User was Updated-" + user.getName());
        } else {
            throw new ResourceNotFoundException("User was not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("Theuser with de ID was deleted "+id);
    }
}
