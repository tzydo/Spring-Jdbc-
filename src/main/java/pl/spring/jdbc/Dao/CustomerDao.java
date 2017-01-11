package pl.spring.jdbc.Dao;

import pl.spring.jdbc.DatabaseTables.Customer;

import javax.sql.DataSource;
import java.util.List;

public interface CustomerDao {
    public void setParameterJdbcTemplate(DataSource dataSource);
    public Customer findByCustomerNumber(int number);
    public Customer findByCustomerName(String name);
    public List<Customer>findByCity(String city);
    public List<Customer>findByCountry(String country);
    public int customersAmount();
    public int customerAmountInCity(String city);
    public int amountCities();
    public int amountState();

}
