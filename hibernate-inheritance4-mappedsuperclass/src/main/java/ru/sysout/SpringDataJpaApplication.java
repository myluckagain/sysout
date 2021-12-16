package ru.sysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sysout.dao.CustomerRepository;
import ru.sysout.model.EmployeeCustomer;
import ru.sysout.model.ExternalCustomer;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringDataJpaApplication {
    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @PostConstruct
    void init() {
        EmployeeCustomer employeeCustomer = new EmployeeCustomer();
        employeeCustomer.setMonthsInCompany(10);
        employeeCustomer.setName("Petr");
        customerRepository.save(employeeCustomer);

        ExternalCustomer externalCustomer = new ExternalCustomer();
        externalCustomer.setSum(110);
        externalCustomer.setName("Vasya");
        customerRepository.save(externalCustomer);

    }
}
