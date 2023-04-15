package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.data.entity.RealEstateEntity;
import com.ssst.homefinderbackend.model.CustomerSupportRequestDto;
import com.ssst.homefinderbackend.model.ErrorObject;
import com.ssst.homefinderbackend.model.RealEstateDto;
import com.ssst.homefinderbackend.service.CustomerSupportService;
import com.ssst.homefinderbackend.service.RealEstateBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/real-estate/customer-support")
@RestController
@Slf4j
public class CustomerSupportController {

    @Autowired
    private CustomerSupportService customerSupportService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerSupportRequestDto> getCustomerSupportRequestById(@PathVariable Integer id) {
        CustomerSupportRequestDto customerSupportRequestDto = customerSupportService.getCustomerSupportRequestById(id);
        if (customerSupportRequestDto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(customerSupportRequestDto);
        }
    }

    @GetMapping
    public ResponseEntity<List<CustomerSupportRequestDto>> getAllCustomerSupportRequests() {
        List<CustomerSupportRequestDto> customerSupportRequestDtos = customerSupportService.getAllCustomerSupportRequests();
        return ResponseEntity.ok(customerSupportRequestDtos);
    }

    @PostMapping
    public ResponseEntity<CustomerSupportRequestDto> addCustomerSupportRequest(@RequestBody CustomerSupportRequestDto customerSupportRequestDto) {
        customerSupportService.addCustomerSupportRequest(customerSupportRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSupportRequestDto);
    }


}
