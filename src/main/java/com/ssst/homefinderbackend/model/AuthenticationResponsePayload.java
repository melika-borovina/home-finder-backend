package com.ssst.homefinderbackend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponsePayload {
    private String token;
}
