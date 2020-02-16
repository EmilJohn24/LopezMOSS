class ProjectBuilder
!!!165829.java!!!	ProjectBuilder()
        //CHANGE: Recently made all methods here public to connect this to other modules
        this.filter = PathFilter.NO_FILTER;
        this.name = null;
!!!165957.java!!!	setPath(inout path : Path) : ProjectBuilder
        this.path = path;
        return this;
!!!166085.java!!!	setName(in name : String) : ProjectBuilder
        this.name = name;
        return this;
!!!166213.java!!!	setFilter(inout filter : PathFilter) : ProjectBuilder
        this.filter = filter;
        return this;
!!!166341.java!!!	createProject() : Project
        if (name == null) this.name = this.path.getFileName().toString();
        return new Project(path, name, filter);
