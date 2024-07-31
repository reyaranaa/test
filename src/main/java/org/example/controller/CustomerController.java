package org.example.controller;

import org.example.exceptions.CustomerNotFoundException;
import org.example.model.CustomerDetails;
import org.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {


    @Autowired
    CustomerService customerService;

     // End point to save customer details
     @PostMapping("/saveCustomer")
     public ResponseEntity<String> addCustomer(@RequestBody CustomerDetails customer) {
         try {
             customerService.saveCustomer(customer);
             return new ResponseEntity<>("Customer saved successfully!", HttpStatus.CREATED);
         } catch (Exception e) {
             return new ResponseEntity<>("Failed to save customer: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

    // End point to return list of customer details with the given first name
    @GetMapping("/getCustomerByFirstName")
    public ResponseEntity<?> getCustomerByFirstName(@RequestParam String firstName) {
        try {
            List<CustomerDetails> customers = customerService.findByFirstName(firstName);
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
