package ca.ubc.ece.cpen221.graphs.one;

import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

public class TwitterAnalysis {

    private File file;
    private Graph<String> graph;
    private Map<String, Vertex<String>> vertexMap;

    /**
     * Creates a new TwitterAnalysis object given a file name containing data of which users
     * follows which (a -> b means b follows a). A graph is then created given the dataset.
     *
     * @param fileName the name of the file to analyze
     * @throws IOException
     */
    public TwitterAnalysis(String fileName) throws IOException {
        file = new File(fileName);
        graph = new AdjacencyListGraph<>();
        makeGraph();
    }

    /**
     * Creates a graph of all the users in the file where an edge is created
     * if b follows a (given a -> b)
     *
     * @throws FileNotFoundException if the file cannot be found
     */
    private void makeGraph() throws FileNotFoundException {
        vertexMap = new HashMap<>();
        Scanner myscanner;
        try {
            myscanner = new Scanner(file);
            while (myscanner.hasNext()) {
                Vertex<String> following = new Vertex<>(myscanner.next());
                myscanner.next();
                Vertex<String> follower = new Vertex<>(myscanner.next());

                if (!vertexMap.containsKey(following.getLabel())) {
                    vertexMap.put(following.getLabel(), following);
                    graph.addVertex(vertexMap.get(following.getLabel()));
                }
                if (!vertexMap.containsKey(follower.getLabel())) {
                    vertexMap.put(follower.getLabel(), follower);
                    graph.addVertex(vertexMap.get(follower.getLabel()));
                }
                if (!graph.edgeExists(vertexMap.get(following.getLabel()),
                    vertexMap.get(follower.getLabel()))) {
                    graph.addEdge(vertexMap.get(following.getLabel()),
                        vertexMap.get(follower.getLabel()));
                }
            }
        } catch (FileNotFoundException | NullPointerException e) {
            System.err.println("File cannot be found");
        }

    }

    /**
     * Obtain a Set that contains all the users that userA and userB both follow
     *
     * @param userA the user to search for common followed users
     * @param userB the user to search for common followed users
     * @return commonFollowers the set that contains the list of all users both userA and userB follow
     */
    public Set<String> commonInfluencers(String userA, String userB) {
        List<Vertex<String>> commonFollowersList =
            Algorithms.commonUpstreamVertices(graph, vertexMap.get(userA), vertexMap.get(userB));
        Set<String> commonFollowers = new HashSet<>();

        for (Vertex<String> stringVertex : commonFollowersList) {
            commonFollowers.add(stringVertex.getLabel());
        }

        return commonFollowers;
    }

    /**
     * Computes the number of retweets needed for a tweet tweeted by UserA to show up
     * in userB's feed. The tweet will show up in userB's feed if userB follows a user who
     * retweets the tweet.
     *
     * postcondition: return -1 if no retweets can be done
     *
     * @param userA the user who posted the tweet
     * @param userB the user who will have the tweet show up in their feed
     * @return The number of retweets needed
     *
     */
    public int numRetweets(String userA, String userB) {
       int numRetweets = Algorithms.shortestDistance(graph, vertexMap.get(userA), vertexMap.get(userB));

       if( numRetweets == -1){
           return numRetweets;
       }else{
           return numRetweets-1;
       }
    }

    /**
     * Reads the command line arguments and calls for the methods specified
     * by the client and prints the results into the terminal
     *
     * precondition: args must be written in the form "file.txt" "method" "userA" "userB"
     *
     * @param args command line arguments to perform the specified methods
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String fileName = "datasets/" + args[0];

        String method = args[1];
        String userA = args[2];
        String userB = args[3];

        TwitterAnalysis toAnalyze;

        toAnalyze = new TwitterAnalysis(fileName);

        if (method.equals("commonInfluencers")) {
            Set<String> commonFollowers = toAnalyze.commonInfluencers(userA, userB);

            for (String s : commonFollowers) {
                System.out.println(s);
            }
        }else {
            if (method.equals("numRetweets")) {
                int numRetweets = toAnalyze.numRetweets(userA, userB);

                System.out.println(numRetweets);
            }
            else{
                System.out.println("Error, method is not valid.");
            }
        }

    }
}
