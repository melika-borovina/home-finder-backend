package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.data.entity.CustomerSupportEntity;
import com.ssst.homefinderbackend.model.CustomerSupportRequestDto;
import com.ssst.homefinderbackend.model.ErrorObject;
import com.ssst.homefinderbackend.service.CustomerSupportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer-support")
public class CustomerSupportController {

    private final CustomerSupportService customerService;

    CustomerSupportController(CustomerSupportService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    ResponseEntity<List<CustomerSupportEntity>> getMessagesList() {
        return new ResponseEntity<>(this.customerService.getMessageList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getMessage(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(this.customerService.getMessage(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(404, e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<Object> createMessage(@RequestBody CustomerSupportRequestDto message) {
        try {
            return new ResponseEntity<>(this.customerService.createMessage(message), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteMessage(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(this.customerService.deleteMessage(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(500, e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
