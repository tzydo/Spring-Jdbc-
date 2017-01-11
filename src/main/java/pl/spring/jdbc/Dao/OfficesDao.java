package pl.spring.jdbc.Dao;

import pl.spring.jdbc.DatabaseTables.Offices;

import javax.sql.DataSource;
import java.util.List;

public interface OfficesDao {
    public void setParameterJdbcTemplate(DataSource dataSource);
    public Offices getOfficeByOfficeCode(int officeCode);
    public List<Offices> getOfficeListByCountry(String Country);
    public List<String> getOfficesCountry();
}
