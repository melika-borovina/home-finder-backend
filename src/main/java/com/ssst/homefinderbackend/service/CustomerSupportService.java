package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.data.entity.CustomerSupportEntity;
import com.ssst.homefinderbackend.data.repository.CustomerSupportRepo;
import com.ssst.homefinderbackend.model.CustomerSupportRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CustomerSupportService {

    @Autowired
    CustomerSupportRepo customerSupportRepo;

    private Map<Integer, CustomerSupportRequestDto> customerSupportRequests = new HashMap<>();
    private int idCounter = 0;

    public CustomerSupportEntity getCustomerSupportRequestById(Integer id) {
        return customerSupportRepo.getReferenceById(id);
    }

    public List<CustomerSupportRequestDto> getAllCustomerSupportRequests() {
        return new ArrayList<>(customerSupportRequests.values());
    }

    public void addCustomerSupportRequest(CustomerSupportEntity customerSupportRequest) {
        customerSupportRepo.save(customerSupportRequest);

    }

    private int generateId(){
        return idCounter++;
    }

}
