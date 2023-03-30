package com.jincomp.jintest.web.jin.controller.user;
  
import java.io.File;
import java.io.IOException;

import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap; 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.multipart.MultipartFile;
  
import com.jincomp.jintest.web.jin.service.JinService;
  
@Controller 
public class FileController {

	@Autowired private JinService jinService; 
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
	
	@Value("${file.upload-location}")
	String fileConfigPath;
	
	@ResponseBody	
	@PostMapping("/upload/fileUpload.do") 
	public Map fileUpload(@RequestParam(value="uploadFile", required = false) MultipartFile file,
						  @RequestParam(value="uploadFiles", required = false) List<MultipartFile> files,
						  ModelMap model) throws IOException {
		List<Map> resultListMap = new ArrayList<Map>();
		if(files != null) { // 다건처리
			log.debug("다건 file 업로드 컨트롤러 진입");
			for (MultipartFile multipartFile : files) {
				Map fileMap = new HashMap();
				fileMap = jinService.saveFile(multipartFile);
		
				if (!fileMap.isEmpty())
					  resultListMap.add(fileMap); 
			} 

			log.debug("다건 file 업로드 결과 : {}", resultListMap);
		}
	
		if (file != null) { 
			log.debug("단건 file 업로드 컨트롤러 진입");
			Map fileMap = new HashMap(); // 단건
			fileMap = jinService.saveFile(file);
			if (!fileMap.isEmpty())
				resultListMap.add(fileMap); 
			
			log.debug("단건 file 업로드 결과 : {}", resultListMap);
		}
		
		
		Map resultMap = new HashMap();
	
		resultMap.put("list", resultListMap);
	
		return resultMap;
	}
	
	//파일의 경로를 찾아서 떨구는 기능
	
	@GetMapping("/upload/fileDownload.do")
	public void	downloadAttach(@RequestParam("filePath") String	fileinfo,
								@RequestParam("fileName") String fileName,
								HttpServletResponse	response) throws IOException {
	
		String uploadPath = new File("").getAbsolutePath() + "\\"+fileConfigPath;
		String path = uploadPath+fileinfo; 
		try { 
			byte[] fileByte = FileUtils.readFileToByteArray(new File(path));
		
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; fileName=\"" 
								+ URLEncoder.encode(fileName, "UTF-8")+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			response.getOutputStream().write(fileByte);
			response.getOutputStream().flush(); response.getOutputStream().close();
		
		}catch (IOException ex){
		
		} 
	}
	
}
 