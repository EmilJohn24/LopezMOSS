package moss.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Class that stores multiple projects
 */
public class MultiProjectStorage implements Iterable<Project>{
    //NOTES on this iterator: The decision to make this project iterator is motivated mainly by the fact that
    //I have observed that another module has become too dependent on multiple objects of this package.

    final private Collection<Project> projects;


    /**
     * @return An iterator for all the contained projects
     */
    @Override
    public Iterator<Project> iterator() {
        return projects.iterator();
    }

    /**
     * Takes projects from the subdirectories of a path and stores them
     * @param projectsFolder Folder that contains the projects
     * @param globFilter GLOB-based filter for files
     * @return An iterable storage
     */
    public static MultiProjectStorage projectsIn(Path projectsFolder, String globFilter){
        return new MultiProjectStorage(projectsFolder, globFilter);
    }

    /**
     * Takes projects from a collection and stores them
     * @param projects Collection of projects to be stored
     * @return An iterable storage of projects
     */
    public static MultiProjectStorage fromCollection(Collection<Project> projects){
        return new MultiProjectStorage(projects);
    }

    /**
     * @param projects Collection of projects to be stored
     */
    private MultiProjectStorage(Collection<Project> projects){
        this.projects = projects;
    }


    /**
     * @param projectsFolder Folder containing projects to be stored
     * @param globFilter Filter for particular types of files
     */
    private MultiProjectStorage(Path projectsFolder, String globFilter){
        projects = new ArrayList<>();

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


}
