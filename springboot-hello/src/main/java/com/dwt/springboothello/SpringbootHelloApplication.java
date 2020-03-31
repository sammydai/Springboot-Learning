package com.dwt.springboothello;

import com.sun.deploy.resources.Deployment_pt_BR;
import com.sun.tools.javac.util.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.jvm.hotspot.oops.BranchData;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;

@RestController
@SpringBootApplication
public class SpringbootHelloApplication {

	public static void main(String[] args) {
		List<String> result = new ArrayList<>();
		// SpringApplication.run(SpringbootHelloApplication.class, args);
		String[] arrs = {"WinSCP 5.8.6", "WinSCP 5.9.4", "WinSCP 5.9.3","aaa"};
		String prefix = arrs[0];
		for (int i=1;i<arrs.length;i++) {
			while (arrs[i].indexOf(prefix)!=0){
				prefix=prefix.substring(0,prefix.length()-1);
				// if (prefix.isEmpty()){
				// 	result.add(arrs[i]);
				// }
			}
		}
		for (String s : result) {
			System.out.println("ssss"+s);
		}

	}

	public static String getPrefix(String s1,String s2){
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0;i<s1.length();i++){
			if (s1.charAt(i) == s2.charAt(i)) {
				stringBuffer.append(s1.charAt(i));
			}else {
				break;
			}
		}
		return stringBuffer.toString();
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello-msg";
	}

}
