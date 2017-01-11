package pl.spring.jdbc.DaoImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.spring.jdbc.Dao.EmployeesDao;
import pl.spring.jdbc.DatabaseTables.Employees;
import pl.spring.jdbc.Mappers.EmployeesMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeesDaoImp implements EmployeesDao {

    private String sql;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setParameterJdbcTemplate(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public Employees getEmployeByEmployeNumber(int employeNumber) {
        Map<String,Object> param = new HashMap<>();
        param.put("employeeNumber",employeNumber);
        sql = "SELECT * From employees WHERE employeeNumber = :employeeNumber";
        return namedParameterJdbcTemplate.queryForObject(sql, param, new EmployeesMapper());
    }

    @Override
    public List<String> getJobTitlesList() {
        List<String>jobTitlesList = new ArrayList<>();
        sql = "SELECT DISTINCT jobTitle FROM employees";

        return jdbcTemplate.queryForList(sql,String.class);
    }

    @Override
    public int getAmountJobsTitles() {
        sql = " SELECT COUNT(DISTINCT jobTitle) FROM employees";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public int getNumberOfWorkersAtThePlace(String jobTitle) {
        sql = "SELECT COUNT(jobTitle) From employees WHERE jobTitle =?";
        return jdbcTemplate.queryForObject(sql,Integer.class,jobTitle);
    }
}

