class PathFilter
!!!164165.java!!!	matchesAll(inout path : Path) : boolean
        //Made package private because this is not to be used anywhere else but this module
        boolean matches = false;
        for (String filter : filters) {
            matches |= FileSystems.getDefault().getPathMatcher(filterType.getFilterTypeIdentifier() + ":" + filter).matches(path);
        }
        return matches;
!!!165061.java!!!	PathFilter(inout filterType : PathFilter::Type, inout  : String...initialFilters)
        this.filterType = filterType;
        this.filters = Arrays.asList(initialFilters);
!!!165189.java!!!	PathFilter(inout filterType : PathFilter::Type, inout initialFilters : Collection<String>)
        this.filterType = filterType;
        this.filters = new ArrayList<>(initialFilters);
