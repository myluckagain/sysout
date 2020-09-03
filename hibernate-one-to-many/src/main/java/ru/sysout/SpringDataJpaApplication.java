package ru.sysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.bad.dao.TopicRepository;
import ru.sysout.bad.model.Topic;

import java.util.Optional;

@SpringBootApplication
public class SpringDataJpaApplication implements CommandLineRunner {
    @Autowired
    private TopicRepository topicRepository;
    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

     // delComment();
    }
    @Transactional
    public void delComment(){
        Optional<Topic> optionalTopic = topicRepository.findById(-1l);
        optionalTopic.get().getComments().remove(0);
        topicRepository.save(optionalTopic.get());
    }
}
