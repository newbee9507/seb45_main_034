package com.seb_main_034.SERVER.rating.service;

import com.seb_main_034.SERVER.movie.entity.Movie;
import com.seb_main_034.SERVER.movie.service.MovieService;
import com.seb_main_034.SERVER.rating.dto.RatingDTO;
import com.seb_main_034.SERVER.rating.entity.Rating;
import com.seb_main_034.SERVER.rating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;


    public Rating saveRating(RatingDTO ratingDTO, Long userId) {
        Rating rating = new Rating();
        rating.setUserId(userId);
        rating.setMovieId(ratingDTO.getMovieId());
        rating.setRating(ratingDTO.getRating());
        return ratingRepository.save(rating);
    }

    public List<Rating> sendRating(Long movieId) {
        return ratingRepository.findByMovieId(movieId);
    }


}
