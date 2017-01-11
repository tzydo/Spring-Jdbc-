package pl.spring.jdbc.Config;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import pl.spring.jdbc.DaoImp.CustomerDaoImp;
import pl.spring.jdbc.DaoImp.EmployeesDaoImp;
import pl.spring.jdbc.DaoImp.OfficesDaoImp;

import javax.sql.DataSource;

@Configuration
@ComponentScan("pl.spring.jdb.daoImp")
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

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public CustomerDaoImp customerDaoImp(){
        return new CustomerDaoImp();
    }

    @Bean
    public EmployeesDaoImp employeesDaoImp(){
        return new EmployeesDaoImp();
    }

    @Bean
    public OfficesDaoImp officesDaoImp(){
        return new OfficesDaoImp();
    }

}
