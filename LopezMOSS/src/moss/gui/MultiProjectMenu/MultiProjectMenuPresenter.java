package moss.gui.MultiProjectMenu;


import com.airhacks.afterburner.views.FXMLView;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import moss.gui.CorrelationMatrixMenu.CorrelationMatrixMenuModel;
import moss.gui.CorrelationMatrixMenu.CorrelationMatrixMenuView;
import moss.project.PathFilter;

import javax.inject.Inject;
import java.awt.*;
import java.nio.file.Path;

/**
 * Controls the multi-project menu
 */
public class MultiProjectMenuPresenter {
    @FXML
    private RadioButton regexRadio;
    @FXML
    private RadioButton glubRadio;
    @FXML
    private TextField filterText;
    @FXML
    private ToggleGroup filterType;
    @FXML
    private ListView<String> filterListView;
    @FXML
    private Button startComparisonButton;
    @FXML
    private Label projectCountLabel;
    @FXML
    private Label projectPathLabel;
    @FXML
    private Button projectPathChooseButton;



    //RENAME: renamed from Controller to Presenter to avoid confusion while reading papers on FXML conventions
    @Inject
    private MultiProjectMenuService services;
    @Inject
    private MultiProjectMenuModel model;
    @Inject
    private CorrelationMatrixMenuModel correlationMatrixModel;


    @FXML
    private void initialize(){
        //PART 1: Project path selection
        projectPathChooseButton.setOnMouseClicked((event -> {
            //PROJECT PATH CHOOSE BUTTON ACTION
            Path requestedPath = services.requestDirectoryFromUser();
            model.setChosenProjectsPath(requestedPath);
            projectPathLabel.setText(String.valueOf(requestedPath));
            projectCountLabel.setText(String.valueOf(services.folderCount(requestedPath)));
            startComparisonButton.setDisable(false);
        }));

        //PART 2: Text filter
        filterText.setOnAction((event -> {
            filterListView.getItems().add(filterText.getText());
            model.getFilterBuilder().addFilter(filterText.getText());
        }));
        //VERBOSE: This simply takes the underlying value of the newly selected radio button (Filter type) and
        //injects that to the filter builder
        filterType.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
                model.getFilterBuilder().setFilterType((PathFilter.Type) newValue.getUserData()));
        regexRadio.setUserData(PathFilter.Type.REGEX);
        glubRadio.setUserData(PathFilter.Type.GLOB);


        //PART 3: Comparison button
        startComparisonButton.setDisable(true);
        startComparisonButton.setOnMouseClicked((event -> {
            //creates the correlation matrix and loads it into the correlation matrix menu's model
            correlationMatrixModel.loadMatrix(services.processMultiProjectFolder(model.getChosenProjectsPath(), model.createFilter()));


            //Load correlation matrix menu
            CorrelationMatrixMenuView matrixView = new CorrelationMatrixMenuView();
            Stage temporaryMatrixStage = new Stage();
            temporaryMatrixStage.setScene(new Scene(matrixView.getView()));
            temporaryMatrixStage.show();
        }));


    }
}
