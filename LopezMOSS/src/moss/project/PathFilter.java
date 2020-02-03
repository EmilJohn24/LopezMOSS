package moss.project;

import java.awt.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Used for filtering paths
 */
@SuppressWarnings("WeakerAccess")
public final class PathFilter {
    //CHANGE: Made class immutable and created builder to mediate immutability


    static public final PathFilter CPP_FILTER = new PathFilter(Type.GLOB, "**/*.cpp");
    static public final PathFilter JAVA_FILTER = new PathFilter(Type.GLOB, "**/*.java");
    static public final PathFilter CPP_AND_JAVA_FILTER = new PathFilter(Type.GLOB,"**/*.{java, cpp, h}");
    static public final PathFilter TXT_FILTER = new PathFilter(Type.GLOB,"**/*.txt");
    static public final PathFilter NO_FILTER = new PathFilter(Type.GLOB,"**/*");

    public enum Type{
        //CHANGE: Made Type enum public
        REGEX("regex"), GLOB("glob");

        String filterNameID;

        /**
         * Sole constructor.  Programmers cannot invoke this constructor.
         * It is for use by code emitted by the compiler in response to
         * enum type declarations.
         *
         * @param name    - The name of this enum constant, which is the identifier
         *                used to declare it.
         */
        Type(String name) {
            this.filterNameID = name;
        }

        /**
         * @return Filter ID string
         */
        String getFilterTypeIdentifier(){return filterNameID;}
    }

    private final Type filterType;
    private final Collection<String> filters;

    /**
     * @param path Path to be matched
     * @return If the path matches any of the filters
     */
    final boolean matchesAll(Path path){
        //Made package private because this is not to be used anywhere else but this module
        boolean matches = false;
        for (String filter : filters) {
            matches |= FileSystems.getDefault().getPathMatcher(filterType.getFilterTypeIdentifier() + ":" + filter).matches(path);
        }
        return matches;
    }

    public static class PathFilterBuilder {
        private Collection<String> filters = new ArrayList<>();
        private Type filterType;
        /**
         * @param filters Filters to be added
         */
        public PathFilterBuilder addAllFilters(Collection<String> filters) {
            this.filters.addAll(filters);
            return this;

        }

        /**
         * @param filter Filter text to be added
         */
        public PathFilterBuilder addFilter(String filter) {
            filters.add(filter);
            return this;
        }

        /**
         * @param filter Filter to be removed
         */
        public PathFilterBuilder removeFilter(String filter) {
            filters.remove(filter);
            return this;
        }

        /**
         * @param filters All filters to be removed
         */
        public PathFilterBuilder removeAllFilters(Collection<String> filters) {
            this.filters.removeAll(filters);
            return this;
        }

        /**
         * @param filterType Filter type of path filter
         */
        public PathFilterBuilder setFilterType(Type filterType) {
            this.filterType = filterType;
            return this;
        }

        public PathFilter createFilter(){
            return new PathFilter(filterType, filters);
        }
    }


    private PathFilter(Type filterType, String... initialFilters){
        this.filterType = filterType;
        this.filters = Arrays.asList(initialFilters);
    }

    private PathFilter(Type filterType, Collection<String> initialFilters){
        this.filterType = filterType;
        this.filters = new ArrayList<>(initialFilters);
    }
}
