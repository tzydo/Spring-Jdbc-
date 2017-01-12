package pl.spring.jdbc.dao;

import org.springframework.stereotype.Component;
import pl.spring.jdbc.model.Customer;

import javax.sql.DataSource;
import java.util.List;

@Component
public interface CustomerDao {
     Customer findByCustomerNumber(int number);
     Customer findByCustomerName(String name);
     List<Customer>findByCity(String city);
     List<Customer>findByCountry(String country);
     int customersAmount();
     int customerAmountInCity(String city);
     int amountCities();
     int amountState();
     void setParameterJdbcTemplate(DataSource database);
}
