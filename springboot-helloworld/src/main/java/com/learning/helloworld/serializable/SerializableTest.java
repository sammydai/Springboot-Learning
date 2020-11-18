package com.learning.helloworld.serializable;

import com.learning.helloworld.domain.Person;

import java.io.*;

/**
 * @Package: com.learning.helloworld
 * @Description: Seralizable Test
 * @Author: Sammy
 * @Date: 2020/11/18 18:33
 */

public class SerializableTest {
	private static void serializeFlyPig() throws IOException {
		Person kkss = new Person("kkss",28,"teacher");
		// kkss.hashCode()
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
}
