package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

public class DijkstraTest {
    
    static Graph[] graphs;
    static Dijkstra[] dijkstra;
    public static int startVertexNumber = 0;
    
    @BeforeClass
    public static void initiateGraphs() {
        graphs = new Graph[4];
        dijkstra = new Dijkstra[4];

        // 5x5 connected
        graphs[0] = Graph.readGraph(new File("testGraphs/Dijkstra/graph0_5x5_connected.txt"));
        dijkstra[0] = new Dijkstra(graphs[0], startVertexNumber);
        dijkstra[0].dijkstra();

        // 5x5 connected with cycle
        graphs[1] = Graph.readGraph(new File("testGraphs/Dijkstra/graph1_5x5_connected_with_cycle.txt"));
        dijkstra[1] = new Dijkstra(graphs[1], startVertexNumber);
        dijkstra[1].dijkstra();

         // 4x7 connected
        graphs[2] = Graph.readGraph(new File("testGraphs/Dijkstra/graph2_4x7_connected.txt"));
        dijkstra[2] = new Dijkstra(graphs[2], startVertexNumber);
        dijkstra[2].dijkstra();

        // 3x5 unconnected
        graphs[3] = Graph.readGraph(new File("testGraphs/Dijkstra/graph3_3x5_unconnected.txt"));
        dijkstra[3] = new Dijkstra(graphs[3], startVertexNumber);
        dijkstra[3].dijkstra();
    }




    @Test
    public void dijkstra_graph0_5x5_connected_isPredecessorsArrayProper() {
        
        int[] actual = dijkstra[0].getPredecessors();
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
    public void determineShortestPath_graph0_5x5_connected_0to0_isProper() {
        int[] actual = dijkstra[0].determineShortestPath(0, 0)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = null;
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph0_5x5_connected_5to5_isProper() {
        int[] actual = dijkstra[0].determineShortestPath(5, 5)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = null;
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph0_5x5_connected_2to4_isProper() {
        int[] actual = dijkstra[0].determineShortestPath(2, 4)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = { 2, 3, 4 };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph0_5x5_connected_0to13_isProper() {
        int[] actual = dijkstra[0].determineShortestPath(0, 13)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = { 0, 5, 6, 7, 8, 13 };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph0_5x5_connected_0to24_isProper() {
        int[] actual = dijkstra[0].determineShortestPath(0, 13)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = { 0, 5, 10, 15, 20, 21, 22, 23, 24 };
        assertArrayEquals(expected, actual);
    }




    @Test
    public void dijkstra_graph1_5x5_connected_with_cycle_isPredecessorsArrayProper() {
        
        int[] actual = dijkstra[1].getPredecessors();
        int[] expected = {
                    1,  0,  1,  2,  3,
                    -1, 7,  2,  7,  8,
                    5,  10, 11, 8,  9,
                    10, 11, 12, 13, 14,
                    15, 20, 21, 22, 23
                    };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph1_5x5_connected_with_cycle_0to1_isProper() {
        int[] actual = dijkstra[1].determineShortestPath(0, 1)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = {0,1};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph1_5x5_connected_with_cycle_1to0_isProper() {
        int[] actual = dijkstra[1].determineShortestPath(1, 0)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = {0,1};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph1_5x5_connected_with_cycle_0to3_isProper() {
        int[] actual = dijkstra[1].determineShortestPath(0, 3)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = {0,1,2,3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph1_5x5_connected_with_cycle_10to8_isProper() {
        int[] actual = dijkstra[1].determineShortestPath(10, 8)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = null;
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph1_5x5_connected_with_cycle_0to24_isProper() {
        int[] actual = dijkstra[1].determineShortestPath(0, 3)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = { 0, 1, 2, 7, 6, 5, 10, 15, 20, 21, 22, 23, 24 };
        assertArrayEquals(expected, actual);
    }




    @Test
    public void dijkstra_graph0_graph2_4x7_connected_isPredecessorsArrayProper() {
        
        int[] actual = dijkstra[2].getPredecessors();
        int[] expected = {
                    -1, 0,  1,  7,
                    0,  9,  2,  6,
                    4,  8,  6,  7,
                    13, 9,  10, 11,
                    12, 16, 17, 18,
                    21, 22, 23, 19,
                    20, 24, 25, 26
                    };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph2_4x7_connected_0to9_isProper() {
        int[] actual = dijkstra[2].determineShortestPath(0, 9)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = { 0, 1, 2, 6, 10, 9 };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph2_4x7_connected_10to16_isProper() {
        int[] actual = dijkstra[2].determineShortestPath(10, 16)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = { 10, 9, 13, 12, 16 };
        assertArrayEquals(expected, actual);
    }
    



    @Test
    public void dijkstra_graph3_3x5_unconnected_isPredecessorsArrayProper() {
        
        int[] actual = dijkstra[3].getPredecessors();
        int[] expected = {
                    -1, 0,  -1,
                    -1, 1,  -1,
                    -1, 4,  7,
                    -1, -1, 9,
                    -1, -1, 11
                    };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph3_3x5_unconnected_5to3_isProper() {
        int[] actual = dijkstra[3].determineShortestPath(5, 3)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = null;
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph3_3x5_unconnected_4to9_isProper() {
        int[] actual = dijkstra[3].determineShortestPath(4, 9)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = null;
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph3_3x5_unconnected_0to14_isProper() {
        int[] actual = dijkstra[3].determineShortestPath(0, 14)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = { 0, 1, 4, 7, 8, 11, 14 };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void determineShortestPath_graph3_3x5_unconnected_4to11_isProper() {
        int[] actual = dijkstra[3].determineShortestPath(4, 11)
                        .stream().mapToInt(i -> i).toArray();
        int[] expected = { 4, 7, 8, 11 };
        assertArrayEquals(expected, actual);
    }
}
