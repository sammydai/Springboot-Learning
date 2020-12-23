package com.learning.apidesign.apiasyncsyncmode;

import lombok.Data;

/**
 * @Package: com.learning.apidesign.apiasyncsyncmode
 * @Description: SyncUploadResponse
 * @Author: Sammy
 * @Date: 2020/12/22 14:44
 */
@Data
public class SyncUploadResponse {
	private String downloadUrl;
	private String thumbnailDownloadUrl;
}
