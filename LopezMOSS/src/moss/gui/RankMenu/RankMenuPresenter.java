package moss.gui.RankMenu;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import moss.gui.utilities.CustomFXMLObjectsGenerators;
import moss.projectpairmachine.ProjectsCorrelationMatrix;

import javax.inject.Inject;
import java.util.List;

public class RankMenuPresenter {
    @FXML
    private GridPane rankGridPane;
    @Inject
    private RankMenuService services;
    @Inject
    private RankMenuModel model;

    @FXML
    private void initialize(){
        //PHASE 1: Take the matrix to be loaded from the model and then rank it using the provided service.
        List<ProjectsCorrelationMatrix.ResultTrio> rankedResults = services.descendingRankResults(model.getMatrix());
        //PHASE 2: Load the ranked results into the grid pane
        int i = 1;
        for (ProjectsCorrelationMatrix.ResultTrio resultTrio : rankedResults){
            rankGridPane.addRow(i++,
                    new Text(resultTrio.getFirstProject().getName()),
                    new Text(resultTrio.getSecondProject().getName()),
                    CustomFXMLObjectsGenerators.generateColorCodedScorePane(resultTrio.getScore()));
        }
        rankGridPane.setGridLinesVisible(true);
    }
}
