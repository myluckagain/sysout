package ru.sysout.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class EmployeeCustomer extends Customer {
    private int monthsInCompany;
}
