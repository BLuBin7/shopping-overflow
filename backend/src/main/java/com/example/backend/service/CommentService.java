package com.example.backend.service;

import com.example.backend.dao.CommentDao;
import com.example.backend.dao.ProductDao;
import com.example.backend.dao.UserDao;
import com.example.backend.entity.Comment;
import com.example.backend.entity.Product;
import com.example.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;

import java.awt.color.CMMException;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;
    public Comment addComment(Comment comment){
        return commentDao.save(comment);
    }
}
