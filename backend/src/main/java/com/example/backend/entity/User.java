package com.example.backend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

@Entity(name = "User")
@Table(name = "\"user\"")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_NAME", referencedColumnName = "userName")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_NAME", referencedColumnName = "roleName")
            }
    )
    private Set<Role> role;


//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "USER_MESSAGE",
//            joinColumns = {
//                    @JoinColumn(name = "USER_ID")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "MESSAGE_ID")
//            }
//    )

//    @JoinColumn(name = "list-comment")
//    private List<Message> messageList;
//
//    public List<Message> getMessageList() {
//        return messageList;
//    }
//
//    public void setMessageList(List<Message> messageList) {
//        this.messageList = messageList;
//    }
    //    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "USER_PRODUCT",
//            joinColumns = {
//                    @JoinColumn(name = "USER_ID")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "Product_ID")
//            }
//    )
//    private Product sold;
//
//    public Product getSold() {
//        return sold;
//    }

//    public void setSold(Product sold) {
//        this.sold = sold;
//    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}
