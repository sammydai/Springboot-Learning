package com.dwt.springbootfileupload.client;

import com.dwt.springbootfileupload.vo.UploadReq;
import com.dwt.springbootfileupload.vo.UploadRes;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @Package: com.reanod.workflow.client
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/15 17:28
 */

public class UploadActivitiClient {

	public UploadRes uploadFile(UploadReq uploadReq){
		CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        UploadRes uploadRes = new UploadRes();
		try {
			InputStream fis = new FileInputStream(new File(uploadReq.getUploadFilePath()));
            HttpPost httpPost = new HttpPost(uploadReq.getServerAddress());
            MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create();
            mEntityBuilder.addBinaryBody("file",fis, ContentType.MULTIPART_FORM_DATA,uploadReq.getUploadFileName());
            httpPost.setEntity(mEntityBuilder.build());
            response = httpclient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            uploadRes.setCode(Integer.toString(statusCode));
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = response.getEntity();
                uploadRes.setMessage(EntityUtils.toString(resEntity, Charset.defaultCharset()));
                // // 消耗掉response
                // EntityUtils.consume(resEntity);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(httpclient);
            HttpClientUtils.closeQuietly(response);
        }
        return uploadRes;
	}
}
