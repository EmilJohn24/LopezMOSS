package moss.project;

import java.nio.file.Path;

public class ProjectBuilder {
    private Path path;
    private String name;
    private String globFilter;

    public ProjectBuilder(){
        this.globFilter = ProjectFlatReaderDistributor.NO_FILTER;
        this.name = null;
    }
    public ProjectBuilder setPath(Path path) {
        this.path = path;
        return this;
    }

    public ProjectBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProjectBuilder setGlobFilter(String globFilter) {
        this.globFilter = globFilter;
        return this;
    }

    public Project createProject() {
        if (name == null) this.name = this.path.getFileName().toString();
        return new Project(path, name, globFilter);
    }
}