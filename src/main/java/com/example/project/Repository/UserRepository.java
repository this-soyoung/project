package com.example.project.Repository;


import com.example.project.Entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;



    //    추가
    public User save(User user){
        entityManager.persist(user);
        return user;
    }


    //    회원 조회
    public Optional<User> findById(Long id){
        return Optional.ofNullable(entityManager.find(User.class, id));
    }
}
