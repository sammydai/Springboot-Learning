package aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package: com.learning.ioc.aop
 * @Description: BServiceImpl
 * @Author: Sammy
 * @Date: 2022/8/26 15:54
 */
@Service
public class BServiceImpl implements BService {

	@Autowired
	private AService aService;

	@Override
	public void b() {
		System.out.println("bbbb");
	}

	@Override
	public void bb() {
		aService.a();
	}

	public AService getaService() {
		return aService;
	}

	public void setaService(AService aService) {
		this.aService = aService;
	}
}
