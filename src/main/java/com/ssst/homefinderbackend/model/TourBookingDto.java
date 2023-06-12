package com.ssst.homefinderbackend.model;

import com.ssst.homefinderbackend.data.entity.RealEstateEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourBookingDto {
    private Integer id;
    private LocalDateTime preferredDate;
    private String email;
    private RealEstateEntity realEstateId;

}
