package lc105;

import java.util.Arrays;

public class Solution {

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder == null || preorder.length == 0) {
			return null;
		}
		int root = preorder[0];
		TreeNode node = new TreeNode(root);
		int devider = getIndex(root, inorder);

		int[] left = null;
		int[] right = null;

		if (devider != 0) {
			left = Arrays.copyOfRange(inorder, 0, devider);
		}
		if (devider != inorder.length - 1) {
			right = Arrays.copyOfRange(inorder, devider + 1, inorder.length);
		}
		if (left != null && left.length != 0) {
			if (left.length == 1) {
				node.left = new TreeNode(left[0]);
			} else {
				node.left = buildTree(
						Arrays.copyOfRange(preorder, 1, 1 + left.length), left);
			}
		}
		if (right != null && right.length != 0) {
			if (right.length == 1) {
				node.right = new TreeNode(right[0]);
			} else {
				
				node.right = buildTree(Arrays.copyOfRange(preorder, preorder.length-right.length, preorder.length), right);
			}
		}
		return node;
	}

	public static void main(String[] args) {
		int[] a = {1,2,3};
		Solution solution = new Solution();
		solution.buildTree(a,a);
	}

	public int getIndex(int value, int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}

}