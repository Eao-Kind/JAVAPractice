package algorithms;


public class MathematicalTriangle {

	public static void main(String[] args) {
		int[][] a = { { 1 }, { 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 , 10} };

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.printf("%4d", a[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		int res = minimunTotal(a);

		System.out.println("min path : " + res);
	}

	public static int minimunTotal(int[][] triangle) {
		int row = triangle.length; // ��
		int column = triangle[row - 1].length; // ��

		int[][] dp = new int[row][column]; // ��������
		dp[0][0] = triangle[0][0];

		for (int i = 1; i < row; i++) {
			// ÿһ��Ԫ�ؽ����Ƶ�
			for (int j = 0; j <= i; j++) {
				if (j == 0) {
					// ����˴���
					dp[i][j] = dp[i - 1][j] + triangle[i][j];
				} else if (j == i) {
					// ���Ҷ˴��� û������
					dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
				} else {
					dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
				}
			}
		}

		int res = Integer.MAX_VALUE;
		for (int i = 0; i < column; i++) {
			res = Math.min(res, dp[row - 1][i]);
		}

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				System.out.printf("%4d", dp[i][j]);
			}
			System.out.println();
		}
		return res;
	}
}