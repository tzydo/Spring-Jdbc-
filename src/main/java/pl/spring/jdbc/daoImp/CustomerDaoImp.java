package pl.spring.jdbc.daoImp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.spring.jdbc.dao.CustomerDao;
import pl.spring.jdbc.model.Customer;
import pl.spring.jdbc.mappers.CustomerMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDaoImp implements CustomerDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setParameterJdbcTemplate(DataSource database) {
        this.jdbcTemplate = new JdbcTemplate(database);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(database);
    }

    @Override
    public Customer findByCustomerNumber(int customerNumber) {

        Map<String,Object> param = new HashMap<>();
        param.put("customerNumber",customerNumber);

        String sql  = "SELECT * FROM customers WHERE customerNumber = :customerNumber";

        Customer customer = namedParameterJdbcTemplate.queryForObject(sql,param, new CustomerMapper());
        return customer;
    }

    @Override
    public Customer findByCustomerName(String customerName) {

        Map<String,Object>param = new HashMap<>();
        param.put("customerName",customerName);

        String sql = "SELECT * FROM customers WHERE customerName = :customerName";
        Customer customer = namedParameterJdbcTemplate.queryForObject(sql,param,new CustomerMapper());
        return customer;
    }

    @Override
    public List<Customer> findByCountry(String country) {
        Map<String,Object>param = new HashMap<>();
        param.put("country",country);

        String sql = "SELECT * FROM customers WHERE country = :country";

        List<Customer> customersList = new ArrayList<>();
        customersList = namedParameterJdbcTemplate.query(sql,param,new CustomerMapper());

        return customersList;
    }

    @Override
    public List<Customer> findByCity(String city) {
        String sql = "SELECT * FROM customers WHERE city = ?";
        return jdbcTemplate.query(sql,new CustomerMapper(),city);
    }

    @Override
    public int customersAmount() {
        String sql = "SELECT COUNT(customerName) FROM customers";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public int customerAmountInCity(String city) {
        String sql = "SELECT COUNT(customerName)FROM customers WHERE city = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,city);
    }

    @Override
    public int amountCities() {
        String sql = "SELECT COUNT(DISTINCT(city)) FROM customers";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public int amountState() {
        String sql = "SELECT COUNT(DISTINCT(state)) FROM customers";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }
}
