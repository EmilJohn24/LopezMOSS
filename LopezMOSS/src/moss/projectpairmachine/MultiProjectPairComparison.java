package moss.projectpairmachine;

import moss.algorithm.ComparisonStrategy;
import moss.project.ProjectFlatReaderDistributor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.stream.Stream;

public class MultiProjectPairComparison {
    private final Hashtable<Path, ProjectFlatReaderDistributor> readerDistributors;
    private final ComparisonStrategy strategy;

    /**
     * @param projectsFolder: path to the folder containing all the projects to be cross-compared
     * @param globFilter: the specifier for which types of files should be included in the comparison using the UNIX GLOB format
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
