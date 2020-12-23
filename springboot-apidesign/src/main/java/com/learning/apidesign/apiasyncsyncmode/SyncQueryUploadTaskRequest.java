package com.learning.apidesign.apiasyncsyncmode;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @Package: com.learning.apidesign.apiasyncsyncmode
 * @Description: SyncQueryUploadTaskRequest
 * @Author: Sammy
 * @Date: 2020/12/22 15:23
 */
@Data
@RequiredArgsConstructor
public class SyncQueryUploadTaskRequest {
	private final String taskId;
}
