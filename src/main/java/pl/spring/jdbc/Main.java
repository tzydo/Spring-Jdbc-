package pl.spring.jdbc;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.spring.jdbc.Config.JavaConfiguration;
import pl.spring.jdbc.DaoImp.CustomerDaoImp;
import pl.spring.jdbc.DaoImp.EmployeesDaoImp;
import pl.spring.jdbc.DaoImp.OfficesDaoImp;
import pl.spring.jdbc.DatabaseTables.Customer;
import pl.spring.jdbc.DatabaseTables.Employees;
import pl.spring.jdbc.DatabaseTables.Offices;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfiguration.class);
        CustomerDaoImp customerDaoImp = context.getBean("customerDaoImp",CustomerDaoImp.class);
        EmployeesDaoImp employeesDaoImp = context.getBean("employeesDaoImp",EmployeesDaoImp.class);
        OfficesDaoImp officesDaoImp = context.getBean("officesDaoImp", OfficesDaoImp.class);

//        Customers
        System.out.println("<---- Customers db ---->");
        System.out.println("Amount cities : " + customerDaoImp.amountCities());
        System.out.println("Amount states "+customerDaoImp.amountState());
        Customer customer = customerDaoImp.findByCustomerNumber(112);
        System.out.println("Customer for id 112 is : "+customer.getCustomerName());


//        Employee
        System.out.println("<---- Employee db ---->");
        System.out.println("Amount jobs : "+employeesDaoImp.getAmountJobsTitles());
        Employees employees = employeesDaoImp.getEmployeByEmployeNumber(1002);
        System.out.println("employee who has number 1002 is :" + employees.getFirstName() + " " + employees.getLastName());

        System.out.println("Job titles list : ");
        List<String> jobTitlesList = employeesDaoImp.getJobTitlesList();
        for (String jobs : jobTitlesList) {
            System.out.println(jobs);
        }


//        Offices
        Offices officeByOfficeCode = officesDaoImp.getOfficeByOfficeCode(5);
        System.out.println("Office with number 5 is in : "+ officeByOfficeCode.getCity());
    }
}
