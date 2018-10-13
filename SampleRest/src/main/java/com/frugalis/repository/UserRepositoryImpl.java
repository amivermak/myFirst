package com.frugalis.repository;

import com.frugalis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public class UserRepositoryImpl implements UserRepo {

//    @Autowired
//    @PersistenceContext
//    EntityManager em;

    @Autowired
    EntityManagerFactory factory;

    @Override
    public List<User> findByfirstname(String firstname) {
//        em.persist();
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
//        em.persist();
        em.getTransaction().commit();
        return null;

    }

    @Override
    public User findById(Long Id) {
        return null;
    }
}
