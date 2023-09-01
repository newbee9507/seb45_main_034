package com.seb_main_034.SERVER.response;

import com.seb_main_034.SERVER.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//public class ErrorResponse {
//
//    private List<Cause> causes;
//    private RuntimeException exception;
//
//    public ErrorResponse of(ExceptionCode code) {
//        Cause cause = new Cause(code.getStatus(), code.getMessage());
//        this.causes.add(cause);
//        return this;
//    }
//
//    @Getter
//    @AllArgsConstructor
//    public static class Cause {
//        private int status;
//        private String message;
//    }
//}
