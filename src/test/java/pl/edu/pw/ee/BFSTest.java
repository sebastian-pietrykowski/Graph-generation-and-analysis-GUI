package pl.edu.pw.ee;

import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.edu.pw.ee.MyExceptions.IllegalVertexException;
import pl.edu.pw.ee.MyExceptions.IllegalWeightException;

public class BFSTest {

    static String folderAbsolutePath;
    static String[] bfsTestFiles;

    @BeforeClass
    public static void initiateGraphs() throws IOException {
        folderAbsolutePath = new File("src\\graphs\\test graphs\\BFS").getAbsolutePath();

        bfsTestFiles = new String[4];

        // 42x42 random
        bfsTestFiles[0] = folderAbsolutePath + "\\randomNotConnectedGraph.txt";
        // 47x39 connected
        bfsTestFiles[1] = folderAbsolutePath + "\\connectedGraph.txt";
        // 19x26 complete
        bfsTestFiles[2] = folderAbsolutePath + "\\completeGraph.txt";
        // 123x101 random
        bfsTestFiles[3] = folderAbsolutePath + "\\random2NotConnectedGraph.txt";
    }

    @Test
    public void testBFSfor42x42Graph() {
        try {
            File testFile = new File(bfsTestFiles[0]);
            Graph graph = Graph.readGraph(testFile);
            BFS bfs = new BFS(graph, 0);
            assertEquals(bfs.getStartVertexNumber(), 0);
            assertEquals(bfs.checkConnectivty(), false);
        } catch (IOException | IllegalVertexException | IllegalWeightException e) {
            System.out.println("Niepoprawny plik");
        }
    }

    @Test
    public void testBFSfor47x39Graph() {
        try {
            File testFile = new File(bfsTestFiles[1]);
            Graph graph = Graph.readGraph(testFile);
            BFS bfs = new BFS(graph, 0);
            assertEquals(bfs.getStartVertexNumber(), 0);
            assertEquals(bfs.checkConnectivty(), true);
        } catch (IOException | IllegalVertexException | IllegalWeightException e) {
            System.out.println("Niepoprawny plik");
        }
    }

    @Test
    public void testBFSfor19x26Graph() {
        try {
            File testFile = new File(bfsTestFiles[2]);
            Graph graph = Graph.readGraph(testFile);
            BFS bfs = new BFS(graph, 0);
            assertEquals(bfs.checkConnectivty(), true);
        } catch (IOException | IllegalVertexException | IllegalWeightException e) {
            System.out.println("Niepoprawny plik");
        }
    }

    @Test
    public void testBFSfor123x101Graph() {
        try {
            File testFile = new File(bfsTestFiles[3]);
            Graph graph = Graph.readGraph(testFile);
            BFS bfs = new BFS(graph, 0);
            assertEquals(bfs.getStartVertexNumber(), 0);
            assertEquals(bfs.checkConnectivty(), false);
        } catch (IOException | IllegalVertexException | IllegalWeightException e) {
            System.out.println("Niepoprawny plik");
        }
    }
}
