package com.jincomp.jintest.web.jin.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor   //Controller에서 RequestBody사용할때 필요함 어떤 파라미터구성이 들어올지 모르므로 있어야함.
@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
public class RentVO {

	private int userNo;
	private String goodsId;
	private String startDate;
	private String endDate;
	//private String userAgeLimit;
	
}
