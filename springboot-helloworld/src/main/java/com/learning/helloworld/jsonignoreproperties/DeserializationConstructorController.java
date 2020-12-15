package com.learning.helloworld.jsonignoreproperties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.learning.helloworld.jsonignoreproperties
 * @Description: DeserializationConstructorController
 * @Author: Sammy
 * @Date: 2020/12/14 18:33
 */
@Slf4j
@RequestMapping("des")
@RestController
public class DeserializationConstructorController {
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("wrong")
	public void wrong() throws JsonProcessingException {
		log.info("result:{}", objectMapper.readValue("{\"code\":1234}", APIResultWrong.class));
		log.info("result:{}", objectMapper.readValue("{\"code\":2000}", APIResultWrong.class));
	}
}
