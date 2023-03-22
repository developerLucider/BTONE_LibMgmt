package com.jincomp.jintest.web.jin.dto;

import java.time.LocalDate;

import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.UserVO;

import lombok.Data;

@Data
public class OrderDTO {

	private UserVO userVo;
	private BookVO bookVo;
	private int orderId;
	private int orderPrice;
	private LocalDate orderDate;
	
}
