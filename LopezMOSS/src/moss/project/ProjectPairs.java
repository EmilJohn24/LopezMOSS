package moss.project;

import moss.algorithm.ComparisonStrategy;

import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class ProjectPairs {
    public static double compare(Project first,
                                 Project second, ComparisonStrategy strategy) throws IOException {
        return strategy.compare(first.getConcatenatedReader(), second.getConcatenatedReader());
    }
}