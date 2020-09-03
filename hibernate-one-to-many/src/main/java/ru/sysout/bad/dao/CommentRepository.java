package ru.sysout.bad.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.bad.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
