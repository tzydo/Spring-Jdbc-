package pl.spring.jdbc.Dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import pl.spring.jdbc.DatabaseTables.Customer;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;


public class CustomerDaoTest {

    private EmbeddedDatabase database;
    private CustomerDaoImp customerDaoImp;


    @Before
    public void setUp() throws Exception {
        //h2
        database = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("sql/create_db.sql")
                .addScript("sql/insert_data.sql")
                .build();

        customerDaoImp = new CustomerDaoImp();
        customerDaoImp.setParameterJdbcTemplate(database);
    }


    @Test
    public void findByCustomerNumberTEst() throws Exception {
        Customer customer = customerDaoImp.findByCustomerNumber(1);

        Assert.assertNotNull(customer);
        Assert.assertEquals(1,customer.getCustomerNumer());
        Assert.assertEquals("John Ferguson",customer.getCustomerName());
        Assert.assertEquals("San Francisko",customer.getCity());
    }

    @Test
    public void findByCustomerNameTest() throws Exception {
        Customer customer = customerDaoImp.findByCustomerName("Atelier graphique");

        Assert.assertNotNull(customer);
        Assert.assertNotEquals(customer.getCustomerNumer(),106); //105
        Assert.assertEquals(customer.getCountry(),"France");
    }

    @Test
    public void findByCountry() throws Exception {
        List<Customer> customer = customerDaoImp.findByCountry("France");

       Assert.assertNotNull(customer);
       assertThat(customer, hasItems(new Customer(
               105, "Atelier graphique", "Schmitt",
               "Carine", "40.32.2555", "54", "rue Royale",
               null, "Nantes", "44000", "France",
               1370,new BigDecimal(21000)))); //homcrest test

        Assert.assertEquals(3,customer.size());

    }

    @Test
    public void findByCity() throws Exception {

    }

    @Test
    public void customersAmount() throws Exception {

    }

    @Test
    public void customerAmountInCity() throws Exception {

    }

    @Test
    public void amountCities() throws Exception {

    }

    @Test
    public void amountState() throws Exception {

    }


    @After
    public void teatDown(){
        database.shutdown();
    }

}