package josepth;

/**
 * 约瑟夫法
 * 
 * @author zs
 *
 */
public class Josepth {
	public static int N = 20;
	public static int M = 5; // 数到M就咔擦一个节点

	class Node {
		int val; // 下标
		Node next;

		public Node(int val) {
			this.val = val;
		}
	}

	public void killNode() {
		Node header = new Node(1); // 第一个节点
		Node x = header; // 目前被点到的节点
		for (int i = 2; i <= N; i++) {
			x = (x.next = new Node(i));
		}
		x.next = header; // 头尾相接
		System.out.println("被咔擦的顺序为：");
		while (x != x.next) {
			// 至少还有俩人，仍然继续报数，咔擦
			for (int i = 1; i < M; i++) {
				x = x.next;
			}
			System.out.println(x.next.val + "被干掉");
			x.next = x.next.next;
		}
		System.out.println("最后的幸运儿是：" + x.val);
	}

	public static void main(String[] args) {
		Josepth josepth = new Josepth();
		josepth.killNode();
	}
}
