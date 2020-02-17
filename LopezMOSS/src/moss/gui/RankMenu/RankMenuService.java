package moss.gui.RankMenu;

import moss.projectpairmachine.ProjectsCorrelationMatrix;

import java.util.Collections;
import java.util.List;

public class RankMenuService {
    /**
     * @param matrix Matrix to be ranked
     * @return Returns a list of ranked pairings from the matrix
     */
    public List<ProjectsCorrelationMatrix.ResultTrio> descendingRankResults(ProjectsCorrelationMatrix matrix){
        List<ProjectsCorrelationMatrix.ResultTrio> flattenedMatrix = matrix.flatten();
        Collections.sort(flattenedMatrix);
        Collections.reverse(flattenedMatrix);
        return flattenedMatrix;
    }
}
