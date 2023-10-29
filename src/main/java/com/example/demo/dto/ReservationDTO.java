package com.example.demo.dto;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationDTO {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long productId;
    private String status;
    private String comments;
    private Double finalPrice;
    private Long userId;
}
