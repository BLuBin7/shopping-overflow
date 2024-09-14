package com.example.backend.controller;

import com.example.backend.dao.CommentDao;
import com.example.backend.dao.ProductDao;
import com.example.backend.dao.UserDao;
import com.example.backend.entity.Comment;
import com.example.backend.entity.Product;
import com.example.backend.entity.User;
import com.example.backend.service.CommentService;
import com.example.backend.service.JwtService;
import com.example.backend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;
    @Resource(name = "redisTemplate")
    private HashOperations<String,String,Comment> hashOperationsComment;


    @PostMapping(value="/addComment/{productId}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Comment addComment(@RequestPart("comment") String commentJson,
                              @PathVariable("productId") Integer productId
    , HttpServletRequest request){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Comment comment = objectMapper.readValue(commentJson, Comment.class);

            Product product = productDao.findById(productId).get();
            List<Comment> listComment = new ArrayList<>();
            listComment.add(comment);
            product.getUserComment().add(comment);;


            String jwtToken = request.getHeader("Authorization");
            if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
                jwtToken = jwtToken.substring(7);
            }
            String username = jwtUtil.getUsernameFromToken(jwtToken);

            jwtService.loadUserByUsername(username);

            hashOperationsComment.put("Comment", username, comment);

            User authenticatedUser = userDao.findById(username).orElse(null);
            if (authenticatedUser != null) {
                comment.setUser_comment(authenticatedUser);
            }

            return commentService.addComment(comment);
        } catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
    }
    }
//    @GetMapping("/allcomment/{productId}")
//    public List<Comment> all(@PathVariable("productId") Integer productId) {
//        Product product = productDao.findById(productId).get();
//        List<Comment> commentList = product.getUserComment();
//
//        return commentList;
//    }
}
