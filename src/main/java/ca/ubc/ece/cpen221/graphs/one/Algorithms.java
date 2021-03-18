package ca.ubc.ece.cpen221.graphs.one;

import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;


import java.util.*;

public class Algorithms {

    /**
     * *********************** Algorithms ****************************
     *
     * Please see the README for this machine problem for a more detailed
     * specification of the behavior of each method that one should
     * implement.
     */

    /**
     * Compute the shortest distance between a vertex a and a vertex b starting from a and
     * traversing to b. The shortest distance represents the number of edges between a and b.
     * If there is no path from a to b, -1 is returned.
     *
     * @param graph to perform the method on
     * @param a the vertex to start from
     * @param b the finishing vertex
     * @return count - the distance between a and b
     */
    public static <T> int shortestDistance(Graph<T> graph, Vertex<T> a, Vertex<T> b) {
        List<Vertex<T>> edges = graph.getNeighbors(a);
        List<Vertex<T>> queue = new ArrayList<>();
        List<Vertex<T>> tier = new ArrayList<>();
        int count = 1;
        int tierexit = 0;

        if(a.equals(b)) {
            return 0;
        }

        for (int i = 0; i < edges.size(); i++) {
            if (!b.equals(edges.get(i))) {
                queue.add(edges.get(i));
                tier.add(edges.get(i));
            }
            else {
                return 1;
            }
        }
        count++;

        while(queue.size() != 0) {
            edges = graph.getNeighbors(queue.get(0));
            for(int j = 0; j < tier.size(); j++) {
                for(int p = 0; p < queue.size(); p++) {
                    if(tier.get(j).equals(queue.get(p))) {
                        tierexit++;
                    }
                }
            }
            if(tierexit == 0) {
                tier.clear();
                tier = new ArrayList<>(queue);
                count++;
            }

            for (int k = 0; k < edges.size(); k++) {
                if (!b.equals(edges.get(k))) {
                    queue.add(edges.get(k));
                }
                else {
                    return count;
                }
            }
            if(queue.size() > 0) {
                queue.remove(0);
            }
            tierexit = 0;
        }

        return -1;
    }

    /**
     * Perform a complete depth first search of the given
     * graph. Start with the search at each vertex of the
     * graph and create a list of the vertices visited.
     * Return a set where each element of the set is a list
     * of elements seen by starting a DFS at a specific
     * vertex of the graph (the number of elements in the
     * returned set should correspond to the number of graph
     * vertices).
     *
     * @param graph graph to perform DFS on
     * @return DFS_traversal - set of lists of DFS traversal paths
     */
    public static <T> Set<List<Vertex<T>>> depthFirstSearch(Graph<T> graph) {
        Set<List<Vertex<T>>> DFS_traversal = new HashSet<>();
        List<Vertex<T>> Vertices = graph.getVertices();

        for (int i = 0; i < Vertices.size(); i++) {
            DFS<T> currGraph = new DFS<>(graph);
            Vertex<T> startV = Vertices.get(i);
            DFS_traversal.add(currGraph.DFS_start(startV));
        }

        return DFS_traversal;
    }

    /**
     * Helper function for depthFirstSearch
     * @param <T>
     */
    private static class DFS<T> {
        private Graph<T> graph;
        private List<Vertex<T>> DFSpath = new ArrayList<>();

        public DFS(Graph<T> graph_) {
            graph = graph_;
        }

        public List<Vertex<T>> DFS_start(Vertex<T> V) {
            DFSpath.add(V);
            DFS_main(graph, V);
            return DFSpath;
        }

        private void DFS_main(Graph<T> graph, Vertex<T> currV) {
            List<Vertex<T>> edges = graph.getNeighbors(currV);
            for (int i = 0; i < edges.size(); i++) {
                if (!DFSpath.contains(edges.get(i))) {
                    DFSpath.add(edges.get(i));
                    DFS_main(graph, edges.get(i));
                }
            }
        }
    }

    /**
     * Perform a complete breadth first search of the given
     * graph. Start with the search at each vertex of the
     * graph and create a list of the vertices visited.
     * Return a set where each element of the set is a list
     * of elements seen by starting a BFS at a specific
     * vertex of the graph (the number of elements in the
     * returned set should correspond to the number of graph
     * vertices).
     *
     * @param graph graph to perform BFS on
     * @return BFS_traversal - set of lists of BFS traversal paths
     */
    public static <T> Set<List<Vertex<T>>> breadthFirstSearch(Graph<T> graph) {
        Set<List<Vertex<T>>> BFS_traversal = new HashSet<>();
        List<Vertex<T>> Vertices = graph.getVertices();

        for (int i = 0; i < Vertices.size(); i++) {
            BFS<T> currGraph = new BFS<T>(graph);
            Vertex<T> startV = Vertices.get(i);
            BFS_traversal.add(currGraph.BFS_start(startV));
        }

        return BFS_traversal;
    }

    /**
     * Helper class for breadthFirstSearch
     *
     * @param <T>
     */
    private static class BFS<T> {
        private Graph<T> graph;
        private List<Vertex<T>> BFSpath = new ArrayList<>();

        public BFS(Graph<T> graph_) {
            graph = graph_;
        }

        public List<Vertex<T>> BFS_start(Vertex<T> V) {
            BFSpath.add(V);
            BFS_main(graph, V);
            return BFSpath;
        }

        private void BFS_main(Graph<T> graph, Vertex<T> currV) {
            List<Vertex<T>> edges = graph.getNeighbors(currV);
            List<Vertex<T>> queue = new ArrayList<>();

            for (int i = 0; i < edges.size(); i++) {
                if (!BFSpath.contains(edges.get(i))) {
                    BFSpath.add(edges.get(i));
                    queue.add(edges.get(i));
                }
            }

            while(queue.size() != 0) {
                edges = graph.getNeighbors(queue.get(0));
                for (int k = 0; k < edges.size(); k++) {
                    if (!BFSpath.contains(edges.get(k))) {
                        BFSpath.add(edges.get(k));
                        queue.add(edges.get(k));
                    }
                }
                queue.remove(0);
            }

        }
    }

    /**
     * Computes the diameter of the graph. The diameter of a graph
     * is the maximum eccentricity of any vertex in the graph (the greatest
     * distance between any pair of vertices). The maximum shortest path
     * between any pair of vertices is the diameter of the graph.
     *
     * @param graph graph to compute diameter
     * @return diam - the diameter of the graph
     *
     */
    public static <T> int diameter(Graph<T> graph) {
        ArrayList<Integer> diamValues = new ArrayList<Integer>();
        List<Vertex<T>> Vertices = graph.getVertices();
        int diam = 0;
        int noEdges = 0;

        if(Vertices.size() == 0) {
            return 0;
        }

        for (int i = 0; i < Vertices.size(); i++) {
            BFSDiameter<T> currGraph = new BFSDiameter<T>(graph);
            Vertex<T> startV = Vertices.get(i);
            diamValues.add(currGraph.BFSDiam_main(startV));
        }

        for(int j = 0; j < diamValues.size(); j++) {
            if(diamValues.get(j) > diam) {
                diam = diamValues.get(j);
            }
            if(diamValues.get(j) == 0) {
                noEdges++;
            }
        }

        if(noEdges == diamValues.size()) {
            return Integer.MAX_VALUE; //infinite diameter, will strengthen postcondition later
        }

        return diam;
    }

    /**
     * Helper class for diameter
     */
    private static class BFSDiameter<T> {
        private Graph<T> graph;

        public BFSDiameter(Graph<T> graph_) {
            graph = graph_;
        }

        public int BFSDiam_main(Vertex<T> currV) {
            ArrayList<Integer> countValues = new ArrayList<Integer>();
            List<Vertex<T>> Vertices = graph.getVertices();
            int maxDistance = 0;

            for(int i = 0; i < Vertices.size(); i++) {
                countValues.add(shortestDistance(graph, currV, Vertices.get(i)));
            }

            for(int y = 0; y < countValues.size(); y++) {
                if(countValues.get(y) > maxDistance) {
                    maxDistance = countValues.get(y);
                }
            }

            return maxDistance;
        }
    }


    /**
     * Searches the given graph for edges between v -> v1 and v -> v2 where v1 and v2 are vertices
     * given by the client
     *
     * @param graph graph to perform the search
     * @param v1 first vertex
     * @param v2 second vertex
     * @param <T> parameter of vertex/graph
     * @return List of all vertices that has an edge with v1 and v2
     */
    public static <T> List<Vertex<T>> commonUpstreamVertices (Graph<T> graph, Vertex<T> v1, Vertex<T> v2) {
        List<Vertex<T>> list = new ArrayList<>();
        List<Vertex<T>> allVertices = graph.getVertices();

        for( int index = 0; index < allVertices.size(); index++){
            if(graph.edgeExists(allVertices.get(index), v1) && graph.edgeExists(allVertices.get(index), v2)){
                list.add(allVertices.get(index));
            }
        }
        return list;
    }

    /**
     * Searches the given graph and returns a list of vertices where there is an edge
     * from v1 -> v and v2 -> v where v1 and v2 are chosen by the client
     *
     * @param graph graph to perform the search
     * @param v1 first vertex
     * @param v2 second vertex
     * @param <T> parameter of vertex/graph
     * @return List of all vertices where v1 and v2 have an edge with it
     */
    public static <T> List<Vertex<T>> commonDownstreamVertices (Graph<T> graph, Vertex<T> v1, Vertex<T> v2){
        List<Vertex<T>> list = new ArrayList<>();
        List<Vertex<T>> allVertices = graph.getVertices();

        for( int index = 0; index < allVertices.size(); index++){
            if(graph.edgeExists(v1, allVertices.get(index))&& graph.edgeExists(v2, allVertices.get(index))){
                list.add(allVertices.get(index));
            }
        }

        return list;
    }

}