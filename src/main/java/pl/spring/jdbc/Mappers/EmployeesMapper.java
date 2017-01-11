package pl.spring.jdbc.Mappers;

import org.springframework.jdbc.core.RowMapper;
import pl.spring.jdbc.DatabaseTables.Employees;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeesMapper implements RowMapper<Employees>{
    @Override
    public Employees mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employees employees = new Employees();
        employees.setEmployeeNumber(rs.getInt("employeeNumber"));
        employees.setLastName(rs.getString("lastName"));
        employees.setFirstName(rs.getString("firstName"));
        employees.setExtension(rs.getString("extension"));
        employees.setEmail(rs.getString("email"));
        employees.setOfficeCode(rs.getString("officeCode"));
        employees.setReportsTo(rs.getInt("reportsTo"));
        employees.setJobTitle(rs.getString("jobTitle"));
        return employees;
    }
}
