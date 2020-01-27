package moss.projectpairmachine;

import moss.algorithm.TokenHashingStrategy;
import moss.project.Project;
import moss.project.Projects;
import moss.project.TestObjects;
import org.junit.Test;

import java.util.Hashtable;
import java.util.Map;

import static org.junit.Assert.*;

public class MultiProjectPairComparisonTest {

    @Test
    public void compareAgainstAll() {

    }


    @Test
    public void module0SubmissionsTest(){
        MultiProjectPairComparison multiProjectPairComparison = new MultiProjectPairComparison(TestObjects.SUBMISSIONS_PATH,
                Projects.CPP_AND_JAVA_FILTER, new TokenHashingStrategy());
        Hashtable<Project, Hashtable<Project, Double>> comparisons = multiProjectPairComparison.compareAllAgainstAll();
        for (Map.Entry<Project, Hashtable<Project, Double>> comparison : comparisons.entrySet()){
            System.out.print(comparison.getKey().getName());
            for (Double score : comparison.getValue().values()){
                System.out.print(" " + score.floatValue());
            }
            System.out.println();
        }
    }
}