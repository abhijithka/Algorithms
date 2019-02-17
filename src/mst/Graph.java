package mst;

import java.util.Arrays;

// Kruskal's Minimum Spanning tree
public class Graph {
    class Edge implements Comparable<Edge> {
        int u, v, weight;

        @Override
        public int compareTo(Edge anotherEdge) {
            return this.weight - anotherEdge.weight;
        }
    }

    class Subset {
        int parent, rank;
    }

    int noOfVertices, noOfEdges;
    Edge[] edges;

    Graph(int vertices, int totalEdges) {
        noOfEdges = totalEdges;
        noOfVertices = vertices;
        edges = new Edge[noOfEdges];
        for (int i = 0; i < noOfEdges; i++) {
            edges[i] = new Edge();
        }
    }

    // Uses path compression
    int find(Subset subsets[], int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    // Union by rank
    void union(Subset subsets[], int x, int y) {
        int rootx = find(subsets, x);
        int rooty = find(subsets, y);

        if (subsets[rootx].rank < subsets[rooty].rank) {
            subsets[rootx].parent = rooty;
        } else if (subsets[rootx].rank > subsets[rooty].rank) {
            subsets[rooty].parent = rootx;
        } else {
            subsets[rootx].parent = rooty;
            subsets[rooty].rank++;
        }
    }

    // Kruskal's
    void MST() {

        Arrays.sort(edges);

        Subset[] subsets = new Subset[noOfVertices];

        for (int i = 0; i < noOfVertices; i++) {
            subsets[i] = new Subset();
        }

        for (int i = 0; i < noOfVertices; i++) {
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        int pickedEdgesCount = 0;
        Edge[] pickedEdges = new Edge[noOfVertices];
        for (int i = 0; i < pickedEdgesCount; i++) {
            pickedEdges[i] = new Edge();
        }
        int e = 0;

        while(pickedEdgesCount < noOfVertices - 1) {
            Edge nextEdge = new Edge();
            nextEdge = edges[e++];

            int rootU = find(subsets, nextEdge.u);
            int rootV = find(subsets, nextEdge.v);

            if (rootU != rootV) {
                pickedEdges[pickedEdgesCount++] = nextEdge;
                union(subsets, rootU, rootV);
            }
        }

        for (int i = 0; i<pickedEdgesCount; i++) {
            System.out.println(pickedEdges[i].u + " -- " + pickedEdges[i].v);
        }
    }

    public static void main(String[] args) {
        /* Let us create following weighted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */
        int V = 4;
        int E = 5;
        Graph graph = new Graph(V, E);

        // add edge 0-1
        graph.edges[0].u = 0;
        graph.edges[0].v = 1;
        graph.edges[0].weight = 10;

        // add edge 0-2
        graph.edges[1].u = 0;
        graph.edges[1].v = 2;
        graph.edges[1].weight = 6;

        // add edge 0-3
        graph.edges[2].u = 0;
        graph.edges[2].v = 3;
        graph.edges[2].weight = 5;

        // add edge 1-3
        graph.edges[3].u = 1;
        graph.edges[3].v = 3;
        graph.edges[3].weight = 15;

        // add edge 2-3
        graph.edges[4].u = 2;
        graph.edges[4].v = 3;
        graph.edges[4].weight = 4;

        graph.MST();
    }
}

