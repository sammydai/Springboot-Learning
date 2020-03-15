package com.dwt.springbootfileupload;

import com.dwt.springbootfileupload.client.UploadActivitiClient;
import com.dwt.springbootfileupload.vo.UploadReq;
import com.dwt.springbootfileupload.vo.UploadRes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootFileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFileUploadApplication.class, args);
		System.out.println("=============================test");
		UploadReq uploadReq = new UploadReq();
		uploadReq.setServerAddress("http://localhost:8989/postfile");
		uploadReq.setUploadFileName("0311面试.txt");
		uploadReq.setUploadFilePath("/Users/daiwenting/Documents/Interview/0311面试.txt");
		UploadRes uploadRes = new UploadActivitiClient().uploadFile(uploadReq);
		System.out.println("============res:"+uploadRes);
	}

}
