package moss.gui.CorrelationMatrixMenu;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import moss.gui.RankMenu.RankMenuModel;
import moss.gui.RankMenu.RankMenuView;
import moss.gui.utilities.CustomColorOperations;
import moss.gui.utilities.CustomFXMLObjectsGenerators;
import moss.gui.utilities.CustomFXMLOperations;
import moss.projectpairmachine.ProjectsCorrelationMatrix;
import moss.projectpairmachine.ProjectsCorrelationMatrix.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Presenter for the correlation matrix
 */
public class CorrelationMatrixMenuPresenter {
    @FXML
    private Button rankButton;
    @FXML
    private GridPane correlationMatrixTable;
    @Inject
    private CorrelationMatrixMenuModel model;
    @Inject
    private CorrelationMatrixMenuService services;
    @Inject
    private RankMenuModel rankMenuModel;


    /**
     * Loads the matrix in the correlation menu model into the window via a grid pane
     */
    private void loadModelMatrixToTable(){
        final ProjectsCorrelationMatrix matrix = model.getMatrix();
        //PHASE 1: Load headers
        Collection<Text> nameText = new ArrayList<>();
        nameText.add(new Text());
        for (String name : matrix.getProjectNames()){
            nameText.add(new Text(name));
        }
        Text[] nameTextArray = new Text[nameText.size()];
        nameText.toArray(nameTextArray);
        correlationMatrixTable.addRow(0, nameTextArray);


        //PHASE 2: Load all the results from the table into the grid pane
        int row = 1;
        for (ResultRow resultRow : matrix.getRows()){
            Collection<Pane> resultsPaneInRow = new ArrayList<>();
            Pane newPanes = new StackPane(new Text(resultRow.getProject().getName()));
            newPanes.setPrefWidth(500);
            resultsPaneInRow.add(newPanes);
            for (ResultSet.ResultRecord result : resultRow.getResults()){
                //CHANGE: Colored pane generation moved to utility function, allowing briefer code elsewere
                resultsPaneInRow.add(CustomFXMLObjectsGenerators
                        .generateColorCodedScorePane(result.getScore()));
            }

            Pane[] resultTextArray = new Pane[resultsPaneInRow.size()];
            resultsPaneInRow.toArray(resultTextArray);
            correlationMatrixTable.addRow(row++, resultsPaneInRow.toArray(resultTextArray));
        }

        correlationMatrixTable.setGridLinesVisible(true);
    }
    @FXML
    private void initialize(){
        loadModelMatrixToTable();
        //Initialize rank button
        rankButton.setOnMouseClicked(event -> {
            rankMenuModel.loadRankOf(model.getMatrix());
            CustomFXMLOperations.showFxmlViewInWindow(RankMenuView.class);
        });
    }

}
