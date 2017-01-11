package pl.spring.jdbc.DaoImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.spring.jdbc.Dao.OfficesDao;
import pl.spring.jdbc.DatabaseTables.Offices;
import pl.spring.jdbc.Mappers.OfficesMapper;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OfficesDaoImp implements OfficesDao{

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private String sql;

    @Override
    @Autowired
    public void setParameterJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Offices getOfficeByOfficeCode(int officeCode) {
        Map<String,Object>param = new HashMap<>();
        param.put("officeCode",officeCode);
        sql = "SELECT * From offices WHERE officeCode = :officeCode";
        return namedParameterJdbcTemplate.queryForObject(sql, param, new OfficesMapper());
    }

    @Override
    public List<Offices> getOfficeListByCountry(String country) {
        sql = "SELECT * From offices WHERE country = :country";
       Map<String,Object>param = new HashMap<>();
       param.put("country",country);

        return namedParameterJdbcTemplate.query(sql, param, new OfficesMapper());
    }

    @Override
    public List<String> getOfficesCountry() {
        sql = "SELECT DISTINCT(country) from offices";
        return jdbcTemplate.queryForList(sql,String.class);
    }

}
