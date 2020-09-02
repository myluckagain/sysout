package ru.sysout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.dao.CommentRepository;
import ru.sysout.dao.TopicRepository;
import ru.sysout.model.Comment;
import ru.sysout.model.Topic;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public Comment addToTopicUsingFindById(long topicId, String text) {
        Optional<Topic> topic = topicRepository.findById(topicId);
        Comment comment = new Comment();
        comment.setTopic(topic.get());
        comment.setText(text);
        comment = commentRepository.save(comment);
        return comment;
    }

    @Transactional
    public Comment addToTopicUsingGetOne(long topicId, String text) {
        Topic topic = topicRepository.getOne(topicId);
        Comment comment = new Comment();
        comment.setTopic(topic);
        comment.setText(text);
        comment = commentRepository.save(comment);
        return comment;
    }
}
