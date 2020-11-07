package com.dwt.jvm8.leetcode;

import com.sun.tools.corba.se.idl.toJavaPortable.ValueGen24;
import sun.jvm.hotspot.oops.BranchData;
import sun.jvm.hotspot.tools.PMap;

import javax.security.auth.kerberos.KerberosKey;
import java.lang.reflect.ParameterizedType;
import java.security.Key;
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

	public static int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> hashMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			hashMap.put(nums[i],i);
		}
		for (int i = 0; i < nums.length; i++) {
			int temp = target - nums[i];
			if (hashMap.containsKey(temp) && (hashMap.get(temp)!=i)){
				return new int[]{i,hashMap.get(temp)};
			}
		}
		return new int[]{};
	}

	/**
	 * Input: nums = [1,2,3,4]
	 *	Output: [1,3,6,10]
	 *	Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
	 * @param nums
	 * @return
	 */
	public static int[] runningSum(int[] nums) {
		int[] result = new int[nums.length];
		if (nums.length==1||nums.length==0||nums==null){
			return nums;
		}
		result[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			result[i] = result[i-1]+nums[i];
		}
		return result;
	}

	public boolean wordPattern(String pattern, String s) {
		String[] split = s.split(" ");
		if (split.length!=pattern.length()) {
			return false;
		}
		Map<Character, String> map = new HashMap<Character, String>();
		for (int i = 0; i < pattern.length(); i++) {
			if (!map.containsKey(pattern.charAt(i))) {
				if (map.containsValue(split[i])){
					return false;
				}
				map.put(pattern.charAt(i),split[i]);
			}else {
				if (!map.get(pattern.charAt(i)).equals(split[i])) {
					return false;
				}
			}
		}
		return true;
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
