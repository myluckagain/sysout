package ru.sysout.good.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.good.model.GoodComment;

public interface GoodCommentRepository extends JpaRepository<GoodComment, Long> {

}
