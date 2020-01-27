package moss.project;

import java.nio.file.Path;

/**
 * Builds <i>Project</i> objects.
 */
public class ProjectBuilder {
    private Path path;
    private String name;
    private String globFilter;

    /**
     * Creates a builder for a <i>Project</i>
     * No filter by default
     */
    public ProjectBuilder(){
        //CHANGE: Recently made all methods here public to connect this to other modules
        this.globFilter = Projects.NO_FILTER;
        this.name = null;
    }

    /**
     * @param path Path to the project folder
     * @return A builder for the project to be built
     */
    public ProjectBuilder setPath(Path path) {
        this.path = path;
        return this;
    }

    /**
     * @param name Name of the project
     * @return A builder for the project to be built
     */
    public ProjectBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param globFilter Text filter for the files that can be accessed from the project in GLOB format.
     * @return A builder for the project to be built
     */
    public ProjectBuilder setGlobFilter(String globFilter) {
        this.globFilter = globFilter;
        return this;
    }

    /**
     * @return The project generated with all values set.
     */
    public Project createProject() {
        if (name == null) this.name = this.path.getFileName().toString();
        return new Project(path, name, globFilter);
    }
}