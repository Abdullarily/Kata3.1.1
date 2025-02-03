package com.example.demo.service;

import com.example.demo.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Transactional
public class UserService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(User user) {
        em.persist(user);
    }

    @Transactional()
    public List<User> allUsers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    public void editUser(User user) {
        em.merge(user);
    }

    public void deleteUser(int id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }
}
