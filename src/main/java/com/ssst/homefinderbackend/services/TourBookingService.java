package com.ssst.homefinderbackend.services;

import com.ssst.homefinderbackend.models.TourBookingDto;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TourBookingService {

    public TourBookingDto createTourBooking(TourBookingDto tourBooking) {
        tourBooking.setId(91L);
        tourBooking.setName("Samantha Jones");
        return tourBooking;
    }

    public List<TourBookingDto> getTourBookingList() {
        List<TourBookingDto> result = new ArrayList<>();
        TourBookingDto x = new TourBookingDto(42L, "John Smith", new Date(), LocalTime.of(2, 22),"john@smith.com", 1);
        TourBookingDto y = new TourBookingDto(103L, "Lindsay Lohan",  new Date(),LocalTime.of(11, 11),"lindsay@lohan.com", 3);
        result.add(x);
        result.add(y);

        return result;
    }

    public TourBookingDto getTourBooking(long id) {
        return new TourBookingDto(id,"John Smith", new Date(),LocalTime.of(2, 22),"john@smith.com", 1 );
    }

    public TourBookingDto updateTourBooking(long id, TourBookingDto tourBooking) {
        System.out.println("Tour Booking found for a give id: " + id);
        tourBooking.setId(id);
        tourBooking.setName("Sam Jones");
        return tourBooking;
    }

    public void deleteTourBooking(long id) {
        System.out.println("Deleted " + id);
    }
}
