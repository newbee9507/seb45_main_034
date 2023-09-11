package com.seb_main_034.SERVER.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class MoviePostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String description;
}
