package com.jincomp.jintest.web.jin.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MainBookListDTO {
	
	private String goodsId;
	private String goodsName;
	private String goodsPrice;
	private String goodsDiscountPrice;
	private LocalDate eventStrDate;
	private LocalDate eventEndDate;
	private int userId;	
	
}
