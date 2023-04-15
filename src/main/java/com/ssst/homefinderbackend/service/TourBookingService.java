package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.model.TourBookingDto;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourBookingService {

    public TourBookingDto createTourBooking(TourBookingDto tourBooking) {
        tourBooking.setId(91);
        tourBooking.setName("Samantha Jones");
        return tourBooking;
    }

    public List<TourBookingDto> getTourBookingList() {
        List<TourBookingDto> result = new ArrayList<>();
        TourBookingDto x = new TourBookingDto(42, "John Smith", new Date(), LocalTime.of(2, 22),"john@smith.com", 1);
        TourBookingDto y = new TourBookingDto(103, "Lindsay Lohan",  new Date(),LocalTime.of(11, 11),"lindsay@lohan.com", 3);
        result.add(x);
        result.add(y);

        return result;
    }

    public List<TourBookingDto> getTourBookingByRealEstateId(Integer realEstateId) {
        List<TourBookingDto> results = new ArrayList<>();
        TourBookingDto x = new TourBookingDto(42, "John Smith", new Date(), LocalTime.of(2, 22),"john@smith.com", 111);
        TourBookingDto y = new TourBookingDto(103, "Lindsay Lohan",  new Date(),LocalTime.of(11, 11),"lindsay@lohan.com", 111);
        results.add(x);
        results.add(y);
        return results.stream()
                .filter(result -> result.getRealEstateId() == realEstateId)
                .collect(Collectors.toList());
    }

    public TourBookingDto getTourBooking(Integer id) {
        return new TourBookingDto(id,"John Smith", new Date(),LocalTime.of(2, 22),"john@smith.com", 1 );
    }

    public TourBookingDto updateTourBooking(Integer id, TourBookingDto tourBooking) {
        System.out.println("Tour Booking found for given id: " + id);
        tourBooking.setId(id);
        tourBooking.setName("Sam Jones");
        return tourBooking;
    }

    public void deleteTourBooking(Integer id) {
        System.out.println("Deleted " + id);
    }
}
