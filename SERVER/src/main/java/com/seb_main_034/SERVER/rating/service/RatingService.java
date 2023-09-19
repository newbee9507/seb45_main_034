package com.seb_main_034.SERVER.rating.service;

import com.seb_main_034.SERVER.comment.entity.Comment;
import com.seb_main_034.SERVER.comment.service.CommentService;
import com.seb_main_034.SERVER.movie.entity.Movie;
import com.seb_main_034.SERVER.movie.service.MovieService;
import com.seb_main_034.SERVER.rating.dto.RatingDTO;
import com.seb_main_034.SERVER.rating.entity.Rating;
import com.seb_main_034.SERVER.rating.mapper.RatingMapper;
import com.seb_main_034.SERVER.rating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private RatingMapper ratingMapper;

    // 평점 추가
    public void addRating(RatingDTO ratingDTO, Long userId) {
        Rating rating = new Rating();
        rating.setUserId(userId);
        rating.setMovieId(ratingDTO.getMovieId());
        rating.setRating(ratingDTO.getRating());
        ratingRepository.save(rating);
        updateAverageRating(rating.getMovieId());
    }

    // 평점 삭제
    public void deleteRating(Long ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElse(null);
        if (rating != null) {
            Long movieId = rating.getMovieId();
            ratingRepository.deleteById(ratingId);
            updateAverageRating(movieId);
        }
    }

    // 평균 평점 업데이트
    private void updateAverageRating(Long movieId) {
        double newAverage = calculateAverageRating(movieId);
        Movie movie = movieService.findMovie(movieId);
        movie.setAverageRating(newAverage);

        Long userId = (movie.getUser() != null) ? movie.getUser().getUserId() : null;
        if (userId == null) {
            // 적절한 예외 처리
            throw new IllegalArgumentException("User is not associated with the movie.");
        }

        movieService.updateMovie(movie, userId);
    }

    // 평균 평점 계산
    private double calculateAverageRating(Long movieId) {
        List<Rating> ratings = ratingRepository.findByMovieId(movieId);
        if (ratings.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getRating();
        }
        return sum / ratings.size();
    }

    public List<Rating> sendRating(Long movieId) {
        return ratingRepository.findByMovieId(movieId);
    }


}
