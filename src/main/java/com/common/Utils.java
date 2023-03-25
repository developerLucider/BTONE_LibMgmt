package com.common;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    private static String CRLF = "\r\n";

    public static HashMap<String, Object> getParameter(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        Enumeration<String> enumeration = request.getParameterNames();

        logger.debug("PARAM START ---------------------------------------------");

        int cnt = 0;
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString();
            String value = request.getParameter(key);

            cnt++;
            logger.debug("[" + StringUtils.leftPad(String.valueOf(cnt), 3, "0") + "] " + key + ":" + value);

            map.put(key, value);
        }
        logger.debug("END   START ---------------------------------------------");
        return map;
    }


    public static String toCamelCase(String underScore) {
        
        if (underScore == null || underScore.length() == 0) {
            return "";            
        }
        
        if (underScore.indexOf('_') < 0 && Character.isLowerCase(underScore.charAt(0)))
            return underScore;
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        int len = underScore.length();
        for (int i = 0; i < len; i++) {
            char currentChar = underScore.charAt(i);
            if (currentChar == '_') {
                nextUpper = true;
                continue;
            }
            if (nextUpper) {
                result.append(Character.toUpperCase(currentChar));
                nextUpper = false;
            } else {
                result.append(Character.toLowerCase(currentChar));
            }
        }

        return result.toString();
    }

    // listToText
    public static String listToText(List head, List list, int flag) {
        List sizeList = new ArrayList();

        int totalLen = 0;

        if (list == null || list.size() == 0) {
            for (int i = 0; i < head.size(); i++) {
                int len = getByteSize(String.valueOf(head.get(i)));
                sizeList.add(len);
                totalLen = totalLen + len + 2;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i);
            


            List keyList = new ArrayList(map.keySet());
            List valueList = new ArrayList(map.values());


            //처음 초기화
            if (i == 0) {
                sizeList = new ArrayList();                    
                for (int col = 0; col < keyList.size(); col++) {
                    sizeList.add(0);
                }
            }

            //
            totalLen = 0;
            for (int col = 0; col < keyList.size(); col++) {

                int maxLen = (int) sizeList.get(col);
                int keyLen = getByteSize(toUnCamelCase(String.valueOf(keyList.get(col))));
                int valueLen = getByteSize(String.valueOf(valueList.get(col)));
                int headLen = 0;

                if (head != null && head.size() > 0) {
                    headLen = getByteSize(StringUtils.defaultString(String.valueOf(head.get(col)), ""));
                }

                if (maxLen < keyLen) {
                    maxLen = keyLen;
                }
                if (maxLen < valueLen) {
                    maxLen = valueLen;
                }
                if (maxLen < headLen) {
                    maxLen = headLen;
                }

                totalLen = totalLen + maxLen + 2;

                sizeList.set(col, maxLen);
            }
        }


        // No 사이즈
        totalLen = totalLen + 7;

        String str = "";

        str += Utils.rpad("", totalLen, '=');
        str += CRLF;

        //head
        if (head != null && head.size() > 0) {
            if (flag == 1) {
                str += "      | ";
            } else {
                str += "No    | ";
            }

            for (int col = 0; col < head.size(); col++) {
                String value = String.valueOf(head.get(col));
                str += rpad(value, (int) sizeList.get(col), ' ') + " |";
            }
        }
        str += CRLF;

        //title     
        if (flag == 1 && list != null && list.size() > 0) {

            for (int i = 0; i < 1; i++) {
                Map map = (Map) list.get(i);

                List keyList = new ArrayList(map.keySet());

                //
                str += "No    | ";

                for (int col = 0; col < keyList.size(); col++) {
                    String key = String.valueOf(keyList.get(col));
                    str += rpad(toUnCamelCase(key), (int) sizeList.get(col), ' ') + " | ";
                }
                //카멜
                //              str += CRLF;
                //              str += "      |";
                //              for (int col = 0; col < keyList.size(); col++) {
                //                  String key = String.valueOf(keyList.get(col));
                //                  str += rpad(key, (int) sizeList.get(col), ' ') + " |";
                //              }           
            }
            str += CRLF;
        }
        str += Utils.rpad("", totalLen, '-');
        str += CRLF;

        //data
        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i);

            List keyList = new ArrayList(map.keySet());
            //          
            str += rpad(String.valueOf((i + 1)), 5, ' ') + " | ";
            for (int col = 0; col < keyList.size(); col++) {
                String value = String.valueOf(map.get(keyList.get(col)));
                str += rpad(value, (int) sizeList.get(col), ' ') + " | ";
            }
            str += CRLF;
        }

        str += Utils.rpad("", totalLen, '-');

        return str;
    }

    /**
     * lpad("1",7, "0") 0000001 한글 2byte 처리
     * 
     */
    public static String lpad(String strSource, int iLength, char cPadder) {
        StringBuffer sbBuffer = null;
        if (!isEmpty(strSource)) {
            int iByteSize = getByteSize(strSource);
            if (iByteSize > iLength) {
                return strSource.substring(0, iLength);
            } else if (iByteSize == iLength) {
                return strSource;
            } else {
                int iPadLength = iLength - iByteSize;
                sbBuffer = new StringBuffer();
                for (int i = 0; i < iPadLength; i++) {
                    sbBuffer.append(cPadder);
                }
                sbBuffer.append(strSource);
                return sbBuffer.toString();
            }
        }
        sbBuffer = new StringBuffer();
        for (int i = 0; i < iLength; i++) {
            sbBuffer.append(cPadder);
        }

        return sbBuffer.toString();
    }

    /**
     * rpad("1",7, "0") 1000000 한글 2byte 처리
     * 
     */
    public static String rpad(String strSource, int iLength, char cPadder) {
        StringBuffer sbBuffer = null;
        if (!isEmpty(strSource)) {
            int iByteSize = getByteSize(strSource);
            if (iByteSize == iLength) {
                return strSource;
            } else {
                int iPadLength = iLength - iByteSize;
                sbBuffer = new StringBuffer(strSource);
                for (int i = 0; i < iPadLength; i++) {
                    sbBuffer.append(cPadder);
                }
                return sbBuffer.toString();
            }
        }
        sbBuffer = new StringBuffer();
        for (int i = 0; i < iLength; i++) {
            sbBuffer.append(cPadder);
        }

        return sbBuffer.toString();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static int getByteSize(String str) {
        if (str == null || str.length() == 0)
            return 0;
        byte[] byteArray = null;
        try {
            byteArray = str.getBytes("euc-kr");
        } catch (Exception e) {

        }
        if (byteArray == null)
            return 0;
        return byteArray.length;
    }

    public static String toUnCamelCase(String underScore) {
        if (StringUtils.isEmpty(underScore)) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int len = underScore.length();

        for (int i = 0; i < len; i++) {
            char currentChar = underScore.charAt(i);

            if ((currentChar >= 65) && (currentChar <= 90)) {
                result.append('_');
                result.append(Character.toUpperCase(currentChar));
            } else {
                result.append(Character.toUpperCase(currentChar));
            }
        }

        return result.toString().toUpperCase();
    }
    
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
