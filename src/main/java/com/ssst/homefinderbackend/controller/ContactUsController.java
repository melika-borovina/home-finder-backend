package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.data.entity.ContactUsEntity;
import com.ssst.homefinderbackend.model.ContactUsMessageDto;
import com.ssst.homefinderbackend.model.ErrorObject;
import com.ssst.homefinderbackend.service.ContactUsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-us")
public class ContactUsController {

    private final ContactUsService contactService;

    ContactUsController(ContactUsService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/list")
    ResponseEntity<List<ContactUsEntity>> getMessagesList() {
        return new ResponseEntity<>(this.contactService.getMessageList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getMessage(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(this.contactService.getMessage(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(404, e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<Object> createMessage(@RequestBody ContactUsMessageDto message) {
        try {
            return new ResponseEntity<>(this.contactService.createMessage(message), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteMessage(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(this.contactService.deleteMessage(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(500, e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
