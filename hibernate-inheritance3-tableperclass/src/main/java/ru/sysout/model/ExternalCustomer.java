package ru.sysout.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class ExternalCustomer extends Customer {
   private long sum;
}
