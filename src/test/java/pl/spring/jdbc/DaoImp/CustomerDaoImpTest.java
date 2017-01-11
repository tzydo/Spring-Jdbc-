package pl.spring.jdbc.DaoImp;

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


public class CustomerDaoImpTest {

    private EmbeddedDatabase database;
    private CustomerDaoImp customerDaoImp;


    @Before
    public void setUp() throws Exception {
        //h2
        database = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("sql/db/createCustomer_db.sql")
                .addScript("sql/inserts/insertCustomer_data.sql")
                .build();

        customerDaoImp = new CustomerDaoImp();
        customerDaoImp.setParameterJdbcTemplate(database);
    }


    @Test
    public void findByCustomerNumberTest() throws Exception {
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
    public void findByCountryTest() throws Exception {
        List<Customer> customerList = customerDaoImp.findByCountry("France");

        Customer customerTest = new Customer(105,
            "Atelier graphique", "Schmitt",
            "Carine", "40.32.2555", "54",
            "rue Royale",null, "Nantes",
            "44000", "France",1370,
            new BigDecimal(21000.00).setScale(2)); //homcrest test

        Assert.assertNotNull(customerList);
        Customer customer = customerList.get(0);
        Assert.assertEquals(customerTest,customer);
        assertThat(customerList, hasItems(customerTest));
        Assert.assertEquals(3,customerList.size());

    }

    @Test
    public void findByCityTest() throws Exception {
        List<Customer>customerList = customerDaoImp.findByCity("San Francisko");

        Assert.assertNotNull(customerList);
        assertThat(customerList,hasItems(new Customer(
                1, "John Ferguson",
                "Anderson", "Dominick",
                "485246982", "Address1",
                "Address2", "San Francisko",
                "CA",	"94218", "USA",
                1286,new BigDecimal(
                        2000.00).setScale(2))));
        assertThat(customerList.size(), is(1));
    }

    @Test
    public void customersAmountTest() throws Exception {
        int amount = customerDaoImp.customersAmount();
        Assert.assertEquals(8,amount);
    }

    @Test
    public void customerAmountInCityTest() throws Exception {
        int customerAmountInCity = customerDaoImp.customerAmountInCity("Las Vegas");
        Assert.assertEquals(2,customerAmountInCity);
    }

    @Test
    public void amountCitiesTest() throws Exception {
        int amountCities = customerDaoImp.amountCities();
        Assert.assertEquals(3,amountCities);
    }

    @Test
    public void amountStateTest() throws Exception {
        int amountState = customerDaoImp.amountState();
        Assert.assertEquals(5,amountState);
    }


    @After
    public void teatDown(){
        database.shutdown();
    }

}