package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.data.entity.CustomerSupportEntity;
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
    public ResponseEntity<CustomerSupportEntity> getCustomerSupportRequestById(@PathVariable Integer id) {
        CustomerSupportEntity customerSupportRequest = customerSupportService.getCustomerSupportRequestById(id);
        if (customerSupportRequest == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(customerSupportRequest);
        }
    }

    @GetMapping
    public ResponseEntity<List<CustomerSupportRequestDto>> getAllCustomerSupportRequests() {
        List<CustomerSupportRequestDto> customerSupportRequestDtos = customerSupportService.getAllCustomerSupportRequests();
        return ResponseEntity.ok(customerSupportRequestDtos);
    }

    @PostMapping
    public ResponseEntity<CustomerSupportEntity> addCustomerSupportRequest(@RequestBody CustomerSupportEntity customerSupportRequest) {
        customerSupportService.addCustomerSupportRequest(customerSupportRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSupportRequest);
    }


}
