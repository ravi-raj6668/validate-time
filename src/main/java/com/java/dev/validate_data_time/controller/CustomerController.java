package com.java.dev.validate_data_time.controller;

import com.java.dev.validate_data_time.entity.CustomerData;
import com.java.dev.validate_data_time.entity.CustomersData;
import com.java.dev.validate_data_time.servcie.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/data")
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping(value = "/getData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerData>> getCustomerData(HttpServletRequest httpServletRequest) {
        LOG.info("Inside get customer data() => {} ", httpServletRequest.getRemoteAddr());
        List<CustomerData> customerDataList = customerService.getCustomerData();
        return new ResponseEntity<>(customerDataList, HttpStatus.OK);
    }

    @GetMapping(value = "/getCustomerData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomersData>> getCustomerData1(HttpServletRequest httpServletRequest) {
        LOG.info("Inside get customer data() => {} ", httpServletRequest.getRemoteAddr());
        List<CustomersData> customerDataList = customerService.getCustomerData1();
        return new ResponseEntity<>(customerDataList, HttpStatus.OK);
    }
}
