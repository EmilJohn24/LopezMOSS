package moss.projectpairmachine;

import moss.algorithm.ComparisonStrategy;
import moss.project.Project;
import moss.project.ProjectBuilder;
import moss.project.ProjectFlatReaderDistributor;
import moss.project.Projects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.stream.Stream;

/**
 * [NOT YET FINISHED]
 * Compares multiple projects pair-wise
 */
final class MultiProjectPairComparison {
    private final Collection<Project> projects = new ArrayList<>(); //TODO: Turn into a collection of Project objects
    private final ComparisonStrategy strategy;

    /**
     * @param projectsFolder Path to the folder containing all the projects to be cross-compared
     * @param globFilter The specifier for which types of files should be included in the comparison using the UNIX GLOB format
     * @param strategy Algorithm to be used for comparison
     */
    MultiProjectPairComparison(Path projectsFolder, String globFilter, ComparisonStrategy strategy) {
        this.strategy = strategy;
        try (Stream<Path> projectPath = Files.list(projectsFolder)) {
            projectPath
                    .filter(Files::isDirectory)
                    .forEach((path) -> {
                        ProjectBuilder projectBuilder = new ProjectBuilder();
                        projects.add(projectBuilder
                                .setPath(path)
                                .setGlobFilter(globFilter)
                                .createProject());
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @apiNote  Although this must functionally be a public method, it has been temporarily demoted to a package-private one because the
                    return type is too verbose and might be encapsulated in a more organized package-private constructed public class
     * @return returns a hashtable linking an individual project and another hashtable containing all the comparisons against the key project
     */
    Hashtable<Project, Hashtable<Project, Double>> compareAllAgainstAll(){
        Hashtable<Project, Hashtable<Project, Double>> results = new Hashtable<>();
        for (Project project : this.projects){
            results.putIfAbsent(project, compareAgainstAll(project));
        }

        return results;

    }
    /**
     * @param project Project to be compared against all stored projects
     * @return A hashtable containing the MOSS of the passed project against all projects stored in this class
     */
    Hashtable<Project, Double> compareAgainstAll(Project project){
        Hashtable<Project, Double> scores = new Hashtable<>();
        for (Project comparedProject : this.projects){
            //Projects.compare (a static class) is called here to get the score of the comparison against the passed project
            scores.putIfAbsent(comparedProject,
                    Projects.compare(project, comparedProject, strategy));
        }
        return scores;
    }

//    public Hashtable<Path, Double> compareAgainstAll(Path path){
//        for (Map.Entry<Path, ProjectFlatReaderDistributor> distributorEntry : projects.entrySet()){
//
//        }
//    }


}
