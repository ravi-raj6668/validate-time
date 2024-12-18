package com.java.dev.validate_data_time.repository;

import com.java.dev.validate_data_time.entity.CustomerData;
import com.java.dev.validate_data_time.entity.CustomersData;
import com.java.dev.validate_data_time.helper.ResultSetIterable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository {
    public static final Logger LOG = LoggerFactory.getLogger(CustomerRepository.class);
    private final DataSource dataSource;

    @Autowired
    public CustomerRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<CustomerData> getData() {
        LOG.info("Fetch data from db");
        List<CustomerData> customerDataList = new ArrayList<>();
        String query = "select id, first_name, last_name, email, gender, scheduletime from customer_data";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            addDataToList(rs, customerDataList);

        } catch (Exception e) {
            throw new RuntimeException("Error while fetching data from databases", e);
        }

//        return filterDataByFourhours(customerDataList);
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime fourHoursAgo = currentTime.minusHours(4);
        return customerDataList.stream().filter(data -> {
            LocalDateTime scheduleTime = data.getScheduletime().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            return scheduleTime.isAfter(fourHoursAgo);
        }).collect(Collectors.toList());
    }

//    private List<CustomerData> filterDataByFourhours(List<CustomerData> customerDataList) {
//        List<CustomerData> filteredCustomerData = new ArrayList<>();
//        long currentTime = System.currentTimeMillis();
//        long fourHourBeforeTime = 4 * 60 * 60 * 1000;
//
//        for(CustomerData data : customerDataList){
//            if(data.getScheduletime() != null){
//                long scheduleTimeData = data.getScheduletime().getTime();
//                if(currentTime - scheduleTimeData <= fourHourBeforeTime){
//                    filteredCustomerData.add(data);
//                }
//            }
//        }
//        return filteredCustomerData;
//    }

    private void addDataToList(ResultSet rs, List<CustomerData> customerDataList) {

        for (ResultSet row : new ResultSetIterable(rs)) {
//            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            try {
                CustomerData data = new CustomerData();
                data.setId(row.getInt("id"));
                data.setFirst_name(row.getString("first_name"));
                data.setLast_name(row.getString("last_name"));
                data.setEmail(row.getString("email"));
                data.setGender(row.getString("gender"));
                data.setScheduletime(row.getTimestamp("scheduletime"));
                customerDataList.add(data);
            } catch (Exception e) {
                throw new RuntimeException("Error processing result set row data", e);
            }
        }
    }


    //TODO : new method for testing only
    public List<CustomersData> getData1() {
        LOG.info("Fetch data from db");
        List<CustomersData> customerDataList = new ArrayList<>();
        String query = "select id, first_name, last_name, email, gender, scheduletime from customer_data";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            addDataToList1(rs, customerDataList);

        } catch (Exception e) {
            throw new RuntimeException("Error while fetching data from databases", e);
        }

     return customerDataList;


    }

    private void addDataToList1(ResultSet rs, List<CustomersData> customerDataList) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fourHoursAgo = LocalDateTime.now().minusHours(4);
        String userId = "Talie";
        for (ResultSet row : new ResultSetIterable(rs)) {
            try {
                String scheduleTime = row.getString("scheduletime");
                LocalDateTime requiredTime = LocalDateTime.parse(scheduleTime, dateTimeFormatter);
                if(isSpecialUser(userId)) {
                    if (requiredTime.isAfter(fourHoursAgo)) {
                        CustomersData data = new CustomersData();
                        data.setId(row.getInt("id"));
                        data.setFirst_name(row.getString("first_name"));
                        data.setLast_name(row.getString("last_name"));
                        data.setEmail(row.getString("email"));
                        data.setGender(row.getString("gender"));
                        data.setScheduletime(row.getString("scheduletime"));
                        customerDataList.add(data);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Error processing result set row data", e);
            }
        }
    }

    private boolean isSpecialUser(String userId) {
        // Example: Fetch special users from a list or database
        List<String> specialUsers = List.of("Andreana", "Morissa", "Tallie"); // Example special users
        return specialUsers.contains(userId);
    }

}
