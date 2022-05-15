package pl.edu.pw.ee;

import pl.edu.pw.ee.generator.*;

public class Main {
    public static void main(String[] args) {

        
        Generator g1 = new CompleteGraphGenerator(3, 3, 0, 1);
        Graph graph1 = g1.generate();
        
        System.out.println("------------------------------");
        /*
        Generator g2 = new RandomGraphGenerator(3, 3, 0, 1);
        Graph graph2 = g2.generate();
        */
        /*
        Generator g3 = new ConnectedGraphGenerator(3, 3, 0, 1, 0);
        Graph graph3 = g3.generate();
        */

        
    }
    
}
