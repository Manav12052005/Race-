package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Viewer extends Application {

    private final Group root = new Group();
    private static final int VIEWER_WIDTH = 1100;
    private static final int VIEWER_HEIGHT = 650;
    private static final int MARGIN_X = 20;
    private static final int MARGIN_Y = 10;
    private static final double SQUARE_WIDTH = Square.SQUARE_WIDTH;

    private final Group DrawBoard = new Group();
    private final Group DrawHand = new Group();

    /**
     * Draw the given board and hand in the window, removing any previously drawn boards/hands.
     *
     * @param boardstate newline separated string representing each row of the board (the board string, see the STRING-REPRESENTATION.md for more details
     * @param hand A string representing the cards in a player's hand (the hand string, see the STRING-REPRESENTATION.md for more details)
     *
     */
    void displayState(String boardstate, String hand) {
        // Draw the game board with given boardstate string
        Board board = new Board(boardstate);
        DrawBoard.setLayoutX(VIEWER_WIDTH - 18 * SQUARE_WIDTH);
//        DrawBoard.setLayoutX(300);
        DrawBoard.setLayoutY(0);
        DrawBoard.getChildren().clear();
        for (Square square : board.getSquares()) {
            square.setLayoutX(square.getValueX());
            square.setLayoutY(square.getValueY());
            square.setImage(square.getImg());
            square.setFitWidth(SQUARE_WIDTH);
            square.setFitHeight(SQUARE_WIDTH);
            DrawBoard.getChildren().add(square);
        }

        // Draw the cards in hand with given hand string
        Hand hands = new Hand(hand);
        DrawHand.setLayoutX(MARGIN_X);
        DrawHand.setLayoutY(MARGIN_Y);
        DrawHand.getChildren().clear();
        int i = 0;
        for (Card card : hands.getCards()) {
            double outerX = (i % 2) * 3 * SQUARE_WIDTH + (i % 2) * 10,
                    outerY = (i / 2) * 3 * SQUARE_WIDTH + (i / 2) * 10;
            for (Square square : card.getCard()) {
                square.setLayoutX(square.getValueX() + outerX);
                square.setLayoutY(square.getValueY() + outerY);
                square.setImage(square.getImg());
                square.setFitWidth(SQUARE_WIDTH);
                square.setFitHeight(SQUARE_WIDTH);

//             For debugging use
//                System.out.println("Adding square of type: " + square.getT());
//                System.out.println("Image: " + square.getImage());

                DrawHand.getChildren().add(square);
            }
            i++;
        }
        // FIXME TASK 4
    }





    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(" - Race to the Raft - work by Group Mon15A3");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);


        VBox vbox = new VBox();
        Label label = new Label("Select Difficulty: (from 0 to 5)");
        ChoiceBox<Integer> choiceBox = new ChoiceBox<>();
        for (int i = 0; i <= 5; i++) {
            choiceBox.getItems().add(i);
        }
        Button confirmButton = new Button("Start Game");
        confirmButton.setOnAction(e -> {
            Integer selectedDifficulty = choiceBox.getValue();
            if (selectedDifficulty != null) {
                String boardstate = RaceToTheRaft.initialiseChallenge(RaceToTheRaft.chooseChallenge(selectedDifficulty));
                String hand = "AfhkBCDahw"; // placeholder for now
                refresh(boardstate, hand);
            }
        });
        vbox.getChildren().addAll(label, choiceBox, confirmButton);
        root.getChildren().add(vbox);

        vbox.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            vbox.setLayoutX((VIEWER_WIDTH - newValue.getWidth()) / 2);
            vbox.setLayoutY((VIEWER_HEIGHT - newValue.getHeight()) / 2);
        });

        root.getChildren().add(DrawBoard);
        root.getChildren().add(DrawHand);
        stage.setScene(scene);
        stage.show();

    }

    public void refresh(String boardstate, String hand) {
        displayState(boardstate, hand);
    }
}
