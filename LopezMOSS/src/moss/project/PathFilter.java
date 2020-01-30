package moss.project;

import java.awt.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("WeakerAccess")
public final class PathFilter {


    static public final PathFilter CPP_FILTER = new PathFilter(Type.GLOB, "**/*.cpp");
    static public final PathFilter JAVA_FILTER = new PathFilter(Type.GLOB, "**/*.java");
    static public final PathFilter CPP_AND_JAVA_FILTER = new PathFilter(Type.GLOB,"**/*.{java, cpp, h}");
    static public final PathFilter TXT_FILTER = new PathFilter(Type.GLOB,"**/*.txt");
    static public final PathFilter NO_FILTER = new PathFilter(Type.GLOB,"**/*");

    enum Type{
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

    private Type filterType;
    private Collection<String> filters;

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


    /**
     * @param filters Filters to be added
     */
    public final void addAllFilters(Collection<String> filters){
        this.filters.addAll(filters);
    }

    /**
     * @param filter Filter text to be added
     */
    public final void addFilter(String filter){
        filters.add(filter);
    }

    /**
     * @param filter Filter to be removed
     */
    public final void removeFilter(String filter){
        filters.remove(filter);
    }

    /**
     * @param filters All filters to be removed
     */
    public final void removeAllFilters(Collection<String> filters){
        this.filters.removeAll(filters);
    }



    public PathFilter(Type filterType, String... initialFilters){
        this.filterType = filterType;
        this.filters = Arrays.asList(initialFilters);
    }
}
