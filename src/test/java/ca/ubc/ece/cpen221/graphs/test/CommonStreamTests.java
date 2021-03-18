package ca.ubc.ece.cpen221.graphs.test;

import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;
import ca.ubc.ece.cpen221.graphs.one.Algorithms;
import ca.ubc.ece.cpen221.graphs.one.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.graphs.one.AdjacencyListGraph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CommonStreamTests {
    final Vertex<String> vertex1 = new Vertex<>("1", "CPEN");
    final Vertex<String> vertex2 = new Vertex<>("5", "211");
    final Vertex<String> vertex3 = new Vertex<>("2", "rocks!");
    final Vertex<String> vertex4 = new Vertex<>("9", ":D");

    @Test
    public void testCommonUpstream_matrix(){
        Graph<String> graph = graphMatrix();
        List<Vertex<String>> expected1 = new ArrayList<>(Arrays.asList(vertex1, vertex4));
        List<Vertex<String>> actual1 = Algorithms.commonUpstreamVertices(graph, vertex2, vertex3);
        List<Vertex<String>> expected2 = new ArrayList<>();
        List<Vertex<String>> actual2 = Algorithms.commonUpstreamVertices(graph, vertex2, vertex1);
        List<Vertex<String>> expected3 = new ArrayList<>();
        List<Vertex<String>> actual3 = Algorithms.commonUpstreamVertices(graph, vertex4, vertex1);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }

    @Test
    public void testCommonUpStream_list(){
        Graph<String> graph = graphList();

        List<Vertex<String>> expected1 = new ArrayList<>(Arrays.asList(vertex1));
        List<Vertex<String>> actual1 = Algorithms.commonUpstreamVertices(graph, vertex3, vertex4);
        List<Vertex<String>> expected2 = new ArrayList<>();
        List<Vertex<String>> actual2 = Algorithms.commonUpstreamVertices(graph, vertex1, vertex3);
        List<Vertex<String>> expected3 = new ArrayList<>();
        List<Vertex<String>> actual3 = Algorithms.commonUpstreamVertices(graph, vertex1, vertex2);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }

    @Test
    public void testCommonDownStream_matrix(){
        Graph<String> graph = graphMatrix();

        List<Vertex<String>> expected1 = new ArrayList<>(Arrays.asList(vertex3, vertex2));
        List<Vertex<String>> actual1 = Algorithms.commonDownstreamVertices(graph, vertex1, vertex4);
        List<Vertex<String>> expected2 = new ArrayList<>();
        List<Vertex<String>> actual2 = Algorithms.commonDownstreamVertices(graph, vertex1, vertex2);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void testCommonDownStream_list(){
        Graph<String> graph = graphList();

        List<Vertex<String>> expected1 = new ArrayList<>();
        List<Vertex<String>> actual1 = Algorithms.commonDownstreamVertices(graph, vertex1, vertex3);
        List<Vertex<String>> expected2 = new ArrayList<>(Arrays.asList(vertex3));
        List<Vertex<String>> actual2 = Algorithms.commonDownstreamVertices(graph, vertex1, vertex2);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }


    public Graph<String> graphMatrix(){
        Graph<String> graph = new AdjacencyMatrixGraph<>();
        graph.addVertex(vertex1);
        graph.addVertex(vertex4);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);

        graph.addEdge(vertex1, vertex2);
        graph.addEdge(vertex1, vertex3);
        graph.addEdge(vertex4, vertex2);
        graph.addEdge(vertex4, vertex3);

        return graph;
    }

    public Graph<String> graphList(){
        Graph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);

        graph.addEdge(vertex1, vertex3);
        graph.addEdge(vertex1, vertex4);
        graph.addEdge(vertex2, vertex3);

        return graph;
    }

}