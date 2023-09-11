package com.seb_main_034.SERVER.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

@Getter
@Setter
@AllArgsConstructor
public class MovieVotePatchDto {

    private long movieId;

    private long vote;
}
