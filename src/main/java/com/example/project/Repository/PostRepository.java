package com.example.project.Repository;

import com.example.project.Entity.Heart;
import com.example.project.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    //    추가
//    public Post save(Post post){
//        entityManager.persist(post);
//        return post;
//    }
//
//    //    게시글 조회
//    public Optional<Post> findById(Long id){
//        return Optional.ofNullable(entityManager.find(Post.class, id));
//    }
//
//    //    게시글 전체 조회
//    public List<Post> findAll(){
//        String query = "select p from Post p";
//        return entityManager.createQuery(query, Post.class).getResultList();
//    }
//
//    //    게시글 삭제
//    public void delete(Post post) {
//        entityManager.remove(post);
//    }

}
