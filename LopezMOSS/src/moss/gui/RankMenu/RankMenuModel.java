package moss.gui.RankMenu;

import moss.project.Projects;
import moss.projectpairmachine.ProjectsCorrelationMatrix;

/**
 * Model for the rank menu
 */
public class RankMenuModel {
    private ProjectsCorrelationMatrix matrix;

    /**
     * @param matrix Matrix to be ranked and displayed into rank menu
     */
    public void loadRankOf(ProjectsCorrelationMatrix matrix){
        this.matrix = matrix;
    }

    /**
     * @return Matrix to be ranked
     */
    ProjectsCorrelationMatrix getMatrix() {
        return matrix;
    }
}
