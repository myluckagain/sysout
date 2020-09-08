package ru.sysout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.sysout.dao.CustomerRepository;
import ru.sysout.model.EmployeeCustomer;
import ru.sysout.model.ExternalCustomer;
import ru.sysout.service.EmployeeCustomerDiscountCalculator;
import ru.sysout.service.ExternalCustomerDiscountCalculator;
import ru.sysout.service.MakeDiscountsService;

@DataJpaTest
@Import({MakeDiscountsService.class, EmployeeCustomerDiscountCalculator.class, ExternalCustomerDiscountCalculator.class})
public class CustomerCalculatorTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MakeDiscountsService makeDiscountsService;

    @BeforeEach
    private void init() {
        EmployeeCustomer employeeCustomer = new EmployeeCustomer();
        employeeCustomer.setMonthsInCompany(10);
        employeeCustomer.setName("Petr");
        customerRepository.save(employeeCustomer);

        ExternalCustomer externalCustomer = new ExternalCustomer();
        externalCustomer.setSum(110);
        externalCustomer.setName("Vasya");
        customerRepository.save(externalCustomer);
    }

    @Test
    public void test() {
        makeDiscountsService.makeDiscounts();
    }
}
