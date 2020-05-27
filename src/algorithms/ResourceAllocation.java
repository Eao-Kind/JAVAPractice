package algorithms;

public class ResourceAllocation {

	public static void main(String[] args) {
		int[][] f = { { 0, 0, 0, 0, 0 }, // 没有第0个项目
				{ 0, 15, 28, 40, 51 }, { 0, 13, 29, 43, 55 }, { 0, 11, 30, 45, 58 }, };

		System.out.println("最大利润:"+SolutionInvest.investMax(f));
	}
}

class SolutionInvest {
	static int M = 4 + 1; // 投资总数+1，4万元， 因为多了一列0向量
	static int N = 3 + 1; // 项目数+1，3项目， 因为多了一行0向量

	public static int investMax(int[][] f) {
		/*
		 * 返回最大利润
		 * 
		 * @param f：投资矩阵
		 */

		int[][] Fsum = new int[N][M]; // 前N个项目共投资M万元的最大收益
		int[][] aItem = new int[N][M]; // 共投资M万元时第N个项目的投资金额，用于追溯路径

		// 初始化表格的情况，即边界情况,具体情况看可表格
		for (int i = 0; i < M - 1; i++) {
			Fsum[1][i] = f[1][i];
			aItem[1][i] = i;
		}

		// 第 k 个项目的投资
		for (int k = 2; k <= N - 1; k++) {
			// 第一行一列是0，第一行即只投资一个项目的情况已经初始化，故从2开始
			for (int m = 1; m <= M - 1; m++) { // M是投资数+1，所以此处循环到M-1
				// 前 K 个项目共分配 m 万元，计算出当投资m万元时候的最好方案
				int max = -1, temp = 0; // 初始化利润为-1
				for (int a = 0; a <= m; a++) {
					// 第 k 个项目分配 a 万元， 计算对比当投资额为m万元时候，第K个项目的最好投资数
					if (f[k][a] + Fsum[k - 1][m - a] > max) {
						// fk(x) = d 表示：x 万元投给第 k 个项目的效益为 d.
						// 对比 f2(1)+F1(0), f2(0)+F1(1) 得出好的投资方案
						max = f[k][a] + Fsum[k - 1][m - a];
						temp = a; // 记录总投资为m万元时第k个项目的投资金额
					}
				}
				Fsum[k][m] = max; // 前K个项目共投资m万元时候的最大收益
				aItem[k][m] = temp; // 记录投资m万元时候第k个项目的投资金额
			}
		}
		printArray(Fsum);
		printArray(aItem);
		printInfo(aItem); // 输出投资信息
		return Fsum[N - 1][M - 1];
	}

	private static void printInfo(int[][] aItem) {
		int index = M - 1;
		for (int k = N - 1; k > 0; k--) {
			System.out.println("第" + k + "个项目，投资" + aItem[k][index] + "万元");
			index -= aItem[k][index];
		}

	}

	private static void printArray(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.printf("%4d", a[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
