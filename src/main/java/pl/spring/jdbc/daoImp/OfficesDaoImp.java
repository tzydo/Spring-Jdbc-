package pl.spring.jdbc.daoImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.spring.jdbc.dao.OfficesDao;
import pl.spring.jdbc.model.Offices;
import pl.spring.jdbc.mappers.OfficesMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OfficesDaoImp implements OfficesDao{

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setParameterJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Offices getOfficeByOfficeCode(int officeCode) {
        Map<String,Object>param = new HashMap<>();
        param.put("officeCode",officeCode);
        String sql = "SELECT * From offices WHERE officeCode = :officeCode";
        return namedParameterJdbcTemplate.queryForObject(sql, param, new OfficesMapper());
    }

    @Override
    public List<Offices> getOfficeListByCountry(String country) {
       String sql = "SELECT * From offices WHERE country = :country";
       Map<String,Object>param = new HashMap<>();
       param.put("country",country);

        return namedParameterJdbcTemplate.query(sql, param, new OfficesMapper());
    }

    @Override
    public List<String> getOfficesCountry() {
        String sql = "SELECT DISTINCT(country) from offices";
        return jdbcTemplate.queryForList(sql,String.class);
    }

}
