package com.aplikacja.herbaciarnia.service;

import com.aplikacja.herbaciarnia.exception.ResourceNotFoundException;
import com.aplikacja.herbaciarnia.model.Customer;
import com.aplikacja.herbaciarnia.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        Customer updatingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(("Customer not found with id: " + customerId)));
        updatingCustomer.setFirstName(updatedCustomer.getFirstName());
        updatingCustomer.setLastName(updatedCustomer.getLastName());

        return customerRepository.save(updatingCustomer);
    }
    public void deleteById(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
