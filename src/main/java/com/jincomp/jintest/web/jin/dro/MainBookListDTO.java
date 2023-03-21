package com.jincomp.jintest.web.jin.dro;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MainBookListDTO {
	private String goodsId;
	private String goodsName;
	private String goodsPrice;
	private String goodsDiscountPrice;
	private String eventStrDate;
	private String eventEndDate;
	private int userId;
	
}
