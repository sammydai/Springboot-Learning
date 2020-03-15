package com.dwt.springbootfileupload.server;

import com.dwt.springbootfileupload.vo.UploadRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;



@RestController
public class UploadServer {

	private static final Logger logger = LoggerFactory.getLogger(UploadServer.class);

	@Value("${upload.path}")
    private String path;

	@PostMapping("/postfile")
	public UploadRes upload(@RequestParam(value = "file",required = false) MultipartFile... files) throws Exception {
		UploadRes uploadRes = new UploadRes();
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				logger.warn("have empty upload file,you need check is right?");
				continue;
			}
			String fileName = new String(file.getOriginalFilename().getBytes("UTF-8"), Charset.defaultCharset());
			InputStream is = file.getInputStream();
			Files.copy(is, Paths.get(path + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
			uploadRes.setMessage("传输成功");
			uploadRes.setCode("200");
		}
		return uploadRes;
	}
}
