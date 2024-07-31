package org.example.service;

import org.example.model.CustomerDetails;
import org.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void saveCustomer(CustomerDetails customerDetails) {
        customerRepository.saveCustomer(customerDetails);
    }

    @Override
    public List<CustomerDetails> findByFirstName(String firstName){
        return customerRepository.findByFirstName(firstName);
    }
}
