package ru.sysout.service;

import org.springframework.stereotype.Service;
import ru.sysout.model.ExternalCustomer;

@Service
public class ExternalCustomerDiscountCalculator implements DiscountCalculator<ExternalCustomer> {
    @Override
    public double calculate(ExternalCustomer customer) {
        if (customer.getSum() > 100)
            return 0.01;
        return 0.05;
    }
    public Class getClazz(){
        return ExternalCustomer.class;
    }
}
