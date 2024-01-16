package com.woof.api;

import org.springframework.http.HttpStatus;

public class ErrorCode {

    //TODO: 에러코드 클래스에 합치기

    //카트 관련 에러코드
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "로그인 후 시도해주세요.");

}
