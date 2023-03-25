package com.jincomp.jintest.web.jin.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jincomp.jintest.web.jin.dto.MainBookListDTO;
import com.jincomp.jintest.web.jin.mapper.BookMapper;
import com.jincomp.jintest.web.jin.mapper.UserMapper;
import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.PointVO;
import com.jincomp.jintest.web.jin.vo.RentVO;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author mskim
 */
@RequiredArgsConstructor
@Service
public class HomeService {

	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);
	private final BookMapper bookMapper;
	private final UserMapper userMapper;
	
	private List<MainBookListDTO> addEventPrice(List<BookVO> bookList) {
		List<MainBookListDTO> mainList = new ArrayList<>();
		
		for(BookVO tmp : bookList) {
			// 가져온 리스트에서 책별로 필요한 정보만 가져와 DTO에담아서 리스트로 생성
			MainBookListDTO mainbook = new MainBookListDTO();
			
			mainbook.setGoodsId(tmp.getGoodsId());
			mainbook.setGoodsName(tmp.getGoodsName());
			mainbook.setGoodsPrice(tmp.getGoodsPrice());
			if(!Objects.isNull(tmp.getEventId())) {
				mainbook.setEventStrDate(tmp.getEventVo().getRateStrDay());
				mainbook.setEventEndDate(tmp.getEventVo().getRateEndDay());
			}
			mainbook.setUserId(tmp.getRentVo().getUserNo());
			mainbook.setGoodsQuantity(tmp.getGoodsQuantity());
			
			// 이벤트 유무에 따라 할인가를 계산해준다.
			//logger.debug("{}의 이벤트 : {}", tmp.getGoodsName() , tmp.getEventId());
			if(Objects.isNull(tmp.getEventId())) {	// 해당 이벤트가 없으면 할인가에 정가를 삽입
				mainbook.setGoodsDiscountPrice(tmp.getGoodsPrice());
			} else {
				//logger.debug("이벤트 고정할인가 = {}\n 이벤트 할인율 = {}",tmp.getEventVo().getFixDiscount(),tmp.getEventVo().getFixDiscount());
				int bookPrice = Integer.parseInt(tmp.getGoodsPrice());	// string 타입인 원가를 int형으로 변환 (계산을 위해)
				
				int rateTmp = tmp.getEventVo().getRate();
				
				double rateDouble = rateTmp;
				
				if(tmp.getEventVo().getFixDiscount() != 0) {	// 고정 할인가가 존재하는 경우
					bookPrice -= tmp.getEventVo().getFixDiscount();	 // 고정 할인가 만큼 감소
					mainbook.setGoodsDiscountPrice(String.valueOf(bookPrice));
				}
				
				if(tmp.getEventVo().getRate() != 0) {
					
					double dcPrice = bookPrice * (rateDouble / (double)100);
					bookPrice -= dcPrice;
					mainbook.setGoodsDiscountPrice(String.valueOf(bookPrice));
				}
			}
			mainList.add(mainbook);
		}
		logger.debug("booklist : {}", mainList);
		
		return mainList; 
	}
	
	
	public List<MainBookListDTO> getMainBookList() {
		logger.debug("-------------------userService bookList 진입");
		
		List<BookVO> bookList = bookMapper.getBookList();
		
		return addEventPrice(bookList);
	}
	
	public List<MainBookListDTO> getMainBookList(String searchType, String keyword) {
		logger.debug("-------------------userService bookList 진입");

		List<BookVO> bookList = bookMapper.searchBookList(searchType, keyword);
		
		return addEventPrice(bookList);
	}
	
	
	
	public Map<String, List<String>> rentBooks(List<String> rentList,
											 	List<String> rentPriceList,
											 	int userNo) {
		Map<String, List<String>> result = new HashMap<>();
		
		List<String> failResult = new ArrayList<>();	// 대여 실패한 책 이름의 리스트
		List<String> successResult = new ArrayList<>(); // 대여 성공한 책 이름의 리스트
		List<String> successPayResult = new ArrayList<>(); // 대여 성공한 책의 ID 리스트
		
		
		logger.debug("rentBooks 진입");
		// 대여할 책 ID 리스트를 iterating
		rentList:
		for(String goodsId : rentList) {
			// rentVo 객체 생성후 정보 setting
			List<RentVO> rentBookList = bookMapper.getRentList();
			
			logger.debug("대여한 책 리스트 : {}", rentBookList);
			
			// 해당 책이 이미 빌려가졌다면 해당 책의 ID를 넘기고 다음
			
			for(RentVO rentBook : rentBookList) {
				if(rentBook.getGoodsId().equals(goodsId)) {
					failResult.add(rentBook.getGoodsId());
					continue rentList;
				}
			}
			
			RentVO rentVo = new RentVO();
			rentVo.setGoodsId(goodsId);
			rentVo.setUserNo(userNo);
			
			String startDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
			String endDate = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
			
			rentVo.setStartDate(startDate);
			rentVo.setEndDate(endDate);
			
			// insert의 결과가 0 이상(성공했다면) -> 해당 아이디로 값을 찾아 해당 ID를 리턴
			if(bookMapper.addRentBook(rentVo) > 0) {
				List<MainBookListDTO> tmp = this.getMainBookList("goods_id", rentVo.getGoodsId());
				successResult.add(tmp.get(0).getGoodsName());
				successPayResult.add(tmp.get(0).getGoodsId());
			}
			
			logger.debug("{} 대여 리스트 추가 완료", rentVo);
		}
		result.put("fail", failResult);
		result.put("success", successResult);
		
		
		// -------- 해당 유저 포인트 총 액수 만큼 차감 -------------
		// 성공한 리스트에 대해 이벤트가를 합친다(이벤트없는 책은 이벤트에 원가가 들어감)
		logger.debug("결제 시작");
		int sumPoint = 0;			// 대여 할 책의 총가격
		for(String rentGoodsId :successPayResult) {	// 성공한 책의 ID별로
			logger.debug(rentGoodsId);
			// 성공한 ID의 이벤트가 포함 책정보를 불러옴
			List<MainBookListDTO> rentBook = this.addEventPrice(bookMapper.searchBookList("goods_id", rentGoodsId));
			
			
			// 해당 책의 이벤트가를 가격에 포함
			String price = rentBook.get(0).getGoodsDiscountPrice();
			sumPoint += Integer.parseInt(price);
		}
		PointVO paymentInfo = new PointVO();
		paymentInfo.setPoint(sumPoint);
		paymentInfo.setUserNo(userNo);
		
		logger.debug("결제자 ID : {} 결제금액 : {}", paymentInfo.getUserNo(), paymentInfo.getPoint());
		userMapper.updatePoint(paymentInfo);
		//-----------------------------------------------------
		
		return result;
	}
	
	
	
}
