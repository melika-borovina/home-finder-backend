package com.ssst.homefinderbackend.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class RealEstateDto {
    private String imgUrl;
    private String imgAlt;
    private Integer bedrooms;
    private Integer bathrooms;
    private Double avgRating;
    private String propertyType;
    private Double price;
    private Double size;
    private String address;
    private List<Integer> features;
}
