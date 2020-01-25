package moss.project;

import moss.algorithm.ComparisonStrategy;

import java.io.IOException;

/**
 * A class containing many operations that can be done on <i>Project</i> objects
 */
@SuppressWarnings("WeakerAccess")
public final class Projects {
    private Projects(){} //cannot be instantiated
    /**
     * Compares two <i>Project</i>s and returns a score from 0.0 to 1.0
     * @param first First project to be compared
     * @param second Second project to be compared
     * @param strategy Algorithm to be used for comparison
     * @return Score for comparison
     * @throws IOException Thrown if one of the projects could not be read properly
     */
    public static double compare(Project first,
                                 Project second, ComparisonStrategy strategy) throws IOException {
        return strategy.compare(first.getConcatenatedReader(), second.getConcatenatedReader());
    }
}