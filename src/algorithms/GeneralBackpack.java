package algorithms;

public class GeneralBackpack {
	public static void main(String[] args) {
		float[] w = { 0, 10, 30, 20 };
		float[] v = { 0, 50, 120, 60 };
		float[] x = new float[4];
		float C = 50;
		int n = 4;
		float total = bag.Knapsack(w, v, x, C, n);
		System.out.println("总价值:" + total);
		for (int i = 1; i < x.length; i++) {
			System.out.printf("第%d个物品数量为:%f,价值为%f\n", i, x[i], x[i] * v[i]);
		}
	}
}

class bag {
	public static float Knapsack(float[] w, float[] v, float[] x, float C, int n) {
		/**
		 * n个物品已经按照V/W 由大到小 贪心策略：考虑价值增长和容量消耗二者的综合效果的方法，即每次选择价值与重量比u:/w
		 * 
		 * @param w: 重量
		 * @param v: 价值
		 * @param x: 解
		 * @param C: 背包重量
		 * @param n: 物品数量
		 * @return total 总价值
		 */
		for (int i = 0; i < n; i++) {
			x[i] = 0; // 初始化解向量
		}
		int i = 1; // 由于从1开始 所以w和v前面都要加0
		float total = 0; // 总重量
		while (w[i] < C) { // 可行函数feasible / 解决函数solution
			x[i] = 1;
			total = total + v[i]; // 解集合 —— 总价值
			C = C - w[i]; // 剩余背包重量
			i++; // 下一个物品
		}
		x[i] = C / w[i]; // 使用完剩余的一点空间
		total = total + v[i] * x[i];
		return total;
	}
}