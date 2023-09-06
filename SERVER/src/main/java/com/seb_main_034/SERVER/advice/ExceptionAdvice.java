package com.seb_main_034.SERVER.advice;

import com.seb_main_034.SERVER.exception.UserException;
import com.seb_main_034.SERVER.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity UserException(UserException e) {

        int status = e.getCode().getStatus();

        HttpStatus resultStatus = HttpStatus.valueOf(status);

        return new ResponseEntity<>(ErrorResponse.sendUserExResponse(e), resultStatus);
    }

}
