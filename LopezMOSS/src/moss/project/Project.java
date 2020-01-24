package moss.project;

import java.io.Reader;
import java.nio.file.Path;

public class Project {
    private final String name;
    private final Path path;
    private final ProjectFlatReaderDistributor readerDistributor;


    Project(Path path, String name, String globFilter){
        this.name = name;
        this.path = path;
        readerDistributor = new ProjectFlatReaderDistributor(this.path, globFilter);
    }

    final Reader getConcatenatedReader(){
        return readerDistributor.distribute();
    }

    public final Path getPath(){
        return this.path;
    }

    public final String getName() {
        return this.name;
    }
}
