package pl.spring.jdbc.Mappers;

import org.springframework.jdbc.core.RowMapper;
import pl.spring.jdbc.DatabaseTables.Offices;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfficesMapper implements RowMapper<Offices> {
    @Override
    public Offices mapRow(ResultSet rs, int rowNum) throws SQLException {
        Offices offices = new Offices();
        offices.setOfficeCode(rs.getInt("officeCode"));
        offices.setCity(rs.getString("city"));
        offices.setPhone(rs.getString("phone"));
        offices.setAddressLine1(rs.getString("addressLine1"));
        offices.setAddressLine2(rs.getString("addressLine2"));
        offices.setState(rs.getString("state"));
        offices.setCountry(rs.getString("country"));
        offices.setPostalCode(rs.getString("postalCode"));
        offices.setTerritory(rs.getString("territory"));

        return offices;
    }
}
