package ru.sysout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sysout.dao.CustomerRepository;
import ru.sysout.model.Customer;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MakeDiscountsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private List<DiscountCalculator> calculators;

    private Map<Class, DiscountCalculator> map = new HashMap<>();

    @PostConstruct
    private void init() {
        for (DiscountCalculator discountCalculator : calculators) {
            map.put(discountCalculator.getClazz(), discountCalculator);
        }
    }

    public void makeDiscounts() {
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            DiscountCalculator discountCalculator = map.get(customer.getClass());
            double discount = discountCalculator.calculate(customer);
            System.out.println(discount);
        }
    }
}

