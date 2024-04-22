package comp1110.ass2;

import gittest.B;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static comp1110.ass2.PathwayCard.Direction.EAST;
import static comp1110.ass2.PathwayCard.Direction.WEST;
import static comp1110.ass2.Square.type.*;

public class RotateTest {
    //tests whether the FireTile and PathwayCard rotate functions works properly
    @Test
    void testRotateEastPC(){ //PathwayCard rotation east test
        Square.type[][] sArray = new Square.type[][]{
                {BLUE, RED, GREEN},
                {YELLOW, PURPLE, RED},
                {GREEN, YELLOW, PURPLE}
        };

        PathwayCard wayCard = new PathwayCard(sArray);
        wayCard.rotate(EAST);

        Square.type[][] expectedResult = new Square.type[][]{
                {GREEN, YELLOW, BLUE},
                {YELLOW, PURPLE, RED},
                {PURPLE, RED, GREEN}
        };
        Assertions.assertArrayEquals(expectedResult, wayCard.getTiles());
    }
    @Test
    void testRotateWestPC(){ //PathwayCard rotation west test
        Square.type[][] sArray = new Square.type[][]{
                {BLUE, RED, GREEN},
                {YELLOW, PURPLE, RED},
                {GREEN, YELLOW, PURPLE}
        };

        PathwayCard wayCard = new PathwayCard(sArray);
        wayCard.rotate(WEST);

        Square.type[][] expectedResult = new Square.type[][]{
                {GREEN, RED, PURPLE},
                {RED, PURPLE, YELLOW},
                {BLUE, YELLOW, GREEN}
        };
        Assertions.assertArrayEquals(expectedResult, wayCard.getTiles());
    }
    @Test
    void testRotateEastFT() { //FireTile rotation east test
        Square.type[][] sArray = new Square.type[][]{
                {FIRE, FIRE, FIRE, NONE},
                {NONE, FIRE, NONE, FIRE},
                {FIRE, NONE, FIRE, NONE}
        };

        FireTile fireTile = new FireTile(sArray, true);
        fireTile.rotate("east");

        Square.type[][] expectedResult = new Square.type[][]{
                {FIRE, NONE, FIRE},
                {NONE, FIRE, FIRE},
                {FIRE, NONE, FIRE},
                {NONE, FIRE, NONE}
        };
        boolean expectedIsHorizontal = false;
        Assertions.assertArrayEquals(expectedResult, fireTile.getTiles());
        Assertions.assertEquals(expectedIsHorizontal, fireTile.getIsHorizontal());
    }
    @Test
    void testRotateWestFT() { //FireTile rotation west test
        Square.type[][] sArray = new Square.type[][]{
                {FIRE, FIRE, FIRE, FIRE},
                {NONE, NONE, FIRE, NONE},
                {FIRE, FIRE, FIRE, FIRE}
        };

        FireTile fireTile = new FireTile(sArray, true);
        fireTile.rotate("west");

        Square.type[][] expectedResult = new Square.type[][]{
                {FIRE, NONE, FIRE},
                {FIRE, FIRE, FIRE},
                {FIRE, NONE, FIRE},
                {FIRE, NONE, FIRE}
        };
        boolean expectedIsHorizontal = false;
        Assertions.assertArrayEquals(expectedResult, fireTile.getTiles());
        Assertions.assertEquals(expectedIsHorizontal, fireTile.getIsHorizontal());
    }

    @Test
    void testRotateFlipFT() { //FireTile flip test
        Square.type[][] sArray = new Square.type[][]{
                {FIRE, NONE, FIRE, FIRE},
                {NONE, NONE, NONE, FIRE},
                {FIRE, FIRE, NONE, NONE}
        };

        FireTile fireTile = new FireTile(sArray, true);
        fireTile.rotate("flip");

        Square.type[][] expectedResult = new Square.type[][]{
                {FIRE, FIRE, NONE, FIRE},
                {FIRE, NONE, NONE, NONE},
                {NONE, NONE, FIRE, FIRE}
        };
        boolean expectedIsHorizontal = false;
        Assertions.assertArrayEquals(expectedResult, fireTile.getTiles());
        Assertions.assertEquals(expectedIsHorizontal, fireTile.getIsHorizontal());
    }
}
