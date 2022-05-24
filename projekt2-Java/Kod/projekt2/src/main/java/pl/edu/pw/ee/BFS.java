package pl.edu.pw.ee;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    private Graph graph;
    private final int startVertexNumber;
    private Queue<Integer> fifo;

    public BFS(Graph graph, int startVertexNumber) {
        this.graph = graph;
        this.startVertexNumber = startVertexNumber;
        fifo = new LinkedList<>();
    }

    public int getStartVertexNumber() {
        return startVertexNumber;
    }

    public boolean checkConnectivty() {

        boolean[] visited = new boolean[graph.getNumberOfVertices()];

        visited[this.getStartVertexNumber()] = true;

        fifo.add(this.getStartVertexNumber());

        while (!fifo.isEmpty()) {
            int currentVertex = fifo.poll();

            for (int i = 0; i < graph.getAdjacencyList().size(); i++) {

                if (currentVertex == graph.getAdjacencyList().get(i).getFromVertex()) {
                    int neighbourVertex = graph.getAdjacencyList().get(i).getToVertex();

                    if (!visited[neighbourVertex]) {
                        visited[neighbourVertex] = true;
                        fifo.add(neighbourVertex);
                    }
                }
            }
        }

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {

                return false;
            }

        }
        return true;
    }
}
