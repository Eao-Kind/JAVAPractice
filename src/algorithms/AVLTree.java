package algorithms;

public class AVLTree<Key extends Comparable<? super Key>, Value> {
	private class Node {
		Key key;// �����൱�ڴʵ���ĵ���
		Value value;// ֵ���൱�ڴʵ���ĵ��ʽ���
		int height;// ���ĸ߶�
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

	private Node root; // ���ڵ�

	public AVLTree() {
		root = null;
	}

	// ��ȡ�ڵ�߶�
	private int height(Node node) {
		if (node != null) {
			return node.height;
		}
		return 0;
	}

	// ��ȡ���߶�
	public int height() {
		return height(root);
	}

	private int max(int a, int b) {
		return a > b ? a : b; // ��ȡ�ϴ�ֵ
	}

	// �滻�ڵ㣿
	private void replaceNode(Node src, Node tar) {
		tar.key = src.key;
		tar.value = src.value;
	}

	// ����----------------------------------------------------
	// �������
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

	// �������
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

	// �������
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

	// ����keyλ��
	private Node search(Node node, Key key) {
		if (node == null) {
			return null;
		} else if (key.compareTo(node.key) == 0) {
			return node;
		} else if (key.compareTo(node.key) < 0) {
			return search(node.left, key); // ������
		} else {
			// key.compareTo(node.key) > 0
			return search(node.right, key); // ������
		}
	}

	public Node Search(Key key) {
		return search(root, key);
	}

	// ������С��ڵ�
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

	// ��������ҽڵ�
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

	// ��ת----------------------------------------------------
	public Node leftLeftRotation(Node k1) {
		/*
		 * LL����ת --- ����ת
		 */
		Node k2 = k1.left; // ʧ�����������k2
		k1.left = k2.right; // k2����������Ϊ ʧ���k1��������
		k2.right = k1; // k2����������Ϊk1
		// ���¼���������ĸ߶�
		k1.height = max(height(k1.left), height(k1.right)) + 1;
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		return k2; // ����ƽ���ĵ�k2
	}

	public Node rightRightRotation(Node k1) {
		/*
		 * RR����ת --- ����ת
		 */
		Node k2 = k1.right; // ʧ�����������k2
		k1.right = k2.left; // k2����������Ϊ ʧ���k1��������
		k2.left = k1; // k2����������Ϊk1
		// ���¼���������ĸ߶�
		k1.height = max(height(k1.left), height(k1.right)) + 1;
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		return k2; // ����ƽ���ĵ�k2
	}

	public Node leftRightRotation(Node k1) {
		/*
		 * LR˫��ת
		 */
		k1.left = rightRightRotation(k1.left); // ����
		return leftLeftRotation(k1); // ����
	}

	public Node rightLeftRotation(Node k1) {
		/*
		 * RL˫��ת
		 */
		k1.right = leftLeftRotation(k1.right); // ����
		return rightRightRotation(k1); // ����
	}
	// -----------------------------------------------------

	// ���벢����Ϊƽ����
	private Node insert(Node node, Key key, Value value) {
		if (node == null)
			return new Node(key, value);

		if (key.compareTo(node.key) == 0) {// key��ͬ�����½ڵ�
			node.value = value;
		} else if (key.compareTo(node.key) < 0) {
			node.left = insert(node.left, key, value); // �ݹ���������
			if (height(node.left) - height(node.right) == 2) {
				// ��ƽ�⣬��Ҫ���е���
				if (key.compareTo(node.left.key) < 0) {
					node = leftLeftRotation(node); // �Ƚڵ����keyС��LL���
				} else {
					node = leftRightRotation(node); // �Ƚڵ����keyС,RR���
				}
			}
		} else {
			node.right = insert(node.right, key, value); // �ݹ���������
			if (height(node.right) - height(node.left) == 2) {
				if (key.compareTo(node.right.key) > 0) {
					node = rightRightRotation(node); // �Ƚڵ����key��RR���
				} else {
					node = rightLeftRotation(node);
				}
			}
		}
		node.height = max(height(node.right), height(node.left)) + 1; // �����߶�
		return node; // ���ظ��ڵ�
	}

	public void insert(Key key, Value value) {
		this.root = insert(this.root, key, value);
	}

	// ɾ��
	public Node remove(Node node, Node target) {
		/**
		 * @param node ��ǰ�������ڵ�
		 * @parma target Ҫɾ���Ľڵ� return ɾ������µ�������
		 */
		if (node == null || target == null)
			return node; // �����
		if (target.key.compareTo(node.key) < 0) {
			node.left = remove(node.left, target); // ������������
			if (Math.abs(height(node.right) - height(node.left)) == 2) { // ʧ��
				if (height(node.right.left) < height(node.right.right)) {
					node = rightRightRotation(node); // ����
				} else {
					node = leftLeftRotation(node); // ����
				}
			}
		} else if (target.key.compareTo(node.key) > 0) {
			node.right = remove(node.right, target); // ������������
			if (Math.abs(height(node.left) - height(node.right)) == 2) {// ʧ��
				if (height(node.left.right) < height(node.left.left)) {
					node = leftLeftRotation(node); // ����
				} else {
					node = rightRightRotation(node); // ����
				}
			}
		} else {// node.key == target.key
			if (node.left == null) {
				return node.right; // ������null����ôɾ������������Ϊ��
			} else if (node.right == null) {
				return node.left; // ������null����ôɾ������������Ϊ��
			} else {
				// �������� ������
				if (height(node.left) > height(node.right)) {
					// ������ ���� ������
					Node predecessor = maxNode(node.left);
					replaceNode(predecessor, node); // �滻
					node.left = remove(node.left, predecessor);
				} else {// �������������������(һ����Ļ�����ν��)
					Node successor = minNode(node.right);// ��node�ĺ�̽��successor
					replaceNode(successor, node);// successor�滻node
					node.right = remove(node.right, successor);// �ٰ�ԭ����successorɾ��
				}

			}

		}
		return node;
	}

	public void remove(Key key) {
		Node z;
		if ((z = search(root, key)) != null) // �ҵ�keyλ��
			root = remove(root, z);
	}

	// ����AVL��----------------------------------------------
	private void destroy(Node node) {
		if (node == null)
			return;
		if (node.left != null)
			destroy(node.left); // �ݻ�������
		if (node.right != null)
			destroy(node.right); // �ݻ�������
		node = null;
	}

	public void destroy() {
		destroy(root);
		System.out.println("�������");
	}
	// ---------------------------------------------------------

	// �ݹ����AVL������Ϣ------------------------------------------------
	private void print(Node tree, Key key, String pos) {
		if (tree != null) {
			if (pos.equals("")) // tree�Ǹ��ڵ�
				System.out.printf("%2d is root\n", tree.key);
			else
				System.out.printf("%2d is %2d's %6s child\n", tree.key, key, pos);
			print(tree.left, tree.key, "left"); // ������
			print(tree.right, tree.key, "right"); // ������
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

		System.out.printf("*******�������: ");
		for (i = 0; i < arr.length; i++) {
			System.out.printf("%d ", arr[i]);
			tree.insert(arr[i], arr[i]);
		}

		System.out.println("\nǰ�����");
		tree.preOrder();

		System.out.println("\n�������");
		tree.InOrder();

		System.out.println("\n�������");
		tree.postOrder();

		System.out.println("\n�߶� : " + tree.height());
		System.out.println("��Сֵ: " + tree.minNode().key);
		System.out.println("���ֵ: " + tree.maxNode().key);
		System.out.println("\n������ϸ��Ϣ��");
		tree.print();

		i = 5;
		System.out.printf("\nɾ���ڵ� �� %d", i);
		tree.remove(i);

		System.out.println("���ĸ߶� : " + tree.height());
		System.out.print("�������: ");
		tree.InOrder();
		System.out.println("\n������ϸ��Ϣ: ");
		tree.print();

		tree.destroy(); // ������
	}
}