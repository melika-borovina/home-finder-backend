package com.ssst.homefinderbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class ArticleDto {

    private Integer id;
    private String title;
    private String content;
    private LocalTime created_at;
    private String author_name;

}
