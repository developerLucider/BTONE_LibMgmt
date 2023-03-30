package com.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	
	// 성공
	SUCCESS(200, "OK"),

	// basic error
    NOT_SUPPORTED_HTTP_METHOD(500, "요청한 Http Method가 지원하지 않는 방식입니다."),
    NOT_VALID_METHOD_ARGUMENT(404,"Request Body나 Argument가 유효하지 않습니다."),
    
    // custom error
    USER_NOT_FOUND(501, "해당 사용자를 찾을 수 없습니다."),
    ITEM_NOT_FOUND(502, "해당 책을 찾을 수 없습니다.");

    private final int status;
    private final String msg;
}
