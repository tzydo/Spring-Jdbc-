package pl.spring.jdbc.Dao;

import pl.spring.jdbc.DatabaseTables.Employees;

import javax.sql.DataSource;
import java.util.List;

public interface EmployeesDao {

    public Employees getEmployeByEmployeNumber(int employeNumber);
    public List<String> getJobTitlesList();
    public int getAmountJobsTitles();
    public int getNumberOfWorkersAtThePlace(String jobTitle);
    public void setParameterJdbcTemplate(DataSource dataSource);
}
