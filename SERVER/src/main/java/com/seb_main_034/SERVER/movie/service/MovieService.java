package com.seb_main_034.SERVER.movie.service;

import com.seb_main_034.SERVER.movie.entity.Movie;
import com.seb_main_034.SERVER.movie.repository.MovieRepository;
import com.seb_main_034.SERVER.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final UserService userService;

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie getMovie(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(
                () -> new NoSuchElementException("없음"));
    }

    public List<Movie> getMovieList() {
        return movieRepository.findAll();
    }
}
