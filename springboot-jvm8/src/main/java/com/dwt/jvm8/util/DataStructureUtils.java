package com.dwt.jvm8.util;

import sun.jvm.hotspot.jdi.ArrayReferenceImpl;

/**
 * @Package: com.dwt.jvm8.util
 * @Description:
 * @Author: Sammy
 * @Date: 2020/11/3 12:06
 */

public class DataStructureUtils {
	public static void main(String[] args) {
		int[] arr = {1,2,3};
		permutation(arr,0);
		// permutation2(arr,3,3);
	}

	/**
	 *
	 * @param arr
	 * @param n 标记数组当前位置，当n=0时，对a[0]...a[length-1]全排序，当n=1时，对a[1]...a[length-1]全排序
	 */
	public static void permutation(int[] arr,int n){
		int length = arr.length;
		if (n>=length-1) {
			for (int i : arr) {
				System.out.print(i);
			}
			System.out.println();
			//
		}else {
			for (int i = n; i < length; i++) {
				{int temp = arr[n];
					arr[n] = arr[i];
					arr[i] = temp;}
				permutation(arr,n+1);
				{int temp = arr[n]; arr[n] = arr[i]; arr[i] = temp;}
			}
		}

	}

	public static void permutation2(int[] arr,int n,int k){
		if (k==1) {
			for (int i : arr) {
				System.out.print(i);
			}
			System.out.println();
		}else {
			for (int i = 0; i < k; i++) {
				{int temp = arr[i];
				 arr[i] = arr[k-1];
				arr[k-1] = temp;}
				permutation2(arr,n,k-1);
				{int temp = arr[i];
				 arr[i] = arr[k-1];
				arr[k-1] = temp;}
			}
		}
	}
}
