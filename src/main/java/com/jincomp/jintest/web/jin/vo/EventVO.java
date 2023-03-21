package com.jincomp.jintest.web.jin.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author mskim
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class EventVO {
	private String eventId;
	private int fixDiscount;
	private LocalDate rateStrDay;
	private LocalDate rateEndDay;
	private int rate;
}
