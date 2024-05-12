package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

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

    private static final double rotateButton_X = 10;
    private static final double rotateButton_Y = VIEWER_HEIGHT - 50;

    private static final double deckChoiceBox_X = 100;
    private static final double deckChoiceBox_Y = VIEWER_HEIGHT - 50;
    private static final double drawCardButton_X = 150;
    private static final double drawCardButton_Y = VIEWER_HEIGHT - 50;

    private static final double cursorPositionX = 10;
    private static final double cursorPositionY = VIEWER_HEIGHT - 20;

    private final Label cursorPosition = new Label();

    private final Group DrawBoard = new Group();
    private final Group DrawHand = new Group();
    private final Group CatGroup = new Group();

    private String[] deckA;
    private String[] deckB;
    private String[] deckC;
    private String[] deckD;

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
        DrawBoard.setLayoutX(shiftX + MARGIN_X);
        DrawBoard.setLayoutY(MARGIN_Y);
        DrawBoard.getChildren().clear();

        // First add all the squareImageViews
        for (Square square : board.getSquares()) {
            ImageView squareImageView = square.getSquareImageView();
            squareImageView.setLayoutX(square.getValueX());
            squareImageView.setLayoutY(square.getValueY());
            DrawBoard.getChildren().add(squareImageView);
        }


        CatGroup.setLayoutX(shiftX + MARGIN_X);
        CatGroup.setLayoutY(MARGIN_Y);
        CatGroup.getChildren().clear();
        // Then add all the catImageViews
        for (Square square : board.getSquares()) {
            if (square.isCat()) {
                ImageView catImageView = square.getCatImageView();
                catImageView.setLayoutX(square.getValueX());
                catImageView.setLayoutY(square.getValueY());
                CatGroup.getChildren().add(catImageView);
            }
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

                square.getSquareImageView().setImage(square.getImg());
                square.getSquareImageView().setFitWidth(SQUARE_WIDTH);
                square.getSquareImageView().setFitHeight(SQUARE_WIDTH);

                if (square.isCat()) {
                    square.getCatImageView().setImage(square.getImg());
                    square.getCatImageView().setFitWidth(SQUARE_WIDTH);
                    square.getCatImageView().setFitHeight(SQUARE_WIDTH);
                }

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

            cardGroup.setOnMousePressed(mouseEvent -> {
                dragDelta[0] = cardGroup.getLayoutX() - mouseEvent.getSceneX();
                dragDelta[1] = cardGroup.getLayoutY() - mouseEvent.getSceneY();
                cardGroup.toFront(); // bring the card to the front
            });

            i++;
        }

        Button rotateButton = new Button("Rotate");
        rotateButton.setLayoutX(rotateButton_X);
        rotateButton.setLayoutY(rotateButton_Y);
        root.getChildren().add(rotateButton);

        Group[] selectedCardGroup = new Group[1];
        rotateButton.setOnAction(e -> {
            if (selectedCardGroup[0] != null) {
                selectedCardGroup[0].setRotate(selectedCardGroup[0].getRotate() + 90);
            }
        });

        for (Node node : DrawHand.getChildren()) {
            if (node instanceof Group) {
                Group cardGroup = (Group) node;
                cardGroup.setOnMouseClicked(e -> {
                    // Check if the clicked cardGroup is already the selected one
                    if (selectedCardGroup[0] == cardGroup) {
                        // Deselect the cardGroup
                        cardGroup.setEffect(null);
                        selectedCardGroup[0] = null;
                    } else {
                        // Remove highlight from previously selected cardGroup
                        if (selectedCardGroup[0] != null) {
                            selectedCardGroup[0].setEffect(null);
                        }

                        // Highlight the clicked cardGroup
                        Glow glow = new Glow();
                        glow.setLevel(0.8); // Adjust the level as needed
                        cardGroup.setEffect(glow);

                        // Store the clicked cardGroup as the selected cardGroup
                        selectedCardGroup[0] = cardGroup;
                    }
                });
            }
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

        // Initialize the game state
        String[] gameState = new String[5];
        // Set the initial values for the game state
        gameState[0] = "board state";
        gameState[1] = "deck state";
        gameState[2] = "hand state";
        gameState[3] = "exhausted cats state";
        gameState[4] = "fire tile bag state";

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
                gameState[0] = RaceToTheRaft.initialiseChallenge(challenge);
                boardstate = gameState[0];

                // initialise the deck
                deckA = Utility.DECK_A;
                deckB = Utility.DECK_B;
                deckC = Utility.DECK_C;
                deckD = Utility.DECK_D;

                // initialise the game state
                gameState[1] = "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy";
                gameState[2] = Hand.generateHand();
                gameState[3] = "";
                gameState[4] = "abcdefghijklmnopqrstuvwxyzABCDE";

                cat = challengeObj.getCatSubstring();

//                hand = new String("");
//                hand = "Abbbccc";
//                hand = "AbdfBcCaDe";
                hand = gameState[2];

                refresh(boardstate, hand);
                root.getChildren().remove(vbox);
            }
        });

// Create a ChoiceBox
        ChoiceBox<String> deckChoiceBox = new ChoiceBox<>();
        deckChoiceBox.getItems().addAll("A", "B", "C", "D");

// Create a Button
        Button drawCardButton = new Button("Draw Card");

// Add action event to the button
        drawCardButton.setOnAction(e -> {
            String selectedDeck = deckChoiceBox.getValue();
            if (selectedDeck != null) {
                // Perform the draw card action here
                System.out.println("Draw card from deck: " + selectedDeck);
            }
        });

// Set the layout for Button
        deckChoiceBox.setLayoutX(deckChoiceBox_X);
        deckChoiceBox.setLayoutY(deckChoiceBox_Y);
        drawCardButton.setLayoutX(drawCardButton_X);
        drawCardButton.setLayoutY(drawCardButton_Y);

// Add the ChoiceBox and Button to the root node
        root.getChildren().addAll(deckChoiceBox, drawCardButton);

        // Perform the draw card action here
//        Draw(gameState, hand);


        vbox.getChildren().addAll(label, choiceBox, confirmButton);
        root.getChildren().add(vbox);

        vbox.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            vbox.setLayoutX((VIEWER_WIDTH - newValue.getWidth()) / 2);
            vbox.setLayoutY((VIEWER_HEIGHT - newValue.getHeight()) / 2);
        });

        root.getChildren().add(DrawBoard);
        root.getChildren().add(DrawHand);
        root.getChildren().add(CatGroup);

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

    public void printGameState(String gamestate) {
        String[] rows = gamestate.split("\n");
        for (String row : rows) {
            System.out.println(row);
        }
    }

    public static int[] getGridIndex(double cursorX, double cursorY) {
        double startX = shiftX + MARGIN_X;
        double startY = MARGIN_Y;
        double squareWidth = SQUARE_WIDTH;

        double relativeX = cursorX - startX;
        double relativeY = cursorY - startY;

        int gridX = (int) Math.floor(relativeX / squareWidth) + 9;
        int gridY = (int) Math.floor(relativeY / squareWidth) + 1;

        return new int[] {gridX, gridY};
    }

    public String Draw(String[] gameState, String hand) {

        StringBuilder sb = new StringBuilder(hand);
        while (sb.length() < 12) {


        }



        return sb.toString();
    }

}
