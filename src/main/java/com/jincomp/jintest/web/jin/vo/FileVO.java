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
public class FileVO {
	
    private String goodsId;
    private String imageOriginalName;
    private String imageSavedName;
    private String imagePath;
}
