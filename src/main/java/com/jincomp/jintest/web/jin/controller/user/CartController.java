package com.jincomp.jintest.web.jin.controller.user;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jincomp.jintest.web.jin.service.CartBooksService;
import com.jincomp.jintest.web.jin.vo.CartBooksVO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CartController {
	
	private final CartBooksService booksService;
	
	@PostMapping("/add/cart")
	@ResponseBody
	public int addBasket(@RequestParam("bookList[]") List<String> bookList,
						 @RequestParam("userNo") String userNo,
						 HttpServletRequest request){
		
		log.info("장바구니 담기 post controller 진입");
				
		log.info("bookList -->", "{}", bookList);
				
		int count = 0;
					
		for(String goodsId : bookList) {
	
			CartBooksVO cartBooksVO = new CartBooksVO();
			
			cartBooksVO.setGoodsId(goodsId);
			cartBooksVO.setUserNo(userNo);
			
			log.info("도서 아이디 장바구니 조회 :", "{}" , goodsId);
			
			count = booksService.insertCart(cartBooksVO);
		}
			
				
		return count;
	}
}
