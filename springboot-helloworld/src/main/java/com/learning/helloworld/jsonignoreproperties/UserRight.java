package com.learning.helloworld.jsonignoreproperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @Package: com.learning.helloworld.jsonignoreproperties
 * @Description:
 * @Author: Sammy
 * @Date: 2020/12/14 16:39
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRight {
	private String name;
}
