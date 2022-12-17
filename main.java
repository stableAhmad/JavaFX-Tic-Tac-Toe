import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.Parent;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

public class main extends Application {
    public Label names;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
   /* */     Parent root = FXMLLoader.load(getClass().getResource("main_scene.fxml"));
        Scene main_scene = new Scene(root);

        stage.setScene(main_scene);
        Image image = new Image("icon/icon.png");
        stage.getIcons().add(image);
        stage.show();
    }

    public void singleInput(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("board_scene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        board playController = loader.getController();
        playController.setPlayMode("single");

        stage.show();
    }

    public void multiInput(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("board_scene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        board playController = loader.getController();
        playController.setPlayMode("multi");

        stage.show();
    }

}
