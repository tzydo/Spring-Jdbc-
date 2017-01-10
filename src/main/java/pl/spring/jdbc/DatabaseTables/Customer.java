package pl.spring.jdbc.DatabaseTables;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class Customer {
    private int customerNumer;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private int salesRepEmployeeNumber;
    private Double creditLimit;

    public Customer(){};
}
