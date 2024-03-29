package com.jincomp.jintest.web.jin.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

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
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class BooksEventInfo implements Serializable {

   public static final long serialVersionUID = 1L;

    
    private String goodsId;
    private String eventId;
	

}