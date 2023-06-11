package com.ssst.homefinderbackend.model;

import com.ssst.homefinderbackend.data.entity.RealEstateEntity;
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
    private RealEstateEntity realEstateId;
}
