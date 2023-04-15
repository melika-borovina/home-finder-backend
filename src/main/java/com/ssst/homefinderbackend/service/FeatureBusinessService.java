package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.data.entity.FeatureEntity;
import com.ssst.homefinderbackend.data.service.FeatureService;
import com.ssst.homefinderbackend.model.FeatureDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class FeatureBusinessService {
    @Autowired
    FeatureService service;

    public List<FeatureEntity> getFeatureList() {
        log.info("getFeatureList() called");
        return service.getFeatureList();
    }

    public FeatureEntity getFeatureById(Integer id) throws Exception  {
        log.info("getFeatureId() called with id: {}", id);
        return service.getFeatureById(id);
    }

    public FeatureEntity validatePayloadAndReturnEntity(Integer featureId, FeatureDto feature) throws Exception {
        Objects.requireNonNull(feature.getName(), "Feature Name is required");
        if (feature.getName().isEmpty()){
            log.info("Feature Name is required!");
            throw new Exception("Feature Name is required!");
        }

        Objects.requireNonNull(feature.getIcon(), "Icon is required");
        if (feature.getIcon().isEmpty()){
            log.info("Icon is required!");
            throw new Exception("Icon is required!");
        }

        // validation
        // 1. update
        if (featureId != null) {
            FeatureEntity featureEntity = service.getFeatureById(featureId);
            if (featureEntity == null) {
                log.info("Feature with id {} does not exist.", featureId);
                throw new Exception(String.format("Could not find Feature with id '%s'", featureId));
            }

            if(!Objects.equals(featureEntity.getName(), feature.getName())) {
                if (service.getFeatureByName(feature.getName()) != null){
                    log.info("Feature with name {} already exists. It is not possible to update it.", feature.getName());
                    throw new Exception(String.format("Feature with name '%s' already exists. It is not possible to update it.", feature.getName()));
                }
            }
            if(!Objects.equals(featureEntity.getIcon(), feature.getIcon())) {
                if (service.getFeatureByIcon(feature.getIcon()) != null){
                    log.info("Feature with Icon {} already exists. It is not possible to update it.", feature.getIcon());
                    throw new Exception(String.format("Feature with Icon '%s' already exists. It is not possible to update it.", feature.getIcon()));
                }
            }
        } else { // 2. insert
            // in a case of insert (featureId is null) check if name already exists
            if (service.getFeatureByName(feature.getName()) != null){
                log.info("Feature with name {} already exists.", feature.getName());
                throw new Exception(String.format("Feature with name '%s' already exists.", feature.getName()));
            }
            if (service.getFeatureByIcon(feature.getIcon()) != null){
                log.info("Feature with Icon {} already exists.", feature.getIcon());
                throw new Exception(String.format("Feature with Icon '%s' already exists.", feature.getIcon()));
            }
        }

        FeatureEntity featureDb = new FeatureEntity();
        // in case of insert featureId will be created on repository level
        if (featureId != null) {
            featureDb.setId(featureId);
        }

        featureDb.setName(feature.getName());
        featureDb.setIcon(feature.getIcon());

        return featureDb;
    }

    public FeatureEntity createFeature(FeatureDto feature) throws Exception {
        log.info("createFeature() called with data {}: ", feature);

        FeatureEntity featureDb = this.validatePayloadAndReturnEntity(null, feature);

        FeatureEntity createdFeature = service.createOrUpdateFeature(featureDb);

        return service.getFeatureById(createdFeature.getId());
    }

    public FeatureEntity updateFeatureById(Integer featureId, FeatureDto feature) throws Exception {
        log.info("updateFeatureById() called with id: {}", featureId);

        FeatureEntity featureDb = this.validatePayloadAndReturnEntity(featureId, feature);

        FeatureEntity createdFeature = service.createOrUpdateFeature(featureDb);

        // go to db and get all objects
        return service.getFeatureById(createdFeature.getId());
    }

    public Integer deleteFeatureById(Integer id) throws Exception {
        log.info("deleteFeatureById() called with id: {}", id);
        return service.deleteFeatureById(id);
    }

}
