package com.seb_main_034.SERVER.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    BAD_REQUEST(400, "잘못된 요청입니다"),
    LOGIN_FAIL(401, "이메일 혹은 비밀번호가 잘못되었습니다."),
    UN_AUTHORITY(403,"접근 권한이 없습니다"),
    USER_NOT_FOUND(404, "회원을 찾을 수 없습니다"),
    USER_EXISTS(409, "이미 존재하는 유저입니다"),
    NICKNAME_EXISTS(409, "이미 존재하는 닉네임입니다"),
    NOT_IMPLEMENTATION(501, "개발중입니다. 빠른 시일내에 찾아뵙도록 노력하겠습니다");

    private final int status;

    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
