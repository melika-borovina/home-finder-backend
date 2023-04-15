package com.ssst.homefinderbackend.data.service;

import com.ssst.homefinderbackend.data.entity.FeatureEntity;
import com.ssst.homefinderbackend.data.repository.FeatureRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class FeatureService {
    @Autowired
    FeatureRepo repository;

    public FeatureEntity createOrUpdateFeature(FeatureEntity feature) {
        return repository.save(feature);
    }

    public List<FeatureEntity> getFeatureList() {
        log.info("getFeature() called");
        return repository.findAll();
    }


    public FeatureEntity getFeatureById(Integer featureId) throws Exception {
        log.info("getFeatureById called with featureId " + featureId);
        Optional<FeatureEntity> result = repository.findById(featureId);
        if (result.isPresent()) {
            return result.get();
        } else {
            log.error(String.format("Could not find feature with id %s", featureId));
            throw new Exception(String.format("Could not find feature with id %s", featureId));
        }
    }
    public FeatureEntity getFeatureByName(String name) throws Exception {
        log.info("getFeatureByName called with name {}", name);

        try {
            return repository.findOneByName(name);
        } catch (Exception e) {
            log.error("findOneByName {} failed.", name, e);
            throw new Exception(String.format("getFeatureByName(\"%s\") failed. Error: %s", name, e.getLocalizedMessage()));
        }
    }
    public FeatureEntity getFeatureByIcon(String icon) throws Exception {
        log.info("getFeatureByIcon called with icon {}", icon);

        try {
            return repository.findOneByIcon(icon);
        } catch (Exception e) {
            log.error("findOneByIcon {} failed.", icon, e);
            throw new Exception(String.format("getFeatureByIcon(\"%s\") failed. Error: %s", icon, e.getLocalizedMessage()));
        }
    }
    public Integer deleteFeatureById(Integer featureId) throws Exception {
        // check whether id exists or not. If not, throw an exception
        this.getFeatureById(featureId);

        // delete the account type
        repository.deleteById(featureId);
        return 1;
    }
}
