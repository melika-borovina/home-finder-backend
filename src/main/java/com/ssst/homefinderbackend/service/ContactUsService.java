package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.data.entity.ContactUsEntity;
import com.ssst.homefinderbackend.data.repository.ContactUsRepo;
import com.ssst.homefinderbackend.model.ContactUsMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContactUsService {

    @Autowired
    ContactUsRepo contactRepository;

    public ContactUsService(ContactUsRepo contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactUsEntity validatePayloadAndReturnEntity(Integer messageId, ContactUsMessageDto message) throws Exception {
        Objects.requireNonNull(message.getMessage(), "Message is required");

        if (messageId != null) {
            ContactUsEntity contactEntity = getMessage(messageId);
            if (contactEntity == null) {
                throw new Exception(String.format("Could not find message with id '%s'", messageId));
            }

        }

        ContactUsEntity messageDb = new ContactUsEntity();

        if (messageId != null) {
            messageDb.setId(messageId);
        }

        messageDb.setMessage(message.getMessage());
        messageDb.setContactUsMessageType(String.valueOf(message.getContactUsMessageType()));
        messageDb.setEmailAddress(message.getEmailAddress());
        messageDb.setPhoneNumber(message.getPhoneNumber());
        messageDb.setFirstName(message.getFirstName());
        messageDb.setLastName(message.getLastName());

        return messageDb;
    }

    public List<ContactUsEntity> getMessageList() {
        return contactRepository.findAll();
    }

    public ContactUsEntity createMessage(ContactUsMessageDto message) throws Exception {

        ContactUsEntity messageDb = this.validatePayloadAndReturnEntity(null, message);

        ContactUsEntity createdMessage = contactRepository.save(messageDb);

        return createdMessage;

    }

    public ContactUsEntity getMessage(Integer messageId) throws Exception  {
        Optional<ContactUsEntity> result = contactRepository.findById(messageId);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new Exception(String.format("Could not find message with id %s", messageId));
        }
    }

    public Integer deleteMessage(Integer messageId)  {
        contactRepository.deleteById(messageId);
        return 1;
    }

}
