package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.model.ContactUsMessageDto;
import com.ssst.homefinderbackend.service.ContactUsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contact-us")
public class ContactUsController {
    @Autowired
    private ContactUsService contactUsService;

    @GetMapping("/{id}")
    public ResponseEntity<ContactUsMessageDto> getContactUsRequestById(@PathVariable Integer id) {
        ContactUsMessageDto contactUsMessageDto = contactUsService.getContactUsRequestById(id);
        if (contactUsMessageDto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(contactUsMessageDto);
        }
    }

    @GetMapping
    public ResponseEntity<List<ContactUsMessageDto>> getAllContactUsRequests() {
        List<ContactUsMessageDto> contactUsMessageDto = contactUsService.getAllContactUsRequests();
        return ResponseEntity.ok(contactUsMessageDto);
    }

    @PostMapping
    public ResponseEntity<ContactUsMessageDto> addContactUsRequest(@RequestBody ContactUsMessageDto contactUsRequestDto) {
        contactUsService.addContactUsRequest(contactUsRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactUsRequestDto);
    }


}
