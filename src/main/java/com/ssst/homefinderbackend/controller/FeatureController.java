package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.data.entity.FeatureEntity;
import com.ssst.homefinderbackend.model.ErrorObject;
import com.ssst.homefinderbackend.model.FeatureDto;
import com.ssst.homefinderbackend.service.FeatureBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/feature")
@RestController
@Slf4j
public class FeatureController {
    @Autowired
    FeatureBusinessService featureBusinessService;

    @GetMapping("")
    ResponseEntity<List<FeatureEntity>> getFeatureList() {
        log.info("getFeatureList() called");
        return new ResponseEntity<>(featureBusinessService.getFeatureList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getFeatureById(@PathVariable Integer id) {
        log.info("getFeatureById() called");
        try {
            return new ResponseEntity<>(featureBusinessService.getFeatureById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(404, e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<Object> createFeature(@RequestBody FeatureDto feature) {
        log.info("createFeature() called");
        try {
            return new ResponseEntity<>(featureBusinessService.createFeature(feature), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> updateFeatureById(@PathVariable Integer id,
                                                @RequestBody FeatureDto feature) {
        log.info("updateFeatureById() called");
        try {
            return new ResponseEntity<>(featureBusinessService.updateFeatureById(id, feature), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteFeatureById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(featureBusinessService.deleteFeatureById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(500, e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

