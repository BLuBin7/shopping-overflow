package com.example.backend.login.dao;

import com.example.backend.login.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Binh
 * Date : 7/11/2023 - 10:22 PM
 * Description :
 */
@Repository
public class UserDaoimpl implements UserDAO {
    private EntityManager entityManager;

    @Autowired
    public UserDaoimpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findbyId(Integer id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("FROM User ", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void Update(User user,String value) {
        entityManager.createQuery("UPDATE User SET _username = :value ")
                .setParameter("value", value)
                .executeUpdate();
    }

    @Override
    public List<User> findByUserName(String username) {
        TypedQuery<User> query = entityManager.createQuery("FROM User where _username =:value",User.class).setParameter("value",username);
        return query.getResultList();
    }
}
