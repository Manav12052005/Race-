package comp1110.ass2;

import comp1110.ass2.gui.Viewer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


/**
 *determines the possible square conditions
 *colour
 *fire
 *raft
 */
public class Square extends Group {
    public static final double SQUARE_WIDTH = 40;
    private double x;
    private double y;
    private double mouseX, mouseY;
    public enum type {
        BLUE, GREEN, PURPLE, RED, YELLOW,
        blueCAT, greenCAT, purpleCAT, redCAT, yellowCAT,
        FIRE, OBJECTIVE, WILD, wildOCCUPIED, NONE
    }
    private final type t;
    private char cat;
    private final ImageView squareImageView;
    private final ImageView catImageView;

    private double startX;
    private double startY;

    public static Square selectedSquare = null;
    private boolean canDrag = true;

    public Square(double x, double y, char t) {
        this.x = x;
        this.y = y;
        Image squareImage;
        Image catImage = null;


        switch (t) {
            case 'b':
                this.t = type.BLUE;
                squareImage = new Image("comp1110/ass2/gui/assets/blue.png");
                break;
            case 'g':
                this.t = type.GREEN;
                squareImage = new Image("comp1110/ass2/gui/assets/green.png");
                break;
            case 'p':
                this.t = type.PURPLE;
                squareImage = new Image("comp1110/ass2/gui/assets/purple.png");
                break;
            case 'r':
                this.t = type.RED;
                squareImage = new Image("comp1110/ass2/gui/assets/red.png");
                break;
            case 'y':
                this.t = type.YELLOW;
                squareImage = new Image("comp1110/ass2/gui/assets/yellow.png");
                break;
            case 'B':
                this.t = type.blueCAT;
                squareImage = new Image("comp1110/ass2/gui/assets/blue.png");
                catImage = new Image("comp1110/ass2/gui/assets/blueCat.png");
                break;
            case 'G':
                this.t = type.greenCAT;
                squareImage = new Image("comp1110/ass2/gui/assets/green.png");
                catImage = new Image("comp1110/ass2/gui/assets/greenCat.png");
                break;
            case 'P':
                this.t = type.purpleCAT;
                squareImage = new Image("comp1110/ass2/gui/assets/purple.png");
                catImage = new Image("comp1110/ass2/gui/assets/purpleCat.png");
                break;
            case 'R':
                this.t = type.redCAT;
                squareImage = new Image("comp1110/ass2/gui/assets/red.png");
                catImage = new Image("comp1110/ass2/gui/assets/redCat.png");
                break;
            case 'Y':
                this.t = type.yellowCAT;
                squareImage = new Image("comp1110/ass2/gui/assets/yellow.png");
                catImage = new Image("comp1110/ass2/gui/assets/yellowCat.png");
                break;
            case 'f':
                this.t = type.FIRE;
                squareImage = new Image("comp1110/ass2/gui/assets/fire.png");
                break;
            case 'o':
            case 'w':
            case 'W':
                this.t = type.OBJECTIVE;
                squareImage = new Image("comp1110/ass2/gui/assets/objective.png");
                break;
            default:
                this.t = type.NONE;
                squareImage = null;
                break;
        }

        squareImageView = new ImageView(squareImage);
        squareImageView.setFitWidth(SQUARE_WIDTH); // set fit width for squareImageView
        squareImageView.setFitHeight(SQUARE_WIDTH); // set fit height for squareImageView

        catImageView = new ImageView(catImage);
        catImageView.setFitWidth(SQUARE_WIDTH); // set fit width for catImageView
        catImageView.setFitHeight(SQUARE_WIDTH); // set fit height for squareImageView

        getChildren().addAll(squareImageView, catImageView);


//        // Set event handlers for catImageView
//        catImageView.setOnMousePressed((MouseEvent event) -> {
//            if (isCat() && canDrag) {
//                mouseX = event.getSceneX();
//                mouseY = event.getSceneY();
//                catImageView.toFront(); // bring the cat to the front
//            }
//        });
//
//
        catImageView.setOnMousePressed((MouseEvent event) -> {
            if (isCat() && canDrag) {
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();

                startX = Math.round(catImageView.getLayoutX() / SQUARE_WIDTH) * SQUARE_WIDTH;
                startY = Math.round(catImageView.getLayoutY() / SQUARE_WIDTH) * SQUARE_WIDTH;

                Viewer.catStartX = (int) Math.round((mouseX - Viewer.shiftX - SQUARE_WIDTH) / SQUARE_WIDTH);
                Viewer.catStartY = (int) Math.round((mouseY - SQUARE_WIDTH) / SQUARE_WIDTH);
                System.out.println("Start position: [" + Viewer.catStartX + "][" + Viewer.catStartY + "]");
            }

            char cat = switch (this.t) {
                case blueCAT -> 'B';
                case greenCAT -> 'G';
                case purpleCAT -> 'P';
                case redCAT -> 'R';
                case yellowCAT -> 'Y';
                default -> throw new IllegalArgumentException("Not a cat");
            };

            Viewer.catColor = new String(new char[]{cat});
        });

        catImageView.setOnMouseDragged((MouseEvent event) -> {
            if (isCat() && canDrag) {
                double deltaX = event.getSceneX() - mouseX;
                double deltaY = event.getSceneY() - mouseY;
                catImageView.setLayoutX(catImageView.getLayoutX() + deltaX);
                catImageView.setLayoutY(catImageView.getLayoutY() + deltaY);
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
            }
        });

        catImageView.setOnMouseReleased((MouseEvent event) -> {
            if (isCat() && canDrag) {
                double newX = Math.round(catImageView.getLayoutX() / SQUARE_WIDTH) * SQUARE_WIDTH;
                double newY = Math.round(catImageView.getLayoutY() / SQUARE_WIDTH) * SQUARE_WIDTH;
//                catImageView.setLayoutX(newX);
//                catImageView.setLayoutY(newY);

                Viewer.endX = (int) (newX / SQUARE_WIDTH);
                Viewer.endY = (int) (newY / SQUARE_WIDTH);

                // Calculate and print the grid index
                int[] gridIndex = Viewer.getGridIndex(catImageView.getLayoutX(), catImageView.getLayoutY());
                System.out.println("Destination position: [" + Viewer.endY + "][" + Viewer.endX + "]");
                System.out.println("Grid index: [" + gridIndex[0] + "][" + gridIndex[1] + "]");

                String move = Viewer.catColor + String.format("%02d%02d", Viewer.catStartY, Viewer.catStartX) + String.format("%02d%02d", Viewer.endY, Viewer.endX);

                String[] gamestate = Viewer.getGameState();
                if (RaceToTheRaft.isCatMovementValid(gamestate, move)) {
                    Viewer.setGameState(RaceToTheRaft.moveCat(gamestate, move));

                    Viewer.printGameState(Viewer.getGameState());

                    catImageView.setLayoutX(newX);
                    catImageView.setLayoutY(newY);
                } else {
                    catImageView.setLayoutX(startX);
                    catImageView.setLayoutY(startY);
                }
            }
        });
//
//
//        catImageView.setOnMouseReleased((MouseEvent event) -> {
//            if (isCat() && canDrag) {
//                double newX = Math.round(catImageView.getLayoutX() / SQUARE_WIDTH) * SQUARE_WIDTH;
//                double newY = Math.round(catImageView.getLayoutY() / SQUARE_WIDTH) * SQUARE_WIDTH;
//                catImageView.setLayoutX(newX);
//                catImageView.setLayoutY(newY);
//
//                // Calculate and print the grid index
//                int[] gridIndex = Viewer.getGridIndex(catImageView.getLayoutX(), catImageView.getLayoutY());
//                System.out.println("Grid index: [" + gridIndex[0] + "][" + gridIndex[1] + "]");
//            }
//        });

//        setOnMousePressed((MouseEvent event) -> {
//            if (isCat() && canDrag) {
//                mouseX = event.getSceneX();
//                mouseY = event.getSceneY();
//                toFront(); // bring the square to the front
//            }
//        });
//
//        setOnMouseDragged((MouseEvent event) -> {
//            if (isCat() && canDrag) {
//                double deltaX = event.getSceneX() - mouseX;
//                double deltaY = event.getSceneY() - mouseY;
//                setLayoutX(getLayoutX() + deltaX);
//                setLayoutY(getLayoutY() + deltaY);
//                mouseX = event.getSceneX();
//                mouseY = event.getSceneY();
//            }
//        });
//
//        setOnMouseReleased((MouseEvent event) -> {
//            if (isCat() && canDrag) {
//                double newX = Math.round(getLayoutX() / SQUARE_WIDTH) * SQUARE_WIDTH;
//                double newY = Math.round(getLayoutY() / SQUARE_WIDTH) * SQUARE_WIDTH;
//                setLayoutX(newX);
//                setLayoutY(newY);
//
//                // Calculate and print the grid index
//                int[] gridIndex = Viewer.getGridIndex(getLayoutX(), getLayoutY());
//                System.out.println("Grid index: [" + gridIndex[0] + "][" + gridIndex[1] + "]");
//            }
//        });

//        setOnMousePressed((MouseEvent event) -> {
//            if (Square.selectedSquare != null) {
//                Square.selectedSquare.setEffect(null); // remove highlight from previously selected square
//            }
//            Square.selectedSquare = this;
//            this.setEffect(new DropShadow(20, Color.WHITE)); // highlight the selected square
//        });
//
//        setOnMouseClicked(event -> {
//            if (Square.selectedSquare != null) {
//                double newX = Math.floor((event.getSceneX() / Square.SQUARE_WIDTH)) * Square.SQUARE_WIDTH;
//                double newY = Math.floor((event.getSceneY() / Square.SQUARE_WIDTH)) * Square.SQUARE_WIDTH;
//                Square.selectedSquare.setLayoutX(newX);
//                Square.selectedSquare.setLayoutY(newY);
//                Square.selectedSquare.setEffect(null); // remove highlight
//                Square.selectedSquare = null; // deselect the square
//            }
//        });
    }

    public static type charToType(char t){
        return switch (t) {
            case 'b' -> type.BLUE;
            case 'g' -> type.GREEN;
            case 'p' -> type.PURPLE;
            case 'r' -> type.RED;
            case 'y' -> type.YELLOW;
            case 'B' -> type.blueCAT;
            case 'G' -> type.greenCAT;
            case 'P' -> type.purpleCAT;
            case 'R' -> type.redCAT;
            case 'Y' -> type.yellowCAT;
            case 'f' -> type.FIRE;
            case 'o', 'w', 'W' -> type.OBJECTIVE;
            case 'N' -> type.NONE;
            default -> throw new IllegalArgumentException("type char ID not valid");
        };
    }

    public type getT() {
        return this.t;
    }

    public Image getImg() {
        return squareImageView.getImage();
    }

    public ImageView getSquareImageView() {
        return squareImageView;
    }

    public ImageView getCatImageView() {
        return catImageView;
    }

    public double getValueX() {
        return x;
    }

    public double getValueY() {
        return y;
    }

    public boolean isCat() {
        return t == type.blueCAT || t == type.greenCAT || t == type.purpleCAT || t == type.redCAT || t == type.yellowCAT;
    }
}
