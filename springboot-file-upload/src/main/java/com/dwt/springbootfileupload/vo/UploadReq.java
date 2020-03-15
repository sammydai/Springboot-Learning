package com.dwt.springbootfileupload.vo;

import lombok.Data;

/**
 * @Package: com.reanod.workflow.vo
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/15 16:36
 */
@Data
public class UploadReq {

	private String serverAddress;

	private String uploadFileName;

	private String uploadFilePath;

}
