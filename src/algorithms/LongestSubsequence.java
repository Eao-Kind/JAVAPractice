package algorithms;

import java.util.Arrays;
import java.util.Collections;

public class LongestSubsequence {
	public static void main(String[] args) {
		int[] nums = { 5, 8, 9, 2, 3, 1, 7, 4, 6 };
		int n = Solution.lengthOfLIS(nums);
		System.out.println("\n最长子序长度：" + n);
	}
}

class Solution {

	public static int lengthOfLIS(int[] nums) {
		int len = nums.length, max = 0;
		int[] dp = new int[len];
		Arrays.fill(dp, 1); // 初始化为1

		for (int i = 1; i < len; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i] && dp[i] < dp[j] + 1) { // 前面有比我小的吗？
					dp[i] = dp[j] + 1; // 有的，那就取当前比较大的状态
				}
			}
			if (max < dp[i]) {
				max = dp[i];
			}
		}

		int[] arr = new int[max];
		System.out.print("最长子序：");
		for (int i = nums.length - 1; i >= 0; i--) {
			if (dp[i] == max) {
				arr[max - 1] = nums[i];
				max--;
			}
		}
		for (int i : arr) {
			System.out.print(i + " ");
		}

		int res = 0;
		for (int i = 0; i < len; i++) {
			res = Math.max(res, dp[i]);
		}
		return res;
	}
}