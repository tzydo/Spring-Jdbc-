package pl.spring.jdbc.DaoImp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import pl.spring.jdbc.Dao.EmployeesDao;
import pl.spring.jdbc.DatabaseTables.Employees;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class EmployeesDaoImpTest {

    private EmbeddedDatabase embeddedDatabase;
    private EmployeesDao employeesDaoImp;

    @Before
    public void setUp() throws Exception {
        //h2
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("sql/db/createEmployee_db.sql")
                .addScript("sql/inserts/insertEmployee_data.sql")
                .build();

        employeesDaoImp = new EmployeesDaoImp();
        employeesDaoImp.setParameterJdbcTemplate(embeddedDatabase);
    }

    @After
    public void tearDown() throws Exception {
        embeddedDatabase.shutdown();
    }

    @Test
    public void getEmployeByEmployeNumberTest() throws Exception {
        Employees employees = employeesDaoImp.getEmployeByEmployeNumber(1002);

        Assert.assertNotNull(employees);
        assertEquals("President", employees.getJobTitle());
    }

    @Test
    public void getJobTitlesListTest() throws Exception {
        List<String> jobsList = employeesDaoImp.getJobTitlesList();

        List<String> listTest = new ArrayList<>();
        listTest.add("President");
        listTest.add("Sales Manager (NA)");
        listTest.add("VP Sales");
        listTest.add("Sales Manager (APAC)");
        listTest.add("Sale Manager (EMEA)");
        listTest.add("Sales Rep");
        listTest.add("VP Marketing");


        Assert.assertNotNull(jobsList);
        Assert.assertThat(jobsList, hasItem("President"));
        assertEquals(listTest, jobsList);
    }

    @Test
    public void getAmountJobsTitlesTest() throws Exception {
        int amountJobsTitles = employeesDaoImp.getAmountJobsTitles();
        Assert.assertNotNull(amountJobsTitles);
        Assert.assertNotEquals(0, amountJobsTitles);
        Assert.assertEquals(7, amountJobsTitles);
    }

    @Test
    public void getNumberOfWorkersAtThePlaceTest() throws Exception {
        int numberOfWorkersAtThePlace = employeesDaoImp.getNumberOfWorkersAtThePlace("Sales Rep");
        Assert.assertNotNull(numberOfWorkersAtThePlace);
        assertNotEquals(0, numberOfWorkersAtThePlace);
        assertEquals(5, numberOfWorkersAtThePlace);
    }
}
