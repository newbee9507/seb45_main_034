package com.seb_main_034.SERVER.comment.service;

import com.seb_main_034.SERVER.comment.entity.Comment;
import com.seb_main_034.SERVER.comment.repository.CommentRepository;
import com.seb_main_034.SERVER.exception.ExceptionCode;
import com.seb_main_034.SERVER.exception.GlobalException;
import com.seb_main_034.SERVER.movie.entity.Movie;
import com.seb_main_034.SERVER.movie.service.MovieService;
import com.seb_main_034.SERVER.users.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;
    private final MovieService movieService;

//    public Comment getComment(Long commentId) { // 필요한 기능인지는 의문. 하나의 질문만을 찾을 일이..?
//        return findById(commentId);
//    }
    public List<Comment> getAllComment(Long movieId) { // 여기 있어야 하는 메서드인가를 생각해 볼 필요가 있음.
        Movie movie = movieService.getMovie(movieId); // 영화 하나를 가져와서
        return movie.getCommentList();  // 해당 영화의 모든 댓글들을 가져옴
    }

    public Comment saveComment(Comment comment, Long movieId) {
        Movie findMovie = movieService.getMovie(movieId); // 영화 하나를 찾아와서
        comment.setMovie(findMovie); // 댓글 엔티티에 영화필드값을 넣어줌.

        Movie movie = comment.getMovie();
        Users user = comment.getUser();

        user.getCommentList().add(comment); // 유저의 댓글목록에 댓글을 추가
        movie.getCommentList().add(comment); // 영화의 댓글목록에 댓글을 추가

        return repository.save(comment);
    }

    public Comment update(Comment comment, Users user, Long commentId, Long movieId) {
        Comment wantUpdateComment = findById(commentId);

        if (verifyUser(wantUpdateComment, user, movieId)) { // 작성자의 id와 현재 로그인한 유저의 id가 불일치 && 찾은 댓글과 현재 페이지의 영화 ID가 다르면 예외발생
            wantUpdateComment.setText(comment.getText());
            return repository.save(wantUpdateComment);
        }

        throw new GlobalException(ExceptionCode.UN_AUTHORITY);
    }

    public void delete(Long commentId, Users user,Long movieId) { // 작성자의 id와 현재 로그인한 유저의 id가 불일치시 예외 발생
        Comment findComment = findById(commentId);

        log.info("findCOmment = {}", findComment.getText());

        if (!verifyUser(findComment, user, movieId)) {
            throw new GlobalException(ExceptionCode.UN_AUTHORITY); // 검증에 실패해야 예외를 반환해야함
        }
        repository.delete(findComment);
    }

    public Comment findById(Long commentId) {
        return repository.findById(commentId).orElseThrow(
                () -> new GlobalException(ExceptionCode.COMMENT_NOT_FOUND));
    }

    /**
     * 본인이 작성한 댓글만을 수정 및 삭제할 수 있음. 관리자는 검증없이 가능.
     * 현재 페이지의 영화ID와, 원래 댓글의 영화ID가 달라도 수정할 수 없음..? ( 페이지에 안 보이게 하는게 기본 )
     */
    private boolean verifyUser(Comment comment, Users user, Long movieId) {
        log.info("본인의 글인지와 영화ID가 일치하는지 검증");
        Long wantUpdateMovieId = comment.getMovie().getMovieId(); // movieId에 해당하는 영화의 ID
        Movie movie = movieService.getMovie(movieId);

        log.info("user.getId = {}, comment.getUserid = {}, wantUpdateMovieId = {}, movie.getId = {}",
                user.getUserId(), comment.getUser().getUserId(), wantUpdateMovieId, movie.getMovieId());

        if (user.getRoles().contains("ADMIN") && wantUpdateMovieId.equals(movie.getMovieId())) {
            return true;
        }

        Long writerId = comment.getUser().getUserId();
        Long requestId = user.getUserId();

        return writerId.equals(requestId) && wantUpdateMovieId.equals(movie.getMovieId());
    }

    public List<Comment> getAllComment() {
        return repository.findAll();
    }
}
