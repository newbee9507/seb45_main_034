package com.seb_main_034.SERVER.auth.handler;

import com.seb_main_034.SERVER.users.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    // 인증에 성공했을시.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Users principal = (Users) authentication.getPrincipal();
        String nickName = principal.getNickName(); // 인증에서 유저정보를 가져오고, 그 중 닉네임을 가져옴.
        if (nickName == null) {
            nickName = "nulltest";
        }
        response.setHeader("nickName", nickName); // null이면 헤더가 생기지 않음

        log.info("{}님, 환영합니다",nickName);

    }
}
