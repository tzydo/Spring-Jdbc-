package pl.spring.jdbc.dao;

import pl.spring.jdbc.model.Offices;
import javax.sql.DataSource;
import java.util.List;

public interface OfficesDao {
     void setParameterJdbcTemplate(DataSource dataSource);
     Offices getOfficeByOfficeCode(int officeCode);
     List<Offices> getOfficeListByCountry(String Country);
     List<String> getOfficesCountry();
}
