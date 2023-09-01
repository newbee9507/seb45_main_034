package com.seb_main_034.SERVER.auth.utils;

import com.seb_main_034.SERVER.exception.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorResponder {

//    public static void sendErrorResponse(HttpServletResponse response, ExceptionCode code) throws IOException {
//        Gson gson = new Gson();
//        ErrorResponse errorResponse = ErrorResponse.of(code);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setStatus(code.setStatus());
//        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
//    }
}
