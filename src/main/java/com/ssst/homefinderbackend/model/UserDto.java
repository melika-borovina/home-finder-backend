package com.ssst.homefinderbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String name;
    private Integer age;
    private String last_name;
    private String occupation;

}
