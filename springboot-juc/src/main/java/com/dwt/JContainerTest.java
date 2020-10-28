package com.dwt;

import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/25 19:50
 */

@Slf4j
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
		oom();
	}

	public void compareSetObject(){
		HashSet set1 = new HashSet();
		set1.add("1");
		set1.add("1");

		for (Object a : set1) {
			System.out.println(a);
		}

		HashSet set2 = new HashSet();
		Person p1 = new Person("1");
		Person p2 = new Person("1");
		set2.add(p1);
		set2.add(p2);


		for (Object a : set2) {
			System.out.println(a);
		}

		System.out.println("p1hashcode "+p1.hashCode());
		System.out.println("p2hashcode "+p2.hashCode());
		System.out.println("p1 equals p2 "+p1.equals(p2));
	}

	public void ArraysListTest(){
		// int[] arr = {1, 2, 3};
		// List list = Arrays.asList(arr);
		// log.info("list:{} size:{} class:{}", list, list.size(), list.get(0).getClass());
		//
		//
		// int[] arr1 = {1, 2, 3};
		// List list1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
		// log.info("list:{} size:{} class:{}", list1, list1.size(), list1.get(0).getClass());
		//

		// Integer[] arr2 = {1, 2, 3};
		// List list2 = Arrays.asList(arr2);
		// log.info("list:{} size:{} class:{}", list2, list2.size(), list2.get(0).getClass());
		//


		// String[] arr = {"1", "2", "3"};
		// List list = Arrays.asList(arr);
		// arr[1] = "4";
		// try {
		// 	list.add("5");
		// } catch (Exception ex) {
		// 	ex.printStackTrace();
		// }
		// log.info("arr:{} list:{}", Arrays.toString(arr), list);
		//

		// 重新生成一个arrayList 原来数组修改，不会影响新的集合
		// String[] arr = {"1", "2", "3"};
		// List list = new ArrayList(Arrays.asList(arr));
		// arr[1] = "4";
		// try {
		// 	list.add("5");
		// } catch (Exception ex) {
		// 	ex.printStackTrace();
		// }
		// log.info("arr:{} list:{}", Arrays.toString(arr), list);


	}

	public void iterateMap(){
		Map<Integer, String> map = new HashMap();
		map.put(1, "Java");
		map.put(2, "C++");
		map.put(3, "PHP");
		/**
		 // 1 迭代器 EntrySet map.entrySet遍历
		 Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
		 while (iterator.hasNext()) {
		 Map.Entry<Integer, String> entry = iterator.next();
		 System.out.println(entry.getKey()+"--"+entry.getValue());
		 }
		 **/

		/**
		 // 2 迭代器 KeySet map.keySet遍历
		 Iterator<Integer> iterator1 = map.keySet().iterator();
		 while (iterator1.hasNext()) {
		 Integer key = iterator1.next();
		 System.out.println(key+"--"+map.get(key));
		 }
		 **/

		/**
		 // 3 foreach keySet
		 for (Integer key : map.keySet()) {
		 System.out.println(key+"-->"+map.get(key));
		 }**/

		/**
		 // 4 foreach EntrySet
		 for (Map.Entry<Integer, String> mm1 : map.entrySet()) {
		 System.out.println(mm1.getKey()+"对应--》"+mm1.getValue());
		 }
		 **/

		/**
		 // 5 lamda
		 map.forEach((key,value)->{
		 System.out.println(key);
		 System.out.println(value);
		 });
		 **/

		/**
		 // 6 Streams API 单线程
		 map.entrySet().stream().forEach((entry)->{
		 System.out.println(entry.getKey());
		 System.out.println();
		 });
		 **/

		//7 Streams API多线程
		// map.entrySet().parallelStream().forEach((entry -> {
		// 	System.out.println(entry.getKey());
		// 	System.out.println("-->"+entry.getValue());
		// }));
	}

	public void ArrayRemoveMethodTest(){
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		for (String ss : list) {
			if ("2".equals(ss)) {
				list.remove(ss);
			}
		}
		System.out.println(list);
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String ss = iterator.next();
			if (ss.equals("2")){
				iterator.remove();
			}
		}

		List<String> list2 = new ArrayList<>();
		list2.add("wupx");
		list2.add("love");
		list2.add("huxy");
		for (String temp : list2) {
			if ("wupx".equals(temp)) {
				list2.remove(temp);
			}
		}
		System.out.println(list2);
	}

	private static List<List<Integer>> data = new ArrayList<>();

	private static void oom() {
		for (int i = 0; i < 1000; i++) {
			List<Integer> rawList = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());
			data.add(rawList.subList(0, 1));
		}
	}

}

class Person {
	private String age;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return Objects.equals(age, person.age);
	}

	@Override
	public int hashCode() {

		return Objects.hash(age);
	}

	Person(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person{" + "age='" + age + '\'' + '}';
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