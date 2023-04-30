package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.data.entity.RealEstateEntity;
import com.ssst.homefinderbackend.data.entity.TourBookingEntity;
import com.ssst.homefinderbackend.data.repository.TourBookingRepo;
import com.ssst.homefinderbackend.model.TourBookingDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourBookingService {

    @Autowired
    TourBookingRepo tourBookingRepo;

    public TourBookingDto createTourBooking(TourBookingDto tourBookingDto) {
        TourBookingEntity tourBookingEntity = new TourBookingEntity();

        tourBookingEntity.setUsername(tourBookingDto.getUsername());
        tourBookingEntity.setPreferredDate(tourBookingDto.getPreferredDate());
        tourBookingEntity.setContactInfo(tourBookingDto.getContactInfo());
        RealEstateEntity realEstateEntity = new RealEstateEntity();
        realEstateEntity.setId(tourBookingDto.getRealEstateId());
        tourBookingEntity.setRealEstateId(realEstateEntity);

        tourBookingEntity = tourBookingRepo.save(tourBookingEntity);
        tourBookingDto.setId(tourBookingEntity.getId());

        return tourBookingDto;
    }

    public TourBookingEntity getTourBooking(Integer id) {
        return tourBookingRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tour booking with id " + id + " not found"));
    }

    public List<TourBookingDto> getTourBookings() {
        List<TourBookingEntity> tourBookingEntities = tourBookingRepo.findAll();
        return tourBookingEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void deleteTourBooking(Integer id) {
        tourBookingRepo.deleteById(id);
    }

    private TourBookingDto convertToDto(TourBookingEntity tourBookingEntity) {
        TourBookingDto tourBookingDto = new TourBookingDto();
        tourBookingDto.setId(tourBookingEntity.getId());
        tourBookingDto.setUsername(tourBookingEntity.getUsername());
        tourBookingDto.setPreferredDate(tourBookingEntity.getPreferredDate());
        tourBookingDto.setContactInfo(tourBookingEntity.getContactInfo());
        tourBookingDto.setRealEstateId(tourBookingEntity.getRealEstateId().getId());

        return tourBookingDto;
    }
}
