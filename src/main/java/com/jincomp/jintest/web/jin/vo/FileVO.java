package com.jincomp.jintest.web.jin.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FileVO {

	private int fileId; // 파일번호
	private String goodsId; // 도서ID
	private String fileName; // 저장할 때 파일 이름
	private String oriName; // 오리지널 파일 이름(즉, 받아 올 때 파일 이름)
	private String fileUrl; // 저장 및 불러올 경로

}
