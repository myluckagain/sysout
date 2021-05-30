package ru.sysout.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.dao.CommentRepository;
import ru.sysout.dao.TopicRepository;
import ru.sysout.model.Comment;
import ru.sysout.model.Topic;

import java.util.Optional;

@Service
public class CommentService {
    private final TopicRepository topicRepository;
    private final CommentRepository commentRepository;

    public CommentService(TopicRepository topicRepository, CommentRepository commentRepository) {
        this.topicRepository = topicRepository;
        this.commentRepository = commentRepository;
    }

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
    public Comment addToTopicUsingGetById(long topicId, String text) {
        Topic topic = topicRepository.getById(topicId);
        Comment comment = new Comment();
        comment.setTopic(topic);
        comment.setText(text);
        comment = commentRepository.save(comment);
        return comment;
    }
}
