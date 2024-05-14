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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.List;
import javafx.application.Platform;


import java.util.Random;


public class Viewer extends Application {

    private final Group root = new Group();
    private static final double VIEWER_WIDTH = 1100;
    private static final double VIEWER_HEIGHT = 650;
    private static final double MARGIN_X = 20;
    private static final double MARGIN_Y = 10;
    private static final double SQUARE_WIDTH = Square.SQUARE_WIDTH;
    public static final double shiftX = 320;
    public static final double shiftY = -20;

    private static double BOARD_WIDTH = 18 * SQUARE_WIDTH;
    private static double BOARD_HEIGHT = 18 * SQUARE_WIDTH;

    private static final double restartButtonX = VIEWER_WIDTH - 95;
    private static final double restartButtonY = VIEWER_HEIGHT - 30;

    private static final double rotateButton_X = 10;
    private static final double rotateButton_Y = VIEWER_HEIGHT - 50;

    private static final double deckChoiceBox_X = 100;
    private static final double deckChoiceBox_Y = VIEWER_HEIGHT - 50;
    private static final double drawCardButton_X = 150;
    private static final double drawCardButton_Y = VIEWER_HEIGHT - 50;

    private static final double placeButton_X = 250;
    private static final double placeButton_Y = VIEWER_HEIGHT - 50;
    private static final double drawFireTileButton_X = drawCardButton_X;
    private static final double drawFireTileButton_Y = drawCardButton_Y - 30;
    private static final double rotateFireTileButton_X = drawFireTileButton_X + 83;
    private static final double rotateFireTileButton_Y = drawFireTileButton_Y - 30;
    private static final double flipFireTileButton_X = drawFireTileButton_X + 100;
    private static final double flipFireTileButton_Y = drawFireTileButton_Y;
    private static final double cursorPositionX = 10;
    private static final double cursorPositionY = VIEWER_HEIGHT - 20;

    public static int catStartX, catStartY, endX, endY;

    private final Label cursorPosition = new Label();

    private final Group DrawBoard = new Group();
    private final Group DrawHand = new Group();
    private final Group CatGroup = new Group();
    private final Group drawnFireTileGroup = new Group();
    private String[] deckA = Utility.DECK_A;
    private String[] deckB = Utility.DECK_B;
    private String[] deckC = Utility.DECK_C;
    private String[] deckD = Utility.DECK_D;
    private FireTile drawnFireTile;
    private String challenge;
    private String hand;
    private String boardstate;
    private String cat;
    public static String catColor;

    private static String[] gamestate;

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
        Hand hands = new Hand("");
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

        boolean[] isDragged = new boolean[1];
        final double[] dragDelta = new double[2];

        for (Node node : DrawHand.getChildren()) {
            if (node instanceof Group) {
                Group cardGroup = (Group) node;

                cardGroup.setOnMousePressed(mouseEvent -> {
                    dragDelta[0] = cardGroup.getLayoutX() - mouseEvent.getSceneX();
                    dragDelta[1] = cardGroup.getLayoutY() - mouseEvent.getSceneY();
                    cardGroup.toFront(); // bring the card to the front
                    isDragged[0] = false; // reset the flag when mouse is pressed
                });

                cardGroup.setOnMouseDragged(mouseEvent -> {
                    cardGroup.setLayoutX(mouseEvent.getSceneX() + dragDelta[0]);
                    cardGroup.setLayoutY(mouseEvent.getSceneY() + dragDelta[1]);
                    isDragged[0] = true; // set the flag when mouse is dragged
                });

                cardGroup.setOnMouseClicked(e -> {
                    if (!isDragged[0]) { // check the flag in the click event and if the card is on the board
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
                            glow.setLevel(0.4);
                            cardGroup.setEffect(glow);

                            // Store the clicked cardGroup as the selected cardGroup
                            selectedCardGroup[0] = cardGroup;
                        }
                    }
                });
            }
        }

        Button placeButton = new Button("Place Card");

        placeButton.setLayoutX(placeButton_X); // Adjust these values as needed
        placeButton.setLayoutY(placeButton_Y); // Position the button at the bottom of the viewer

        placeButton.setOnAction(e -> {
            if (selectedCardGroup[0] != null) {
                // Disable the mouse event handlers of the selected card
                selectedCardGroup[0].setOnMousePressed(null);
                selectedCardGroup[0].setOnMouseDragged(null);
                selectedCardGroup[0].setOnMouseClicked(null);

                // Remove the glow effect from the selected card
                selectedCardGroup[0].setEffect(null);

                // Clear the selected card
                selectedCardGroup[0] = null;
            }
        });

        root.getChildren().add(placeButton);

        ChoiceBox<String> deckChoiceBox = new ChoiceBox<>();
        // cross (✕) represents deck A, square (□) represents deck B, circle (◯) represents deck C, triangle (△) represents deck D
        deckChoiceBox.getItems().addAll("✕", "□", "◯", "△");

        Button drawCardButton = new Button("Draw Card");

        // Convert String[] arrays to List<String>
        List<String> deckAList = Arrays.asList(Utility.DECK_A);
        List<String> deckBList = Arrays.asList(Utility.DECK_B);
        List<String> deckCList = Arrays.asList(Utility.DECK_C);
        List<String> deckDList = Arrays.asList(Utility.DECK_D);

        Deck deckAObj = new Deck('A', deckAList);
        Deck deckBObj = new Deck('B', deckBList);
        Deck deckCObj = new Deck('C', deckCList);
        Deck deckDObj = new Deck('D', deckDList);

        drawCardButton.setOnAction(e -> {
            String selectedDeck = deckChoiceBox.getValue();
            if (selectedDeck != null) {
                String drawnCard = null;
                switch (selectedDeck) {
                    case "✕":
                        drawnCard = Deck.drawCard(deckAObj);
                        break;
                    case "□":
                        drawnCard = Deck.drawCard(deckBObj);
                        break;
                    case "◯":
                        drawnCard = Deck.drawCard(deckCObj);
                        break;
                    case "△":
                        drawnCard = Deck.drawCard(deckDObj);
                        break;
                }
                if (drawnCard != null) {
                    // Create a Card object from the drawn card string
                    Card card = new Card(drawnCard);
                    // Get the Group containing the card's image views
                    Group cardGroup = new Group(card);
                    // Adjust position as needed
                    cardGroup.setLayoutX(400);
                    cardGroup.setLayoutY(100);
                    // Add the card's group to the root
                    root.getChildren().add(cardGroup);
                }
                // Perform the draw card action here
                System.out.println("Draw card from deck: " + selectedDeck);
            }
        });


        deckChoiceBox.setLayoutX(deckChoiceBox_X);
        deckChoiceBox.setLayoutY(deckChoiceBox_Y);
        drawCardButton.setLayoutX(drawCardButton_X);
        drawCardButton.setLayoutY(drawCardButton_Y);

        root.getChildren().addAll(deckChoiceBox, drawCardButton);

        // FIXME TASK 4
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(" - Race to the Raft - work by Group Mon15A3");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        gamestate = new String[5];
        gamestate[0] = "board state";
        gamestate[1] = "deck state";
        gamestate[2] = "hand state";
        gamestate[3] = "exhausted cats state";
        gamestate[4] = "fire tile bag state";

        Image titleImage = new Image("comp1110/ass2/gui/assets/title.png");

        ImageView titleImageView = new ImageView(titleImage);

        titleImageView.setX(VIEWER_WIDTH / 2 - titleImage.getWidth() / 2);
        titleImageView.setY(30); // Adjust this value as needed

        root.getChildren().add(titleImageView);

        Button drawFireTileButton = new Button("Draw Fire Tile");
        drawFireTileButton.setLayoutX(drawFireTileButton_X);
        drawFireTileButton.setLayoutY(drawFireTileButton_Y);
        root.getChildren().add(drawFireTileButton);

        root.getChildren().add(drawnFireTileGroup);

        drawFireTileButton.setOpacity(0);

        Button rotateFireTileButton = new Button("Rotate Fire Tile");
        rotateFireTileButton.setLayoutX(rotateFireTileButton_X);
        rotateFireTileButton.setLayoutY(rotateFireTileButton_Y);
        root.getChildren().add(rotateFireTileButton);

        Button flipFireTileButton = new Button("Flip Fire Tile");
        flipFireTileButton.setLayoutX(flipFireTileButton_X);
        flipFireTileButton.setLayoutY(flipFireTileButton_Y);
        root.getChildren().add(flipFireTileButton);

        rotateFireTileButton.setDisable(true);
        flipFireTileButton.setDisable(true);

        rotateFireTileButton.setOnAction(e -> {
            if (drawnFireTile != null) {
                drawnFireTile.rotate(PathwayCard.Direction.EAST); // Rotate the FireTile object
                drawnFireTileGroup.getChildren().clear();
                // Re-render the rotated fire tile
                char[][] tiles = drawnFireTile.getTiles();
                for (int row = 0; row < tiles.length; row++) {
                    for (int col = 0; col < tiles[0].length; col++) {
                        if (tiles[row][col] == 'f') {
                            ImageView fireTileImageView = new ImageView(new Image("comp1110/ass2/gui/assets/fire.png"));
                            fireTileImageView.setFitWidth(SQUARE_WIDTH);
                            fireTileImageView.setFitHeight(SQUARE_WIDTH);
                            fireTileImageView.setLayoutX(col * SQUARE_WIDTH);
                            fireTileImageView.setLayoutY(row * SQUARE_WIDTH);
                            drawnFireTileGroup.getChildren().add(fireTileImageView);
                        }
                    }
                }
            }
        });

        flipFireTileButton.setOnAction(e -> {
            if (drawnFireTile != null) {
                drawnFireTile.rotate(PathwayCard.Direction.FLIP); // Flip the FireTile object
                drawnFireTileGroup.getChildren().clear();
                // Re-render the flipped fire tile
                char[][] tiles = drawnFireTile.getTiles();
                for (int row = 0; row < tiles.length; row++) {
                    for (int col = 0; col < tiles[0].length; col++) {
                        if (tiles[row][col] == 'f') {
                            ImageView fireTileImageView = new ImageView(new Image("comp1110/ass2/gui/assets/fire.png"));
                            fireTileImageView.setFitWidth(SQUARE_WIDTH);
                            fireTileImageView.setFitHeight(SQUARE_WIDTH);
                            fireTileImageView.setLayoutX(col * SQUARE_WIDTH);
                            fireTileImageView.setLayoutY(row * SQUARE_WIDTH);
                            drawnFireTileGroup.getChildren().add(fireTileImageView);
                        }
                    }
                }
            }
        });

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
                gamestate[0] = RaceToTheRaft.initialiseChallenge(challenge);
                boardstate = gamestate[0];

                BOARD_WIDTH = boardstate.split("\n")[0].length() * SQUARE_WIDTH;
                BOARD_HEIGHT = boardstate.split("\n").length * SQUARE_WIDTH;

                // initialise the deck
                deckA = Utility.DECK_A;
                deckB = Utility.DECK_B;
                deckC = Utility.DECK_C;
                deckD = Utility.DECK_D;

                // initialise the game state
                gamestate[1] = "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy";
                gamestate[2] = Hand.generateHand();
                gamestate[3] = "";
                gamestate[4] = "abcdefghijklmnopqrstuvwxyzABCDE";

                cat = challengeObj.getCatSubstring();

//                hand = new String("");
//                hand = "Abbbccc";
//                hand = "AbdfBcCaDe";
                hand = gamestate[2];
                drawFireTileButton.setOpacity(1);

                // Set the action event handler for the drawFireTileButton
                drawFireTileButton.setOnAction(fireTileEvent -> {
                    String fireTileBag = gamestate[4];
                    if (!fireTileBag.isEmpty()) {
                        String drawnFireTile = FireTile.pickFire(fireTileBag);
                        gamestate[4] = fireTileBag.replace(drawnFireTile.substring(0, 1), "");
                        renderFireTile(drawnFireTile);
                        rotateFireTileButton.setDisable(false);
                        flipFireTileButton.setDisable(false);
                    }
                });

                refresh(boardstate, hand);
                root.getChildren().remove(vbox);

                root.getChildren().remove(titleImageView);
            }
        });


        // Perform the draw card action here
//        Draw(gameState, hand);


        vbox.getChildren().addAll(label, choiceBox, confirmButton);
        root.getChildren().add(vbox);

        vbox.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            vbox.setLayoutX((VIEWER_WIDTH - newValue.getWidth()) / 2);
            vbox.setLayoutY((VIEWER_HEIGHT - newValue.getHeight()) / 2 + 25);
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
            CatGroup.getChildren().clear();

            // Show the challenge selection part again
            if (!root.getChildren().contains(vbox)) {
                root.getChildren().add(vbox);
            }

            root.getChildren().add(titleImageView);

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

//        while (true) {
//
//        }

    }
    private void renderFireTile(String fireTileString) {
        drawnFireTileGroup.getChildren().clear();

        FireTile fireTile = FireTile.actionStringToFT(fireTileString);
        drawnFireTile = fireTile;
        char[][] tiles = fireTile.getTiles();

        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[0].length; col++) {
                if (tiles[row][col] == 'f') {
                    ImageView fireTileImageView = new ImageView(new Image("comp1110/ass2/gui/assets/fire.png"));
                    fireTileImageView.setFitWidth(SQUARE_WIDTH);
                    fireTileImageView.setFitHeight(SQUARE_WIDTH);
                    fireTileImageView.setLayoutX(col * SQUARE_WIDTH);
                    fireTileImageView.setLayoutY(row * SQUARE_WIDTH);
                    drawnFireTileGroup.getChildren().add(fireTileImageView);
                }
            }
        }

        // Position the fire tile group below the bottom left card
        drawnFireTileGroup.setLayoutX(MARGIN_X);
        drawnFireTileGroup.setLayoutY(MARGIN_Y + 11.5 * SQUARE_WIDTH);

        // Add mouse event handlers to the drawnFireTileGroup for moving and placing the fire tile
        final double[] dragDelta = new double[2];
        drawnFireTileGroup.setOnMousePressed(mouseEvent -> {
            dragDelta[0] = drawnFireTileGroup.getLayoutX() - mouseEvent.getSceneX();
            dragDelta[1] = drawnFireTileGroup.getLayoutY() - mouseEvent.getSceneY();
            drawnFireTileGroup.toFront(); // Bring the fire tile to the front when pressed
        });

        drawnFireTileGroup.setOnMouseDragged(mouseEvent -> {
            drawnFireTileGroup.setLayoutX(mouseEvent.getSceneX() + dragDelta[0]);
            drawnFireTileGroup.setLayoutY(mouseEvent.getSceneY() + dragDelta[1]);
        });

        drawnFireTileGroup.setOnMouseReleased(mouseEvent -> {
            double newX = Math.round((drawnFireTileGroup.getLayoutX() - MARGIN_X) / SQUARE_WIDTH) * SQUARE_WIDTH + MARGIN_X;
            double newY = Math.round((drawnFireTileGroup.getLayoutY() - MARGIN_Y) / SQUARE_WIDTH) * SQUARE_WIDTH + MARGIN_Y;
            drawnFireTileGroup.setLayoutX(newX);
            drawnFireTileGroup.setLayoutY(newY);

            // Check if the fire tile is placed on the board
            if (newX >= MARGIN_X + shiftX && newX < MARGIN_X + shiftX + BOARD_WIDTH &&
                    newY >= MARGIN_Y && newY < MARGIN_Y + BOARD_HEIGHT) {
                // Calculate the board position
                int boardX = (int) ((newX - MARGIN_X - shiftX) / SQUARE_WIDTH);
                int boardY = (int) ((newY - MARGIN_Y) / SQUARE_WIDTH);

                // Get the board dimensions
                char[][] charBoard = Board.charBoard(boardstate);
                int boardRows = charBoard.length;
                int boardCols = charBoard[0].length;

                // Check if the fire tile fits within the board bounds
                if (boardX >= 0 && boardX + fireTile.getTiles()[0].length <= boardCols &&
                        boardY >= 0 && boardY + fireTile.getTiles().length <= boardRows) {
                    // Update the board state with the placed fire tile
                    String newBoardState = FireTile.placeOnBoardFT(fireTile, charBoard, new int[]{boardY, boardX});
                    gamestate[0] = newBoardState;
                    boardstate = newBoardState;

                    // Remove the fire tile from the drawn group
                    drawnFireTileGroup.getChildren().clear();

                    refresh(boardstate, hand);
                } else {
                    // If the fire tile doesn't fit within the board bounds, snap it back to its original position
                    drawnFireTileGroup.setLayoutX(MARGIN_X);
                    drawnFireTileGroup.setLayoutY(MARGIN_Y + 11.5 * SQUARE_WIDTH);
                }
            } else {
                // If the fire tile is not placed on the board, snap it back to its original position
                drawnFireTileGroup.setLayoutX(MARGIN_X);
                drawnFireTileGroup.setLayoutY(MARGIN_Y + 11.5 * SQUARE_WIDTH);
            }
        });
    }
    public void refresh(String boardstate, String hand) {
        displayState(boardstate, hand);
    }

    public static void printGameState(String[] gamestate) {
        String[] rows = gamestate[0].split("\n");
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

    public static String[] getGameState() {
        return gamestate;
    }

    public static String[] setGameState(String[] newGameState) {
        gamestate = newGameState;
        return gamestate;
    }

}
