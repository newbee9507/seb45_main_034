package com.seb_main_034.SERVER.movie.Controller;

import com.seb_main_034.SERVER.comment.dto.CommentResponseDto;
import com.seb_main_034.SERVER.comment.entity.Comment;
import com.seb_main_034.SERVER.comment.mapper.CommentMapper;
import com.seb_main_034.SERVER.movie.dto.MovieCommentResponseDto;
import com.seb_main_034.SERVER.movie.dto.MoviePostDto;
import com.seb_main_034.SERVER.movie.dto.MovieResponseDto;
import com.seb_main_034.SERVER.movie.entity.Movie;
import com.seb_main_034.SERVER.movie.mapper.MovieMapper;
import com.seb_main_034.SERVER.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@Validated
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final CommentMapper commentMapper;

    @PostMapping("/create")
    public ResponseEntity saveMovie(@Valid @RequestBody MoviePostDto postDto) {

        Movie movie = movieMapper.moviePostDtoToMovie(postDto);
        movieService.saveMovie(movie);

        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    //영화보기 임시로 작성했습니다.
    @GetMapping("/{movieId}")
    public ResponseEntity viewMovie(@PathVariable @Positive Long movieId) {

        Movie movie = movieService.getMovie(movieId);
        List<Comment> commentList = movie.getCommentList();
        List<CommentResponseDto> commentResponseDto = commentMapper.commentListToResponseListDto(commentList);
        MovieResponseDto movieResponseDto = movieMapper.movieToMovieResponseDto(movie);

        return new ResponseEntity<>(new MovieCommentResponseDto(movieResponseDto, commentResponseDto), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getMovies() {
        List<Movie> movieList = movieService.getMovieList();

        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }
}
