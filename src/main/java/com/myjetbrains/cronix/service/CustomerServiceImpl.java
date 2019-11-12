package com.myjetbrains.cronix.service;

import com.myjetbrains.cronix.api.v1.mapper.CustomerMapper;
import com.myjetbrains.cronix.api.v1.model.CustomerDTO;
import com.myjetbrains.cronix.domain.Customer;
import com.myjetbrains.cronix.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customersDto = customerRepository.findAll().stream()
                .map(customer -> customerMapper.customerToCustomerDTO(customer))
                .collect(Collectors.toList());

        return customersDto;
    }

    public CustomerDTO getCustomerById(Long id) {
        return
                customerRepository.findById(id)
                        .map(customer -> customerMapper.customerToCustomerDTO(customer))
                        .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.customerDtoToCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        customerDTO.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());
        return customerDTO;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }
}
