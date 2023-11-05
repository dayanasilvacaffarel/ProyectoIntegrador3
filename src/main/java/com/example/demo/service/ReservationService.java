package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ReservationDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.User;
import com.example.demo.repository.ReservationRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    private ReservationDTO reservationToReservationDto(Reservation reservation){
        ReservationDTO reservationDTO=new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setProductId(reservation.getProduct().getId());
        reservationDTO.setStatus(reservation.getStatus());
        reservationDTO.setComments(reservation.getComments());
        reservationDTO.setFinalPrice(reservation.getFinalPrice());
        reservationDTO.setUserId(reservation.getUser().getId());
        return reservationDTO;
    }
    private Reservation reservationDtoToReservation(ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
        Product product =new Product();
        User user = new User();
        product.setId(reservationDTO.getId());
        user.setId(reservationDTO.getId());
        reservation.setId(reservationDTO.getId());
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setStatus(reservationDTO.getStatus());
        reservation.setComments(reservationDTO.getComments());
        reservation.setFinalPrice(reservationDTO.getFinalPrice());
        return reservation;
    }
    public ReservationDTO addReservation(Reservation reservation){
        Reservation reservationSaved = reservationRepository.save(reservation);
        return reservationToReservationDto(reservationSaved);
    }

    public List<ReservationDTO> getReservations(){
        List<Reservation> reservationFound = reservationRepository.findAll();
        List<ReservationDTO> reservationDTOList = new ArrayList<>();
        for (Reservation reservation : reservationFound){
            reservationDTOList.add(reservationToReservationDto(reservation));
        }
        return reservationDTOList;
    }
    public Optional<ReservationDTO> getReservationById(Long id){
        Optional<Reservation> reservationFound = reservationRepository.findById(id);
        if (reservationFound.isPresent()){
            return  Optional.of(reservationToReservationDto(reservationFound.get()));
        }else {
            return Optional.empty();
        }
    }
    public void deleteReservation(Long id){
        reservationRepository.deleteById(id);
    }
    public ReservationDTO updateReservation(Long reservationId, Reservation updatedReservationDTO){
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isPresent()){
            Reservation existingReservation = reservationOptional.get();

            existingReservation.setStartDate(updatedReservationDTO.getStartDate());
            existingReservation.setEndDate(updatedReservationDTO.getEndDate());
            existingReservation.setStatus(updatedReservationDTO.getStatus());
            existingReservation.setComments(updatedReservationDTO.getComments());
            existingReservation.setFinalPrice(updatedReservationDTO.getFinalPrice());

            Reservation updatedReservation = reservationRepository.save(existingReservation);

            return reservationToReservationDto(updatedReservation);

        }else {
            throw  new ResourceNotFoundException("Reservation not found with ID: "+reservationId);
        }
    }


}
