package com.idsd.customerservice.service.impl;
import com.idsd.customerservice.dto.CustomerDTO;
import com.idsd.customerservice.entity.Customer;
import com.idsd.customerservice.mapper.CustomerMapper;
import com.idsd.customerservice.repository.CustomerRepository;
import com.idsd.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    @Override
    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer customer = CustomerMapper.toEntity(dto);
        return CustomerMapper.toDto(repository.save(customer));
    }
    @Override
    public List<CustomerDTO> getAllCustomers() {
        return repository.findAll().stream()
                .map(CustomerMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public CustomerDTO getCustomerById(Long id) {
        return repository.findById(id)
                .map(CustomerMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }
    @Override
    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }
}
