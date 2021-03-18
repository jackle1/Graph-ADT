package ca.ubc.ece.cpen221.graphs.test;

import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;
import ca.ubc.ece.cpen221.graphs.one.AdjacencyMatrixGraph;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AdjacencyMatrixTests {

    final Vertex<String> vertex1 = new Vertex<>("1", "CPEN");
    final Vertex<String> vertex2 = new Vertex<>("5", "211");
    final Vertex<String> vertex3 = new Vertex<>("2", "rocks!");
    final Vertex<String> vertex4 = new Vertex<>("9", ":D");
    final Vertex<String> vertex5 = new Vertex<>("7", "Yay");
    final Vertex<String> vertex6 = new Vertex<>("3", "CPEN");
    final Vertex<String> vertex7 = new Vertex<>("4", "221");
    final Vertex<String> vertex8 = new Vertex<>("0", "rocks!");
    final Vertex<String> vertex9 = new Vertex<>("6", ":D");
    final Vertex<String> vertex10 = new Vertex<>("8", "Yay");
    final Vertex<String> vertex11 = new Vertex<>("a", "b");

    @Test
    public void edgeExistsTest1() {
        Graph<String> graph1 = testGraph1();

        assertTrue(graph1.edgeExists(vertex1, vertex2));
        assertTrue(graph1.edgeExists(vertex1, vertex3));
        assertTrue(graph1.edgeExists(vertex2, vertex3));
        assertTrue(graph1.edgeExists(vertex3, vertex1));
        assertFalse(graph1.edgeExists(vertex2, vertex1));
    }

    @Test
    public void edgeExistsTest2() {
        Graph<String> graph2 = testGraph2();

        assertTrue(graph2.edgeExists(vertex5, vertex4));
        assertFalse(graph2.edgeExists(vertex4, vertex5));
    }

    @Test
    public void getNeighboursTest1() {
        Graph<String> graph1 = testGraph1();

        List<Vertex<String>> actualv1 = graph1.getNeighbors(vertex1);
        List<Vertex<String>> actualv2 = graph1.getNeighbors(vertex2);
        List<Vertex<String>> actualv3 = graph1.getNeighbors(vertex3);
        List<Vertex<String>> expectedv1 = new ArrayList<>(Arrays.asList(vertex2, vertex3));
        List<Vertex<String>> expectedv2 = new ArrayList<>(Arrays.asList(vertex3));
        List<Vertex<String>> expectedv3 = new ArrayList<>(Arrays.asList(vertex1, vertex2));

        assertEquals(expectedv1, actualv1);
        assertEquals(expectedv2, actualv2);
        assertEquals(expectedv3, actualv3);

    }

    @Test
    public void getNeighboursTest2() {
        Graph<String> graph2 = testGraph2();
        List<Vertex<String>> actualv5 = new ArrayList<>(Arrays.asList(vertex4));
        List<Vertex<String>> actualv4 = new ArrayList<>();
        List<Vertex<String>> expectedv5 = graph2.getNeighbors(vertex5);
        List<Vertex<String>> expectedv4 = graph2.getNeighbors(vertex4);

        assertEquals(expectedv4, actualv4);
        assertEquals(expectedv5, actualv5);
    }

    @Test
    public void getVerticesTest1() {
        Graph<String> graph1 = testGraph1();
        List<Vertex<String>> actual = new ArrayList<>(Arrays.asList(vertex1, vertex3, vertex2));
        List<Vertex<String>> expected = graph1.getVertices();

        assertEquals(expected, actual);
    }

    @Test
    public void getVerticesTest2() {
        Graph<String> graph2 = testGraph2();
        List<Vertex<String>> actual = new ArrayList<>(Arrays.asList(vertex5, vertex4));
        List<Vertex<String>> expected = graph2.getVertices();

        assertEquals(expected, actual);
    }

    @Test
    public void getVerticesTest3() {
        Graph<String> graph3 = testGraph3();
        List<Vertex<String>> actual =
            new ArrayList<>(Arrays.asList(vertex1, vertex3, vertex2, vertex5, vertex4));
        List<Vertex<String>> expected = graph3.getVertices();

        assertEquals(expected, actual);
    }

    @Test
    public void getVerticesTest4() {
        Graph<String> graph4 = testGraph4();
        List<Vertex<String>> expected = graph4.getVertices();
        List<Vertex<String>> actual = new ArrayList<>(Arrays
            .asList(vertex8, vertex1, vertex3, vertex6, vertex7, vertex2, vertex9, vertex5, vertex10,
                vertex4, vertex11));

        assertEquals(expected, actual);
    }

    public Graph<String> testGraph1() {
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

    public Graph<String> testGraph2() {
        Graph<String> graph2 = new AdjacencyMatrixGraph<>();
        graph2.addVertex(vertex4);
        graph2.addVertex(vertex5);
        graph2.addEdge(vertex5, vertex4);

        return graph2;
    }

    public Graph<String> testGraph3() {
        Graph<String> graph3 = new AdjacencyMatrixGraph<>();
        graph3.addVertex(vertex1);
        graph3.addVertex(vertex2);
        graph3.addVertex(vertex3);
        graph3.addVertex(vertex4);
        graph3.addVertex(vertex5);

        return graph3;
    }

    public Graph<String> testGraph4() {
        Graph<String> graph4 = new AdjacencyMatrixGraph<>();
        graph4.addVertex(vertex1);
        graph4.addVertex(vertex2);
        graph4.addVertex(vertex3);
        graph4.addVertex(vertex4);
        graph4.addVertex(vertex5);
        graph4.addVertex(vertex6);
        graph4.addVertex(vertex7);
        graph4.addVertex(vertex8);
        graph4.addVertex(vertex9);
        graph4.addVertex(vertex10);
        graph4.addVertex(vertex11);

        return graph4;
    }

}
