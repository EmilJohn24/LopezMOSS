package moss.gui.MultiProjectMenu;

import moss.algorithm.TokenHashingStrategy;
import moss.project.MultiProjectStorage;
import moss.project.PathFilter;
import moss.project.TestObjects;
import moss.projectpairmachine.ProjectsCorrelationMatrix;
import moss.projectpairmachine.SimpleMultiProjectComparison;

import javax.inject.Inject;
import java.nio.file.Path;
import java.util.Collection;

/**
 * Services to be used by the multi-project menu GUI
 */
public class MultiProjectMenuService {
    @Inject
    private final SimpleMultiProjectComparison comparisonMachine = SimpleMultiProjectComparison.fromStrategy(new TokenHashingStrategy());

    /**
     * @param projectsFolder Folder containing all the projects to be compared
     * @param filters Filters to be imposed upon the project folders
     * @return A matrix containing the results
     */
    public ProjectsCorrelationMatrix processMultiProjectFolder(Path projectsFolder, PathFilter filters){
        MultiProjectStorage projects =
                MultiProjectStorage.projectsIn(projectsFolder, filters);
        return comparisonMachine.compareAll(projects);
    }

    /**
     * @param paths Collection of paths to be included in the project
     * @param filters Filters to be imposed upon the projects
     * @return A matrix containing the results
     */
    public ProjectsCorrelationMatrix processMultiPaths(Collection<Path> paths, PathFilter filters){
        MultiProjectStorage projects =
                MultiProjectStorage.fromPathCollection(paths, filters);
        return comparisonMachine.compareAll(projects);
    }
}
