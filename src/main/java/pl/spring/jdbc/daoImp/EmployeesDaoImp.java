package pl.spring.jdbc.daoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.spring.jdbc.dao.EmployeesDao;
import pl.spring.jdbc.model.Employees;
import pl.spring.jdbc.mappers.EmployeesMapper;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeesDaoImp implements EmployeesDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Autowired
    public void setParameterJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Employees getEmployeByEmployeNumber(int employeNumber) {
        Map<String,Object> param = new HashMap<>();
        param.put("employeeNumber",employeNumber);
        String sql = "SELECT * From employees WHERE employeeNumber = :employeeNumber";
        return namedParameterJdbcTemplate.queryForObject(sql, param, new EmployeesMapper());
    }

    @Override
    public List<String> getJobTitlesList() {
        String sql = "SELECT DISTINCT jobTitle FROM employees";

        return jdbcTemplate.queryForList(sql,String.class);
    }

    @Override
    public int getAmountJobsTitles() {
        String sql = " SELECT COUNT(DISTINCT jobTitle) FROM employees";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public int getNumberOfWorkersAtThePlace(String jobTitle) {
        String sql = "SELECT COUNT(jobTitle) From employees WHERE jobTitle =?";
        return jdbcTemplate.queryForObject(sql,Integer.class,jobTitle);
    }
}

