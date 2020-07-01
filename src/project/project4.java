package project;

public class project4 {
	public static void main(String[] args) {
		Genetic t = new Genetic();
		int times = 100, n = 50; // 实验次数，问题规模
		t.test(times, n);
	}
}

class Genetic {

	public int[] InitialSolution(int length) { // 初始解 随机 0/1
		int[] a = new int[length];
		for (int i = 0; i < length; i++)
			a[i] = (int) (Math.random() * 2); //
		return a;
	}

	public int[] variation(int[] a) { // 变异
		int[] newarray = new int[a.length];
		for (int i = 0; i < a.length; i++) // a[i]-1*（-1） 以1/length概率从0到1，1到0
			newarray[i] = (Math.random() < 1.0 / a.length) ? (a[i] - 1) * (-1) : a[i];
		return newarray;
	}

	public boolean eliminate(int[] a, int fx) { // 自然选择————判断是否保留x' （与所求函数相关）求和xi
		return arraySum(a) > fx;
	}

	public int genetic(int length) { // 返回 最优解值
		int[] a = InitialSolution(length); // 产生一个长度为length的随机解
		int fx = arraySum(a), count = 0, maxfx = length;
		while (count < 200) { // 200次自然选择
			int[] newarray = variation(a); // 变异
			if (eliminate(newarray, fx)) { // 选择
				fx = arraySum(newarray);
				a = newarray;
			}
			count++;
		}
		return fx;
	}

	public void test(int times, int length) {
		int count = 0;
		int[] countArray = new int[times];
		while (count < times) {
			int fx = genetic(length);
			System.out.printf("第%d次得到最优解为:%d\n", count + 1, fx);
			countArray[count++] = fx;
		}
		System.out.println("平均最优解为：" + arraySum(countArray) / times);
	}

	public int arraySum(int[] temp) {
		int sum = 0;
		for (int i = 0; i < temp.length; i++) {
			sum += temp[i];
		}
		return sum;
	}
}

/*
 *(1+1)EA是一种简单并且有效的演化算法,经常用于演化计算的理论分析中，它采用了变异和选择两种操作并且种群大小为1. 假设 是要最大化的目标函数或者适应值函数.(1+1)EA可以描述如下：

算法. (1+1) EA.
1：Begin
2：初始化：随机生成一个初始解 ,令 ；
3： While(终止条件不成立) Ｄo 
4：   以 的概率独立的翻转 的每一位 得到新解  （变异）
5:    如果 ,则 （选择）
6： End While
7: 返回  
8: End
这里采用(1+1)EA算法求解OneMax函数，OneMax函数定义是 。 可以看成一个位串， ，也就是说 或 
举例说明：
假如算法第2步中产生的随机解为 ，则 。
则第3步中while中的终止条件不成立。
在第4步中变异得到解 (也可能得到一个更差的解，比如 ，则在第5
步中会被舍弃)
在第5步中比较 
在第6步中结束本次while循环
因为没有得到最优解，所以回到第3步，继续循环。直到找到最优解，则停止循环，输出最优解 .

要求：采用(1+1)EA算法求解OneMax函数的最优解。本算法需要获得最优解的迭代次数 ， 是指while所做的迭代次数。考虑这是随机算法，求得一次最优解的 可能出现很大的随机性，所以需要做100次，得到迭代次数 的平均值. OneMax函数中n=50。n是问题的规模。
 
 */
