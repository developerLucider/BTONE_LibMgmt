package com.jincomp.jintest.web.jin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor   //Controller에서 RequestBody사용할때 필요함 어떤 파라미터구성이 들어올지 모르므로 있어야함.
@AllArgsConstructor
@Data
@Getter
@Setter
public class BookVO {

	private String goodsId;
	private String goodsName;
	private String goodsPrice;
	private String eventId;
	
	private EventVO eventVo;
	private RentVO rentVo;
	
}
