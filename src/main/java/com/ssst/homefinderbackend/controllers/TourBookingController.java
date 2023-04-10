package com.ssst.homefinderbackend.controllers;

import com.ssst.homefinderbackend.models.TourBookingDto;
import com.ssst.homefinderbackend.services.TourBookingService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/book-tour")
@RestController
public class TourBookingController {
    private final TourBookingService tourBookingService;

    TourBookingController(TourBookingService tourBookingService) {
        this.tourBookingService = tourBookingService;
    }

    @GetMapping("/list")
    public List<TourBookingDto> getTourBookings() {
        return tourBookingService.getTourBookingList();
    }

    @GetMapping("/list/real-estate/{realEstateId}")
    public List<TourBookingDto> getTourBookingByRealEstateId(@PathVariable long realEstateId) {
        return tourBookingService.getTourBookingByRealEstateId(realEstateId);
    }

    @GetMapping("/{id}")
    public TourBookingDto getTourBooking(@PathVariable long id) {
        return tourBookingService.getTourBooking(id);
    }

    @PostMapping
    public TourBookingDto createTourBooking(@RequestBody TourBookingDto tourBooking) {
        return tourBookingService.createTourBooking(tourBooking);
    }

    @PutMapping("/{id}")
    public TourBookingDto updateTourBooking(@PathVariable long id, @RequestBody TourBookingDto tourBooking) {
        return tourBookingService.updateTourBooking(id, tourBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteTourBooking(@PathVariable long id) {
        tourBookingService.deleteTourBooking(id);
    }
}
