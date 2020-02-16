class MultiProjectStorage
!!!162245.java!!!	projectCount() : int
        return projects.size();
!!!162373.java!!!	iterator() : Project
        return projects.iterator();
!!!162501.java!!!	projectsIn(inout projectsFolder : Path, inout filter : PathFilter) : MultiProjectStorage
        return new MultiProjectStorage(projectsFolder, filter);
!!!162629.java!!!	fromCollection(inout projects : Collection<Project>) : MultiProjectStorage
        return new MultiProjectStorage(projects);
!!!162757.java!!!	fromPathCollection(inout projectPaths : Collection<Path>, inout filters : PathFilter) : MultiProjectStorage
        return new MultiProjectStorage(projectPaths, filters);
!!!162885.java!!!	MultiProjectStorage(inout projectPaths : Collection<Path>, inout filters : PathFilter)
        this.projects = new ArrayList<>();
        for (Path projectPath : projectPaths){
            //TODO: This is precisely the same snippet of code as the one from the other constructor. Consider code extraction
            ProjectBuilder projectBuilder = new ProjectBuilder();
            projects.add(projectBuilder
                    .setPath(projectPath)
                    .setFilter(filters)
                    .createProject());
        }
!!!163013.java!!!	MultiProjectStorage(inout projects : Collection<Project>)
        this.projects = projects;
!!!163141.java!!!	MultiProjectStorage(inout projectsFolder : Path, inout filter : PathFilter)
        projects = new ArrayList<>();

        try (Stream<Path> projectPath = Files.list(projectsFolder)) {
            projectPath
                    .filter(Files::isDirectory)
                    .forEach((path) -> {
                        ProjectBuilder projectBuilder = new ProjectBuilder();
                        projects.add(projectBuilder
                                .setPath(path)
                                .setFilter(filter)
                                .createProject());
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

