package com.seb_main_034.SERVER.movie.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MoviePatchDto {
    private long movieId;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private String description;

    private String vote;
}
