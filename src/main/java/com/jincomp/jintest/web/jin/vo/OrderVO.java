package com.jincomp.jintest.web.jin.vo;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class OrderVO {

	private int orderId;
	private int orderPrice;
	private LocalDate orderDate;
	
	private UserVO userVo;
	private BookVO bookVo;
}
