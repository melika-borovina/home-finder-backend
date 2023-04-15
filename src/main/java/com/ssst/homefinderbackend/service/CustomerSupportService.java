package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.model.CustomerSupportRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CustomerSupportService {

    private Map<Integer, CustomerSupportRequestDto> customerSupportRequests = new HashMap<>();
    private int idCounter = 0;

    public CustomerSupportRequestDto getCustomerSupportRequestById(Integer id) {
        return customerSupportRequests.get(id);
    }

    public List<CustomerSupportRequestDto> getAllCustomerSupportRequests() {
        return new ArrayList<>(customerSupportRequests.values());
    }

    public void addCustomerSupportRequest(CustomerSupportRequestDto customerSupportRequestDto) {
        int customerSupportId = generateId();
        customerSupportRequestDto.setCustomerSupportId(customerSupportId);
        customerSupportRequests.put(customerSupportId, customerSupportRequestDto);
    }

    private int generateId(){
        return idCounter++;
    }

}
