

import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.animation.TranslateTransition;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class credits {
    public Label l;
    public Stage stage;

    public void transition() {
        System.out.println("working trans");
        TranslateTransition t = new TranslateTransition();
        t.setDuration(Duration.seconds(5));
        t.setToX(0);
        t.setToY(-426);

        t.setNode(l);

        t.setOnFinished(event -> stage.close());
        t.play();

    }

}