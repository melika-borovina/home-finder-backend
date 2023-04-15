package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.model.ContactUsMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContactUsService {

    private Map<Integer, ContactUsMessageDto> contactUsRequests = new HashMap<>();
    private int idCounter = 0;

    public ContactUsMessageDto getContactUsRequestById(Integer id) {
        return contactUsRequests.get(id);
    }

    public List<ContactUsMessageDto> getAllContactUsRequests() {
        return new ArrayList<>(contactUsRequests.values());
    }

    public void addContactUsRequest(ContactUsMessageDto contactUsRequestDto) {
        int contactUsId = generateId();
        contactUsRequestDto.setContactUsId(contactUsId);
        contactUsRequests.put(contactUsId, contactUsRequestDto);
    }

    private int generateId() {
        return ++idCounter;
    }
}
