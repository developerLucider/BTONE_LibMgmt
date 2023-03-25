package com.common;

import java.time.LocalDate;

public class AdultCertification {
    // 성인 인증 메서드
    public static int adultCertification(String rrnFront, String rrnBack) {
       
       // 오늘 날짜
       LocalDate today = LocalDate.now();
       int todayYear = today.getYear();
       int todayMonth = today.getMonthValue();
       int todayDay = today.getDayOfMonth();
       
       // 주민등록번호를 통해 입력 받은 날짜
       int year = Integer.parseInt(rrnFront.substring(0,2));
       int month = Integer.parseInt(rrnFront.substring(2,4));
       int day = Integer.parseInt(rrnFront.substring(4,6));
       
       // 주민등록번호 뒷자리로 몇년대인지
       String gender = rrnBack.substring(0,1);
       if(gender.equals("1") || gender.equals("2")) {
          year += 1900;
       } else if(gender.equals("3") || gender.equals("4")){
          year += 2000;
       } else if(gender.equals("0") || gender.equals("9")) {
          year += 1800;
       }
       
       // 올해 - 태어난 년도
       int americanAge = todayYear - year;
       System.out.println("나이 : " + americanAge);
       
       // 생일이 안 지났으면 -1
       if(month > todayMonth) {
          americanAge--;
       } else if(month == todayMonth) {
          if(day > todayDay) {
             americanAge--;
          }
       }
       
       return americanAge;
    }
}
