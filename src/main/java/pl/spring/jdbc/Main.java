package pl.spring.jdbc;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.spring.jdbc.config.JavaConfiguration;
import pl.spring.jdbc.dao.CustomerDao;
import pl.spring.jdbc.daoImp.CustomerDaoImp;
import pl.spring.jdbc.daoImp.EmployeesDaoImp;
import pl.spring.jdbc.daoImp.OfficesDaoImp;
import pl.spring.jdbc.model.Customer;
import pl.spring.jdbc.model.Employees;
import pl.spring.jdbc.model.Offices;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfiguration.class);
        CustomerDao customerDao = context.getBean("customerDaoImp",CustomerDaoImp.class);
        EmployeesDaoImp employeesDaoImp = context.getBean("employeesDaoImp",EmployeesDaoImp.class);
        OfficesDaoImp officesDaoImp = context.getBean("officesDaoImp", OfficesDaoImp.class);

//        Customers
        System.out.println("<---- Customers db ---->");
        System.out.println("Amount cities : " + customerDao.amountCities());
        System.out.println("Amount states "+customerDao.amountState());
        Customer customer = customerDao.findByCustomerNumber(112);
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
