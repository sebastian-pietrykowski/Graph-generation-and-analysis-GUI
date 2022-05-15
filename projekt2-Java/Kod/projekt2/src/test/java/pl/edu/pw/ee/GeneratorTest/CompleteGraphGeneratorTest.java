package pl.edu.pw.ee.GeneratorTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.edu.pw.ee.Graph;
import pl.edu.pw.ee.generator.CompleteGraphGenerator;
import pl.edu.pw.ee.generator.Generator;

public class CompleteGraphGeneratorTest {
    
    static Generator generator[];
    static Graph graph[];

    @BeforeClass
    public static void initiateGraphs() {
        generator = new Generator[10];
        graph = new Graph[10];

        // same columns and rows
        generator[0] = new CompleteGraphGenerator(4, 4, 0, 1);
        graph[0] = generator[0].generate();

        // same columns and rows, huge graph
        generator[1] = new CompleteGraphGenerator(50, 50, 13.4, 87.1323);
        graph[1] = generator[1].generate();

        // different columns and rows
        generator[2] = new CompleteGraphGenerator(17, 19, 0.123, 1.8910);
        graph[2] = generator[2].generate();

        // 1 column
        generator[3] = new CompleteGraphGenerator(1, 15, 7813.98, 9999.999);
        graph[3] = generator[3].generate();

        // 1 row
        generator[4] = new CompleteGraphGenerator(15, 1, 7813.98, 9999.999);
        graph[4] = generator[4].generate();
    }


    // graph[0]
    @Test
    public void generate_graph0_checkNumberOfVertices() {
        assertTrue( GeneratorTestAuxiliaryMethods.checkNumberOfVertices(graph[0]) );
    }

    @Test
    public void generate_graph0_isNumberOfEdgesMax() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfEdgesMax(graph[0]) );
    }

    @Test
    public void generate_graph0_checkEdgesSituation() {
        assertTrue( GeneratorTestAuxiliaryMethods.checkEdgesSituation(graph[0]) );
    }

    @Test
    public void generate_graph0_areEdgesNotTheSame() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesNotTheSame( graph[0] ) );
    }

    @Test
    public void generate_graph0_areEdgesWithinRange() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesWeightWithinRange( generator[0], graph[0] ) );
    }


    // graph[1]
    @Test
    public void generate_graph1_checkNumberOfVertices() {
        assertTrue( GeneratorTestAuxiliaryMethods.checkNumberOfVertices(graph[1]) );
    }

    @Test
    public void generate_graph1_isNumberOfEdgesMax() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfEdgesMax(graph[1]) );
    }

    @Test
    public void generate_graph1_checkEdgesSituation() {
        assertTrue( GeneratorTestAuxiliaryMethods.checkEdgesSituation(graph[1]) );
    }

    @Test
    public void generate_graph1_areEdgesNotTheSame() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesNotTheSame( graph[1] ) );
    }

    @Test
    public void generate_graph1_areEdgesWithinRange() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesWeightWithinRange( generator[1], graph[1] ) );
    }


    
    // graph[2]
    @Test
    public void generate_graph2_checkNumberOfVertices() {
        assertTrue( GeneratorTestAuxiliaryMethods.checkNumberOfVertices(graph[2]) );
    }

    @Test
    public void generate_graph2_isNumberOfEdgesMax() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfEdgesMax(graph[2]) );
    }

    @Test
    public void generate_graph2_checkEdgesSituation() {
        assertTrue( GeneratorTestAuxiliaryMethods.checkEdgesSituation(graph[2]) );
    }

    @Test
    public void generate_graph2_areEdgesNotTheSame() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesNotTheSame( graph[2] ) );
    }

    @Test
    public void generate_graph2_areEdgesWithinRange() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesWeightWithinRange( generator[2], graph[2] ) );
    }



    // graph[3]
    @Test
    public void generate_graph3_checkNumberOfVertices() {
        assertTrue( GeneratorTestAuxiliaryMethods.checkNumberOfVertices(graph[3]) );
    }

    @Test
    public void generate_graph3_isNumberOfEdgesMax() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfEdgesMax(graph[3]) );
    }

    @Test
    public void generate_graph3_checkEdgesSituation() {
        assertTrue( GeneratorTestAuxiliaryMethods.checkEdgesSituation(graph[3]) );
    }

    @Test
    public void generate_graph3_areEdgesNotTheSame() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesNotTheSame( graph[3] ) );
    }

    @Test
    public void generate_graph3_areEdgesWithinRange() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesWeightWithinRange( generator[3], graph[3] ) );
    }



    // graph[4]
    @Test
    public void generate_graph4_checkNumberOfVertices() {
        assertTrue( GeneratorTestAuxiliaryMethods.checkNumberOfVertices(graph[4]) );
    }

    @Test
    public void generate_graph4_isNumberOfEdgesMax() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfEdgesMax(graph[4]) );
    }

    @Test
    public void generate_graph4_checkEdgesSituation() {
        assertTrue( GeneratorTestAuxiliaryMethods.checkEdgesSituation(graph[4]) );
    }

    @Test
    public void generate_graph4_areEdgesNotTheSame() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesNotTheSame( graph[4] ) );
    }

    @Test
    public void generate_graph4_areEdgesWithinRange() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesWeightWithinRange( generator[4], graph[4] ) );
    }



    @Test
    public void generate_0columns() {
        assertThrows(IllegalArgumentException.class, () -> new CompleteGraphGenerator(0, 1, 0, 1) );
    }

    @Test
    public void generate_negativNumberOfColumns() {
        assertThrows(IllegalArgumentException.class, () -> new CompleteGraphGenerator(-8, 1, 0, 1) );
    }

    @Test
    public void generate_negativeFromWeight() {
        assertThrows(IllegalArgumentException.class, () -> new CompleteGraphGenerator(4, 5, -10, 1) );
    }

    @Test
    public void generate_fromWeightGreaterThanToWeight() {
        assertThrows(IllegalArgumentException.class, () -> new CompleteGraphGenerator(4, 5, 20, 1) );
    }
}

    
