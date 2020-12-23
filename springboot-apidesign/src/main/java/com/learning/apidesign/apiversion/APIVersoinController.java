package com.learning.apidesign.apiversion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Package: com.learning.apidesign.apiversion
 * @Description: APIVersoinController
 * @Author: Sammy
 * @Date: 2020/12/22 14:24
 */
@Slf4j
@RestController
@RequestMapping("apiversion")
@APIVersion("v1")
public class APIVersoinController {

	@GetMapping("/api/item/v1")
	public void wrong1() {

	}

	@GetMapping("/api/v1/shop")
	public void wrong2() {

	}

	@GetMapping("/v1/api/merchant")
	public void wrong3() {

	}

	@GetMapping("/v1/api/user")
	public int right() {
		return 1;
	}

	@GetMapping(value = "/api/user", params = "version=2")
	public int right2(@RequestParam("version") int version) {
		return 2;
	}

	@GetMapping(value = "/api/user", headers = "X-API-VERSION=3")
    public int right3(@RequestHeader("X-API-VERSION") int version) {
        return 3;
    }

	@GetMapping(value = "/api/user")
	@APIVersion("v4")
	public int right4() {

		return 4;
	}

}
