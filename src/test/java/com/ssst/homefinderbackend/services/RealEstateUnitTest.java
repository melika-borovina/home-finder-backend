package com.ssst.homefinderbackend.services;

import com.ssst.homefinderbackend.data.entity.RealEstateEntity;
import com.ssst.homefinderbackend.data.repository.RealEstateRepo;
import com.ssst.homefinderbackend.data.service.RealEstateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.jupiter.api.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RealEstateUnitTest {

    @Mock
    private RealEstateRepo repository;

    @InjectMocks
    private RealEstateService service;

    @Test
    @Order(10)
    public void testCreateOrUpdateRealEstate() {
        RealEstateEntity realEstate = new RealEstateEntity();
        realEstate.setId(1);
        realEstate.setImgUrl("image_url");

        when(repository.save(any(RealEstateEntity.class))).thenReturn(realEstate);

        RealEstateEntity result = service.createOrUpdateRealEstate(realEstate);

        verify(repository).save(realEstate);

        assertEquals(realEstate, result);
    }

    @Test
    @Order(20)
    public void testGetRealEstateList() {
        List<RealEstateEntity> realEstateList = new ArrayList<>();

        when(repository.findAll()).thenReturn(realEstateList);

        List<RealEstateEntity> result = service.getRealEstateList();

        verify(repository).findAll();

        assertEquals(realEstateList, result);
    }

    @Test
    @Order(30)
    public void testGetRealEstateById() throws Exception {
        RealEstateEntity realEstate = new RealEstateEntity();
        realEstate.setId(1);

        when(repository.findById(1)).thenReturn(Optional.of(realEstate));

        RealEstateEntity result = service.getRealEstateById(1);

        verify(repository).findById(1);

        assertEquals(realEstate, result);
    }

    @Test(expected = Exception.class)
    @Order(40)
    public void testGetRealEstateById_ThrowsException() throws Exception {
        when(repository.findById(1)).thenReturn(Optional.empty());

        service.getRealEstateById(1);

        verify(repository).findById(1);
    }

    @Test
    @Order(50)
    public void testGetRealEstateByImgUrl() throws Exception {
        RealEstateEntity realEstate = new RealEstateEntity();
        realEstate.setId(1);
        realEstate.setImgUrl("image_url");

        when(repository.findOneByImgUrl("image_url")).thenReturn(realEstate);

        RealEstateEntity result = service.getRealEstateByImgUrl("image_url");

        verify(repository).findOneByImgUrl("image_url");

        assertEquals(realEstate, result);
    }

    @Test(expected = Exception.class)
    @Order(60)
    public void testGetRealEstateByImgUrl_ThrowsException() throws Exception {
        when(repository.findOneByImgUrl("image_url")).thenThrow(new Exception("Error"));

        service.getRealEstateByImgUrl("image_url");

        verify(repository).findOneByImgUrl("image_url");
    }

    @Test
    @Order(70)
    public void testDeleteRealEstateById() throws Exception {
        RealEstateEntity realEstate = new RealEstateEntity();
        realEstate.setId(1);

        when(repository.findById(1)).thenReturn(Optional.of(realEstate));

        Integer result = service.deleteRealEstateById(1);

        verify(repository).findById(1);
        verify(repository).deleteById(1);

        assertEquals(Integer.valueOf(1), result);
    }

    @Test(expected = Exception.class)
    @Order(80)
    public void testDeleteRealEstateById_ThrowsException() throws Exception {
        when(repository.findById(1)).thenReturn(Optional.empty());

        service.deleteRealEstateById(1);

        verify(repository).findById(1);
    }

}