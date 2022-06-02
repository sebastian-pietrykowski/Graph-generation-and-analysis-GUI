package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import MyExceptions.IllegalVertexException;
import MyExceptions.IllegalWeightException;
import pl.edu.pw.ee.pathsOnGraph.PathOnGraph;

public class DijkstraTest {
    
    static String folderAbsolutePath;
    static String[] graphsPaths;
    
    @BeforeClass
    public static void initiateGraphs() throws IOException {
        folderAbsolutePath = new File("src\\graphs\\test graphs\\Dijkstra").getAbsolutePath();

        graphsPaths = new String[4];

        // 5x5 connected
        graphsPaths[0] = folderAbsolutePath + "\\graph0_5x5_connected.txt";
        
        // 5x5 connected with cycle
        graphsPaths[1] = folderAbsolutePath + "\\graph1_5x5_connected_with_cycle.txt";

         // 4x7 connected
         graphsPaths[2] = folderAbsolutePath + "\\graph2_4x7_connected.txt";

        // 3x5 unconnected
        graphsPaths[3] = folderAbsolutePath + "\\graph3_3x5_unconnected.txt";
    }

    @Test
    public void dijkstra_graph0_5x5_connected_isPredecessorsArrayProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[0]));
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.dijkstra(0);

        int[] actual = dijkstra.getPredecessors();
        int[] expected = {
                    -1, 0,  1,  2,  3,
                    0,  5,  6,  7,  8,
                    5,  6,  7,  8,  13,
                    10, 11, 22, 13, 14,
                    15, 20, 21, 22, 23
                    };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph0_5x5_connected_0to0_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[0]));
        Dijkstra dijkstra = new Dijkstra(graph);
        
        int[] actual = dijkstra.determineShortestPath(0, 0).getPath();
        int[] expected = {0};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph0_5x5_connected_5to5_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[0]));
        Dijkstra dijkstra = new Dijkstra(graph);

        int[] actual = dijkstra.determineShortestPath(5, 5).getPath();
        int[] expected = {5};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph0_5x5_connected_2to4_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[0]));
        Dijkstra dijkstra = new Dijkstra(graph);

        int[] actual = dijkstra.determineShortestPath(2, 4).getPath();
        int[] expected = { 2, 3, 4 };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph0_5x5_connected_0to13_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[0]));
        Dijkstra dijkstra = new Dijkstra(graph);

        int[] actual = dijkstra.determineShortestPath(0, 13).getPath();
        int[] expected = { 0, 5, 6, 7, 8, 13 };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph0_5x5_connected_0to24_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[0]));
        Dijkstra dijkstra = new Dijkstra(graph);

        int[] actual = dijkstra.determineShortestPath(0, 24).getPath();
        int[] expected = { 0, 5, 10, 15, 20, 21, 22, 23, 24 };
        assertArrayEquals(expected, actual);
    }




    @Test
    public void dijkstra_graph1_5x5_connected_with_cycle_isPredecessorsArrayProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[1]));
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.dijkstra(0);

        int[] actual = dijkstra.getPredecessors();
        int[] expected = {
                    -1,  0,  1,  2,  3,
                    6, 7,  2,  7,  8,
                    5,  10, 11, 8,  9,
                    10, 11, 12, 13, 14,
                    15, 20, 21, 22, 23
                    };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph1_5x5_connected_with_cycle_0to1_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[1]));
        Dijkstra dijkstra = new Dijkstra(graph);

        int[] actual = dijkstra.determineShortestPath(0, 1).getPath();
        int[] expected = {0,1};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph1_5x5_connected_with_cycle_1to0_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[1]));
        Dijkstra dijkstra = new Dijkstra(graph);

        int[] actual = dijkstra.determineShortestPath(1, 0).getPath();
        int[] expected = {1,0};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph1_5x5_connected_with_cycle_0to3_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[1]));
        Dijkstra dijkstra = new Dijkstra(graph);
        
        int[] actual = dijkstra.determineShortestPath(0, 3).getPath();
        int[] expected = {0,1,2,3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph1_5x5_connected_with_cycle_10to8_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[1]));
        Dijkstra dijkstra = new Dijkstra(graph);

        PathOnGraph actualPath = dijkstra.determineShortestPath(10, 8);
        assertTrue(actualPath == null);
    }

    @Test
    public void determineShortestPath_graph1_5x5_connected_with_cycle_0to24_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[1]));
        Dijkstra dijkstra = new Dijkstra(graph);

        int[] actual = dijkstra.determineShortestPath(0, 24).getPath();
        int[] expected = { 0, 1, 2, 7, 6, 5, 10, 15, 20, 21, 22, 23, 24 };
        assertArrayEquals(expected, actual);
    }




    @Test
    public void dijkstra_graph2_4x7_connected_isPredecessorsArrayProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[2]));
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.dijkstra(0);

        int[] actual = dijkstra.getPredecessors();
        int[] expected = {
                    -1, 0,  1,  -1,
                    0,  -1,  2,  6,
                    4,  10,  6,  7,
                    13, 9,  10, 11,
                    12, 16, 17, 18,
                    21, 22, 23, 19,
                    20, 24, 25, 26
                    };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph2_4x7_connected_0to9_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[2]));
        Dijkstra dijkstra = new Dijkstra(graph);

        int[] actual = dijkstra.determineShortestPath(0, 9).getPath();
        int[] expected = { 0, 1, 2, 6, 10, 9 };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph2_4x7_connected_10to16_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[2]));
        Dijkstra dijkstra = new Dijkstra(graph);

        int[] actual = dijkstra.determineShortestPath(10, 16).getPath();
        int[] expected = { 10, 9, 13, 12, 16 };
        assertArrayEquals(expected, actual);
    }
    



    @Test
    public void dijkstra_graph3_3x5_unconnected_isPredecessorsArrayProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[3]));
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.dijkstra(0);

        int[] actual = dijkstra.getPredecessors();
        int[] expected = {
                    -1, 0,  -1,
                    -1, 1,  -1,
                    -1, 4,  7,
                    -1, -1, 8,
                    -1, -1, 11
                    };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph3_3x5_unconnected_5to3_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[3]));
        Dijkstra dijkstra = new Dijkstra(graph);

        PathOnGraph actualPath = dijkstra.determineShortestPath(5, 3);
        assertTrue(actualPath == null);
    }

    @Test
    public void determineShortestPath_graph3_3x5_unconnected_4to9_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[3]));
        Dijkstra dijkstra = new Dijkstra(graph);

        PathOnGraph actualPath = dijkstra.determineShortestPath(4, 9);
        assertTrue(actualPath == null);
    }

    @Test
    public void determineShortestPath_graph3_3x5_unconnected_0to14_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[3]));
        Dijkstra dijkstra = new Dijkstra(graph);

        int[] actual = dijkstra.determineShortestPath(0, 14).getPath();
        int[] expected = { 0, 1, 4, 7, 8, 11, 14 };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph3_3x5_unconnected_4to11_isProper() throws IOException, IllegalVertexException, IllegalWeightException {
        Graph graph = Graph.readGraph(new File(graphsPaths[3]));
        Dijkstra dijkstra = new Dijkstra(graph);

        int[] actual = dijkstra.determineShortestPath(4, 11).getPath();
        int[] expected = { 4, 7, 8, 11 };
        assertArrayEquals(expected, actual);
    }
}
