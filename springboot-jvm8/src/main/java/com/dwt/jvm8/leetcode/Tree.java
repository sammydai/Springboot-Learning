package com.dwt.jvm8.leetcode;

import apple.laf.JRSUIUtils;
import com.sun.corba.se.impl.protocol.giopmsgheaders.LocateReplyMessage_1_0;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.dwt.jvm8.leetcode
 * @Description:
 * @Author: Sammy
 * @Date: 2020/11/7 13:30
 */

public class Tree {
	public static void main(String[] args) {
		TreeNode cc = new TreeNode(1, null,null);
		TreeNode aa = new TreeNode(23, null,cc);
		TreeNode bb = new TreeNode(8, null,null);
		// TreeNode dd = new TreeNode(72,null,null);
		TreeNode rl = new TreeNode(9,aa,bb);
		TreeNode rr =new TreeNode(20,null,null);
		// TreeNode t1 = new TreeNode(15,null,null);
		// TreeNode t2 = new TreeNode(7,null,null);
		// TreeNode rl = new TreeNode(9,null,null);
		// TreeNode rr = new TreeNode(20,t1,t2);
		TreeNode root = new TreeNode(3, rl,rr);

		// depthFirstSearch(root);
		System.out.println("");
		System.out.println("------------");
		// breadFirstSearch(root);
		System.out.println(breadFirstSearch1(root));
		// System.out.println(minDepth(root));
		// printAllRootToLeafPaths(root,new ArrayList());
	}

	/**
	 * 求最小深度
	 * 递归算法：Min（左子树最小深度，右子树最小深度）+1
	 * 递归结束条件，到了叶子节点
	 * 如果左子树的深度为0，不能取min（0，右子树）+1 =1
	 * 只有右子树的情况下，直接取右子树的最小值+1
	 * return RightNum+1;
	 * @param root
	 * @return
	 */
	public static int minDepth(TreeNode root) {
		if (root==null){
			return 0;
		}
		if (root.left==null&&root.right==null){
			return 1;
		}
		int minResult = Integer.MAX_VALUE;
		int LeftNum = minDepth(root.left);
		int RightNum = minDepth(root.right);
		if (LeftNum == 0){
			return RightNum+1;
		}
		if (RightNum==0){
			return LeftNum+1;
		}
		return Math.min(LeftNum,RightNum)+1;
	}

	public static int minDepth1(TreeNode root) {
		if (root==null){
			return 0;
		}
		int minResult = 1;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (node.left==null&&node.right==null) {
					return minResult;
				}
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			minResult++;
		}
		return minResult;
	}

	/**
	 * 深度遍历树dfs
	 * @param root
	 */
	public static void depthFirstSearch(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		TreeNode node = null;
		while (!stack.empty()) {
			// node = stack.pop();
			node = stack.pop();
			System.out.print(node.val+" ");//遍历根结点
			if (node.right != null) {
				stack.push(node.right);//先将右子树压栈
			}
			if (node.left != null) {
				stack.push(node.left);//再将左子树压栈
			}
		}
	}

	/**
	 * 广度遍历树bfs
	 * @param root
	 */
	public static void breadFirstSearch(TreeNode root){
		if (root ==null) return;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			System.out.println(node.val+" ");
			if (node.left!=null){
				queue.offer(node.left);
			}
			if (node.right!=null){
				queue.offer(node.right);
			}
		}
	}

	public static List<List<Integer>> breadFirstSearch1(TreeNode root){
		if (root ==null) return null;
		List<List<Integer>> result = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			List<Integer> tempResult = new ArrayList<>();
			for (int i = queue.size(); i >0; i--) {
				TreeNode node = queue.poll();
				tempResult.add(node.val);
				if (node.left!=null){
					queue.offer(node.left);
				}
				if (node.right!=null){
					queue.offer(node.right);
				}
			}
			result.add(tempResult);
		}
		return result;
	}

	/**
	 * 打印所有子树分支
	 * @param root
	 * @return [[3, 9, 23, 1], [3, 9, 8], [3, 20]]
	 */
	public static List<List<Integer>> searchTree(TreeNode root){
		if (root ==null) return null;
		List<List<Integer>> result = new ArrayList<>();
		if (root.left ==null && root.right==null){
			result.add(Arrays.asList(root.val));
			return result;
		}
		helper(root,result,"");
		return result;
	}

	public static void helper(TreeNode treeNode,List<List<Integer>> result,String curr){
		if (treeNode.right==null && treeNode.left==null){
			List<Integer> tempResult = new ArrayList<>();
			String[] vals = curr.split(",");
			for (String val : vals) {
				tempResult.add(Integer.parseInt(val));
			}
			tempResult.add(treeNode.val);
			result.add(tempResult);
			return;
		}
		if (treeNode.left!=null){
			helper(treeNode.left,result,curr+treeNode.val+",");
		}
		if (treeNode.right!=null){
			helper(treeNode.right,result,curr+treeNode.val+",");
		}
	}

	/**
	 * 打印所有子树
	 * @param node
	 * @param path
	 */
	public static void printAllRootToLeafPaths(TreeNode node,ArrayList path)
	{
		if(node==null)
		{
			return;
		}
		path.add(node.val);

		if(node.left==null && node.right==null)
		{
			System.out.println(path);
			return;
		}
		else
		{
			printAllRootToLeafPaths(node.left,new ArrayList(path));
			printAllRootToLeafPaths(node.right,new ArrayList(path));
		}
	}
}
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode() {}
	TreeNode(int val) { this.val = val; }
	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
