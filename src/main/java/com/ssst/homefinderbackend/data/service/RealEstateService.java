package com.ssst.homefinderbackend.data.service;

import com.ssst.homefinderbackend.data.entity.RealEstateEntity;
import com.ssst.homefinderbackend.data.repository.RealEstateRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class RealEstateService {
    @Autowired
    RealEstateRepo repository;

    public RealEstateEntity createOrUpdateRealEstate(RealEstateEntity realEstate) {
        return repository.save(realEstate);
    }

    public List<RealEstateEntity> getRealEstateList() {
        log.info("getCurrency() called");
        return repository.findAll();
    }


    public RealEstateEntity getRealEstateById(Integer realEstateId) throws Exception {
        log.info("getRealEstateById called with currencyId " + realEstateId);
        Optional<RealEstateEntity> result = repository.findById(realEstateId);
        if (result.isPresent()) {
            return result.get();
        } else {
            log.error(String.format("Could not find real estate with id %s", realEstateId));
            throw new Exception(String.format("Could not find real estate with id %s", realEstateId));
        }
    }
    public RealEstateEntity getRealEstateByImgUrl(String imgUrl) throws Exception {
        log.info("getRealEstateByImgUrl called with url {}", imgUrl);

        try {
            return repository.findOneByImgUrl(imgUrl);
        } catch (Exception e) {
            log.error("findOneByImgUrl {} failed.", imgUrl, e);
            throw new Exception(String.format("getRealEstateByImgUrl(\"%s\") failed. Error: %s", imgUrl, e.getLocalizedMessage()));
        }
    }
    public Integer deleteRealEstateById(Integer realEstateId) throws Exception  {
        // check whether id exists or not. If not, throw an exception
        this.getRealEstateById(realEstateId);

        // delete the account type
        repository.deleteById(realEstateId);
        return 1;
    }
}