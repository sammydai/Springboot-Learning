package aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package: com.learning.ioc.aop
 * @Description: AServiceImpl
 * @Author: Sammy
 * @Date: 2022/8/26 15:53
 */
@Service
public class AServiceImpl implements AService {

	@Autowired
	BService bService;

	@Override
	public void a() {
		bService.b();
		System.out.println("aaaa");
	}

	public BService getbService() {
		return bService;
	}

	public void setbService(BService bService) {
		this.bService = bService;
	}
}
