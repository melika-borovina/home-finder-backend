package com.ssst.homefinderbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerSupportRequestDto {

    private Integer customerSupportId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private CustomerSupportMessageType customerSupportMessageType;
    private String message;
}
