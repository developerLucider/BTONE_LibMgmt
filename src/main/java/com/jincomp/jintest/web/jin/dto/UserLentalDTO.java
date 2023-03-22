package com.jincomp.jintest.web.jin.dto;
import java.time.LocalDate;

import lombok.Data;

@Data
public class UserLentalDTO {
	
	private String userName;
	private String goodsName;
	private String startDate;
	private String endDate;
	private String goodsId;
	private String userNo;
}
