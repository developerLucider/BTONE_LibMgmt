package com.jincomp.jintest.web.jin.dro;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter

public class AdminAddBookDTO {
	
	private String goodsId;
	private String goodsName;
	private String goodsPrice;
	private String eventId;
}
