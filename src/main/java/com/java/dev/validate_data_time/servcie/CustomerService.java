package com.java.dev.validate_data_time.servcie;

import com.java.dev.validate_data_time.entity.CustomerData;
import com.java.dev.validate_data_time.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerData> getCustomerData() {
        LOG.info("inside get customer data() fetch from db");
        return customerRepository.getData();
    }
}
