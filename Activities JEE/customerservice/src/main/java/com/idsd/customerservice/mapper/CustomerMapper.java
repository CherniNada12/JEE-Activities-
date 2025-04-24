package com.idsd.customerservice.mapper;

import com.idsd.customerservice.dto.CustomerDTO;
import com.idsd.customerservice.entity.Customer;

public class CustomerMapper {
    public static CustomerDTO toDto(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
    }

    public static Customer toEntity(CustomerDTO dto) {
        return Customer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }
}
