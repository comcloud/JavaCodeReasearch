package com.cloud.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @version v1.0
 * @ClassName Graph
 * @Author rayss
 * @Datetime 2021/6/4 11:35 下午
 */

public class Graph {
    /**
     * 顶点集合
     */
    private final List<String> vertexList;
    /**
     * 存储图对应的邻接矩阵
     */
    private final int[][] edges;
    /**
     * 边的数目
     */
    private int numOfEdges;

    /**
     * 标记节点是否被访问
     */
    private boolean[] isVisited;

    public static void main(String[] args) {
//        String[] vertexes = {"A", "B", "C", "D", "E"};
        String[] vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};

        int n = 8;
        Graph graph = new Graph(n);

        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }

        //构建关系
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(5,6,1);

        graph.showGraph();

        System.out.println("深度优先");
        graph.dfs();
        System.out.println();
        System.out.println("广度优先");
        graph.bfs();
    }


    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>();
    }

    /**
     * 添加顶点
     *
     * @param vertex 顶点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param x      表示点下标为第几个顶点
     * @param y      表示点下标为第几个顶点
     * @param weight 插入的值
     */
    public void insertEdge(int x, int y, int weight) {
        edges[x][y] = weight;
        edges[y][x] = weight;
        numOfEdges++;
    }

    /**
     * @param index 索引位置
     * @return 根据给定索引查找对应当前行存在边索引位置
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param v1 某一行
     * @param v2 某一列
     * @return 对应某一行中下一次边的位置索引
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    private void dfs(boolean[] isVisited, int index) {
        //首先访问该节点就直接打印
        System.out.print(getValueByIndex(index) + "->");
        //将节点设置为已经访问
        isVisited[index] = true;
        //查找节点i的第一个邻接结点w
        int w = getFirstNeighbor(index);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w已经被访问过
            w = getNextNeighbor(index, w);
        }
    }

    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    public void bfs(boolean[] isVisited, int i) {
        //队列的头节点对应的下标
        int u;
        //临接结点w
        int w;
        //队列，记录结点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //打印结点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已经访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);
        //循环判断
        while (!queue.isEmpty()) {
            u = queue.poll();
            w = getFirstNeighbor(u);
            while (w != -1) {
                //此时存在，判断是否已经访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //以u为前驱点，找w下一个点
                w = getNextNeighbor(u, w);
            }
        }

    }

    public void showGraph() {
        System.out.println("  " + vertexList);
        for (int i = 0; i < edges.length; i++) {
            System.out.println(vertexList.get(i) + " " + Arrays.toString(edges[i]));
        }
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }
}
