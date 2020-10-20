package com.dwt.springboothelloworld;

import cn.hutool.core.util.StrUtil;
import com.dwt.springboothelloworld.config.ServerConfig;
import com.dwt.springboothelloworld.domain.Person;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;


@RestController
@SpringBootApplication
public class SpringbootHelloworldApplication {

	@Autowired
	private ServerConfig serverConfig;

	public static void main(String[] args) throws Exception {

		// SpringApplication.run(SpringbootHelloworldApplication.class, args);
		// serializeFlyPig();
		// Person person = deserializeFlyPig();
		// System.out.println(person.toString());

		// IOUtils.readFully(inputStream,bytes);
		// System.out.println(new String(bytes));
	}




	private static void serializeFlyPig() throws IOException {
        Person kkss = new Person("kkss",28,"teacher");
        // ObjectOutputStream 对象输出流，将 flyPig 对象存储到E盘的 flyPig.txt 文件中，完成对 flyPig 对象的序列化操作
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("/Users/daiwenting/Documents/lua/Person.txt")));
        oos.writeObject(kkss);
        System.out.println("kkss Person 对象序列化成功！");
        oos.close();
    }

    private static Person deserializeFlyPig() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("/Users/daiwenting/Documents/lua/Person.txt")));
        Person person = (Person) ois.readObject();
        System.out.println("kkss Person 对象反序列化成功！");
        return person;
    }

	@GetMapping(value = "/httpserver/hello")
	public String getHello(@RequestParam(name = "who",required = false) String who) {
		if (StrUtil.isBlank(who)) {
			who = "World First this is localhost !!! "+serverConfig.getUrl();
		}
		return StrUtil.format("Hello, {}!", who);
	}

}
