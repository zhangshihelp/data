package binarytree;

public class SearchBinaryTree {

	public static void main(String[] args) {
		SearchBinaryTree binaryTree = new SearchBinaryTree();
		int[] intArray = new int[] { 50, 30, 20, 44, 88, 33, 87, 16, 7, 77 };
		for (int i : intArray) {
			binaryTree.put(i);
		}
		binaryTree.midOrder(binaryTree.root);
		try {
			binaryTree.deleteNode(50);
			binaryTree.midOrder(binaryTree.root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private TreeNode root;

	public SearchBinaryTree() {

	}

	public void midOrder(TreeNode node) {
		if (node == null) {
			return;
		} else {
			midOrder(node.leftChild);
			System.out.println(node.data);
			midOrder(node.rightChild);
		}
	}

	/**
	 * 创建查找二叉树,添加节点
	 */
	public TreeNode put(int data) {
		TreeNode node = null;
		TreeNode parent = null;
		node = new TreeNode(0, data);
		if (root == null) {
			root = node;
		}
		node = root;
		while (node != null) {
			parent = node;
			if (data > node.data) {
				node = node.rightChild;
			} else if (data < node.data) {
				node = node.leftChild;
			} else {
				return node;
			}
		}
		// 表示将此节点添加到相应位置
		node = new TreeNode(0, data);
		if (data < parent.data) {
			parent.leftChild = node;
		} else {
			parent.rightChild = node;
		}
		node.parent = parent;
		return node;
	}

	/**
	 * 删除节点
	 * 
	 * @author zs
	 * @throws Exception
	 *
	 */
	public void deleteNode(int key) throws Exception {
		TreeNode node = searchNode(key);
		if (node == null) {
			throw new Exception("该节点无法找到");
		} else {
			// 删除该节点
			delete(node);
		}
	}

	private void delete(TreeNode node) throws Exception {
		if (node == null) {
			throw new Exception("该节点无法找到");
		} else {
			TreeNode parent = node.parent;
			// 被删除的节点无左右孩子
			if (node.leftChild == null && node.rightChild == null) {
				if (parent.leftChild == node) {
					parent.leftChild = null;
				} else {
					parent.rightChild = null;
				}
				return;
			}

			// 被删除的节点有左无右
			if (node.leftChild != null && node.rightChild == null) {
				if (parent.leftChild == node) {
					parent.leftChild = node.leftChild;
				} else {
					parent.rightChild = node.leftChild;
				}
				return;
			}

			// 被删除节点有右无左
			if (node.leftChild == null && node.rightChild != null) {
				if (parent.leftChild == node) {
					parent.leftChild = node.rightChild;
				} else {
					parent.rightChild = node.rightChild;
				}
				return;
			}

			// 左右孩子都有
			// 找到该节点的后继节点
			TreeNode next = getNextNode(node);
			delete(next);
			node.data = next.data;
		}
	}

	/**
	 * 获取一个节点的后继节点
	 * @param node
	 * @return
	 */
	private TreeNode getNextNode(TreeNode node) {
		if (node == null) {
			return null;
		} else {
			if (node.rightChild != null) {
				// 找某节点最小关键字节点
				return getMinTreeNode(node.rightChild);
			} else {
				TreeNode parent = node.parent;
				while (parent != null && node == parent.rightChild) {
					node = parent;
					parent = parent.parent;
				}
				return parent;
			}
		}
	}

	private TreeNode getMinTreeNode(TreeNode node) {
		if (node == null) {
			return null;
		} else {
			while (node.leftChild != null) {
				node = node.leftChild;
			}
		}
		return node;
	}

	private TreeNode searchNode(int key) {
		TreeNode node = root;
		if (node == null) {
			return null;
		} else {
			while (node != null && key != node.data) {
				if (key < node.data) {
					node = node.leftChild;
				} else {
					node = node.rightChild;
				}
			}
		}
		return node;
	}

	class TreeNode {
		private int key;
		private TreeNode leftChild;
		private TreeNode rightChild;
		private TreeNode parent;
		private int data;

		public TreeNode(int key, int data) {
			super();
			this.key = key;
			this.data = data;
			this.leftChild = null;
			this.rightChild = null;
			this.parent = null;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public TreeNode getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(TreeNode leftChild) {
			this.leftChild = leftChild;
		}

		public TreeNode getRightChild() {
			return rightChild;
		}

		public void setRightChild(TreeNode rightChild) {
			this.rightChild = rightChild;
		}

		public TreeNode getParent() {
			return parent;
		}

		public void setParent(TreeNode parent) {
			this.parent = parent;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

	}
}
