package pl.spring.jdbc.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.spring.jdbc.DatabaseTables.Customer;
import pl.spring.jdbc.Mappers.CustomerMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerDaoImp implements CustomerDao {

    private String sql;

//    @Autowired
    private JdbcTemplate jdbcTemplate;
//    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setParameterJdbcTemplate(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Customer findByCustomerNumber(int customerNumber) {

        Map<String,Object> param = new HashMap<>();
        param.put("customerNumber",customerNumber);

        sql  = "SELECT * FROM customers WHERE customerNumber = :customerNumber";

        Customer customer = namedParameterJdbcTemplate.queryForObject(sql,param, new CustomerMapper());
        return customer;
    }

    @Override
    public Customer findByCustomerName(String customerName) {

        Map<String,Object>param = new HashMap<>();
        param.put("customerName",customerName);

        sql = "SELECT * FROM customers WHERE customerName = :customerName";
        Customer customer = namedParameterJdbcTemplate.queryForObject(sql,param,new CustomerMapper());
        return customer;
    }

    @Override
    public List<Customer> FindByCity(String city) {
        Map<String,Object>param = new HashMap<>();
        param.put("city",city);

        sql = "SELECT * FROM customers WHERE city = :city";

        List<Customer> customersList = new ArrayList<>();
        customersList = namedParameterJdbcTemplate.query(sql,param,new CustomerMapper());

        return customersList;
    }

    @Override
    public List<Customer> FindByCountry(String country) {
        return null;
    }

    @Override
    public int customersAmount() {
        return 0;
    }

    @Override
    public int customerAmountInCity(String city) {
        return 0;
    }

    @Override
    public int amountCities() {
        return 0;
    }

    @Override
    public int amountState() {
        return 0;
    }
}
