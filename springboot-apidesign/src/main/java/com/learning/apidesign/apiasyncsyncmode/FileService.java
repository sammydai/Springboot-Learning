package com.learning.apidesign.apiasyncsyncmode;

import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.learning.apidesign.apiasyncsyncmode
 * @Description: FileService
 * @Author: Sammy
 * @Date: 2020/12/22 14:47
 */
@Service
public class FileService {

	private ExecutorService threadPool = Executors.newFixedThreadPool(2);

	private AtomicInteger atomicInteger = new AtomicInteger(0);

	private ConcurrentHashMap<String,SyncQueryUploadTaskResponse> downloadUrl =new ConcurrentHashMap<>();

	//异步上传操作
	public AsyncUploadResponse asyncUpload(AsyncUploadRequest request) {
		AsyncUploadResponse response = new AsyncUploadResponse();
		//生成唯一的上传任务ID
		String taskId = "upload" + atomicInteger.incrementAndGet();
		//异步上传操作只返回任务ID
		response.setTaskId(taskId);
		threadPool.submit(() -> {
			String url = uploadFile(request.getFile());
			downloadUrl.computeIfAbsent(taskId, id -> new SyncQueryUploadTaskResponse(id)).setDownloadUrl(url);
		});

		threadPool.submit(()->{
			String url = uploadThumbnailFile(request.getFile());
			downloadUrl.computeIfAbsent(taskId,id->new SyncQueryUploadTaskResponse(id)).setThumbnailDownloadUrl(url);
		});
		return response;
	}

	private String uploadThumbnailFile(byte[] file) {
        try {
            TimeUnit.MILLISECONDS.sleep(1500 + ThreadLocalRandom.current().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "http://www.demo.com/download/" + UUID.randomUUID().toString();
    }

	private String uploadFile(byte[] file) {
		try {
			TimeUnit.MILLISECONDS.sleep(500 + ThreadLocalRandom.current().nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "http://www.demo.com/download/" + UUID.randomUUID().toString();
	}

	public SyncQueryUploadTaskResponse syncQueryUploadTask(SyncQueryUploadTaskRequest request) {
		SyncQueryUploadTaskResponse response = new SyncQueryUploadTaskResponse(request.getTaskId());
		response.setDownloadUrl(downloadUrl.getOrDefault(request.getTaskId(),response).getDownloadUrl());
		response.setThumbnailDownloadUrl(downloadUrl.getOrDefault(request.getTaskId(),response).getThumbnailDownloadUrl());
		return response;

	}

	public SyncUploadResponse syncUpload(SyncUploadRequest request) {
		SyncUploadResponse response = new SyncUploadResponse();
        response.setDownloadUrl(uploadFile(request.getFile()));
        response.setThumbnailDownloadUrl(uploadThumbnailFile(request.getFile()));
        return response;
	}
}
