package com.seb_main_034.SERVER.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovieCommentResponseDto<T> {

    private T movieResponseDto;
    private T commentList;
}
