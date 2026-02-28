package com.learning.datasource.multi.config;

import io.github.imfangs.dify.client.*;
import io.github.imfangs.dify.client.model.DifyConfig;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2025/10/14 15:46]
 */
public class DifyClientConfig {

	// 创建完整的 Dify 客户端
	DifyClient client = DifyClientFactory.createClient("https://api.dify.ai/v1", "your-api-key");

	// 创建特定类型的客户端
	DifyChatClient chatClient = DifyClientFactory.createChatClient("https://api.dify.ai/v1", "your-api-key");
	DifyCompletionClient completionClient = DifyClientFactory.createCompletionClient("https://api.dify.ai/v1", "your-api-key");
	DifyChatflowClient chatflowClient = DifyClientFactory.createChatWorkflowClient("https://api.dify.ai/v1", "your-api-key");
	DifyWorkflowClient workflowClient = DifyClientFactory.createWorkflowClient("https://api.dify.ai/v1", "your-api-key");
	DifyDatasetsClient datasetsClient = DifyClientFactory.createDatasetsClient("https://api.dify.ai/v1", "your-api-key");

	// 使用自定义配置创建客户端
	DifyConfig config = DifyConfig.builder()
			.baseUrl("https://api.dify.ai/v1")
			.apiKey("your-api-key")
			.connectTimeout(5000)
			.readTimeout(60000)
			.writeTimeout(30000)
			.build();

	DifyClient clientWithConfig = DifyClientFactory.createClient(config);
}
