package com.dwt.jvm8;

import com.dwt.jvm8.domain.Trader;
import com.dwt.jvm8.domain.Transaction;
import com.fasterxml.jackson.core.sym.Name1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.jvm.hotspot.types.CIntegerType;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@SpringBootApplication
public class SpringbootLogAopApplication {

	public static void main(String[] args) {
		// SpringApplication.run(SpringbootLogAopApplication.class, args);
		Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

		List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

		//1 2011年所有交易，按照交易额排序（从低到高）
		List<Transaction> collect = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
				.sorted(Comparator.comparing(Transaction::getYear))
				.collect(toList());
		System.out.println("1----: "+collect.toString());

		//2 交易员都在哪些不同的城市工作过
		List<String> collect1 = transactions.stream().map(Transaction::getTrader)
				.map(Trader::getCity)
				.distinct()
				.collect(toList());
		System.out.println("2----: "+collect1.toString());

		//3 查找所有来自剑桥的交易员，并按照姓名排序
		List<Trader> collect2 = transactions.stream().map(Transaction::getTrader)
				.filter(trader -> trader.getCity().equals("Cambridge"))
				.distinct()
				.sorted(Comparator.comparing(Trader::getName))
				.collect(toList());
		System.out.println("3----: "+collect2.toString());

		//4 返回所有交易员的姓名字符串，按照字母排序
		String reduce = transactions.stream().map(Transaction::getTrader)
				.map(Trader::getName)
				.distinct()
				.sorted()
				.reduce("", (n1, n2) -> n1 + n2);
		System.out.println("4----: "+reduce);

		//5 有没有交易员是在米兰工作的
		boolean milan = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
		System.out.println("5----: "+milan);

		//6 打印生活在剑桥的交易员的所有交易额
		transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
				.map(Transaction::getValue)
				.forEach(System.out::println);

		//7 最高的交易额
		Optional<Integer> reduce1 = transactions.stream().map(Transaction::getValue)
				.reduce(Integer::max);
		System.out.println("7----: "+reduce1);

		//8 交易额最小的交易
		Optional<Transaction> min = transactions.stream().min(Comparator.comparing(Transaction::getValue));
		System.out.println("8---: "+min.toString());
	}

	public void testStreamFun(){
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
		forEach(list,(ii)->{
			System.out.println("accept--"+ii);
		});

		List<String> list2 = Arrays.asList("lambad", "class", "keyworld", "blacktea");
		List<Integer> map1 = map(list2, (String ss) -> {
			return ss.length();
		});
		System.out.println(map1.toString());
	}

	public static <T> void forEach(List<T> list, Consumer<T> c){
		for (T t : list) {
			c.accept(t);
		}
	}

	public static <T,R> List<R> map(List<T> list, Function<T,R> f){
		List<R> result = new ArrayList<>();
		for (T t : list) {
			result.add(f.apply(t));
		}
		return result;
	}



}
