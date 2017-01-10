package pl.spring.jdbc.Dao;

import pl.spring.jdbc.DatabaseTables.Customer;

import java.util.List;

public interface CustomerDao {

    public Customer findByCustomerNumber(int number);
    public Customer findByCustomerName(String name);
    public List<Customer>FindByCity(String city);
    public List<Customer>FindByCountry(String country);
    public int customersAmount();
    public int customerAmountInCity(String city);
    public int amountCities();
    public int amountState();

}
