package metrics;

import moss.algorithm.TokenHashingStrategy;
import moss.project.MultiProjectStorage;
import moss.project.PathFilter;
import moss.projectpairmachine.ProjectsCorrelationMatrix;
import moss.projectpairmachine.SimpleMultiProjectComparison;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.function.Function;

public final class PerformanceTest {


    /**
     * Time individual program operations based on input directory
     */
    @Test
    public void correlationMatrixCreationTime(){
        SimpleMultiProjectComparison comparisonEngine = SimpleMultiProjectComparison.fromStrategy(new TokenHashingStrategy());
        PathFilter filter = new PathFilter.PathFilterBuilder()
                .setFilterType(PathFilter.Type.GLOB)
                .addFilter("**/*.cpp")
                .addFilter("**/*.java")
                .addFilter("**/*.h")
                .addFilter("**/*.c")
                .createFilter();
        long startTime, endTime;
        Path path = Paths.get("C:\\Users\\ACER\\git_submissions");
        //PHASE 1: Time file loading
        startTime = System.nanoTime();
        MultiProjectStorage storage = MultiProjectStorage.projectsIn(path, filter);
        endTime = System.nanoTime();

        long fileLoadingDuration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("File Loading Time: " + fileLoadingDuration / 1000000 + " ms");


        //PHASE 2: Time file loading
        startTime = System.nanoTime();
        ProjectsCorrelationMatrix matrix = comparisonEngine.compareAll(storage);
        endTime = System.nanoTime();

        long matrixCreationTime = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("Matrix creation Time: " + matrixCreationTime / 1000000 + " ms");

    }
}
