class ProjectFlatReaderDistributor
!!!166469.java!!!	ProjectFlatReaderDistributor(inout projectPath : Path)
        this(projectPath, PathFilter.NO_FILTER);
!!!166597.java!!!	ProjectFlatReaderDistributor(inout projectPath : Path, inout globFilter : PathFilter)
        MultiStreamReaderGenerator generatorTmp;
        Collection<InputStream> inputFileStreams = new ArrayList<>();

        try(Stream<Path> paths = Files.walk(projectPath)) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(globFilter::matchesAll)
                    .forEach(path -> {
                        try {
                            inputFileStreams.add(Files.newInputStream(path));
                        } catch (IOException e) {
                            //TODO: Handle properly (This will happen if the file stream could not be created)
                            e.printStackTrace();
                        }
                    });
            generatorTmp = new MultiStreamReaderGenerator(inputFileStreams);
        } catch (IOException e) {
            //TODO: Handle properly
            e.printStackTrace();
            generatorTmp = null;
            System.exit(1);
        }


        generator = generatorTmp;
!!!166725.java!!!	distribute() : Reader
        return generator.generate();
