package com.example.backend.dao;

import com.example.backend.entity.Category;
import com.example.backend.entity.Comment;
import com.example.backend.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends CrudRepository<Comment, Integer> {

    List<Comment> findAll(Pageable pageable);
}
