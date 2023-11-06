package com.example.project;

import com.example.project.Entity.Heart;
import com.example.project.Entity.Post;
import com.example.project.Entity.Reply;
import com.example.project.Entity.User;
import com.example.project.Repository.HeartRepository;
import com.example.project.Repository.PostRepository;
import com.example.project.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MyPageTests {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private HeartRepository heartRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    //    좋아요 누르기
    @Test
    public void saveTest() {


//        좋아요 누르기

//        postId 가 2인 게시글을 가져와라
        Optional<Post> post = postRepository.findById(2L);
        Optional<User> user = userRepository.findById(1L);
//        final User user=User.builder().userNickname("쏘0").userPassword("0000").userPhone("01085532559").userEmail("msy2559@naver.com").build();
//        entityManager.persist(user);
//        Heart heart=Heart.builder().user(user).post(post.orElse(null)).build();
        Heart heart = Heart.builder().user(user.orElse(null)).post(post.orElse(null)).build();

        heartRepository.save(heart);

    }


//    좋아요 해제하기

    @Test
    public void deleteTest() {

//        Optional<Heart> heart=heartRepository.delete
        Long userId = 1L;
        List<Post> likedPosts = heartRepository.findPostsLikedByUser(userId);
        for (Post post : likedPosts) {
            // 좋아요(Heart) 엔티티를 찾음
            Heart heart = heartRepository.findHeartByUserAndPost(userRepository.findById(userId).orElse(null), post);
            if (heart != null) {
                // 좋아요(Heart) 엔티티를 삭제
                heartRepository.delete(heart);
                log.info("ID {}의 게시물 {}에 대한 좋아요 삭제 완료", userId, post.getId());
            }
        }
    }


//    내가 좋아요 누른 게시글 보기

    @Test
    public void findPostsLikedByUserTest() {
        Long userId = 1L;
        List<Post> likedPosts = heartRepository.findPostsLikedByUser(userId);

        if (likedPosts != null && !likedPosts.isEmpty()) {

            for (Post post : likedPosts) {
                log.info("좋아요한 게시물: {}", post);
            }

        } else {
            log.info("좋아요 한 게시물이 존재하지 않습니다.");
        }


    }

}
