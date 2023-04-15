package com.ssst.homefinderbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDto {
    private Integer id;
    private String title;
    private String description;
    private Integer rating;
    private Integer realEstateId;
    private Integer userId;
}
