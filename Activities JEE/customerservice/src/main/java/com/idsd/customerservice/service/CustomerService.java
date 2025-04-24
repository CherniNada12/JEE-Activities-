package com.idsd.customerservice.service;

import com.idsd.customerservice.dto.CustomerDTO;
import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO dto);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    void deleteCustomer(Long id);
}
