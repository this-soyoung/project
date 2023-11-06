package com.example.project;

import com.example.project.Entity.Post;
import com.example.project.Entity.Reply;
import com.example.project.Entity.User;
import com.example.project.Repository.PostRepository;
import com.example.project.Repository.ReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class PostTests {


    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void saveTest() {
//        게시물, 댓글 작성

        final User user=User.builder().userNickname("쏘").userPassword("1234").userPhone("01085532559").userEmail("msy2559@naver.com").build();
        entityManager.persist(user);
        entityManager.flush();

        final Post post=Post.builder().postTitle("테스트123").postContent("테스트내용1").user(user).build();

        final Reply reply=Reply.builder().user(user).post(post).replyContent("댓글123").build();


        postRepository.save(post);
        replyRepository.save(reply);



    }

    @Test
    public void findByIdTest() {
//      게시물, 댓글 조회

        postRepository.findById(2L).ifPresent(post -> log.info(post.getPostContent()));
        postRepository.findById(2L).map(Post::toString).ifPresent(log::info);

        replyRepository.findById(3L).ifPresent(reply -> log.info(reply.getReplyContent()));

        replyRepository.findById(3L).ifPresent(reply -> {
            if (reply.getPost() != null && reply.getPost().getId() == 2L) {
                log.info("게시글 제목: " + reply.getPost().getPostTitle());
                log.info("댓글 내용: "+reply.getReplyContent());
            } else {
                log.info("게시글이 존재하지 않거나 id가 2인 게시글과 관련된 댓글이 없습니다.");
            }
        });


    }



}
