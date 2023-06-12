package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.data.entity.TourBookingEntity;
import com.ssst.homefinderbackend.data.repository.TourBookingRepo;
import com.ssst.homefinderbackend.model.TourBookingDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TourBookingService {

    private final TourBookingRepo repository;

    public TourBookingService(TourBookingRepo tourBookingRepository) {
        this.repository = tourBookingRepository;
    }

    public TourBookingEntity validatePayloadAndReturnEntity(Integer tourBookingId, TourBookingDto tourBooking) throws Exception {
        Objects.requireNonNull(tourBooking.getEmail(), "Email is required");

        if (tourBookingId != null) {
            TourBookingEntity tourBookingEntity = getTourBooking(tourBookingId);
            if (tourBookingEntity == null) {
                throw new Exception(String.format("Could not find booking with id '%s'", tourBookingId));
            }
        }

        TourBookingEntity tourBookingDb = new TourBookingEntity();
        if (tourBookingId != null) {
            tourBookingDb.setId(tourBookingId);
        }

        tourBookingDb.setEmail(tourBooking.getEmail());
        tourBookingDb.setPreferredDate(tourBooking.getPreferredDate());
        tourBookingDb.setRealEstateId(tourBooking.getRealEstateId());

        return tourBookingDb;
    }

    public TourBookingEntity createTourBooking(TourBookingDto tourBooking) throws Exception {
        TourBookingEntity tourBookingDb = this.validatePayloadAndReturnEntity(null, tourBooking);
        TourBookingEntity createdTourBooking = repository.save(tourBookingDb);
        return createdTourBooking;
    }

    public List<TourBookingEntity> getTourBookings() {
        return repository.findAll();
    }

    public TourBookingEntity getTourBooking(Integer tourBookingId) throws Exception  {
        Optional<TourBookingEntity> result = repository.findById(tourBookingId);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new Exception(String.format("Could not find booking with id %s", tourBookingId));
        }
    }

    public Integer deleteTourBooking(Integer tourBookingId) {
        repository.deleteById(tourBookingId);
        return 1;
    }

}
