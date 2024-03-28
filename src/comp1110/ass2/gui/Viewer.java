package comp1110.ass2.gui;

import comp1110.ass2.Board;
import comp1110.ass2.Hand;
import comp1110.ass2.Square;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Viewer extends Application {

    private final Group root = new Group();
    private static final int VIEWER_WIDTH = 1100;
    private static final int VIEWER_HEIGHT = 650;
    private static final int MARGIN_X = 20;
    private static final int MARGIN_Y = 20;
    private static final double SQUARE_WIDTH = 40;

    private final Group controls = new Group();
    private Group DrawBoard = new Group();
    private Group DrawHand = new Group();
    private TextArea handTextField;
    private TextArea boardTextField;

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
        DrawBoard.setLayoutY(0);
        DrawBoard.getChildren().clear();
        for (Square square : board.getSquares()) {
            square.setLayoutX(square.getValueX());
            square.setLayoutY(square.getValueY());
            square.setImage(square.getImg());
            square.setFitWidth(SQUARE_WIDTH);
            square.setFitHeight(SQUARE_WIDTH);

            // For debugging use
//            System.out.println("Adding square of type: " + square.getT());
//            System.out.println("Image: " + square.getImage());

            DrawBoard.getChildren().add(square);
        }

        // Darw the cards in hand with given hand string
        Hand hands = new Hand(hand);
        DrawHand.setLayoutX(MARGIN_X);
        DrawHand.setLayoutY(MARGIN_Y);
        DrawHand.getChildren().clear();
        for (Square square : hands.getSquares()) {
            square.setLayoutX(square.getValueX());
            square.setLayoutY(square.getValueY());
            square.setImage(square.getImg());
            square.setFitWidth(SQUARE_WIDTH);
            square.setFitHeight(SQUARE_WIDTH);

            DrawHand.getChildren().add(square);
        }
        // FIXME TASK 4
    }

    /**
     * Generate controls for Viewer
     */
    private void makeControls() {
        Label playerLabel = new Label("Cards in hand:");
        handTextField = new TextArea();
        handTextField.setPrefWidth(100);
        Label boardLabel = new Label("Board State:");
        boardTextField = new TextArea();
        boardTextField.setPrefWidth(100);
        Button button = refreshButton();
        button.setLayoutY(VIEWER_HEIGHT - 250);
        button.setLayoutX(MARGIN_X);
        HBox fields = new HBox();
        fields.getChildren().addAll(handTextField, boardTextField);
        fields.setSpacing(20);
        fields.setLayoutX(MARGIN_X);
        fields.setLayoutY(VIEWER_HEIGHT - 200);
        HBox labels = new HBox();
        labels.getChildren().addAll(playerLabel, boardLabel);
        labels.setSpacing(45);
        labels.setLayoutX(MARGIN_X);
        labels.setLayoutY(VIEWER_HEIGHT - 220);
        controls.getChildren().addAll(fields, labels, button);
    }

    /**
     * Create refresh button. Upon pressing, capture the textFields and call displayState
     * @return the created button
     */
    private Button refreshButton() {
        Button button = new Button("Refresh");
        button.setOnAction(e -> {
            String boardText = boardTextField.getText();
            String handCards = handTextField.getText();
            displayState(boardText, handCards);
        });
        return button;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Race to the Raft Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        makeControls();
        displayState("""
            fffffffffrrfffffff
            fffffffffrRfffffff
            fffffffffrrfffffff
            fffgffyrgpygyrygbr
            fffgGfggyygprbprpg
            fffgggbgprbpygbpyb
            ffffffbpbpgrbrrbgy
            ffffffgygybpgygprb
            ffffffbrrrybgygybg
            ffffffgpbbyrprgbbp
            ffffffbyrbpybgpryg
            ffffffpgyrggrbgyby
            fffffybgbpryybpgyp
            ffffYyybpgbprygrow
            fffyyyyryygbygybww"""
            , "AfhkDahw");
        root.getChildren().add(controls);
        root.getChildren().add(DrawBoard);
        root.getChildren().add(DrawHand);
        makeControls();
        stage.setScene(scene);
        stage.show();
    }
}
