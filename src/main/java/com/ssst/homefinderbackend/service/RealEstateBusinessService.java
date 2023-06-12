package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.data.entity.FeatureEntity;
import com.ssst.homefinderbackend.data.entity.RealEstateEntity;
import com.ssst.homefinderbackend.data.service.FeatureService;
import com.ssst.homefinderbackend.data.service.RealEstateService;
import com.ssst.homefinderbackend.model.RealEstateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class RealEstateBusinessService {
    @Autowired
    RealEstateService service;

    @Autowired
    FeatureService featureService;

    public List<RealEstateEntity> getRealEstateList() {
        log.info("getRealEstateList() called");
        return service.getRealEstateList();
    }

    public RealEstateEntity getRealEstateById(Integer id) throws Exception  {
        log.info("getRealEstateById() called with id: {}", id);
        return service.getRealEstateById(id);
    }

    public RealEstateEntity validatePayloadAndReturnEntity(Integer realEstateId, RealEstateDto realEstate) throws Exception {
        Objects.requireNonNull(realEstate.getImgUrl(), "Image Url is required");
        if (realEstate.getImgUrl().isEmpty()){
            log.info("Image url is required!");
            throw new Exception("Image url is required!");
        }

        Objects.requireNonNull(realEstate.getImgAlt(), "Image Alt is required");
        if (realEstate.getImgAlt().isEmpty()){
            log.info("Image alt is required!");
            throw new Exception("Image alt is required!");
        }

        Objects.requireNonNull(realEstate.getBedrooms(), "Bedrooms is required");
        Objects.requireNonNull(realEstate.getBathrooms(), "Bathrooms is required");

        Objects.requireNonNull(realEstate.getPropertyType(), "Property type is required");
        if (realEstate.getPropertyType().isEmpty()){
            log.info("Property type is required!");
            throw new Exception("Property type is required!");
        }

        Objects.requireNonNull(realEstate.getPrice(), "Price is required");
        Objects.requireNonNull(realEstate.getSize(), "Size is required");

        Objects.requireNonNull(realEstate.getAddress(), "Address is required");
        if (realEstate.getAddress().isEmpty()){
            log.info("Address is required!");
            throw new Exception("Address is required!");
        }


        // validation
        // 1. update
        if (realEstateId != null) {
            RealEstateEntity existingEntity = service.getRealEstateById(realEstateId);
            if (existingEntity == null) {
                log.info("Real estate with id {} does not exist.", realEstateId);
                throw new Exception(String.format("Could not find RealEstate with id '%s'", realEstateId));
            }

            if(!Objects.equals(existingEntity.getImgUrl(), realEstate.getImgUrl())) {
                if (service.getRealEstateByImgUrl(realEstate.getImgUrl()) != null){
                    log.info("Real estate with url {} already exists. It is not possible to update it.", realEstate.getImgUrl());
                    throw new Exception(String.format("Real estate with url '%s' already exists. It is not possible to update it.", realEstate.getImgUrl()));
                }
            }
        } else { // 2. insert
            // in a case of insert (realEstateId is null) check if name already exists
            if (service.getRealEstateByImgUrl(realEstate.getImgUrl()) != null){
                log.info("Real estate with img url {} already exists", realEstate.getImgUrl());
                throw new Exception(String.format("Instance with img url '%s' already exists", realEstate.getImgUrl()));
            }
        }

        List<FeatureEntity> featureEntities = new ArrayList<>();
        if (realEstate.getFeatures() != null) {
            for (Integer featureId : realEstate.getFeatures()) {
                FeatureEntity featureEntity = featureService.getFeatureById(featureId);
                if (featureEntity == null) {
                    log.info("Feature with id {} does not exist.", featureId);
                    throw new Exception(String.format("Could not find Feature with id '%s'", featureId));
                }
                featureEntities.add(featureEntity);
            }
        }

        RealEstateEntity realEstateDb = new RealEstateEntity();
        // in case of insert realEstateId will be created on repository level
        if (realEstateId != null) {
            realEstateDb.setId(realEstateId);
        }

        realEstateDb.setImgUrl(realEstate.getImgUrl());
        realEstateDb.setImgAlt(realEstate.getImgAlt());
        realEstateDb.setBedrooms(realEstate.getBedrooms());
        realEstateDb.setBathrooms(realEstate.getBathrooms());
        realEstateDb.setPropertyType(realEstate.getPropertyType());
        realEstateDb.setPrice(realEstate.getPrice());
        realEstateDb.setSize(realEstate.getSize());
        realEstateDb.setAddress(realEstate.getAddress());
        realEstateDb.setFeatures(featureEntities);

        return realEstateDb;
    }

    public RealEstateEntity createRealEstate(RealEstateDto realEstate) throws Exception {
        log.info("createRealEstate() called with data {}: ", realEstate);

        RealEstateEntity realEstateDb = this.validatePayloadAndReturnEntity(null, realEstate);

        RealEstateEntity createdRealEstate = service.createOrUpdateRealEstate(realEstateDb);

        return service.getRealEstateById(createdRealEstate.getId());
    }

    public RealEstateEntity updateRealEstateById(Integer realEstateId, RealEstateDto realEstate) throws Exception {
        log.info("updateInstanceById() called with id: {}", realEstateId);

        RealEstateEntity realEstateDb = this.validatePayloadAndReturnEntity(realEstateId, realEstate);

        RealEstateEntity createdRealEstate = service.createOrUpdateRealEstate(realEstateDb);

        // go to db and get all objects
        return service.getRealEstateById(createdRealEstate.getId());
    }

    public Integer deleteRealEstateById(Integer id) throws Exception {
        log.info("deleteRealEstateById() called with id: {}", id);
        return service.deleteRealEstateById(id);
    }
}