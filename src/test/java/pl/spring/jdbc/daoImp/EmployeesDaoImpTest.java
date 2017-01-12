package pl.spring.jdbc.daoImp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import pl.spring.jdbc.dao.EmployeesDao;
import pl.spring.jdbc.model.Employees;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


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

        assertNotNull(employees);
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


        assertNotNull(jobsList);
        assertThat(jobsList, hasItem("President"));
        assertEquals(listTest, jobsList);
    }

    @Test
    public void getAmountJobsTitlesTest() throws Exception {
        int amountJobsTitles = employeesDaoImp.getAmountJobsTitles();
        assertNotNull(amountJobsTitles);
        assertNotEquals(0, amountJobsTitles);
        assertEquals(7, amountJobsTitles);
    }

    @Test
    public void getNumberOfWorkersAtThePlaceTest() throws Exception {
        int numberOfWorkersAtThePlace = employeesDaoImp.getNumberOfWorkersAtThePlace("Sales Rep");
        assertNotNull(numberOfWorkersAtThePlace);
        assertNotEquals(0, numberOfWorkersAtThePlace);
        assertEquals(5, numberOfWorkersAtThePlace);
    }
}
