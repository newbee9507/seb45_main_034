package com.seb_main_034.SERVER.movie.entity;

import com.seb_main_034.SERVER.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String description;

    @Column
    private Long vote = 0L;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE) // 영화가 1, 댓글이 n, 영화가 삭제되면 댓글도 삭제됨
    private List<Comment> commentList = new ArrayList<Comment>();
}
