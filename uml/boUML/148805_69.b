class Project
!!!165317.java!!!	Project(inout path : Path, in name : String, inout globFilter : PathFilter)
        this.name = name;
        this.path = path;
        readerDistributor = new ProjectFlatReaderDistributor(this.path, globFilter);
!!!165445.java!!!	getConcatenatedReader() : Reader
        return readerDistributor.distribute();
!!!165573.java!!!	getPath() : Path
        return this.path;
!!!165701.java!!!	getName() : String
        return this.name;
