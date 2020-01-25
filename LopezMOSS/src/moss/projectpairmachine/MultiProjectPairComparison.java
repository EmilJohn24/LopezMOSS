package moss.projectpairmachine;

import moss.algorithm.ComparisonStrategy;
import moss.project.ProjectFlatReaderDistributor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.stream.Stream;

/**
 * [NOT YET FINISHED]
 * Compares multiple projects pair-wise
 */
public class MultiProjectPairComparison {
    private final Hashtable<Path, ProjectFlatReaderDistributor> readerDistributors; //TODO: Turn into a collection of Project objects
    private final ComparisonStrategy strategy;

    /**
     * @param projectsFolder Path to the folder containing all the projects to be cross-compared
     * @param globFilter The specifier for which types of files should be included in the comparison using the UNIX GLOB format
     * @param strategy Algorithm to be used for comparison
     */
    public MultiProjectPairComparison(Path projectsFolder, String globFilter, ComparisonStrategy strategy) {
        this.strategy = strategy;
        this.readerDistributors = new Hashtable<>();
        try (Stream<Path> projectPath = Files.list(projectsFolder)) {
            projectPath
                    .filter(Files::isDirectory)
                    .forEach((path) -> readerDistributors.putIfAbsent(path, new ProjectFlatReaderDistributor(projectsFolder, globFilter)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public Hashtable<Path, Double> compareAgainstAll(Path path){
//        for (Map.Entry<Path, ProjectFlatReaderDistributor> distributorEntry : readerDistributors.entrySet()){
//
//        }
//    }


}
