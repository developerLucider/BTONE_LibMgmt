package com.jincomp.jintest.web.jin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartBooksVO {

	private int cartId;	
	private String userNo;
	private String goodsId;
	
//	// 주문 상품의 개수
//	private int count;
//	
//	//상품의 개수 갱신
//	public void addCount(int count) {
//		this.count += count;
//	}
}
