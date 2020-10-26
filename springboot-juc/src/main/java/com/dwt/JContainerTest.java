package com.dwt;

import java.util.*;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/25 19:50
 */

public class JContainerTest {
	// public static void main(String[] args) {
	// 	Student stu1 = new Student("张三",21,"156482",172);
	// 	Student stu2 = new Student("李四",18,"561618",168);
	// 	Student stu3 = new Student("王五",19,"841681",170);
	// 	Student stu4 = new Student("赵七",20,"562595",180);
	//
	// 	List<Student> list = new ArrayList<>();
	//
	// 	list.add(stu3);
	// 	list.add(stu1);
	// 	list.add(stu4);
	// 	list.add(stu2);
	//
	// 	System.out.println("-----------排序前----------");
	//
	// 	Iterator<Student> iterator = list.iterator();
	// 	while (iterator.hasNext()) {
	// 		System.out.println(iterator.next());
	// 	}
	//
	// 	Collections.sort(list);
	// 	System.out.println("-----------按照年龄排序后----------");
	// 	for (int i = 0; i < list.size(); i++) {
	// 		System.out.println(list.get(i).toString());
	// 	}
	// }

	public static void main(String[] args) {
		// list to array
		// List<String> list = new ArrayList<String>();
		// list.add("王磊");
		// list.add("的博客");
		// list.toArray();
		// // array to list
		// String[] array = new String[]{"王磊","的博客"};
		// List<String> strings = Arrays.asList(array);

		Map<Integer, String> map = new HashMap();
		map.put(1, "Java");
		map.put(2, "C++");
		map.put(3, "PHP");
		Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, String> entry = iterator.next();
			System.out.println(entry.getKey()+"--"+entry.getValue());
		}
	}

}

class Student implements Comparable{
	private int age;
	private String name;
	private String tel;
	private int height;

	public Student( String name,int age, String tel, int height) {
		this.age = age;
		this.name = name;
		this.tel = tel;
		this.height = height;
	}

	@Override
	public String toString() {
		return "Student{" +
				"age=" + age +
				", name='" + name + '\'' +
				", tel='" + tel + '\'' +
				", height=" + height +
				'}';
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int compareTo(Object o) {
		//使用当前对象的年龄和其他对象的年龄比较，如果<0返回负数，>0返回正数，=0返回0
		int z = this.age - ((Student)o).getAge();
		if(z<0)
			return -1;  // 返回其他负数也行
		else if(z == 0)
			return 0;
		else
			return 1;  //返回其他正数也行
	}
}