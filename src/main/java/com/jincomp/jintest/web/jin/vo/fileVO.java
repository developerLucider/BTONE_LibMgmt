package com.jincomp.jintest.web.jin.vo;

import lombok.Data;

@Data
public class fileVO {
	 private String uploadFileName; //내가 업로드한 파일명
	 private String storeFileName; //서버 내부에서 관리하는 파일명
    
}
