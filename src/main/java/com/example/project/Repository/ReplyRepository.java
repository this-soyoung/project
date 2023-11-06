package com.example.project.Repository;

import com.example.project.Entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ReplyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    //    댓글 추가
    public Reply save(Reply reply){
        entityManager.persist(reply);
        return reply;
    }

    //    댓글 조회
    public Optional<Reply> findById(Long id){
        return Optional.ofNullable(entityManager.find(Reply.class, id));
    }

    //    댓글 전체 조회
    public List<Reply> findAll(){
        String query = "select r from Reply r";
        return entityManager.createQuery(query, Reply.class).getResultList();
    }

    //    댓글 삭제
    public void delete(Reply reply) {
        entityManager.remove(reply);
    }

}
