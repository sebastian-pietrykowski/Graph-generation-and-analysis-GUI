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

    public boolean checkConnectivty(BFS bfs) {

        boolean[] visited = new boolean[graph.getNumberOfVertices()];

        visited[bfs.getStartVertexNumber()] = true;
     
        fifo.add(bfs.getStartVertexNumber());
        // Object[] adjacencyArray = graph.getAdjacencyList().toArray();
        while (!fifo.isEmpty()) {
            int currentVertex = fifo.poll();
            // System.out.println("curr= "+currentVertex);
            for (int i = 0; i < graph.getAdjacencyList().size(); i++) {
                //  int neighbourVertex = graph.getAdjacencyList().get(i).getToVertex();
                if (currentVertex == graph.getAdjacencyList().get(i).getFromVertex()) {
                    int neighbourVertex = graph.getAdjacencyList().get(i).getToVertex();
                 //   System.out.println(graph.getAdjacencyList().get(i).getToVertex());
                    if (!visited[neighbourVertex]) {
                        visited[neighbourVertex] = true;
                        fifo.add(neighbourVertex);
                    }
                }
            }
        }
      //  for(int i = 0 ; i < visited.length;i++)
      //  System.out.println(visited[i]);
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
               // System.out.println("?");
                return false;
            }
           // System.out.println("?");
            //return true;

        }
        return true;
    }
}
