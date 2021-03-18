package ca.ubc.ece.cpen221.graphs.test;

import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;
import ca.ubc.ece.cpen221.graphs.one.Algorithms;
import ca.ubc.ece.cpen221.graphs.one.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.graphs.one.AdjacencyListGraph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class shortestDistanceTests {
    final Vertex<Integer> v1 = new Vertex<>("NodeA",12314);
    final Vertex<Integer> v2 = new Vertex<>("NodeB",24125);
    final Vertex<Integer> v3 = new Vertex<>("NodeC",31425);
    final Vertex<Integer> v4 = new Vertex<>("NodeD",42512);
    final Vertex<Integer> v5 = new Vertex<>("NodeE",57374);
    final Vertex<Integer> v6 = new Vertex<>("NodeF",69597);

    final Vertex<String> vertex1 = new Vertex<>("1", "CPEN");
    final Vertex<String> vertex2 = new Vertex<>("5", "211");
    final Vertex<String> vertex3 = new Vertex<>("2", "rocks!");
    final Vertex<String> vertex4 = new Vertex<>("9", ":D");
    final Vertex<String> vertex5 = new Vertex<>("7", "Yay");
    final Vertex<String> vertex6 = new Vertex<>("1231", "New");
    final Vertex<String> vertex7 = new Vertex<>("1441", "New2");

    @Test
    public void shortestDistanceGraph1Test1() {
        Graph<Integer> original = TestAdjacencyListGraph1();
        int expected = 2;
        assertEquals(expected, Algorithms.shortestDistance(original,v4,v5));
    }

    @Test
    public void shortestDistanceGraph1Test2() {
        Graph<Integer> original = TestAdjacencyListGraph1();
        int expected = 0;
        assertEquals(expected, Algorithms.shortestDistance(original,v4,v4));
    }

    @Test
    public void shortestDistanceGraph2Test1() {
        Graph<String> original = TestAdjacencyMatrixGraph2();
        int expected = 3;
        assertEquals(expected, Algorithms.shortestDistance(original,vertex5,vertex6));
    }

    public Graph<Integer> TestAdjacencyListGraph1() {
        Graph<Integer> graph = new AdjacencyListGraph<>();
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addEdge(v1,v2);
        graph.addEdge(v1,v3);
        graph.addEdge(v1,v4);
        graph.addEdge(v2,v5);
        graph.addEdge(v2,v6);
        graph.addEdge(v4,v6);
        graph.addEdge(v6,v5);
        return graph;
    }
    public Graph<String> TestAdjacencyMatrixGraph2() {
        Graph<String> graph2 = new AdjacencyMatrixGraph<>();
        graph2.addVertex(vertex1);
        graph2.addVertex(vertex2);
        graph2.addVertex(vertex3);
        graph2.addVertex(vertex4);
        graph2.addVertex(vertex5);
        graph2.addVertex(vertex6);
        graph2.addVertex(vertex7);
        graph2.addEdge(vertex2, vertex3);
        graph2.addEdge(vertex2, vertex7);
        graph2.addEdge(vertex3, vertex6);
        graph2.addEdge(vertex5, vertex2);
        graph2.addEdge(vertex5, vertex4);

        return graph2;
    }
}