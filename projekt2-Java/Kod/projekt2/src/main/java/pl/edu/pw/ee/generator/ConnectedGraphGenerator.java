package pl.edu.pw.ee.generator;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import pl.edu.pw.ee.Graph;

public class ConnectedGraphGenerator extends AbstractGenerator implements Generator {

    private Graph graph;
    private int columns;
    private int rows;
    private double fromWeight;
    private double toWeight;
    
    private int startVertexNumber; // vertex where generating graph beggins
    private Set<Integer> visited; // set of vertices to which there exists connection from startVertexNumber vertex
    private Set<Integer> frontier; /* set of vertices to which it is possible to create
                                    single edge from one of the vertices from set visited */

    public ConnectedGraphGenerator( int columns, int rows, double fromWeight, double toWeight, int startVertexNumber ) {
        super( columns, rows, fromWeight, toWeight );
        this.columns = columns;
        this.rows = rows;
        this.fromWeight = fromWeight;
        this.toWeight = toWeight;
        this.startVertexNumber = startVertexNumber;
    }

    @Override
    public Graph generate() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * Finds already visited vertex neighboring to <code>vertex</code>.
     * @param vertex number of vertex for which a neighbor from set <code>visited</code> will be found
     * @return number of vertex from set <code>visited</code> neighboring to <code>vertex</code>
     */
    private int visitedNeighbor( int frontierVertex ) {
        Set<Integer> potenialNeighbors = graph.potenialNeighbors(frontierVertex);
        Random random = new Random();
        int neighborNumber = random.nextInt( potenialNeighbors.size() );
        Integer[] potenialNeighborsArray = potenialNeighbors.toArray( new Integer[potenialNeighbors.size()] );
        return potenialNeighborsArray[ neighborNumber ];
    }

    /**
     * Returns array of vertices to which there can be created edge leading from
     * <code>visitedVertex</code>, that are not present in set <code>visited</code>.
     * @param visitedVertex number of vertex for which unvisited neighbrors will be found
     * @return array of vertices neighboring to <code>visitedVertex</code> not present
     *         in set <code>visited</code>
     */
    private ArrayList<Integer> unvisitedNeighbors( int visitedVertex ) {
        Set<Integer> potentialNeighbors = graph.potenialNeighbors( visitedVertex );
        ArrayList<Integer> unvisitedNeighbors = new ArrayList<>();
        for( Integer neighbor: potentialNeighbors)
            if( !visited.contains( neighbor ))
            unvisitedNeighbors.add( neighbor );
        return unvisitedNeighbors;
    }
}
