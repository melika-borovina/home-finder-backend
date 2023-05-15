package com.ssst.homefinderbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class ArticleDto {

    private Integer id;
    private String title;
    private String content;
    private String imageURL;
    private String author_name;

}
