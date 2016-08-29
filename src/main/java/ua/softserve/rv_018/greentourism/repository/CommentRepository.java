package ua.softserve.rv_018.greentourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.softserve.rv_018.greentourism.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
