package pl.spring.jdbc.DaoImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.spring.jdbc.Dao.CustomerDao;
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
    public List<Customer> findByCountry(String country) {
        Map<String,Object>param = new HashMap<>();
        param.put("country",country);

        sql = "SELECT * FROM customers WHERE country = :country";

        List<Customer> customersList = new ArrayList<>();
        customersList = namedParameterJdbcTemplate.query(sql,param,new CustomerMapper());

        return customersList;
    }

    @Override
    public List<Customer> findByCity(String city) {
       sql = "SELECT * FROM customers WHERE city = ?";
       return jdbcTemplate.query(sql,new CustomerMapper(),city);
    }

    @Override
    public int customersAmount() {
        sql = "SELECT COUNT(customerName) FROM customers";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public int customerAmountInCity(String city) {
        sql = "SELECT COUNT(customerName)FROM customers WHERE city = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,city);
    }

    @Override
    public int amountCities() {
        sql = "SELECT COUNT(DISTINCT(city)) FROM customers";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public int amountState() {
        sql = "SELECT COUNT(DISTINCT(state)) FROM customers";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }
}
