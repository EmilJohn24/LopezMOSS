package moss.gui.CorrelationMatrixMenu;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
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
    private GridPane correlationMatrixTable;
    @Inject
    private CorrelationMatrixMenuModel model;
    @Inject
    private CorrelationMatrixMenuService services;


    /**
     * Loads the matrix in the correlation menu model into the window via a grid pane
     */
    private void loadModelMatrixToTable(){
        final ProjectsCorrelationMatrix matrix = model.getMatrix();
        int row = 0;
        for (ResultRow resultRow : matrix.getRows()){
            Collection<Text> resultText = new ArrayList<>();
            resultText.add(new Text(resultRow.getProject().getName()));
            for (ResultSet.ResultRecord result : resultRow.getResults()){
                resultText.add(new Text(String.valueOf(result.getScore())));
            }

            Text[] resultTextArray = new Text[resultText.size()];
            resultText.toArray(resultTextArray);
            correlationMatrixTable.addRow(row++, resultText.toArray(resultTextArray));
        }
    }
    @FXML
    private void initialize(){
        loadModelMatrixToTable();

    }

}
