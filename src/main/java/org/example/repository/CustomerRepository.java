package org.example.repository;

import org.example.exceptions.CustomerNotFoundException;
import org.example.model.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

     //DAO to save customer details into database
    public void saveCustomer(CustomerDetails customerDetails) {
        try {
            String sql = "INSERT INTO customers (first_name, last_name, date_of_birth) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, customerDetails.getFistName(), customerDetails.getLastName(), customerDetails.getDateOfBirth());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error saving customer: " + e.getMessage(), e);
        }

    }

     //DAO to get customer details by first Name from database
     public List<CustomerDetails> findByFirstName(String firstName) {
         try {
             String sql = "SELECT * FROM customers WHERE first_name = ?";
             List<CustomerDetails> customers = jdbcTemplate.query(sql, new Object[]{firstName}, new CustomerRowMapper());

             if (customers.isEmpty()) {
                 throw new CustomerNotFoundException("Customer with first name " + firstName + " not found");
             }

             return customers;
         } catch (DataAccessException e) {
             throw new RuntimeException("Error retrieving customer by first name: " + e.getMessage(), e);
         }
     }

    private static class CustomerRowMapper implements RowMapper<CustomerDetails> {
        @Override
        public CustomerDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            CustomerDetails customer = new CustomerDetails();

            customer.setFistName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setDateOfBirth(String.valueOf(rs.getDate("date_of_birth")));
            return customer;
        }
    }
}
