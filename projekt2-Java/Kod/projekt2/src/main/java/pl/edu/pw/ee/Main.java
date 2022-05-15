package pl.edu.pw.ee;

import pl.edu.pw.ee.generator.Generator;
import pl.edu.pw.ee.generator.CompleteGraphGenerator;
import pl.edu.pw.ee.generator.Generator;

public class Main {
    public static void main(String[] args) {
        Generator g1 = new CompleteGraphGenerator(3, 3, 0, 1);
        g1.generate();

        
    }
    
}
