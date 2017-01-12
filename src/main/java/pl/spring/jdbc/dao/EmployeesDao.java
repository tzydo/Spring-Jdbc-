package pl.spring.jdbc.dao;

import pl.spring.jdbc.model.Employees;
import javax.sql.DataSource;
import java.util.List;

public interface EmployeesDao {
     void setParameterJdbcTemplate(DataSource dataSource);
     Employees getEmployeByEmployeNumber(int employeNumber);
     List<String> getJobTitlesList();
     int getAmountJobsTitles();
     int getNumberOfWorkersAtThePlace(String jobTitle);
}
