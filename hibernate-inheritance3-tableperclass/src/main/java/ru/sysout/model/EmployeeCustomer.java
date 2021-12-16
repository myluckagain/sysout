package ru.sysout.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class EmployeeCustomer extends Customer {
    private int monthsInCompany;
}
