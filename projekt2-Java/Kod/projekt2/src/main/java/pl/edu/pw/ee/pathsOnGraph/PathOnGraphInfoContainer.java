package pl.edu.pw.ee.pathsOnGraph;

import java.util.ArrayList;

public class PathOnGraphInfoContainer {
    
    ArrayList<PathOnGraphInfo> pathsInfos = new ArrayList<>();
    int counter = 1;

    public PathOnGraphInfoContainer() {}


    public void addPath( PathOnGraphInfo pathInfo ) {
        pathsInfos.add(pathInfo);
        pathInfo.setPathNumber(counter++);
    }

    public void removePathByPathNumber( int pathNumber ) {
        for( PathOnGraphInfo pathInfo: pathsInfos )
            if( pathInfo.getPathNumber() == pathNumber ) {
                pathsInfos.remove(pathInfo);
                break;
            }
    }

    public void removePathByArrayIndex( int arrayIndex ) {
        pathsInfos.remove(arrayIndex);
    }

    public int getPathsNumber() { return pathsInfos.size(); }

    public ArrayList<PathOnGraphInfo> getElements() { return pathsInfos; }
}
