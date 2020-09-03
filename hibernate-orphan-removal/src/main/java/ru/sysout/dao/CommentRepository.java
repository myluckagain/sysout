package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
