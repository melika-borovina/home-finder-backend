package com.ssst.homefinderbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TourBookingDto {
    private Integer id;
    private String name;
    private Date preferredDate;
    private LocalTime preferredTime;
    private String contactInfo;
    private Integer realEstateId;

}
