package ca.ubc.ece.cpen221.graphs.test;

import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;
import ca.ubc.ece.cpen221.graphs.one.AdjacencyListGraph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class AdjacencyListTests {
    final Vertex<Integer> v1 = new Vertex<>("NodeA",12314);
    final Vertex<Integer> v2 = new Vertex<>("NodeB",24125);
    final Vertex<Integer> v3 = new Vertex<>("NodeC",31425);
    final Vertex<Integer> v4 = new Vertex<>("NodeD",42512);
    final Vertex<Integer> v5 = new Vertex<>("NodeE",57374);
    final Vertex<Integer> v6 = new Vertex<>("NodeF",69597);


    @Test
    public void addVertexTest() {
        Graph<Integer> original = testVertices();
        HashMap<Vertex<Integer>, List<Vertex<Integer>>> expected = new HashMap<>();
        expected.put(v1, new ArrayList<>());
        expected.put(v2, new ArrayList<>());
        expected.put(v3, new ArrayList<>());
        expected.put(v4, new ArrayList<>());
        expected.put(v5, new ArrayList<>());
        expected.put(v6, new ArrayList<>()); // VV i just put this here because IntelliJ suggested doing this, not too sure if it's a right thing to do, but we can't use methods thats not in the graph interface
        assertEquals(expected, ((AdjacencyListGraph<Integer>) original).getGraph());
    }

    public Graph<Integer> testVertices() {
        Graph<Integer> in = new AdjacencyListGraph<>();
        in.addVertex(v1);
        in.addVertex(v2);
        in.addVertex(v3);
        in.addVertex(v4);
        in.addVertex(v5);
        in.addVertex(v6);
        return in;
    }

    @Test
    public void edgeExistTest1() {
        Graph<Integer> original = TestGraph1();
        assertTrue(original.edgeExists(v1, v4));
        assertTrue(original.edgeExists(v4, v1));
        assertTrue(original.edgeExists(v6, v1));
        assertTrue(original.edgeExists(v4, v6));
    }

    @Test
    public void edgeExistTest2() {
        Graph<Integer> original = TestGraph2();
        assertTrue(original.edgeExists(v2, v5));
        assertTrue(original.edgeExists(v2, v3));
        assertFalse(original.edgeExists(v3, v5));
        assertFalse(original.edgeExists(v5, v6));
        assertTrue(original.edgeExists(v3,v6));
    }

    @Test
    public void getNeighboursTest1() {
        Graph<Integer> original = TestGraph1();
        List<Vertex<Integer>> expected1 = new ArrayList<>(Arrays.asList(v4,v6));
        List<Vertex<Integer>> expected2 = new ArrayList<>(Arrays.asList(v1,v6));
        List<Vertex<Integer>> expected3 = new ArrayList<>(Arrays.asList(v1));
        assertEquals(expected1,original.getNeighbors(v1));
        assertEquals(expected2,original.getNeighbors(v4));
        assertEquals(expected3,original.getNeighbors(v6));
    }

    @Test
    public void getNeighboursTest2() {
        Graph<Integer> original = TestGraph2();
        List<Vertex<Integer>> expected1 = new ArrayList<>(Arrays.asList(v3,v5));
        List<Vertex<Integer>> expected2 = new ArrayList<>(Arrays.asList(v6));
        List<Vertex<Integer>> expected3 = new ArrayList<>(Arrays.asList(v2));
        List<Vertex<Integer>> expected4 = new ArrayList<>(Arrays.asList(v3,v5));
        assertEquals(expected1,original.getNeighbors(v2));
        assertEquals(expected2,original.getNeighbors(v3));
        assertEquals(expected3,original.getNeighbors(v5));
        assertEquals(expected4,original.getNeighbors(v6));

    }

    @Test
    public void getVerticesTest1() {
        Graph<Integer> original = TestGraph1();
        List<Vertex<Integer>> expected = original.getVertices();
        List<Vertex<Integer>> actual = new ArrayList<>(Arrays.asList(v1, v4, v6));

        assertEquals(expected, actual);
    }

    @Test
    public void getVerticesTest2() {
        Graph<Integer> original = TestGraph2();
        List<Vertex<Integer>> expected = original.getVertices();
        List<Vertex<Integer>> actual = new ArrayList<>(Arrays.asList(v2, v3, v5, v6));

        assertEquals(expected, actual);
    }

    public Graph<Integer> TestGraph1() {
        Graph<Integer> graph = new AdjacencyListGraph<>();
        graph.addVertex(v1);
        graph.addVertex(v4);
        graph.addVertex(v6);
        graph.addEdge(v1,v4);
        graph.addEdge(v1,v6);
        graph.addEdge(v4,v1);
        graph.addEdge(v4,v6);
        graph.addEdge(v6,v1);
        return graph;
    }

    public Graph<Integer> TestGraph2() {
        Graph<Integer> graph = new AdjacencyListGraph<>();
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(v2,v3);
        graph.addEdge(v2,v5);
        graph.addEdge(v3,v6);
        graph.addEdge(v5,v2);
        graph.addEdge(v6,v3);
        graph.addEdge(v6,v5);
        return graph;
    }

}
