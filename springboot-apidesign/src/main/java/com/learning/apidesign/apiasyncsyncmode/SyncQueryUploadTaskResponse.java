package com.learning.apidesign.apiasyncsyncmode;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @Package: com.learning.apidesign.apiasyncsyncmode
 * @Description: SyncQueryUploadTaskResponse
 * @Author: Sammy
 * @Date: 2020/12/22 14:55
 */
@Data
@RequiredArgsConstructor
public class SyncQueryUploadTaskResponse {
	private final String taskId;
    private String downloadUrl;
    private String thumbnailDownloadUrl;
}
