package ca.ubc.ece.cpen221.graphs.test;

import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;
import ca.ubc.ece.cpen221.graphs.one.AdjacencyListGraph;
import ca.ubc.ece.cpen221.graphs.one.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.graphs.one.Algorithms;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class breadthFirstSearchTests {
    //vertices for the adjacencyList
    final Vertex<Integer> v1 = new Vertex<>("NodeA",12314);
    final Vertex<Integer> v2 = new Vertex<>("NodeB",24125);
    final Vertex<Integer> v3 = new Vertex<>("NodeC",31425);
    final Vertex<Integer> v4 = new Vertex<>("NodeD",42512);
    final Vertex<Integer> v5 = new Vertex<>("NodeE",57374);
    final Vertex<Integer> v6 = new Vertex<>("NodeF",69597);

    //vertices for the adjacencyMatrix
    final Vertex<String> vertex1 = new Vertex<>("1", "CPEN");
    final Vertex<String> vertex2 = new Vertex<>("5", "211");
    final Vertex<String> vertex3 = new Vertex<>("2", "rocks!");
    final Vertex<String> vertex4 = new Vertex<>("9", ":D");
    final Vertex<String> vertex5 = new Vertex<>("7", "Yay");
    final Vertex<String> vertex6 = new Vertex<>("1231", "New");
    final Vertex<String> vertex7 = new Vertex<>("1441", "New2");

    final List<Vertex<Integer>> BFS_test1_result1 = new ArrayList<>(Arrays.asList(v1,v2,v3,v4,v5,v6));
    final List<Vertex<Integer>> BFS_test1_result2 = new ArrayList<>(Arrays.asList(v2,v5,v6));
    final List<Vertex<Integer>> BFS_test1_result3 = new ArrayList<>(Arrays.asList(v3));
    final List<Vertex<Integer>> BFS_test1_result4 = new ArrayList<>(Arrays.asList(v4,v6,v5));
    final List<Vertex<Integer>> BFS_test1_result5 = new ArrayList<>(Arrays.asList(v5));
    final List<Vertex<Integer>> BFS_test1_result6 = new ArrayList<>(Arrays.asList(v6,v5));

    final List<Vertex<Integer>> BFS_test2_result1 = new ArrayList<>(Arrays.asList(v1,v3,v4,v6,v5,v2));
    final List<Vertex<Integer>> BFS_test2_result2 = new ArrayList<>(Arrays.asList(v2,v3,v5,v1,v4,v6));
    final List<Vertex<Integer>> BFS_test2_result3 = new ArrayList<>(Arrays.asList(v3,v1,v4,v6,v5,v2));
    final List<Vertex<Integer>> BFS_test2_result4 = new ArrayList<>(Arrays.asList(v4,v5,v2,v3,v1,v6));
    final List<Vertex<Integer>> BFS_test2_result5 = new ArrayList<>(Arrays.asList(v5,v2,v3,v1,v4,v6));
    final List<Vertex<Integer>> BFS_test2_result6 = new ArrayList<>(Arrays.asList(v6,v3,v4,v5,v1,v2));

    final List<Vertex<String>> BFS_test3_result1 = new ArrayList<>(Arrays.asList(vertex1,vertex2,vertex3));
    final List<Vertex<String>> BFS_test3_result2 = new ArrayList<>(Arrays.asList(vertex2,vertex3,vertex1));
    final List<Vertex<String>> BFS_test3_result3 = new ArrayList<>(Arrays.asList(vertex3,vertex1,vertex2));

    final List<Vertex<String>> BFS_test4_result1 = new ArrayList<>(Arrays.asList(vertex1));
    final List<Vertex<String>> BFS_test4_result2 = new ArrayList<>(Arrays.asList(vertex2,vertex3,vertex7,vertex6));
    final List<Vertex<String>> BFS_test4_result3 = new ArrayList<>(Arrays.asList(vertex3,vertex6));
    final List<Vertex<String>> BFS_test4_result4 = new ArrayList<>(Arrays.asList(vertex4));
    final List<Vertex<String>> BFS_test4_result5 = new ArrayList<>(Arrays.asList(vertex5,vertex2,vertex4,vertex3,vertex7,vertex6));
    final List<Vertex<String>> BFS_test4_result6 = new ArrayList<>(Arrays.asList(vertex6));
    final List<Vertex<String>> BFS_test4_result7 = new ArrayList<>(Arrays.asList(vertex7));

    @Test
    public void depthFirstSearchGraph1Test() {
        Graph<Integer> original = TestAdjacencyListGraph1();
        Set<List<Vertex<Integer>>> expected = new HashSet<>();
        expected.add(BFS_test1_result1);
        expected.add(BFS_test1_result2);
        expected.add(BFS_test1_result3);
        expected.add(BFS_test1_result4);
        expected.add(BFS_test1_result5);
        expected.add(BFS_test1_result6);
        assertEquals(expected, Algorithms.breadthFirstSearch(original));
    }

    @Test
    public void depthFirstSearchGraph2Test() {
        Graph<Integer> original = TestAdjacencyListGraph2();
        Set<List<Vertex<Integer>>> expected = new HashSet<>();
        expected.add(BFS_test2_result1);
        expected.add(BFS_test2_result2);
        expected.add(BFS_test2_result3);
        expected.add(BFS_test2_result4);
        expected.add(BFS_test2_result5);
        expected.add(BFS_test2_result6);
        assertEquals(expected, Algorithms.breadthFirstSearch(original));
    }

    @Test
    public void depthFirstSearchGraph3Test() {
        Graph<String> original = TestAdjacencyMatrixGraph3();
        Set<List<Vertex<String>>> expected = new HashSet<>();
        expected.add(BFS_test3_result1);
        expected.add(BFS_test3_result2);
        expected.add(BFS_test3_result3);
        assertEquals(expected, Algorithms.breadthFirstSearch(original));
    }

    @Test
    public void depthFirstSearchGraph4Test() {
        Graph<String> original = TestAdjacencyMatrixGraph4();
        Set<List<Vertex<String>>> expected = new HashSet<>();
        expected.add(BFS_test4_result1);
        expected.add(BFS_test4_result2);
        expected.add(BFS_test4_result3);
        expected.add(BFS_test4_result4);
        expected.add(BFS_test4_result5);
        expected.add(BFS_test4_result6);
        expected.add(BFS_test4_result7);
        assertEquals(expected, Algorithms.breadthFirstSearch(original));

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

    public Graph<Integer> TestAdjacencyListGraph2() {
        Graph<Integer> graph = new AdjacencyListGraph<>();
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v1);
        graph.addVertex(v4);
        graph.addEdge(v1,v3);
        graph.addEdge(v2,v3);
        graph.addEdge(v2,v5);
        graph.addEdge(v3,v1);
        graph.addEdge(v3,v4);
        graph.addEdge(v3,v6);
        graph.addEdge(v4,v5);
        graph.addEdge(v5,v2);
        graph.addEdge(v6,v3);
        graph.addEdge(v6,v4);
        graph.addEdge(v6,v5);
        return graph;
    }

    public Graph<String> TestAdjacencyMatrixGraph3() {
        Graph<String> graph1 = new AdjacencyMatrixGraph<>();
        graph1.addVertex(vertex1);
        graph1.addVertex(vertex2);
        graph1.addVertex(vertex3);
        graph1.addEdge(vertex1, vertex2);
        graph1.addEdge(vertex2, vertex3);
        graph1.addEdge(vertex3, vertex1);
        graph1.addEdge(vertex1, vertex3);
        graph1.addEdge(vertex3, vertex2);

        return graph1;
    }

    public Graph<String> TestAdjacencyMatrixGraph4() {
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
