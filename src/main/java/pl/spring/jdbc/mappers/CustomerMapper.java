package pl.spring.jdbc.mappers;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import pl.spring.jdbc.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public @Data class CustomerMapper implements RowMapper<Customer>{

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

        Customer customer = new Customer();
        customer.setCustomerNumer(rs.getInt("customerNumber"));
        customer.setCustomerName(rs.getString("customerName"));
        customer.setContactFirstName(rs.getString("contactFirstName"));
        customer.setContactLastName(rs.getString("contactLastName"));
        customer.setPhone(rs.getString("phone"));
        customer.setAddressLine1(rs.getString("addressLine1"));
        customer.setAddressLine2(rs.getString("addressLine2"));
        customer.setCity(rs.getString("city"));
        customer.setState(rs.getString("state"));
        customer.setPostalCode(rs.getString("postalCode"));
        customer.setCountry(rs.getString("country"));
        customer.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
        customer.setCreditLimit(rs.getBigDecimal("creditLimit"));
        return customer;
    }
}
