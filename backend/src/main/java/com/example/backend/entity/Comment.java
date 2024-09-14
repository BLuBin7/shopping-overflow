package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity(name = "Comment")
@Table(name = "\"comment\"")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer comment_Id;
    private int id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
    private User user_comment;
    private String text;

//    public Integer getComment_Id() {
//        return comment_Id;
//    }
//
//    public void setComment_Id(Integer comment_Id) {
//        this.comment_Id = comment_Id;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(User user_comment) {
        this.user_comment = user_comment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
