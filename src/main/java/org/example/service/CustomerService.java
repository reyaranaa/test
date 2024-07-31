package org.example.service;


import org.example.model.CustomerDetails;

import java.util.List;

public interface CustomerService {

     void saveCustomer(CustomerDetails customerDetails);
     List<CustomerDetails> findByFirstName(String firstName);
    }
