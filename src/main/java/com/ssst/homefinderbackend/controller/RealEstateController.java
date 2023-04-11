package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.data.entity.RealEstateEntity;
import com.ssst.homefinderbackend.model.ErrorObject;
import com.ssst.homefinderbackend.model.RealEstatePayload;
import com.ssst.homefinderbackend.service.RealEstateBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/real-estate")
@RestController
@Slf4j
public class RealEstateController {
    @Autowired
    RealEstateBusinessService service;

    @GetMapping("")
    ResponseEntity<List<RealEstateEntity>> getRealEstateList() {
        log.info("getRealEstateList() called");
        return new ResponseEntity<>(service.getRealEstateList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getRealEstateById(@PathVariable Integer id) {
        log.info("getRealEstateById() called");
        try {
            return new ResponseEntity<>(service.getRealEstateById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(404, e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<Object> createRealEstate(@RequestBody RealEstatePayload realEstate) {
        log.info("createRealEstate() called");
        try {
            return new ResponseEntity<>(service.createRealEstate(realEstate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> updateRealEstateById(@PathVariable Integer id,
                                                @RequestBody RealEstatePayload realEstate) {
        log.info("updateRealEstateById() called");
        try {
            return new ResponseEntity<>(service.updateRealEstateById(id, realEstate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteRealEstateById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(service.deleteRealEstateById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(500, e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
