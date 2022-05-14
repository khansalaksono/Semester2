//https://www.hackerrank.com/contests/graph-asd-a/challenges/network-cost

import java.io.*;
import java.util.*;

public class NetworkCost {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        Graph graph = new Graph(n);
        input.nextLine();
        for(int i = 0; i < m; i++){
            String xyRaw = input.nextLine();
            String[] xy = xyRaw.split(" ");
            int xySource = Integer.parseInt(xy[0]);
            int xyDestin = Integer.parseInt(xy[2]);
            graph.addEdge(xySource, xyDestin);
        }
        int c0 = input.nextInt();
        int c1 = input.nextInt();
        graph.algoDijkstra(c0, c1);
    }
}

class Graph {
    int vertices;
    int[][] matrix;

    Graph(int vertices) {
        this.vertices = vertices;
        matrix = new int[vertices][vertices];
    }

    void addEdge(int source, int destination) {
        matrix[source][destination] = 1;
        matrix[destination][source] = 1;
    }

//    void printGraph() {
//        for (int i = 0; i < vertices; i++) {
//            for (int j = 0; j < vertices; j++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }

    public void algoDijkstra(int source, int destination) {
        int[] networkCost = new int[vertices];
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            networkCost[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        networkCost[source] = 0;
        for (int i = 0; i < vertices - 1; i++) {
            int min = Integer.MAX_VALUE;
            int indexMin = 0;
            for (int j = 0; j < vertices; j++) {
                if (!visited[j] && networkCost[j] < min) {
                    min = networkCost[j];
                    indexMin = j;
                }
            }

            visited[indexMin] = true;
            for (int j = 0; j < vertices; j++) {
                if (networkCost[indexMin] != Integer.MAX_VALUE && !visited[j] && matrix[indexMin][j] != 0 && networkCost[indexMin] + matrix[indexMin][j] < networkCost[j]) {
                    networkCost[j] = matrix[indexMin][j] + networkCost[indexMin];
                }
            }
        }

        if (networkCost[destination] == Integer.MAX_VALUE) networkCost[destination] = -1;

        System.out.println(networkCost[destination]);
    }
}
