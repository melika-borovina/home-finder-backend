package com.ssst.homefinderbackend.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class RealEstatePayload {
    private String imgUrl;
    private String imgAlt;
    private Integer bedrooms;
    private Integer bathrooms;
    private String propertyType;
    private Double price;
    private Double size;
    private String address;
    private Double avgRating;

}
