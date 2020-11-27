package com.learning.jdk8.leetcode;

import java.util.*;

/**
 * @Package: com.dwt.jvm8.leetcode
 * @Description:
 * @Author: Sammy
 * @Date: 2020/11/7 10:47
 */

public class TwoSum {
	public static void main(String[] args) {
		int[] test ={3,1,2,10,1};
		// int[] ints = runningSum(test);
		// int[] ints = twoSum(test, 6);
		// Arrays.stream(ints).forEach(System.out::println);
		// helper("dog cat cat dog");
		String ss ="adaia";
		int i = ss.indexOf("a", 1);
		System.out.println(i);
	}




	public static void helper(String s){
		String[] split = s.split(" ");
		Map<String, List<Integer>> sMap = new HashMap<>();
		for (int i = 0; i < split.length; i++) {
			String tempKey = split[i];
			if (sMap.containsKey(tempKey)){
				sMap.get(tempKey).add(i);
			}else {
				List<Integer> tempIndices = new ArrayList<>();
				tempIndices.add(i);
				sMap.put(split[i], tempIndices);
			}
		}
		Map<String, List<Integer>> pMap = new HashMap<>();

	}

}
