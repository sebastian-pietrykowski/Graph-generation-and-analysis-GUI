package pl.edu.pw.ee;

import MyExceptions.IllegalVertexException;
import MyExceptions.IllegalWeightException;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Paweł
 */
public class GraphTest {

    static String folderAbsolutePath;
    static String[] graphsReadGraph;

    @BeforeClass
    public static void initiateGraphs() throws IOException {
        folderAbsolutePath = new File("src\\graphs\\test graphs\\Graph").getAbsolutePath();

        graphsReadGraph = new String[4];

        // 9x12 random
        graphsReadGraph[0] = folderAbsolutePath + "\\randomGraphTestFile.txt";

        // 5x4 complete
        graphsReadGraph[1] = folderAbsolutePath + "\\completeGraphTestFile.txt";

        // 7x7 connected
        graphsReadGraph[2] = folderAbsolutePath + "\\connectedGraphTestFile.txt";


        /*  // 3x5 unconnected
        graphsPaths[3] = folderAbsolutePath + "\\graph3_3x5_unconnected.txt";*/
    }

    @Test
    public void testReadGraph_forGraph12x9() {
        int[] expectedFromVertex = {6, 10, 11, 14, 16, 21, 24, 32, 38, 41, 45,
            45, 47, 48, 50, 52, 53, 54, 54, 58, 60, 60, 63, 69, 70, 71, 76, 76,
            80, 80, 83, 83, 84, 88, 90, 92, 92, 99, 100, 101, 102, 104, 105, 105,
            107};
        int[] expectedToVertex = {7, 11, 23, 26, 4, 20, 25, 31, 26, 42, 44, 57,
            46, 60, 38, 53, 41, 53, 55, 59, 61, 72, 64, 57, 82, 59, 75, 77, 68,
            79, 71, 95, 96, 89, 102, 80, 93, 100, 101, 89, 103, 103, 104, 106, 106};

        double[] expectedWeights = {1.610194, 2.522246, 3.265562, 4.825005,
            3.693001, 4.593854, 3.897205, 2.213115, 1.856132, 1.412217, 2.422573,
            4.852646, 2.361988, 1.550011, 3.724761, 2.561298, 4.078551, 3.960985,
            3.911984, 4.959750, 3.291529, 4.680152, 4.131944, 4.562565, 4.013460,
            1.960843, 1.085807, 4.982168, 1.492292, 1.141263, 0.582883, 1.468995,
            2.585337, 0.028125, 1.833731, 2.634177, 3.703119, 0.972361, 3.525848,
            4.458081, 0.694979, 2.142405, 2.053279, 4.451789, 1.953206};
        try {
            File testFile = new File(graphsReadGraph[0]);

            Graph graph = Graph.readGraph(testFile);
            assertEquals(graph.columns, 9, graph.getColumns());
            assertEquals(graph.rows, 12 , graph.getRows());
            int i = 0;
            for (Edge e : graph.getAdjacencyList()) {
                assertEquals(e.getFromVertex(), expectedFromVertex[i]);
                assertEquals(e.getToVertex(), expectedToVertex[i]);
                assertEquals(e.getWeight(), expectedWeights[i], 0.0);
                i++;
            }
        } catch (IllegalVertexException | IOException | IllegalWeightException e) {
            System.out.println("Niepoprawny plik");
        }

    }

    @Test
    public void testReadGraph_forGraph5x4() {
        int[] expectedFromVertex = {0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5,
            5, 5, 6, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 11,
            11, 11, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14, 14, 15, 15, 15, 16, 16,
            17, 17, 17, 18, 18, 18, 19, 19};
        int[] expectedToVertex = {1, 4, 0, 2, 5, 1, 3, 6, 2, 7, 5, 0, 8, 4, 6,
            1, 9, 5, 7, 2, 10, 6, 3, 11, 9, 4, 12, 8, 10, 5, 13, 9, 11, 6, 14,
            10, 7, 15, 13, 8, 16, 12, 14, 9, 17, 13, 15, 10, 18, 14, 11, 19, 17,
            12, 16, 18, 13, 17, 19, 14, 18, 15};

        double[] expectedWeights = {0.9934410094414436, 4.319136609280742,
            2.367627130096461, 3.8581521252032163, 1.0689860456077587,
            4.932323494816987, 2.84885593342442, 2.843116901375662,
            4.904463847237922, 4.664784203981726,
            2.918807181515084, 3.1291507943252483, 4.849076417910323,
            3.113463995467307, 2.3367496786793813, 2.748451566869094, 1.0118819543716095,
            2.8400009686293153, 0.7897845472585302, 0.039613794627417764, 1.2997381134145591,
            4.452435332314414, 1.9426084059783433, 4.82481292807091,
            4.594800323336195, 3.5914920177112815, 0.9721856879262591,
            1.1070366393375342, 2.1254563744619226, 2.2208020597072835, 2.829621033666551,
            4.639400600759621, 4.534431795901271, 3.596287490594089, 1.5861135645718794,
            0.2930762006710258, 4.2344130044650425, 4.644141816837366,
            4.833136463389557, 3.305615141295999, 4.8733635374241775,
            1.0005348752030563, 1.4964087978342229, 4.15442302728451, 4.041509644910388,
            4.10787104771811, 3.841786498860747, 0.09788096885124442, 2.2977291789371406,
            4.269790790071488, 4.78341034745661, 3.161890191347296,
            3.206739281555434, 1.4250427465805993,
            2.481790091346495, 1.8101351707142521, 4.923411167887833,
            3.399160222235142, 4.051900425233468, 2.070682007803744,
            4.0192146888509725, 4.196257092479691};
        try {
            File testFile = new File(graphsReadGraph[1]);
            Graph graph = Graph.readGraph(testFile);
            assertEquals(graph.columns, 4 , graph.getColumns());
            assertEquals(graph.rows, 5 , graph.getRows());
            int i = 0;
            for (Edge e : graph.getAdjacencyList()) {
                assertEquals(e.getFromVertex(), expectedFromVertex[i]);
                assertEquals(e.getToVertex(), expectedToVertex[i]);
                assertEquals(e.getWeight(), expectedWeights[i], 0.0);
                i++;
            }
        } catch (IllegalVertexException | IOException | IllegalWeightException e) {
            System.out.println("Niepoprawny plik");
        }

    }

    @Test
    public void testReadGraph_forGraph7x7() {
        int[] expectedFromVertex = {0,0, 3, 4, 5, 5, 7, 8, 8, 9, 9, 10, 12,
            15, 15, 15, 16, 17, 17, 20, 22, 22, 24, 24, 25, 25, 26, 26,
            27, 29, 29, 29, 30, 30, 33, 35, 36, 37, 37, 38, 39, 39,
            40, 40, 44, 46, 46, 47};
        int[] expectedToVertex = {1, 7, 4, 5, 12, 6, 8, 15, 9, 10, 2, 3,
            11, 22, 16, 14, 17, 24, 18, 13, 21, 29, 23, 25, 32, 26,
            27, 19, 20, 30, 36, 28, 37, 31, 34, 42, 35, 38, 44, 39,
            40, 46, 41, 33, 43, 47, 45, 48};

        double[] expectedWeights = {5.6897337056849375, 2.1932643331435324,
            7.094083995155011, 5.192534332617934,
            2.6160408959140127, 3.9468864581487018, 5.906239242352141,
            4.1496854216123715, 2.9868860600701628,
            2.057245789056103, 4.045821097358582, 7.201428644604222,
            5.6827519419315555, 3.924640565892675, 3.8590370158436835,
            7.597259028767761, 2.3639147099362647, 2.183072974174279,
            2.1978644165745145, 7.09607894461081, 5.078594624793879,
            7.929975581011772, 4.164465615545361, 2.960270845939295, 2.694306917993601,
            7.844705857029277, 5.690534384186208, 3.9842405803009155,
            4.422318619880091, 6.1390711303225505, 7.034289790787052,
            2.896594155956267, 7.105557954237528, 3.370807878881018,
            2.359088939084743, 3.0902236186877188, 6.557482357364184,
            2.9675728871590747, 2.786774271412667, 2.441013809868459,
            2.9643500682335446, 4.194745485236132, 3.8274055021402686,
            6.55263695125928, 5.885477899430782, 5.074525668907899,
            2.2620683443539864, 5.529080154859274,};
        try {
            File testFile = new File(graphsReadGraph[2]);

            Graph graph = Graph.readGraph(testFile);
            assertEquals(graph.getColumns(), 7 , graph.columns);
            assertEquals(graph.getRows(), 7, graph.rows);
            int i = 0;
            for (Edge e : graph.getAdjacencyList()) {
                assertEquals(e.getFromVertex(), expectedFromVertex[i]);
                assertEquals(e.getToVertex(), expectedToVertex[i]);
                assertEquals(e.getWeight(), expectedWeights[i], 0.0);
                i++;
            }
        } catch (IllegalVertexException | IOException | IllegalWeightException e) {
            System.out.println("Niepoprawny plik");
        }

    }
}
