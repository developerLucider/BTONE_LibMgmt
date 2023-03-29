package com.jincomp.jintest.web.jin.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jincomp.jintest.web.jin.mapper.JinMapper;
import com.jincomp.jintest.web.jin.mapper.UserMapper;
import com.jincomp.jintest.web.jin.vo.JinTestVO;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author kyj
 */
@RequiredArgsConstructor
@Service
public class JinService {

	private static final Logger log = LoggerFactory.getLogger(JinService.class);

	private final UserMapper jinMapper;


	public List<JinTestVO> getUserList() {
		log.debug("JinService 진입>");
		List<JinTestVO> mapper = jinMapper.getUserList();

		return mapper;
	}

	@Value("${file.upload-location}")
    String fileConfigPath;

	public Map saveFile(MultipartFile files) throws IOException {

        if (files.isEmpty()) {
            return null;
        }

        String savedFileName = "";
        // 1. 파일 저장 경로 설정 : 실제 서비스되는 위치(프로젝트 외부에 저장)
        //  String uploadPath = fileConfigPath;  //현재는 절위의 yml에 지정 한 경로로 사용시 필요

        String uploadPath = new File("").getAbsolutePath() + "\\"+fileConfigPath;  //absolutePath
        log.debug("uploadPath >>>"+uploadPath);
        File Folder = new File(uploadPath);

    	// 해당 디렉토리가 없다면 디렉토리를 생성.
		if (!Folder.exists()) {
			try {
				Folder.mkdir(); // 폴더 생성합니다. ("새폴더"만 생성)
				log.debug("폴더가 생성완료.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			log.debug("폴더가 이미 존재");
		}
        // 2. 원본 파일 이름 알아오기
        String originalFileName = files.getOriginalFilename();
       // file.get
        log.debug("originalFileName     >>  "+originalFileName);
        // 3. 파일 이름 중복되지 않게 이름 변경(서버에 저장할 이름) UUID 사용  //해당정보로 insert 처리 후 파일 목록 조회 가능
        UUID uuid = UUID.randomUUID();
        savedFileName = uuid.toString()+"_"+ originalFileName;
        log.debug("savedFileName     >>  "+savedFileName);
        // 4. 파일 생성
        File file1 = new File(uploadPath + savedFileName);
        // 5. 서버로 전송
        files.transferTo(file1);
        Map resultMap = new HashMap();
        resultMap.put("originalFileName", originalFileName);
        resultMap.put("filePathName", uploadPath + savedFileName);
        resultMap.put("serverfileName",savedFileName);

        return resultMap;

	}

	public byte[] download(String path) throws IOException {

		byte[] fileByte = null;
		try {
			fileByte = FileUtils.readFileToByteArray(new File(path));
		} catch (Exception e) {
			// throw new Exception("download error");
		}

		return fileByte;
	}



}

