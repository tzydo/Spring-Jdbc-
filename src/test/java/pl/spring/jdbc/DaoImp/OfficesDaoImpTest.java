package pl.spring.jdbc.DaoImp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import pl.spring.jdbc.Dao.OfficesDao;
import pl.spring.jdbc.DatabaseTables.Offices;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;


public class OfficesDaoImpTest {
    private EmbeddedDatabase embeddedDatabase;
    private OfficesDao officesDaoImp;



    @Before
    public void setUp() throws Exception {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("sql/db/createOffices_db.sql")
                .addScript("sql/inserts/insertOffices_data.sql")
                .build();

        officesDaoImp = new OfficesDaoImp();
        officesDaoImp.setParameterJdbcTemplate(embeddedDatabase);
    }

    @After
    public void tearDown() throws Exception {
        embeddedDatabase.shutdown();
    }

    @Test
    public void getOfficeByOfficeCodeTest() throws Exception {
        Offices officeByOfficeCode = officesDaoImp.getOfficeByOfficeCode(1);
        Offices officeTest = new Offices(
                1, "San Francisco", "16502194782",
                "100 Market Street", "Suite 300",
                "CA", "USA", "94080", "NA");

        Assert.assertNotNull(officeByOfficeCode);
        Assert.assertEquals(officeByOfficeCode,(officeTest));
    }

    @Test
    public void getOfficeListByCountryTest() throws Exception {
        List<Offices> officeListByCountry = officesDaoImp.getOfficeListByCountry("USA");

        List<Offices>officeTestList = new ArrayList<>();
        Offices officeTest = new Offices(
                1, "San Francisco", "16502194782",
                "100 Market Street", "Suite 300",
                "CA", "USA", "94080", "NA");

        officeTestList.add(officeTest);

        Assert.assertNotNull(officeListByCountry);
        assertThat(officeListByCountry,hasItem(officeTest));
    }

    @Test
    public void getOfficesCountryTest() throws Exception {
        List<String> officesCountry = officesDaoImp.getOfficesCountry();

        List<String>listTest = new ArrayList<>();
        listTest.add("USA");
        listTest.add("France");
        listTest.add("Japan");
        listTest.add("UK");
        listTest.add("Australia");


        Assert.assertNotNull(officesCountry);
        assertThat(officesCountry,hasItem("USA"));
        Assert.assertEquals(officesCountry, listTest);
    }

}