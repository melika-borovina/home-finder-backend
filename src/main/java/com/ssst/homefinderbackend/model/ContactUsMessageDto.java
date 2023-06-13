package com.ssst.homefinderbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactUsMessageDto {

    private Integer contactUsId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String contactUsMessageType;
    private String message;

}
