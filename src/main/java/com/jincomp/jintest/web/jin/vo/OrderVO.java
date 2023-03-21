package com.jincomp.jintest.web.jin.vo;

import lombok.Data;

@Data
public class OrderVO {

	private int orderId;
	private String userNo;
	private String goodsId;
	private int orderPrice;
	private String orderDate;
	
}
