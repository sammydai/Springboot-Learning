package com.learning.helloworld.jsonignoreproperties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.learning.helloworld.jsonignoreproperties
 * @Description: EnumusedAPI Demo
 * @Author: Sammy
 * @Date: 2020/12/14 13:25
 */
@RestController
@Slf4j
public class EnumusedAPIDemo {

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("test")
	public void test() throws JsonProcessingException {
		log.info("color:{}", objectMapper.writeValueAsString(Color.BLUE));
	}

	@PostMapping("wrong")
    public UserWrong wrong(@RequestBody UserWrong user) {
        return user;
    }

    @PostMapping("right")
	public Object right(@RequestBody UserRight user){
		return user;
	}

}
