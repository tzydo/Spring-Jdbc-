package pl.spring.jdbc.Config;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Zyzio on 09.01.2017.
 */
@Configuration
@ComponentScan("pl.spring.jdb")
@PropertySource("classpath:dataConfig.property")
public class JavaConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(environment.getProperty("ServerName"));
        dataSource.setDatabaseName(environment.getProperty("DatabaseName"));
        dataSource.setUser(environment.getProperty("DatabaseUser"));
        dataSource.setPassword(environment.getProperty("DatabasePassword"));

        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
