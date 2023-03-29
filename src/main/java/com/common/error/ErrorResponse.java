package com.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
    private final HttpStatus status;
    private final String code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.name();
        this.message = errorCode.getMsg();
    }

    public static ResponseEntity<ErrorResponse> error(BtoneException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ErrorResponse.builder()
                        .status(e.getErrorCode().getStatus())
                        .code(e.getErrorCode().name())
                        .message(e.getErrorCode().getMsg())
                        .build());
    }
}
