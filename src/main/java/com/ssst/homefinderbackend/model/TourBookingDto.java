package com.ssst.homefinderbackend.model;

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
    private String username;
    private LocalDateTime preferredDate;
    private String contactInfo;
    private Integer realEstateId;

}
