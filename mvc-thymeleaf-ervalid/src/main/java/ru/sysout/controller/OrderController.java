package ru.sysout.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sysout.dao.OrderRepository;
import ru.sysout.model.AnimalCart;
import ru.sysout.model.Order;

import java.util.List;

@AllArgsConstructor
@Controller
public class OrderController {
    private final OrderRepository orderRepository;
    private final AnimalCart productCart;

    @GetMapping("/order")
    public String get(Model model) {
        List<Order> orders=orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order";
    }

    @PostMapping("/order")
    public String order(String phone) {

        Order order=new Order();
        order.setPhone(phone);
        order.setAnimals(productCart.getAnimals());

        orderRepository.save(order);

        productCart.clear();

        return  "redirect:/order";
    }
}
