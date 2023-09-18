package com.seb_main_034.SERVER.rating.controller;

import com.seb_main_034.SERVER.movie.entity.Movie;
import com.seb_main_034.SERVER.movie.service.MovieService;
import com.seb_main_034.SERVER.rating.dto.RatingDTO;
import com.seb_main_034.SERVER.rating.entity.Rating;
import com.seb_main_034.SERVER.rating.service.RatingService;
import com.seb_main_034.SERVER.users.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.DoubleStream;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @Autowired
    private MovieService movieService;

    @PostMapping
    public void saveRating(@RequestBody RatingDTO ratingDTO,
                           @AuthenticationPrincipal Users user) {
        Long userId = user.getUserId();
        ratingService.saveRating(ratingDTO, userId);
        setMovieAverageRating(ratingDTO.getMovieId()); // 순서
    }

    private void setMovieAverageRating(Long movieId) { // 이 메서드가 불려오기 전, 방금 등록한 별점 역시 포함되어야 함.

        Movie movie = movieService.findMovie(movieId); // 영화를 찾아와서

        List<Rating> ratings = ratingService.sendRating(movieId); // 해당 영화에 있는 모든 별점들을 가져옴.
        DoubleStream tmp = ratings.stream().mapToDouble(Rating::getRating); // 별점객체들을 실제 별점점수로 바꿔서
        movie.setAverageRating(tmp.average().orElse(0)); // 평균을 내고 만약 별점이 null이라면 0을 반환함
    }
}
