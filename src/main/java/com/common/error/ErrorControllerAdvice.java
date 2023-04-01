package com.common.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ErrorControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ErrorControllerAdvice.class);
	
	@ExceptionHandler(value = BtoneException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleCustomException(BtoneException e) {
	    // 로그로 후처리 테스트
		logger.error("BtoneException ----- {} = {}", e.getErrorCode().name(), e.getErrorCode().getMsg());
	    return ErrorResponse.error(e);
	}
	 
	 
	  
	//요 밑으로 쭉쭉 추가적인 ExceptionHandler들을 추가해서 처리합니다
	/*
	 * @ExceptionHandler(value = CustomException.class)
	 * 
	 * @ResponseStatus(value = HttpStatus.BAD_REQUEST) protected
	 * ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
	 * ErrorResponse response = ErrorResponse.of(e.getErrorCode());
	 * response.setDetail(e.getMessage()); return new ResponseEntity<>(response,
	 * HttpStatus.BAD_REQUEST); }
	 */
}
