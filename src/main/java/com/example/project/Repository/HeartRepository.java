package com.example.project.Repository;

import com.example.project.Entity.Heart;
import com.example.project.Entity.Post;
import com.example.project.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HeartRepository{

    @PersistenceContext
    private EntityManager entityManager;

    //    좋아요 누르기
    public Heart save(Heart heart){
         entityManager.persist(heart);
         return heart;
}

    //    좋아요 해제
    public void delete(Heart heart) {
        entityManager.remove(heart);
    }

    // 특정 사용자와 게시물에 대한 좋아요 엔티티 찾기
    public Heart findHeartByUserAndPost(User user, Post post) {
        String query = "SELECT h FROM Heart h WHERE h.user = :user AND h.post = :post";
        return entityManager.createQuery(query, Heart.class)
                .setParameter("user", user)
                .setParameter("post", post)
                .getSingleResult();
    }

    //    좋아요 한 게시물 조회
    public List<Post> findPostsLikedByUser(Long userId) {
        String query = "SELECT h.post FROM Heart h WHERE h.user.id = :userId";
        return entityManager.createQuery(query, Post.class)
                .setParameter("userId", userId)
                .getResultList();

    }



}
