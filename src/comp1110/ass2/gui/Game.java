package comp1110.ass2.gui;

import comp1110.ass2.RaceToTheRaft;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1100;
    private static final int WINDOW_HEIGHT = 650;

    // FIXME TASK 11 Basic game

    private Viewer viewer = new Viewer();





    // FIXME TASK 13 Fully working game

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);

        viewer.start(stage);
        stage.show();

        //

//        String boardstate = RaceToTheRaft.initialiseChallenge(RaceToTheRaft.chooseChallenge(0));
//        String hand = "AfhkBCDahw";
//        viewer.refresh(boardstate, hand);
    }
}
