package com.common.error;

import lombok.Getter;

@Getter
public class BtoneException extends RuntimeException{
	private ErrorCode errorCode;
	
	public BtoneException(ErrorCode errorCode) { 
    	this.errorCode = errorCode;
    }
	
}
