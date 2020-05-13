package algorithms;

public class AVLTree<Key extends Comparable<? super Key>, Value> {
	private class Node {
		Key key;// 键，相当于词典里的单词
		Value value;// 值，相当于词典里的单词解释
		int height;// 结点的高度
		Node left;
		Node right;

		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
			int height = 1;
		}
	}

	private Node root; // 根节点

	public AVLTree() {
		root = null;
	}

	// 获取节点高度
	private int height(Node node) {
		if (node != null) {
			return node.height;
		}
		return 0;
	}

	// 获取根高度
	public int height() {
		return height(root);
	}

	private int max(int a, int b) {
		return a > b ? a : b; // 获取较大值
	}

	// 替换节点？
	private void replaceNode(Node src, Node tar) {
		tar.key = src.key;
		tar.value = src.value;
	}

	// 遍历----------------------------------------------------
	// 先序遍历
	private void preOrder(Node node) {
		if (node != null) {
			System.out.print(node.key + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	public void preOrder() {
		preOrder(root);
	}

	// 中序遍历
	private void inOrder(Node node) {
		if (node != null) {
			inOrder(node.left);
			System.out.print(node.key + " ");
			inOrder(node.right);
		}
	}

	public void InOrder() {
		inOrder(root);
	}

	// 后序遍历
	private void postOrder(Node node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.key + " ");
		}
	}

	public void postOrder() {
		postOrder(root);
	}
	// -------------------------------------------------------

	// 查找key位置
	private Node search(Node node, Key key) {
		if (node == null) {
			return null;
		} else if (key.compareTo(node.key) == 0) {
			return node;
		} else if (key.compareTo(node.key) < 0) {
			return search(node.left, key); // 左子树
		} else {
			// key.compareTo(node.key) > 0
			return search(node.right, key); // 右子树
		}
	}

	public Node Search(Key key) {
		return search(root, key);
	}

	// 返回最小左节点
	private Node minNode(Node node) {
		if (node == null) {
			return null;
		} else if (node.left == null) {
			return node;
		} else {
			return minNode(node.left);
		}
	}

	public Node minNode() {
		return minNode(root);
	}

	// 返回最大右节点
	private Node maxNode(Node node) {
		if (node == null) {
			return null;
		} else if (node.right == null) {
			return node;
		} else {
			return minNode(node.right);
		}
	}

	public Node maxNode() {
		return maxNode(root);
	}

	// 旋转----------------------------------------------------
	public Node leftLeftRotation(Node k1) {
		/*
		 * LL单旋转 --- 右旋转
		 */
		Node k2 = k1.left; // 失衡点左子树是k2
		k1.left = k2.right; // k2的右子树变为 失衡点k1的左子树
		k2.right = k1; // k2的右子树变为k1
		// 重新计算各子树的高度
		k1.height = max(height(k1.left), height(k1.right)) + 1;
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		return k2; // 返回平衡后的点k2
	}

	public Node rightRightRotation(Node k1) {
		/*
		 * RR单旋转 --- 左旋转
		 */
		Node k2 = k1.right; // 失衡点左子树是k2
		k1.right = k2.left; // k2的右子树变为 失衡点k1的左子树
		k2.left = k1; // k2的右子树变为k1
		// 重新计算各子树的高度
		k1.height = max(height(k1.left), height(k1.right)) + 1;
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		return k2; // 返回平衡后的点k2
	}

	public Node leftRightRotation(Node k1) {
		/*
		 * LR双旋转
		 */
		k1.left = rightRightRotation(k1.left); // 左旋
		return leftLeftRotation(k1); // 右旋
	}

	public Node rightLeftRotation(Node k1) {
		/*
		 * RL双旋转
		 */
		k1.right = leftLeftRotation(k1.right); // 左旋
		return rightRightRotation(k1); // 右旋
	}
	// -----------------------------------------------------

	// 插入并调整为平衡树
	private Node insert(Node node, Key key, Value value) {
		if (node == null)
			return new Node(key, value);

		if (key.compareTo(node.key) == 0) {// key相同，更新节点
			node.value = value;
		} else if (key.compareTo(node.key) < 0) {
			node.left = insert(node.left, key, value); // 递归找左子树
			if (height(node.left) - height(node.right) == 2) {
				// 不平衡，需要进行调整
				if (key.compareTo(node.left.key) < 0) {
					node = leftLeftRotation(node); // 比节点的左key小，LL情况
				} else {
					node = leftRightRotation(node); // 比节点的右key小,RR情况
				}
			}
		} else {
			node.right = insert(node.right, key, value); // 递归找右子树
			if (height(node.right) - height(node.left) == 2) {
				if (key.compareTo(node.right.key) > 0) {
					node = rightRightRotation(node); // 比节点的右key大，RR情况
				} else {
					node = rightLeftRotation(node);
				}
			}
		}
		node.height = max(height(node.right), height(node.left)) + 1; // 调整高度
		return node; // 返回根节点
	}

	public void insert(Key key, Value value) {
		this.root = insert(this.root, key, value);
	}

	// 删除
	public Node remove(Node node, Node target) {
		/**
		 * @param node 当前子树根节点
		 * @parma target 要删除的节点 return 删除后的新的子树根
		 */
		if (node == null || target == null)
			return node; // 空情况
		if (target.key.compareTo(node.key) < 0) {
			node.left = remove(node.left, target); // 左子树继续找
			if (Math.abs(height(node.right) - height(node.left)) == 2) { // 失衡
				if (height(node.right.left) < height(node.right.right)) {
					node = rightRightRotation(node); // 左旋
				} else {
					node = leftLeftRotation(node); // 右旋
				}
			}
		} else if (target.key.compareTo(node.key) > 0) {
			node.right = remove(node.right, target); // 右子树继续找
			if (Math.abs(height(node.left) - height(node.right)) == 2) {// 失衡
				if (height(node.left.right) < height(node.left.left)) {
					node = leftLeftRotation(node); // 右旋
				} else {
					node = rightRightRotation(node); // 左旋
				}
			}
		} else {// node.key == target.key
			if (node.left == null) {
				return node.right; // 左子树null，那么删除后右子树变为根
			} else if (node.right == null) {
				return node.left; // 右子树null，那么删除后左子树变为根
			} else {
				// 左右子树 均存在
				if (height(node.left) > height(node.right)) {
					// 左子树 深于 右子树
					Node predecessor = maxNode(node.left);
					replaceNode(predecessor, node); // 替换
					node.left = remove(node.left, predecessor);
				} else {// 如果右子树比左子树深(一样深的话无所谓了)
					Node successor = minNode(node.right);// 找node的后继结点successor
					replaceNode(successor, node);// successor替换node
					node.right = remove(node.right, successor);// 再把原来的successor删掉
				}

			}

		}
		return node;
	}

	public void remove(Key key) {
		Node z;
		if ((z = search(root, key)) != null) // 找到key位置
			root = remove(root, z);
	}

	// 销毁AVL树----------------------------------------------
	private void destroy(Node node) {
		if (node == null)
			return;
		if (node.left != null)
			destroy(node.left); // 摧毁左子树
		if (node.right != null)
			destroy(node.right); // 摧毁右子树
		node = null;
	}

	public void destroy() {
		destroy(root);
		System.out.println("销毁完毕");
	}
	// ---------------------------------------------------------

	// 递归输出AVL树的信息------------------------------------------------
	private void print(Node tree, Key key, String pos) {
		if (tree != null) {
			if (pos.equals("")) // tree是根节点
				System.out.printf("%2d is root\n", tree.key);
			else
				System.out.printf("%2d is %2d's %6s child\n", tree.key, key, pos);
			print(tree.left, tree.key, "left"); // 左子树
			print(tree.right, tree.key, "right"); // 右子树
		}
	}

	public void print() {
		if (root != null)
			print(root, root.key, "");
	}
	// -----------------------------------------------------------

	private static int arr[] = { 3, 2, 1, 4, 5, 6, 7, 16, 15, };

	public static void main(String[] args) {
		int i;
		AVLTree<Integer, Integer> tree = new AVLTree<>();

		System.out.printf("*******依次添加: ");
		for (i = 0; i < arr.length; i++) {
			System.out.printf("%d ", arr[i]);
			tree.insert(arr[i], arr[i]);
		}

		System.out.println("\n前序遍历");
		tree.preOrder();

		System.out.println("\n中序遍历");
		tree.InOrder();

		System.out.println("\n后序遍历");
		tree.postOrder();

		System.out.println("\n高度 : " + tree.height());
		System.out.println("最小值: " + tree.minNode().key);
		System.out.println("最大值: " + tree.maxNode().key);
		System.out.println("\n树的详细信息：");
		tree.print();

		i = 5;
		System.out.printf("\n删除节点 ： %d", i);
		tree.remove(i);

		System.out.println("树的高度 : " + tree.height());
		System.out.print("中序遍历: ");
		tree.InOrder();
		System.out.println("\n树的详细信息: ");
		tree.print();

		tree.destroy(); // 销毁树
	}
}