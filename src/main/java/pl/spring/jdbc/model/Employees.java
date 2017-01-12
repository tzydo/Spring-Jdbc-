package pl.spring.jdbc.model;

import lombok.Data;

public @Data class Employees {

    private int employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String officeCode;
    private int reportsTo;
    private String jobTitle;
}
