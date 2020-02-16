class PathFilterBuilder
!!!164293.java!!!	addAllFilters(inout filters : Collection<String>) : PathFilter::PathFilterBuilder
            this.filters.addAll(filters);
            return this;
!!!164421.java!!!	addFilter(in filter : String) : PathFilter::PathFilterBuilder
            filters.add(filter);
            return this;
!!!164549.java!!!	removeFilter(in filter : String) : PathFilter::PathFilterBuilder
            filters.remove(filter);
            return this;
!!!164677.java!!!	removeAllFilters(inout filters : Collection<String>) : PathFilter::PathFilterBuilder
            this.filters.removeAll(filters);
            return this;
!!!164805.java!!!	setFilterType(inout filterType : PathFilter::Type) : PathFilter::PathFilterBuilder
            this.filterType = filterType;
            return this;
!!!164933.java!!!	createFilter() : PathFilter
            //CHANGE: Added a check for emptiness of filter array. This is to ensure that even with a lack of filters, the behavior we will get
            //is exactly that: having no filters.
            if (filters.size() == 0) return PathFilter.NO_FILTER;
            return new PathFilter(filterType, filters);
