package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

public class RotateTest {
    //tests whether the FireTile and PathwayCard rotate functions works properly
    @Test
    void testRotateEastPC(){ //PathwayCard rotation east test
        int[][] sArray = new int[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}
        };

        PathwayCard wayCard = new PathwayCard(sArray);
        wayCard.rotate("east");

        int[][] expectedResult = new int[][]{
                {6, 3, 0},
                {7, 4, 1},
                {8, 5, 2}
        };
        Assertions.assertArrayEquals(expectedResult, wayCard.getTiles());
    }
    @Test
    void testRotateWestPC(){ //PathwayCard rotation west test
        int[][] sArray = new int[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}
        };

        PathwayCard wayCard = new PathwayCard(sArray);
        wayCard.rotate("west");

        int[][] expectedResult = new int[][]{
                {2, 5, 8},
                {1, 4, 7},
                {0, 3, 6}
        };
        Assertions.assertArrayEquals(expectedResult, wayCard.getTiles());
    }
    @Test
    void testRotateEastFT() { //FireTile rotation east test
        int[][] sArray = new int[][]{
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 10, 11}
        };

        FireTile fireTile = new FireTile(sArray, true);
        fireTile.rotate("east");

        int[][] expectedResult = new int[][]{
                {8, 4, 0},
                {9, 5, 1},
                {10, 6, 2},
                {11, 7, 3}
        };
        boolean expectedIsHorizontal = false;
        Assertions.assertArrayEquals(expectedResult, fireTile.getTiles());
        Assertions.assertEquals(expectedIsHorizontal, fireTile.getIsHorizontal());
    }
    @Test
    void testRotateWestFT() { //FireTile rotation west test
        int[][] sArray = new int[][]{
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 10, 11}
        };

        FireTile fireTile = new FireTile(sArray, true);
        fireTile.rotate("west");

        int[][] expectedResult = new int[][]{
                {3, 7, 11},
                {2, 6, 10},
                {1, 5, 9},
                {0, 4, 8}
        };
        boolean expectedIsHorizontal = false;
        Assertions.assertArrayEquals(expectedResult, fireTile.getTiles());
        Assertions.assertEquals(expectedIsHorizontal, fireTile.getIsHorizontal());
    }

    @Test
    void testRotateFlipFT() { //FireTile flip test
        int[][] sArray = new int[][]{
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 10, 11}
        };

        FireTile fireTile = new FireTile(sArray, true);
        fireTile.rotate("flip");

        int[][] expectedResult = new int[][]{
                {3, 2, 1, 0},
                {7, 6, 5, 4},
                {11, 10, 9, 8}
        };
        boolean expectedIsHorizontal = false;
        Assertions.assertArrayEquals(expectedResult, fireTile.getTiles());
        Assertions.assertEquals(expectedIsHorizontal, fireTile.getIsHorizontal());
    }
}
