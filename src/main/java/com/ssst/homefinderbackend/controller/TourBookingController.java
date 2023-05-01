package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.data.entity.TourBookingEntity;
import com.ssst.homefinderbackend.model.TourBookingDto;
import com.ssst.homefinderbackend.service.TourBookingService;
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
    public List<TourBookingDto> getTourBookings() {
        return tourBookingService.getTourBookings();
    }

    @GetMapping("/{id}")
    public TourBookingEntity getTourBooking(@PathVariable Integer id) {
        return tourBookingService.getTourBooking(id);
    }

    @PostMapping
    public TourBookingDto createTourBooking(@RequestBody TourBookingDto tourBooking) {
        return tourBookingService.createTourBooking(tourBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteTourBooking(@PathVariable Integer id) {
        tourBookingService.deleteTourBooking(id);
    }
}
