package com.example.demo.controller;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.entity.Reservation;
import com.example.demo.service.ProductService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;


    @PostMapping
    public ResponseEntity<ReservationDTO> addReservation(@RequestBody Reservation reservation){
        if (productService.getProductById(reservation.getProduct().getId())!=null &&userService.getUserById(reservation.getUser().getId())!=null){
            return ResponseEntity.ok(reservationService.addReservation(reservation));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id){
        Optional<ReservationDTO> reservationFound = reservationService.getReservationById(id);
        if (reservationFound.isPresent()){
            return ResponseEntity.ok(reservationFound.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation (@PathVariable Long id) throws ResourceNotFoundException{
        Optional<ReservationDTO> reservationFound = reservationService.getReservationById(id);
        if (reservationFound.isPresent()){
            reservationService.deleteReservation(id);
            return ResponseEntity.ok("The Reservation was removed ID: " +id);
        }else {
            throw new ResourceNotFoundException("Delete error");
        }
    }

    @PutMapping("/{id}")
    public  ResponseEntity<ReservationDTO> updateReservation (@PathVariable Long id, @RequestBody Reservation updatedReservation){
        try {
            ReservationDTO updatedReservationDTO = reservationService.updateReservation(id, updatedReservation);
            return ResponseEntity.ok(updatedReservationDTO);
        }catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
