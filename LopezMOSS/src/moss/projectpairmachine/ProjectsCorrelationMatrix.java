package moss.projectpairmachine;

import moss.project.Project;

import java.util.*;

/**
 * Contains results for multi-project comparisons
 */
public final class ProjectsCorrelationMatrix implements Iterable<ProjectsCorrelationMatrix.ResultRow> {
    //CHANGE: changed class from package-private to public because the data needs to be accessible everywhere in the program.
    //The only real restriction is that this should only be constructable within the package
    private final Collection<ResultRow> rows;

    /**
     * @param rows The rows of results to be loaded into the matrix
     */
    private ProjectsCorrelationMatrix(Collection<ResultRow> rows){
        this.rows = rows;
    }


    /**
     * @return A flattened version of the matrix. For example, if {@code project1} and {@code project2} have a result of 0.5,
     * their result would be placed in a single ResultTrio object containing both projects and the score. The flattened list
     * will not contain the diagonal (self-comparisons) of the matrix.
     * @see ResultTrio
     */
    public List<ResultTrio> flatten(){
        List<ResultTrio> resultTrios = new ArrayList<>();
        for (ResultRow row : rows){
            for (ResultSet.ResultRecord result : row.getResults()){
                ResultTrio newTrio = new ResultTrio(row.getProject(), result.getProject(), result.getScore());
                //NOTE:This part checks if both projects being compared are the same. This is because this diagonal always yields a score 1.0 and does not
                //carry any info.
                if (!resultTrios.contains(newTrio) && row.getProject() != result.getProject()){
                    resultTrios.add(newTrio);
                }
            }
        }
        return resultTrios;
    }


    /**
     * A comparison result between a pair of projects stored as one class as compared to rows. This will be used
     * to output a flattened version of the projects
     * @see ProjectsCorrelationMatrix::flatten
     */
    static public final class ResultTrio implements Comparable<ResultTrio>{
        private final Project firstProject;
        private final Project secondProject;
        private final double score;

        private ResultTrio(Project firstProject, Project secondProject, double score){
            this.firstProject = firstProject;
            this.secondProject = secondProject;
            this.score = score;
        }

        /**
         * @return First project
         */
        public final Project getFirstProject() {
            return this.firstProject;
        }

        /**
         * @return Second Project
         */
        public final Project getSecondProject() {
            return this.secondProject;
        }

        /**
         * @return Score of the comparison between the first and second project
         */
        public final double getScore() {
            return score;
        }

        /**
         * @param o ProjectTrio to be compared with
         * @return If the two projects are asymmetrically the same. This means that a trio whose first and second project, and second and first projects are equal are the same
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ResultTrio that = (ResultTrio) o;
            return (Objects.equals(firstProject, that.firstProject) &&
                    Objects.equals(secondProject, that.secondProject))
                    ||
                    ((Objects.equals(firstProject, that.secondProject)) &&
                    Objects.equals(secondProject, that.firstProject));
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstProject, secondProject);
        }

        /**
         * @param o Trio to be compared to
         * @return The comparison of the score of the two results
         */
        @Override
        public int compareTo(ResultTrio o) {
            return Double.compare(score, o.score);
        }
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
    //CHANGE: Changed to final to prevent inheritance
    final public Iterator<ResultRow> iterator() {
        return rows.iterator();
    }


    /**
     * @return The project names of all projects in the matrix
     */
    public final Collection<String> getProjectNames(){
        Collection<String> names = new ArrayList<>();
        for (ResultRow row : rows){
            names.add(row.getProject().getName());
        }
        return names;

    }
    /**
     * Holds information on the compared project and results for a single row of comparison
     */
    static public final class ResultRow{
        //CHANGE: Changed to public to allow results to be referenced everywhere
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
    static public final class ResultSet implements Iterable<ResultSet.ResultRecord> {
        //CHANGE: Changed to public to allow objects to be referenced everywhere
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
        static public final class ResultRecord {
            //CHANGE: Changed to public to allow referencing everywhere
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
