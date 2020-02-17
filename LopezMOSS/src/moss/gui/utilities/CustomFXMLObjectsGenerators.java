package moss.gui.utilities;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * A set of custom FXML object generators, FXML nodes which have custom properties that are used exclusively by LopezMOSS for its GUI display.
 * This means that the methods here are meaningless in any other context.
 */
public final class CustomFXMLObjectsGenerators {
    /**
     * @param value Value to be put in the pane (Ranges from 0.0 to 1.0
     * @return A pane with the given value
     */
    public static Pane generateColorCodedScorePane(double value){
        Pane scoreTextContainer = new StackPane(new Text(String.format("%.2f", value)));
        scoreTextContainer.setBackground(
                new Background(
                        new BackgroundFill(CustomColorOperations
                                .interpolateColor(Color.GREEN, Color.RED, value), null, null)
                )
        );

        return scoreTextContainer;
    }

    private CustomFXMLObjectsGenerators(){

    }
}