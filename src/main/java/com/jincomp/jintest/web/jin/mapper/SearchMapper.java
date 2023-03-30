package com.jincomp.jintest.web.jin.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import com.jincomp.jintest.web.jin.vo.BookPopWord;

@Mapper
public interface SearchMapper {

	/*검색 시 단어 insert*/
	public int insertSearchBook(String bookPopularWord);
	
	/* 검색 자동 완성 */
	public List<Map<String, Object>> autoComplete(Map<String, Object> paramMap) throws Exception;
	
	/* 검색 랭킹 */
	public List<BookPopWord> getBookSearchRanking();
}
