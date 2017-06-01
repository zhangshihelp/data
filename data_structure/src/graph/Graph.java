package graph;

import java.util.LinkedList;

/**
 * 图
 * 
 * @author zs
 *
 */
public class Graph {

	private int vertexSize; // 顶点数量
	private int[] vertexs; // 顶点数组
	private int[][] matrix;
	private static final int MAX_WEIGHT = 1000;
	private boolean[] isVisited;

	public Graph(int vertexSize) {
		this.vertexSize = vertexSize;
		matrix = new int[vertexSize][vertexSize];
		vertexs = new int[vertexSize];
		for (int i = 0; i < vertexSize; i++) {
			vertexs[i] = i;
		}
		isVisited = new boolean[vertexSize];
	}

	public int[] getVertexs() {
		return vertexs;
	}

	public void setVertexs(int[] vertexs) {
		this.vertexs = vertexs;
	}

	/**
	 * 获取某个顶点的出度
	 * 
	 * @return
	 */
	public int getOutDegree(int index) {
		int degree = 0;
		for (int j = 0; j < matrix[index].length; j++) {
			int weight = matrix[index][j];
			if (weight != 0 && weight != MAX_WEIGHT) {
				degree++;
			}
		}
		return degree;
	}

	/**
	 * 获取某个顶点的入度
	 * 
	 * @return
	 */
	public int getInDegree(int index) {
		int degree = 0;
		for (int j = 0; j < matrix[index].length; j++) {
			int weight = matrix[j][index];
			if (weight != 0 && weight != MAX_WEIGHT) {
				degree++;
			}
		}
		return degree;
	}

	/**
	 * 获取两个顶点之间的权值
	 * 
	 * @return
	 */
	public int getWeight(int v1, int v2) {
		int weight = matrix[v1][v2];
		return weight == 0 ? 0 : (weight == MAX_WEIGHT ? -1 : weight);
	}

	/**
	 * 获取某个顶点的第一个邻接点
	 */
	public int getFirstNeighbor(int index) {
		for (int j = 0; j < vertexSize; j++) {
			if (matrix[index][j] > 0 && matrix[index][j] < MAX_WEIGHT) {
				return j;
			}
		}
		return -1;
	}

	/**
	 * 根据前一个邻接点的下标来获取下一个邻接点
	 * 
	 * @param v表示要找的顶点
	 * @param index表示该顶点相对于哪个邻接点去获取下一个邻接点
	 */
	public int getNextNeighbor(int v, int index) {
		for (int j = index + 1; j < vertexSize; j++) {
			if (matrix[v][j] > 0 && matrix[v][j] < MAX_WEIGHT) {
				return j;
			}
		}
		return -1;
	}

	/**
	 * 图的深度优先遍历算法
	 */
	private void depthFirstSearch(int i) {
		isVisited[i] = true;
		int w = getFirstNeighbor(i);
		while (w != -1) {
			if (!isVisited[w]) {
				// 需要遍历该顶点
				System.out.println("访问到了：" + w + "顶点");
				depthFirstSearch(w);
			}
			w = getNextNeighbor(i, w); // 第一个相对于w的邻接点
		}
	}

	/**
	 * 对外公开的深度优先遍历
	 */
	public void depthFirstSearch() {
		isVisited = new boolean[vertexSize];
		for (int i = 0; i < vertexSize; i++) {
			if (!isVisited[i]) {
				System.out.println("访问到了：" + i + "顶点");
				depthFirstSearch(i);
			}
		}
		isVisited = new boolean[vertexSize];
	}

	/**
	 * 图的广度优先搜索算法
	 */
	public void broadFirstSearch() {
		isVisited = new boolean[vertexSize];
		for (int i = 0; i < vertexSize; i++) {
			if (!isVisited[i]) {
				broadFirstSearch(i);
			}
		}
	}

	/**
	 * 实现广度优先遍历
	 * 
	 * @param i
	 */
	private void broadFirstSearch(int i) {
		int u, w;
		LinkedList<Integer> queue = new LinkedList<>();
		System.out.println("访问到：" + i + "顶点");
		isVisited[i] = true;
		queue.add(i); // 第一次把v0加到队列
		while (!queue.isEmpty()) {
			u = (Integer) (queue.removeFirst().intValue());
			w = getFirstNeighbor(u);
			while (w != -1) {
				if (!isVisited[w]) {
					System.out.println("访问到：" + w + "顶点");
					isVisited[w] = true;
					queue.add(w);
				}
				w = getNextNeighbor(u, w);
			}
		}
	}

	/**
	 * 普里姆算法
	 */
	public void prim() {
		int[] lowcost = new int[vertexSize]; // 最小代价顶点权值的数组，为0表示已经获取最小权值
		int[] adjvex = new int[vertexSize]; // 放顶点
		int min, minId, sum = 0;
		for (int i = 1; i < vertexSize; i++) {
			lowcost[i] = matrix[0][i];
		}
		for (int i = 1; i < vertexSize; i++) {
			min = MAX_WEIGHT;
			minId = 0;
			for (int j = 1; j < vertexSize; j++) {
				if (lowcost[j] < min && lowcost[j] > 0) {
					min = lowcost[j];
					minId = j;
				}
			}
			System.out.println("顶点：" + adjvex[minId] + "权值：" + min);
			sum += min;
			lowcost[minId] = 0;
			for (int j = 1; j < vertexSize; j++) {
				if (lowcost[j] != 0 && matrix[minId][j] < lowcost[j]) {
					lowcost[j] = matrix[minId][j];
					adjvex[j] = minId;
				}
			}
		}
		System.out.println("最小生成树权值和：" + sum);
	}

	public static void main(String[] args) {
		Graph graph = new Graph(9);
		// int[] a1 = new int[] { 0, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 6 };
		// int[] a2 = new int[] { 9, 0, 3, MAX_WEIGHT, MAX_WEIGHT };
		// int[] a3 = new int[] { 2, MAX_WEIGHT, 0, 5, MAX_WEIGHT };
		// int[] a4 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0, 1 };
		// int[] a5 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT,
		// MAX_WEIGHT, 0 };
		// System.out.println(graph.getOutDegree(4));
		// System.out.println(graph.getInDegree(4));
		// System.out.println(graph.getWeight(0, 4));

		int[] a1 = new int[] { 0, 10, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT };
		int[] a2 = new int[] { 10, 0, 18, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, MAX_WEIGHT, 12 };
		int[] a3 = new int[] { MAX_WEIGHT, MAX_WEIGHT, 0, 22, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 8 };
		int[] a4 = new int[] { MAX_WEIGHT, MAX_WEIGHT, 22, 0, 20, MAX_WEIGHT, MAX_WEIGHT, 16, 21 };
		int[] a5 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 20, 0, 26, MAX_WEIGHT, 7, MAX_WEIGHT };
		int[] a6 = new int[] { 11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 26, 0, 17, MAX_WEIGHT, MAX_WEIGHT };
		int[] a7 = new int[] { MAX_WEIGHT, 16, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 17, 0, 19, MAX_WEIGHT };
		int[] a8 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, 7, MAX_WEIGHT, 19, 0, MAX_WEIGHT };
		int[] a9 = new int[] { MAX_WEIGHT, 12, 8, 21, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0 };
		graph.matrix[0] = a1;
		graph.matrix[1] = a2;
		graph.matrix[2] = a3;
		graph.matrix[3] = a4;
		graph.matrix[4] = a5;
		graph.matrix[5] = a6;
		graph.matrix[6] = a7;
		graph.matrix[7] = a8;
		graph.matrix[8] = a9;

		// graph.depthFirstSearch();
		// graph.broadFirstSearch();
		graph.prim();

	}
}
