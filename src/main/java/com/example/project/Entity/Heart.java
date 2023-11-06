package com.example.project.Entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_HEART")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Heart {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @NotNull
    private User user;

    @ManyToOne @NotNull
    private Post post;

    @CreationTimestamp
    private LocalDateTime likeDate;


    @Builder
    public Heart(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}
