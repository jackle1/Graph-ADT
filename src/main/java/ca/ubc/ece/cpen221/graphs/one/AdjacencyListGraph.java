package ca.ubc.ece.cpen221.graphs.one;

import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/******************************************************************************
 *  Dependencies: Graph.java Vertex.java
 *
 *  A data type that represents a Graph using Adjacency Lists.
 *
 ******************************************************************************/


public class AdjacencyListGraph<T> implements Graph<T> {
    HashMap<Vertex<T>, List<Vertex<T>>> graph;

    public AdjacencyListGraph() {
        this.graph = new HashMap<>();
    }

    public void addVertex(Vertex<T> v) {
        graph.put(v, new ArrayList<>());
    }

    public void addEdge(Vertex<T> v1, Vertex<T> v2) {
        List<Vertex<T>> toAdd = graph.get(v1);
        toAdd.add(v2);
        graph.replace(v1,toAdd);
    }

    public boolean edgeExists(Vertex<T> v1, Vertex<T> v2) {
        List<Vertex<T>> v1connections = graph.get(v1);

        return (v1connections.contains(v2));
    }

    public List<Vertex<T>> getNeighbors(Vertex<T> v) {
        return graph.get(v);
    }

    public List<Vertex<T>> getVertices() {
        List<Vertex<T>> originalList = new ArrayList<>();
        List<Vertex<T>> allVertices = new ArrayList<>();
        ArrayList<String> vertexStrings = new ArrayList<String>();
        String vtostring;

        for ( Vertex<T> vertex : graph.keySet() ) {
            originalList.add(vertex);
            vtostring = vertex.getLabel();
            vertexStrings.add(vtostring);
        }
        Collections.sort(vertexStrings);

        for( int i = 0; i < vertexStrings.size(); i++) {
            for( int j = 0; j < vertexStrings.size(); j++) {
                if(vertexStrings.get(i).equals(originalList.get(j).getLabel())) {
                    allVertices.add(originalList.get(j));
                }
            }
        }

        return allVertices;
    }



    public HashMap<Vertex<T>, List<Vertex<T>>> getGraph() {
        return graph;
    }
}
