package com.seb_main_034.SERVER.advice;

import com.seb_main_034.SERVER.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
//@RestControllerAdvice
//public class ExceptionAdvice {
//
//    @ExceptionHandler
//    public ResponseEntity UserException(UserException e) {
//
//        int status = e.getCode().getStatus();
//        String message = e.getCode().getMessage();
//
//        List<ErrorResponse.Cause> causes =
//
//        HttpStatus resultStatus = HttpStatus.valueOf(status);
//
//        return new ResponseEntity(of, resultStatus);
//    }
//
//}
