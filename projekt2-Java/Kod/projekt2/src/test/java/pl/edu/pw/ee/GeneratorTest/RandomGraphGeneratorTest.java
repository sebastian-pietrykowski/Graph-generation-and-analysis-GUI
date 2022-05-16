package pl.edu.pw.ee.GeneratorTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.edu.pw.ee.Graph;
import pl.edu.pw.ee.generator.Generator;
import pl.edu.pw.ee.generator.RandomGraphGenerator;

public class RandomGraphGeneratorTest {
    static Generator generator[];
    static Graph graph[];

    @BeforeClass
    public static void initiateGraphs() {
        generator = new Generator[10];
        graph = new Graph[10];

        // same columns and rows
        generator[0] = new RandomGraphGenerator(4, 4, 0, 1);
        graph[0] = generator[0].generate();

        // same columns and rows, huge graph
        generator[1] = new RandomGraphGenerator(50, 50, 13.4, 87.1323);
        graph[1] = generator[1].generate();

        // different columns and rows
        generator[2] = new RandomGraphGenerator(11, 19, 0.123, 1.8910);
        graph[2] = generator[2].generate();

        // 1 column
        generator[3] = new RandomGraphGenerator(1, 15, 7813.98, 9999.999);
        graph[3] = generator[3].generate();

        // 1 row
        generator[4] = new RandomGraphGenerator(15, 1, 7813.98, 9999.999);
        graph[4] = generator[4].generate();
    }


    // graph[0]
    @Test
    public void generate_sameColumnsAndRows_graph0_isNumberOfVerticesProper() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfVerticesProper(graph[0]) );
    }

    @Test
    public void generate_sameColumnsAndRows_graph0_isNumberOfEdgesLessThanOrEqualToMax() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfEdgesLessThanOrEqualToMax(graph[0]) );
    }

    @Test
    public void generate_sameColumnsAndRows_graph0_areEdgesSituatedProperly() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesSituatedProperly(graph[0]) );
    }

    @Test
    public void generate_sameColumnsAndRows_graph0_areEdgesNotTheSame() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesNotTheSame( graph[0] ) );
    }

    @Test
    public void generate_sameColumnsAndRows_graph0_areEdgesWithinRange() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesWeightWithinRange( generator[0], graph[0] ) );
    }



    // graph[1]
    @Test
    public void generate_sameColumnsAndRowsHugeGraph_graph1_isNumberOfVerticesProper() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfVerticesProper(graph[1]) );
    }

    @Test
    public void generate_sameColumnsAndRowsHugeGraph_graph1_isNumberOfEdgesLessThanOrEqualToMax() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfEdgesLessThanOrEqualToMax(graph[1]) );
    }

    @Test
    public void generate_sameColumnsAndRowsHugeGraph_graph1_areEdgesSituatedProperly() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesSituatedProperly(graph[1]) );
    }

    @Test
    public void generate_sameColumnsAndRowsHugeGraph_graph1_areEdgesNotTheSame() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesNotTheSame( graph[1] ) );
    }

    @Test
    public void generate_sameColumnsAndRowsHugeGraph_graph1_areEdgesWithinRange() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesWeightWithinRange( generator[1], graph[1] ) );
    }

    
    // graph[2]
    @Test
    public void generate_differentColumnsAndRows_graph2_isNumberOfVerticesProper() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfVerticesProper(graph[2]) );
    }

    @Test
    public void generate_differentColumnsAndRows_graph2_isNumberOfEdgesLessThanOrEqualToMax() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfEdgesLessThanOrEqualToMax(graph[2]) );
    }

    @Test
    public void generate_differentColumnsAndRows_graph2_areEdgesSituatedProperly() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesSituatedProperly(graph[2]) );
    }

    @Test
    public void generate_differentColumnsAndRows_graph2_areEdgesNotTheSame() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesNotTheSame( graph[2] ) );
    }

    @Test
    public void generate_differentColumnsAndRows_graph2_areEdgesWithinRange() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesWeightWithinRange( generator[2], graph[2] ) );
    }



    // graph[3]
    @Test
    public void generate_1column_graph3_isNumberOfVerticesProper() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfVerticesProper(graph[3]) );
    }

    @Test
    public void generate_1column_graph3_isNumberOfEdgesLessThanOrEqualToMax() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfEdgesLessThanOrEqualToMax(graph[3]) );
    }

    @Test
    public void generate_1column_graph3_areEdgesSituatedProperly() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesSituatedProperly(graph[3]) );
    }

    @Test
    public void generate_1column_graph3_areEdgesNotTheSame() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesNotTheSame( graph[3] ) );
    }

    @Test
    public void generate_1column_graph3_areEdgesWithinRange() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesWeightWithinRange( generator[3], graph[3] ) );
    }



    // graph[4]
    @Test
    public void generate_1row_graph4_isNumberOfVerticesProper() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfVerticesProper(graph[4]) );
    }

    @Test
    public void generate_1row_graph4_isNumberOfEdgesLessThanOrEqualToMax() {
        assertTrue( GeneratorTestAuxiliaryMethods.isNumberOfEdgesLessThanOrEqualToMax(graph[4]) );
    }

    @Test
    public void generate_1row_graph4_areEdgesSituatedProperly() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesSituatedProperly(graph[4]) );
    }

    @Test
    public void generate_1row_graph4_areEdgesNotTheSame() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesNotTheSame( graph[4] ) );
    }

    @Test
    public void generate_1row_graph4_areEdgesWithinRange() {
        assertTrue( GeneratorTestAuxiliaryMethods.areEdgesWeightWithinRange( generator[4], graph[4] ) );
    }


    // cases with errors

    @Test
    public void generate_0columns() {
        assertThrows(IllegalArgumentException.class, () -> new RandomGraphGenerator(0, 1, 0, 1) );
    }

    @Test
    public void generate_0rows() {
        assertThrows(IllegalArgumentException.class, () -> new RandomGraphGenerator(1, 0, 0, 1) );
    }

    @Test
    public void generate_0columnsand0rows() {
        assertThrows(IllegalArgumentException.class, () -> new RandomGraphGenerator(0, 0, 0, 1) );
    }

    @Test
    public void generate_negativNumberOfColumns() {
        assertThrows(IllegalArgumentException.class, () -> new RandomGraphGenerator(-8, 1, 0, 1) );
    }

    @Test
    public void generate_negativNumberOfRows() {
        assertThrows(IllegalArgumentException.class, () -> new RandomGraphGenerator(5, -1, 0, 1) );
    }

    @Test
    public void generate_negativNumberOfColumnsAndRows() {
        assertThrows(IllegalArgumentException.class, () -> new RandomGraphGenerator(-5, -1, 0, 1) );
    }

    @Test
    public void generate_negativeFromWeight() {
        assertThrows(IllegalArgumentException.class, () -> new RandomGraphGenerator(4, 5, -10, 1) );
    }

    @Test
    public void generate_negativeToWeight() {
        assertThrows(IllegalArgumentException.class, () -> new RandomGraphGenerator(4, 5, 10, -1) );
    }

    @Test
    public void generate_negativeFromWeightAndToWeight() {
        assertThrows(IllegalArgumentException.class, () -> new RandomGraphGenerator(4, -5, -10, 1) );
    }

    @Test
    public void generate_fromWeightGreaterThanToWeight() {
        assertThrows(IllegalArgumentException.class, () -> new RandomGraphGenerator(4, 5, 20, 1) );
    }
}
