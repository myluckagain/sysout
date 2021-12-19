package ru.sysout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.dao.UserRepository;
import ru.sysout.model.User;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    @Transactional
    public void addUser(String name, String phone) {
        User user = new User(name);
        if (phone != null)
            user.setPhone(phone);
        userRepository.save(user);
    }


}
