package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.data.entity.CustomerSupportEntity;
import com.ssst.homefinderbackend.data.repository.CustomerSupportRepo;
import com.ssst.homefinderbackend.model.CustomerSupportRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerSupportService {

    @Autowired
    CustomerSupportRepo customerRepository;

    public CustomerSupportService(CustomerSupportRepo customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerSupportEntity validatePayloadAndReturnEntity(Integer messageId, CustomerSupportRequestDto message) throws Exception {
        Objects.requireNonNull(message.getMessage(), "Message is required");

        if (messageId != null) {
            CustomerSupportEntity customerEntity = getMessage(messageId);
            if (customerEntity == null) {
                throw new Exception(String.format("Could not find message with id '%s'", messageId));
            }

        }

        CustomerSupportEntity messageDb = new CustomerSupportEntity();

        if (messageId != null) {
            messageDb.setId(messageId);
        }

        messageDb.setMessage(message.getMessage());
        messageDb.setCustomerSupportMessageType(String.valueOf(message.getCustomerSupportMessageType()));
        messageDb.setEmailAddress(message.getEmailAddress());
        messageDb.setPhoneNumber(message.getPhoneNumber());
        messageDb.setFirstName(message.getFirstName());
        messageDb.setLastName(message.getLastName());

        return messageDb;
    }

    public List<CustomerSupportEntity> getMessageList() {
        return customerRepository.findAll();
    }

    public CustomerSupportEntity createMessage(CustomerSupportRequestDto message) throws Exception {

        CustomerSupportEntity messageDb = this.validatePayloadAndReturnEntity(null, message);

        CustomerSupportEntity createdMessage = customerRepository.save(messageDb);

        return createdMessage;

    }

    public CustomerSupportEntity getMessage(Integer messageId) throws Exception  {
        Optional<CustomerSupportEntity> result = customerRepository.findById(messageId);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new Exception(String.format("Could not find message with id %s", messageId));
        }
    }

    public Integer deleteMessage(Integer messageId)  {
        customerRepository.deleteById(messageId);
        return 1;
    }

}
