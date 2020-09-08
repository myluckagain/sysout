package ru.sysout.service;

import org.springframework.stereotype.Service;
import ru.sysout.model.EmployeeCustomer;

@Service
public class EmployeeCustomerDiscountCalculator implements DiscountCalculator<EmployeeCustomer> {
    @Override
    public double calculate(EmployeeCustomer customer) {
        if (customer.getMonthsInCompany() > 12)
            return 0.1;
        return 0.05;
    }

    public Class getClazz(){
        return EmployeeCustomer.class;
    }
}
