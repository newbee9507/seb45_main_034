package com.seb_main_034.SERVER.movie.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieResponseDto {
    private long movieId;

    private String title;

    private String content;

    private String description;

    private Long vote;
}
