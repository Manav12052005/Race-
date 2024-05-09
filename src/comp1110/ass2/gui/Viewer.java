package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Viewer extends Application {

    private final Group root = new Group();
    private static final double VIEWER_WIDTH = 1100;
    private static final double VIEWER_HEIGHT = 650;
    private static final double MARGIN_X = 20;
    private static final double MARGIN_Y = 10;
    private static final double SQUARE_WIDTH = Square.SQUARE_WIDTH;
    private static final double shiftX = 320;

    private static final double restartButtonX = VIEWER_WIDTH - 95;
    private static final double restartButtonY = VIEWER_HEIGHT - 30;

    private static final double cursorPositionX = 10;
    private static final double cursorPositionY = VIEWER_HEIGHT - 20;

    private final Label cursorPosition = new Label();

    private final Group DrawBoard = new Group();
    private final Group DrawHand = new Group();

    private String challenge;
    private String hand;
    private String boardstate;
    private String cat;

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
//        DrawBoard.setLayoutX(VIEWER_WIDTH - 18 * SQUARE_WIDTH);
        DrawBoard.setLayoutX(shiftX + MARGIN_X);
        DrawBoard.setLayoutY(MARGIN_Y);
        DrawBoard.getChildren().clear();
        for (Square square : board.getSquares()) {
            square.setLayoutX(square.getValueX());
            square.setLayoutY(square.getValueY());

//            System.out.println(square.getValueX());
//            System.out.println(square.getValueY());
//            System.out.println();

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
            double outerX = (double) (i % 2) * 3 * SQUARE_WIDTH + (double) (i % 2) * SQUARE_WIDTH,
                    outerY = (double) (i / 2) * 3 * SQUARE_WIDTH + (double) (i / 2) * SQUARE_WIDTH;

            // Create a new group for each card
            Group cardGroup = new Group();

            for (Square square : card.getCard()) {
                square.setLayoutX(square.getValueX() + outerX);
                square.setLayoutY(square.getValueY() + outerY);

//             For debugging use
//                System.out.println(square.getValueX() + outerX);
//                System.out.println(square.getValueY() + outerY);
//                System.out.println();

                square.setImage(square.getImg());
                square.setFitWidth(SQUARE_WIDTH);
                square.setFitHeight(SQUARE_WIDTH);

//             For debugging use
//                System.out.println("Adding square of type: " + square.getT());
//                System.out.println("Image: " + square.getImage());

//                DrawHand.getChildren().add(square);
                cardGroup.getChildren().add(square);
            }

            // Add mouse event handlers to the card group
            final double[] dragDelta = new double[2];
            cardGroup.setOnMousePressed(mouseEvent -> {
                dragDelta[0] = cardGroup.getLayoutX() - mouseEvent.getSceneX();
                dragDelta[1] = cardGroup.getLayoutY() - mouseEvent.getSceneY();
            });

            cardGroup.setOnMouseDragged(mouseEvent -> {
                cardGroup.setLayoutX(mouseEvent.getSceneX() + dragDelta[0]);
                cardGroup.setLayoutY(mouseEvent.getSceneY() + dragDelta[1]);
            });

//            cardGroup.setOnMouseReleased(mouseEvent -> {
//                double newX = Math.round(cardGroup.getLayoutX() / SQUARE_WIDTH) * SQUARE_WIDTH;
//                double newY = Math.round(cardGroup.getLayoutY() / SQUARE_WIDTH) * SQUARE_WIDTH;
//                cardGroup.setLayoutX(newX);
//                cardGroup.setLayoutY(newY);
//            });
            cardGroup.setOnMouseReleased(mouseEvent -> {
                double newX = Math.round((cardGroup.getLayoutX() - outerX) / SQUARE_WIDTH) * SQUARE_WIDTH + outerX;
                double newY = Math.round((cardGroup.getLayoutY() - outerY) / SQUARE_WIDTH) * SQUARE_WIDTH + outerY;
                cardGroup.setLayoutX(newX);
                cardGroup.setLayoutY(newY);
            });

            // Add the card group to the DrawHand group
            DrawHand.getChildren().add(cardGroup);

            i++;
        }
        // FIXME TASK 4
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(" - Race to the Raft - work by Group Mon15A3");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        Label label = new Label("Select Difficulty: (from 0 to 5)");
        ChoiceBox<Integer> choiceBox = new ChoiceBox<>();
        for (int i = 0; i <= 5; i++) {
            choiceBox.getItems().add(i);
        }
        Button confirmButton = new Button("Start Game!");
        confirmButton.setOnAction(e -> {
            Integer selectedDifficulty = choiceBox.getValue();
            String challenge = RaceToTheRaft.chooseChallenge(selectedDifficulty);
            Challenge challengeObj = new Challenge(RaceToTheRaft.initialiseChallenge(challenge));
            if (selectedDifficulty != null) {
                boardstate = RaceToTheRaft.initialiseChallenge(challenge);
                cat = challengeObj.getCatSubstring();
//                hand = new String("");
                hand = "Abbbccc";

                refresh(boardstate, hand);
                root.getChildren().remove(vbox);
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


        // Add a restart button to the root group
        Button restartButton = new Button("Restart Game");

        restartButton.setOnAction(e -> {
            // Clear the game state
            boardstate = null;
            hand = null;
            cat = null;
            DrawBoard.getChildren().clear();
            DrawHand.getChildren().clear();

            // Show the challenge selection part again
            if (!root.getChildren().contains(vbox)) {
                root.getChildren().add(vbox);
            }
        });

        restartButton.setLayoutX(restartButtonX); // Adjust these values as needed
        restartButton.setLayoutY(restartButtonY); // Position the button at the bottom of the viewer

        root.getChildren().add(restartButton);


        // Add the cursor position label to the root group
        cursorPosition.setLayoutX(cursorPositionX); // Adjust these values as needed
        cursorPosition.setLayoutY(cursorPositionY); // Position the label at the bottom of the viewer
        root.getChildren().add(cursorPosition);

        // Set up a mouse moved event handler to update the cursor position label
        root.setOnMouseMoved(event -> {
            double x = event.getX();
            double y = event.getY();
            String formattedX = String.format("%.2f", x);
            String formattedY = String.format("%.2f", y);
            cursorPosition.setText("x: " + formattedX + ", y: " + formattedY);
        });
    }

    public void refresh(String boardstate, String hand) {
        displayState(boardstate, hand);
    }
}
