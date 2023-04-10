package com.ssst.homefinderbackend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String title;
    private String description;
    private Integer rating;
    private Long realEstateId;
    private Long userId;
}
