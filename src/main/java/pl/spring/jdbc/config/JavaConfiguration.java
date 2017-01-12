package pl.spring.jdbc.config;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import pl.spring.jdbc.dao.CustomerDao;
import pl.spring.jdbc.dao.EmployeesDao;
import pl.spring.jdbc.daoImp.CustomerDaoImp;
import pl.spring.jdbc.daoImp.EmployeesDaoImp;
import pl.spring.jdbc.daoImp.OfficesDaoImp;

import javax.sql.DataSource;

@Configuration
@ComponentScan({"pl.spring.jdb.daoImp","pl.spring.jdbc.dao"})
@PropertySource("classpath:dataConfig.property")
public class JavaConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(environment.getProperty("ServerName"));
        dataSource.setDatabaseName(environment.getProperty("DatabaseName"));
        dataSource.setURL(environment.getProperty("Url"));
        dataSource.setUser(environment.getProperty("DatabaseUser"));
        dataSource.setPassword(environment.getProperty("DatabasePassword"));

        return dataSource;
    }

//    @Bean
//    public JdbcTemplate getJdbcTemplate(){
//        return new JdbcTemplate(dataSource());
//    }
//
//    @Bean
//    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(){
//        return new NamedParameterJdbcTemplate(dataSource());
//    }

    @Bean
    public CustomerDao customerDaoImp(){
        return new CustomerDaoImp();
    }

    @Bean
    public EmployeesDao employeesDaoImp(){
        return new EmployeesDaoImp();
    }

    @Bean
    public OfficesDaoImp officesDaoImp(){
        return new OfficesDaoImp();
    }

}
