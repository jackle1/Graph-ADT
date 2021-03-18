package ca.ubc.ece.cpen221.graphs.test;

import ca.ubc.ece.cpen221.graphs.one.TwitterAnalysis;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TwitterTests {

    @Test
    public void commonInfluencersTest1() throws IOException {
        String[] arguments = {"test.txt", "commonInfluencers", "2", "3"};
        TwitterAnalysis.main(arguments);
        System.out.println("**Expected**");
        System.out.println("1");
        System.out.println("8");
        System.out.println("54");
    }

    @Test
    public void commonInfluencersTest2() throws IOException {
        String[] arguments = {"test2.txt", "commonInfluencers", "2", "3"};
        TwitterAnalysis.main(arguments);
        System.out.println("**Expected**");
        System.out.println("None, no users should have been printed");
    }

    @Test
    public void numRetweetsTest1() throws IOException {
        String[] arguments = {"test3.txt", "numRetweets", "23", "14"};
        TwitterAnalysis.main(arguments);
        System.out.println("**Expected**");
        System.out.println("5");
    }

    @Test
    public void numRetweets2() throws IOException {
        String[] arguments = {"test4.txt", "numRetweets", "1", "2"};
        TwitterAnalysis.main(arguments);
        System.out.println("**Expected**");
        System.out.println("0");
    }


    @Test (expected = NullPointerException.class)
    public void noFileTest() throws IOException {
        String[] arguments = {"test5.txt", "numRetweets", "1", "2"};
        TwitterAnalysis.main(arguments);
    }

    @Test
    public void invalidMethod() throws  IOException{
        String[] arguments = {"test3.txt", "commonfollowers", "23", "14"};
        TwitterAnalysis.main(arguments);
    }

}
