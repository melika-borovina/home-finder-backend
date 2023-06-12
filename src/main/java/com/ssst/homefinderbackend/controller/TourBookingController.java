package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.data.entity.TourBookingEntity;
import com.ssst.homefinderbackend.model.ErrorObject;
import com.ssst.homefinderbackend.model.TourBookingDto;
import com.ssst.homefinderbackend.service.TourBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/tour")
@RestController
public class TourBookingController {
    private final TourBookingService tourBookingService;

    TourBookingController(TourBookingService tourBookingService) {
        this.tourBookingService = tourBookingService;
    }

    @GetMapping("/list")
    ResponseEntity<List<TourBookingEntity>> getTourBookings() {
        return new ResponseEntity<>(this.tourBookingService.getTourBookings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getTourBooking(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(this.tourBookingService.getTourBooking(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(404, e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<Object> createTourBooking(@RequestBody TourBookingDto tourBooking) {
        try {
            return new ResponseEntity<>(this.tourBookingService.createTourBooking(tourBooking), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteReview(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(this.tourBookingService.deleteTourBooking(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(500, e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
