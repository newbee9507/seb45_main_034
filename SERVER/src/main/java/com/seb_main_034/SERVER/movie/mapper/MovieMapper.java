package com.seb_main_034.SERVER.movie.mapper;

import com.seb_main_034.SERVER.movie.dto.MoviePostDto;
import com.seb_main_034.SERVER.movie.dto.MovieResponseDto;
import com.seb_main_034.SERVER.movie.entity.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie moviePostDtoToMovie(MoviePostDto moviePostDto);

    MovieResponseDto movieToMovieResponseDto(Movie movie);

}
