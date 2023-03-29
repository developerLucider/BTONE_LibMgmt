package com.common.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	// 성공
	SUCCESS(HttpStatus.OK, "OK"),

	// 400~500 error
    NOT_SUPPORTED_HTTP_METHOD(HttpStatus.BAD_REQUEST,"요청한 Http Method가 지원하지 않는 방식입니다."),
    NOT_VALID_METHOD_ARGUMENT(HttpStatus.NOT_FOUND,"Request Body나 Argument가 유효하지 않습니다.");
    
    
    // TODO: custom 만들어보자
    //USER_NOT_FOUND(HttpStatus.valueOf("userNotFound"), "해당 사용자를 찾을 수 없습니다."),
    //ITEM_NOT_FOUND(HttpStatus.valueOf("bookNotFound"), "해당 책을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String msg;
}