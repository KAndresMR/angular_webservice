package com.jakarta.dao;

import com.jakarta.models.User;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    
    public void save(User user) {
        em.persist(user);
    }

    public User findByCedula(String cedula) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.cedula = :cedula", User.class);
        query.setParameter("cedula", cedula);
        List<User> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public void update(User user) {
        em.merge(user);
    }

    public void delete(String cedula) {
        User user = findByCedula(cedula);
        if (user != null) {
            em.remove(user);
        }
    }

    public List<User> findAll() {
        return em.createQuery("SELECT u FROM usuarios u", User.class).getResultList();
    }
}
