package moss.projectpairmachine;


import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

class ProjectFlatReaderDistributor {
    private final MultiStreamReaderGenerator generator;
    static final String CPP_FILTER = "**/*.cpp";
    static final String JAVA_FILTER = "**/*.java";
    static final String TXT_FILTER = "**/*.txt";

    ProjectFlatReaderDistributor(Path path) throws IOException {
        this(path, "*.");
    }

    //if an empty string is passed to extension, this will not do any extension filtering
    //the filter uses the GLOB syntax
    ProjectFlatReaderDistributor(Path projectPath, String globFilter) {
        MultiStreamReaderGenerator generatorTmp;
        PathMatcher globMatcher = FileSystems.getDefault().getPathMatcher("glob:" + globFilter);
        Collection<InputStream> inputFileStreams = new ArrayList<>();

        try(Stream<Path> paths = Files.walk(projectPath)) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(globMatcher::matches)
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
    }


    Reader distribute(){
        return generator.generate();
    }
}