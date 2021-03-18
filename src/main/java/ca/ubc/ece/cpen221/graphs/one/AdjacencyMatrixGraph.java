package ca.ubc.ece.cpen221.graphs.one;

import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

/******************************************************************************
 *  Dependencies: Graph.java Vertex.java
 *
 *  A data type that represents a Graph using Adjacency Matrices.
 *
 ******************************************************************************/

public class AdjacencyMatrixGraph<T> implements Graph<T> {
    /**
     * REP INVARIANT
     * -A vertex is added only once
     * -An edge between two vertices is only added once
     *
     *
     * ABSTRACTION FUNCTION
     * -represents an graph of type adjacency matrix with graph.size()+1 vertices.
     * A 1 in graph[v1][v2] represents an edge from v1 -> v2 and a 0 otherwise.
     *
     */
    private int[][] graph;
    private int size;
    HashMap<Vertex<T>, Integer> vertexMap;
    HashMap<Integer, Vertex<T>> indexMap;
    private int vertexCount;

    /**
     * Constructor to create a Graph of type AdjacencyMatrixGraph
     */
    public AdjacencyMatrixGraph() {
        graph = new int[10][10];
        size = graph.length;
        vertexCount = 0;
        vertexMap = new HashMap<>();
        indexMap = new HashMap<>();
    }

    /**
     * Private method to grow the array size once it reaches capacity
     *
     * @param oldSize the size the array was before
     */
    private void growMatrix(int oldSize) {
        int[][] newGraph = new int[size][size];

        for (int row = 0; row < oldSize; row++) {
            for (int col = 0; col < oldSize; col++) {
                newGraph[row][col] = graph[row][col];
            }
        }

        graph = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                graph[row][col] = newGraph[row][col];
            }
        }
    }

    /**
     * Grow the size of the matrix in order to add more vertices
     */
    private void growSize() {
        int oldSize = size;
        size += 10;

        growMatrix(oldSize);

    }

    /**
     * Adds a vertex to the graph.
     * <p>
     * Precondition: v is not already a vertex in the graph
     * </p>
     * @param v vertex to be added to graph
     */
    public void addVertex(Vertex<T> v) {
        vertexMap.put(v, vertexCount);
        indexMap.put(vertexCount, v);

        if (vertexMap.size() == size) {
            growSize();
        }
        vertexCount++;
    }

    /**
     * Adds an edge from v1 to v2.
     * <p>
     * Precondition: v1 and v2 are vertices in the graph
     * </p>
     * @param v1 the first vertex to add to graph
     * @param v2 the second vertex to add to graph
     */
    public void addEdge(Vertex<T> v1, Vertex<T> v2) {
        int index1 = vertexMap.get(v1);
        int index2 = vertexMap.get(v2);

        graph[index2][index1] = 1;
    }

    /**
     * Check if there is an edge from v1 to v2.
     * <p>
     * Precondition: v1 and v2 are vertices in the graph
     * </p>
     * <p>
     * Postcondition: return true iff an edge from v1 connects to v2
     * </p>
     */
    public boolean edgeExists(Vertex<T> v1, Vertex<T> v2) {
        int index1 = vertexMap.get(v1);
        int index2 = vertexMap.get(v2);

        if (graph[index2][index1] != 1) {
            return false;
        }

        return true;
    }

    /**
     * Get an array containing all vertices adjacent to v.
     * <p>
     * Precondition: v is a vertex in the graph
     * </p>
     * <p>
     * Postcondition: returns a list containing each vertex w such that there is
     * an edge from v to w. The size of the list must be as small as possible
     * (No trailing null elements). This method should return a list of size 0
     * iff v has no downstream neighbors.
     * </p>
     */
    public List<Vertex<T>> getNeighbors(Vertex<T> v) {
        List<Vertex<T>> list = new ArrayList<>();

        int colIndex = vertexMap.get(v);

        for (int row = 0; row < size; row++) {
            if (graph[row][colIndex] == 1) {
                list.add(indexMap.get(row));
            }
        }
        return list;
    }

    /**
     * Get all vertices in the graph.
     * <p>
     * Postcondition: returns a list containing all vertices in the graph,
     * sorted by label in non-descending order.
     * This method should return a list of size 0 iff the graph has no vertices.
     * </p>
     */
    public List<Vertex<T>> getVertices() {
        List<Vertex<T>> vertices = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for( int i = 0; i < vertexCount; i++){
            labels.add(indexMap.get(i).getLabel());
        }

        Collections.sort(labels);
        
        for(int j = 0; j < vertexCount; j++) {
            for (int i = 0; i < vertexCount; i++) {
                if (labels.get(j).equals(indexMap.get(i).getLabel())) {
                    vertices.add(indexMap.get(i));
                }
            }
        }

        return vertices;
    }

}
