package moss.projectpairmachine;

import moss.project.Project;

import java.util.*;

/**
 * Contains results for multi-project comparisons
 */
final class ProjectsCorrelationMatrix implements Iterable<ProjectsCorrelationMatrix.ResultRow> {
    private final Collection<ResultRow> rows;

    private ProjectsCorrelationMatrix(Collection<ResultRow> rows){
        this.rows = rows;
    }


    /**
     * @return A collection of <b>ResultRow</b>s, which contain immutable information about each row of results
     */
    final public Collection<ResultRow> getRows(){
        return rows;
    }

    /**
     * @return An iterator for result rows
     */
    @Override
    public Iterator<ResultRow> iterator() {
        return rows.iterator();
    }


    /**
     * Holds information on the compared project and results for a single row of comparison
     */
    static final class ResultRow{
        private final Project rowProject;
        private final ResultSet results;

        private ResultRow(Project project, ResultSet results){
            this.rowProject = project;
            this.results = results;
        }


        /**
         * @return Results present in row
         */
        public final ResultSet getResults() {
            return results;
        }

        /**
         * @return Project associated with this row
         */
        public final Project getProject() {
            return rowProject;
        }
    }

    /**
     * Contains the results of a single project against all projects
     */
    static final class ResultSet implements Iterable<ResultSet.ResultRecord> {
        //this class does not know about the existence of the project every other project here was compared to.
        //this is knowledge that the instantiator of the correlation matrix must track himself
        private Collection<ResultRecord> results = new ArrayList<>();
        /**
         * Collects all results of a comparison before compiling them into an immutable results object
         */
        private ResultSet(){
            //private to prevent instantiation from
        }

        /**
         * @param resultRecord result record to be added to the set
         */
        private void add(ResultRecord resultRecord){
            results.add(resultRecord);
        }

        /**
         * @param project Project to be checked
         * @return True if there is already a result for the given project
         */
        public boolean containsResultFor(Project project){
            for (ResultRecord record : results){
                if (record.getProject().equals(project)) return true;
            }
            return false;
        }



        /**
         * @return Returns the iterator for this set
         */
        @Override
        public final Iterator<ResultRecord> iterator() {
            return results.iterator();
        }


        /**
         * Contains a single result of a comparison.
         */
        static final class ResultRecord {
            private final Project projectComparedAgainst;
            private final double score;

            private ResultRecord(Project projectComparedAgainst, double score) {
                this.projectComparedAgainst = projectComparedAgainst;
                this.score = score;
            }

            public final Project getProject() {
                return projectComparedAgainst;
            }

            public double getScore() {
                return score;
            }
        }
    }


    /**
     * Helps build the matrix containing comparison results
     */
    static class ProjectsCorrelationMatrixBuilder {
        //I have made explicit use of a linked hash map here because it maintains the order things were put in
        private LinkedHashMap<Project, ResultSet> resultTable = new LinkedHashMap<>();

        /**
         * @return The matrix built from the added results
         */
        ProjectsCorrelationMatrix createMatrix(){
            //the only role of this function is to create the matrix by taking the values in the result table
            //and putting it in a collection of result rows, the format the actual matrix uses
            Collection<ResultRow> rows = new ArrayList<>();
            for (Map.Entry<Project, ResultSet> resultTableEntry : resultTable.entrySet()){
                rows.add(new ResultRow(resultTableEntry.getKey(), resultTableEntry.getValue()));
            }
            return new ProjectsCorrelationMatrix(rows);

        }
        /**
         * [HELPER FUNCTION]
         * Adds a result record in only one direction of comparison
         *
         * @param projectComparedTo      project where the result is added
         * @param projectComparedAgainst project that the other project was compared against
         * @param result                 result of their comparison
         */
        private void addResultOneWay(Project projectComparedTo, Project projectComparedAgainst, double result) {
            //this will add the result to the table asymmetrically
            resultTable.putIfAbsent(projectComparedTo, new ResultSet());
            ResultSet projectResults = resultTable.get(projectComparedTo);

            //PHASE 2: If this comparison is already in the table, we will refrain from putting it again
            if (projectResults.containsResultFor(projectComparedAgainst)) return;


            projectResults.add(new ResultSet.ResultRecord(projectComparedAgainst, result));
        }


        /**
         * Adds result to table symmetrically
         *
         * @param firstProjectCompared  First project compared
         * @param secondProjectCompared Second project compared
         * @param result                Result of their comparison
         */
        final void addRecordIfNone(Project firstProjectCompared, Project secondProjectCompared, double result) {
            //TWO-WAY adding
            //All operations are only done on a single direction
            this.addResultOneWay(firstProjectCompared, secondProjectCompared, result);
            this.addResultOneWay(secondProjectCompared, firstProjectCompared, result);
        }
    }

}
