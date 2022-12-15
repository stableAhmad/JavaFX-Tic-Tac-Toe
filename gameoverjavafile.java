

import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class gameoverjavafile {
    public Label result;

    public void gameoverdisplay(String v) {
        if (v.equals("X") || v.equals("O")) {
            result.setText(v + " WINS!!");
        } else {
            result.setText("ITS A TIE");
        }
    }

    public void playAgain(ActionEvent x) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main_scene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) x.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}