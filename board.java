

import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.Parent;
import java.util.Arrays;
import javafx.scene.Scene;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.net.URL;

import java.util.List;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class board implements Initializable {
    private String playMode;
    private String SimBoard;

    private String charPlay = "X";
    public Label l1;
    public Label l2;
    public Label l3;
    public Label l4;
    public Label l5;
    public Label l6;
    public Label l7;
    public Label l8;
    public Label l9;
    private List<Label> depth;

    public void initialize(URL location, ResourceBundle resources) {

        depth = initializeLabels(l1, l2, l3, l4, l5, l6, l7, l8, l9);
        SimBoard = "         "; // 9 spaces represent 9 cells in the board

    }                                                   //      DONE WITH



    public void setPlayMode(String x) {
        playMode = x;
    }                                                                               //      DONE WITH

    public boolean play(MouseEvent event) throws Exception {

        if (playMode.equals("multi")) {
            Label temp = (Label) event.getSource();

            if (temp.getText().equals("")) {
                temp.setText(charPlay);

            }

            if (checkState().equals("win")) {
                switchScenes(charPlay, new FXMLLoader(getClass().getResource("gameover.fxml")), l1);
                return true;
            } else if (checkState().equals("tie")) {
                switchScenes("h", new FXMLLoader(getClass().getResource("gameover.fxml")), l1);

                return true;
            }

            charPlay = (charPlay.equals("X")) ? "O" : "X";

            return true;

        } else {

            Label temp = (Label) event.getSource();
            if (temp.getText().equals("")) {
                temp.setText(charPlay);
                int playLabel = Integer.parseInt(temp.getId().charAt(temp.getId().length() - 1) + "") - 1;
                if (playLabel != 8) {
                    SimBoard = SimBoard.substring(0, playLabel) + "X" + SimBoard.substring(playLabel + 1);
                } else {
                    SimBoard = SimBoard.substring(0, playLabel) + "X";
                }

                depth.remove(temp);
            }

            if (checkState().equals("win")) {
                switchScenes(charPlay, new FXMLLoader(getClass().getResource("gameover.fxml")), l1);
                return true;
            } else if (checkState().equals("tie")) {
                switchScenes("h", new FXMLLoader(getClass().getResource("gameover.fxml")), l1);

                return true;
            }

            // cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc

            Label nextcpuPlay = depth.get(0);
            int score = -2;

            for (int i = 0; i < depth.size(); i++) {
                List<Label> remaining = new ArrayList<Label>();
                for (Label l : depth) {
                    if (!(l.equals(depth.get(i))))
                        remaining.add(l);
                }

                int result = minimax(depth.get(i), remaining, true, SimBoard); // ? about the current label and list

                if (result > score) {
                    score = result;
                    nextcpuPlay = depth.get(i);

                    if (result == 1) {

                    }
                }
            }

            nextcpuPlay.setText("O");

            int playLabel2 = Integer.parseInt(nextcpuPlay.getId().charAt(nextcpuPlay.getId().length() - 1) + "") - 1;
            if (playLabel2 != 8) {
                SimBoard = SimBoard.substring(0, playLabel2) + "O" + SimBoard.substring(playLabel2 + 1);
            } else {
                SimBoard = SimBoard.substring(0, playLabel2) + "O";

            }

            depth.remove(nextcpuPlay);

            if (checkState().equals("win")) {
                switchScenes("O", new FXMLLoader(getClass().getResource("gameover.fxml")), l1);
                return true;
            } else if (checkState().equals("tie")) {
                switchScenes("h", new FXMLLoader(getClass().getResource("gameover.fxml")), l1);

                return true;
            }

            // END OF CODE HERE
            return true;
        }

    }



    public static void switchScenes(String winnerChar, FXMLLoader loader, Label l1) throws Exception {

        Parent root = loader.load();
        gameoverjavafile controller = loader.getController();
        controller.gameoverdisplay(winnerChar);
        Scene scene = new Scene(root);
        Stage stage = (Stage) l1.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }               //  DONE WITH




    public String checkState() {
        if (l1.getText().equals(l2.getText()) && l1.getText().equals(l3.getText()) && !(l1.getText().equals("")) ||
                l4.getText().equals(l5.getText()) && l4.getText().equals(l6.getText()) && !(l4.getText().equals("")) ||
                l7.getText().equals(l8.getText()) && l7.getText().equals(l9.getText()) && !(l7.getText().equals("")) ||
                l1.getText().equals(l5.getText()) && l1.getText().equals(l9.getText()) && !(l1.getText().equals("")) ||
                l3.getText().equals(l5.getText()) && l3.getText().equals(l7.getText()) && !(l3.getText().equals("")) ||
                l1.getText().equals(l4.getText()) && l1.getText().equals(l7.getText()) && !(l1.getText().equals("")) ||
                l2.getText().equals(l5.getText()) && l2.getText().equals(l8.getText()) && !(l2.getText().equals("")) ||
                l3.getText().equals(l6.getText()) && l3.getText().equals(l9.getText()) && !(l3.getText().equals("")))
            return "win";
        else if ((!l1.getText().equals("")) &&
                (!l2.getText().equals("")) &&
                (!l3.getText().equals("")) &&
                (!l4.getText().equals("")) &&
                (!l5.getText().equals("")) &&
                (!l6.getText().equals("")) &&
                (!l7.getText().equals("")) &&
                (!l8.getText().equals("")) &&
                (!l9.getText().equals("")))
            return "tie";

        return "going";
    }                                                                                          //      DONE WITH




    private List<Label> initializeLabels(Label... labels) {
        List<Label> depth = new ArrayList<Label>();
        for (Label x : labels) {

            depth.add(x);
        }
        return depth;
    }
    //////// AI SECTION////////////////////////////////////////////////

    private int minimax(Label current, List<Label> depth, boolean maxTurn, String brd) {

        int playLabel = Integer.parseInt(current.getId().charAt(current.getId().length() - 1) + "") - 1;
        if (playLabel != 8) {
            brd = brd.substring(0, playLabel) + ((maxTurn) ? "O" : "X") + brd.substring(playLabel + 1);
        } else {
            brd = brd.substring(0, playLabel) + ((maxTurn) ? "O" : "X");
        }

        if (checkAIBoard(brd).equals("win")) {

            return (maxTurn) ? 1 : -1;
        } else if (checkAIBoard(brd).equals("tie")) {

            return 0;
        }

        if (maxTurn) {

            int value = 2;

            for (int i = 0; i < depth.size(); i++) {
                List<Label> nextDepth = new ArrayList<Label>();
                for (Label l : depth) {
                    if (!(l.equals(depth.get(i)))) {
                        nextDepth.add(l);
                    }
                }

                value = Math.min(value, minimax(depth.get(i), nextDepth, !maxTurn, brd));
            }

            return value;

        } else {
            int value = -2;

            for (int i = 0; i < depth.size(); i++) {
                List<Label> nextDepth = new ArrayList<Label>();
                for (Label l : depth) {
                    if (!(l.equals(depth.get(i)))) {
                        nextDepth.add(l);
                    }
                }

                value = Math.max(value, minimax(depth.get(i), nextDepth, !maxTurn, brd));
            }
            return value;

        }

    }

    // CHECK THE AI BOARD
    private String checkAIBoard(String brd) {
        if (((brd.charAt(0) == brd.charAt(1)) && (brd.charAt(0) == brd.charAt(2)) && !(brd.charAt(0) == ' ')) ||
                (brd.charAt(3) == brd.charAt(4)) && (brd.charAt(3) == brd.charAt(5)) && !(brd.charAt(3) == ' ') ||
                (brd.charAt(6) == brd.charAt(7)) && (brd.charAt(6) == brd.charAt(8)) && !(brd.charAt(6) == ' ') ||
                (brd.charAt(0) == brd.charAt(3)) && (brd.charAt(0) == brd.charAt(6)) && !(brd.charAt(0) == ' ') ||
                (brd.charAt(1) == brd.charAt(4)) && (brd.charAt(1) == brd.charAt(7)) && !(brd.charAt(1) == ' ') ||
                (brd.charAt(2) == brd.charAt(5)) && (brd.charAt(2) == brd.charAt(8)) && !(brd.charAt(2) == ' ') ||
                (brd.charAt(0) == brd.charAt(4)) && (brd.charAt(0) == brd.charAt(8)) && !(brd.charAt(0) == ' ') ||
                (brd.charAt(2) == brd.charAt(4)) && (brd.charAt(2) == brd.charAt(6)) && !(brd.charAt(2) == ' ')) {
            return "win";
        } else if (brd.charAt(0) != ' ' &&
                brd.charAt(1) != ' ' &&
                brd.charAt(2) != ' ' &&
                brd.charAt(3) != ' ' &&
                brd.charAt(4) != ' ' &&
                brd.charAt(5) != ' ' &&
                brd.charAt(6) != ' ' &&
                brd.charAt(7) != ' ' &&
                brd.charAt(8) != ' ') {
            return "tie";
        }
        return "going";
    }                                                                           //      DONE WITH

                }