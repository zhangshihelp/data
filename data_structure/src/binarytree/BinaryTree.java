package binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class BinaryTree {

	private TreeNode root = null;

	public BinaryTree() {
		root = new TreeNode(1, "A");
	}

	/**
	 * 构建二叉树 
	 * 			A 
	 * 		 B     C 
	 * 	   D   E     F
	 */
	public void createBinaryTree() {
		TreeNode nodeB = new TreeNode(2, "B");
		TreeNode nodeC = new TreeNode(3, "C");
		TreeNode nodeD = new TreeNode(4, "D");
		TreeNode nodeE = new TreeNode(5, "E");
		TreeNode nodeF = new TreeNode(6, "F");
		root.leftchild = nodeB;
		root.rightchild = nodeC;
		nodeB.leftchild = nodeD;
		nodeB.rightchild = nodeE;
		nodeC.rightchild = nodeF;
	}

	/**
	 * 求二叉树的高度
	 * 
	 * @author zs
	 *
	 */
	public int getHeight() {
		return getHeight(root);
	}

	private int getHeight(TreeNode node) {
		if (node == null) {
			return 0;
		} else {
			int i = getHeight(node.leftchild);
			int j = getHeight(node.rightchild);
			return (i < j) ? j + 1 : i + 1;
		}
	}

	/**
	 * 获取二叉树的节点数
	 * 
	 * @author zs
	 *
	 */
	public int getSize() {
		return getSize(root);
	}

	private int getSize(TreeNode node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + getSize(node.leftchild) + getSize(node.rightchild);
		}
	}
	
	/**
	 * 通过前序遍历的数据序列反向生成二叉树
	 * 			 A
	 * 		B		 C
	 * 	  D	  E	   #   F
	 *  #  # #  #     # #
	 * 
	 * ABD##E##C#F##
	 */
	public void createBinaryTreePre(ArrayList<String> data) {
		createBinaryTree(data.size(),data);
	}

	private TreeNode createBinaryTree(int size, ArrayList<String> data) {
		if(data.size() == 0) {
			return null;
		}
		String d = data.get(0);
		TreeNode node;
		int index = size - data.size();
		if(d.equals("#")) {
			node = null;
			data.remove(0);
			return node;
		}
		node = new TreeNode(index, d);
		if(index == 0) {
			// 创建根节点
			root = node;
		}
		data.remove(0);
		node.leftchild = createBinaryTree(size, data);
		node.rightchild = createBinaryTree(size, data);
		
		return node;
	}

	/**
	 * 前序遍历--迭代
	 * 
	 * @author zs
	 *
	 */
	public void preOrder(TreeNode node) {
		if (node == null) {
			return;
		} else {
			System.out.println("preOrder data:" + node.getData());
			preOrder(node.leftchild);
			preOrder(node.rightchild);
		}
	}

	/**
	 * 前序遍历--非迭代
	 */
	public void nonRecOrder(TreeNode node) {
		if (node == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(node);
		while (!stack.isEmpty()) {
			// 出栈和进栈
			TreeNode n = stack.pop(); // 弹出根节点
			// 压入子节点
			System.out.println("nonRecOrder data:" + n.getData());
			if (n.rightchild != null) {
				stack.push(n.rightchild);
			}
			if (n.leftchild != null) {
				stack.push(n.leftchild);
			}
		}
	}

	/**
	 * 中序遍历--迭代
	 * 
	 * @author zs
	 *
	 */
	public void midOrder(TreeNode node) {
		if (node == null) {
			return;
		} else {
			midOrder(node.leftchild);
			System.out.println("midOrder data:" + node.getData());
			midOrder(node.rightchild);
		}
	}

	/**
	 * 后序遍历--迭代
	 * 
	 * @author zs
	 *
	 */
	public void postOrder(TreeNode node) {
		if (node == null) {
			return;
		} else {
			postOrder(node.leftchild);
			postOrder(node.rightchild);
			System.out.println("postOrder data:" + node.getData());
		}
	}

	public class TreeNode {
		private int index;
		private String data;
		private TreeNode leftchild;
		private TreeNode rightchild;
		private TreeNode parent;

		public TreeNode getParent() {
			return parent;
		}

		public void setParent(TreeNode parent) {
			this.parent = parent;
		}

		public int getIndex() {
			return index;
		}
		
		public void setIndex(int index) {
			this.index = index;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public TreeNode(int index, String data) {
			this.index = index;
			this.data = data;
			this.leftchild = null;
			this.rightchild = null;
		}
	}

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree();
		// binaryTree.createBinaryTree();
		// int height = binaryTree.getHeight();
		// System.out.println("treeHeight:" + height);
		// int size = binaryTree.getSize();
		// System.out.println("treeSize:" + size);
		// binaryTree.preOrder(binaryTree.root);
		// binaryTree.midOrder(binaryTree.root);
		// binaryTree.postOrder(binaryTree.root);
		// binaryTree.nonRecOrder(binaryTree.root);
		ArrayList<String> data = new ArrayList<>();
		String[] dataArray = new String[] {"A","B","D","#","#","E","#","#","C","#","F","#","#",};
		for (String string : dataArray) {
			data.add(string);
		}
		binaryTree.createBinaryTreePre(data);
		binaryTree.preOrder(binaryTree.root);
	}

}
