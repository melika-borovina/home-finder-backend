package com.ssst.homefinderbackend.services;

import com.ssst.homefinderbackend.data.entity.CustomerSupportEntity;
import com.ssst.homefinderbackend.data.repository.CustomerSupportRepo;
import com.ssst.homefinderbackend.model.CustomerSupportRequestDto;
import com.ssst.homefinderbackend.service.CustomerSupportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerSupportServiceTest {

    @Mock
    private CustomerSupportRepo customerSupportRepo;

    private CustomerSupportService customerSupportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerSupportService = new CustomerSupportService();
        customerSupportService.customerSupportRepo = customerSupportRepo;
    }

    @Test
    void testGetCustomerSupportRequestById() {
        // Arrange
        Integer id = 1;
        CustomerSupportEntity expectedEntity = new CustomerSupportEntity();
        when(customerSupportRepo.getReferenceById(id)).thenReturn(expectedEntity);

        // Act
        CustomerSupportEntity result = customerSupportService.getCustomerSupportRequestById(id);

        // Assert
        assertEquals(expectedEntity, result);
        verify(customerSupportRepo, times(1)).getReferenceById(id);
    }

    @Test
    void testGetAllCustomerSupportRequests() {
        // Arrange
        CustomerSupportRequestDto dto1 = new CustomerSupportRequestDto();
        CustomerSupportRequestDto dto2 = new CustomerSupportRequestDto();
        customerSupportService.customerSupportRequests.put(1, dto1);
        customerSupportService.customerSupportRequests.put(2, dto2);

        // Act
        List<CustomerSupportRequestDto> result = customerSupportService.getAllCustomerSupportRequests();

        // Assert
        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
    }

    @Test
    void testAddCustomerSupportRequest() {
        // Arrange
        CustomerSupportEntity customerSupportRequest = new CustomerSupportEntity();

        // Act
        customerSupportService.addCustomerSupportRequest(customerSupportRequest);

        // Assert
        verify(customerSupportRepo, times(1)).save(customerSupportRequest);
    }

    @Test
    void testGenerateId_ReturnsIncrementalValues() {
        // Arrange
        int firstId = customerSupportService.generateId();
        int secondId = customerSupportService.generateId();

        // Assert
        assertNotEquals(firstId, secondId);
    }

    @Test
    void testGenerateId_ReturnsUniqueIds() {
        // Arrange
        int id1 = customerSupportService.generateId();
        int id2 = customerSupportService.generateId();
        int id3 = customerSupportService.generateId();

        // Assert
        assertNotEquals(id1, id2);
        assertNotEquals(id1, id3);
        assertNotEquals(id2, id3);
    }

}
