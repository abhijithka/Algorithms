package dfs;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
    private int noOfVertices;
    private LinkedList<Integer> adj[];

    Graph(int v) {
        noOfVertices = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.println(v + " ");

        Iterator<Integer> i = adj[v].listIterator();
        while(i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    void DFS(int source) {
        boolean[] visited = new boolean[noOfVertices+1];
        DFSUtil(source, visited);
    }

    public static void main(String[] args) {
        Graph g = new Graph(7);

        g.addEdge(1, 3);
        g.addEdge(3, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 1);
        g.addEdge(2, 5);
        g.addEdge(5, 2);
        g.addEdge(3, 5);
        g.addEdge(5, 3);
        g.addEdge(2, 4);
        g.addEdge(4, 2);
        g.addEdge(5, 4);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(6, 4);
        g.addEdge(5, 6);
        g.addEdge(6, 5);

        System.out.println("BFS starting with node 1");
        g.DFS(1);
    }

}
