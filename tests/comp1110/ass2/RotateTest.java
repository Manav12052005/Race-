package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static comp1110.ass2.PathwayCard.Direction.EAST;
import static comp1110.ass2.PathwayCard.Direction.WEST;
import static comp1110.ass2.PathwayCard.rotate;

public class RotateTest {
    //tests whether the FireTile and PathwayCard rotate functions works properly

    int[] dummyInt = new int[]{0, 0};
    @Test
    void testRotateEastPC(){ //PathwayCard rotation east test
        char[][] sArray = new char[][]{
                {'b', 'r', 'g'},
                {'y', 'p', 'r'},
                {'g', 'y', 'p'}
        };

        PathwayCard wayCard = new PathwayCard(sArray, dummyInt);
        rotate(sArray, EAST);

        char[][] expectedResult = new char[][]{
                {'g', 'y', 'b'},
                {'y', 'p', 'r'},
                {'p', 'r', 'g'}
        };
        Assertions.assertArrayEquals(expectedResult, wayCard.getTiles());
    }
    @Test
    void testRotateWestPC(){ //PathwayCard rotation west test
        char[][] sArray = new char[][]{
                {'b', 'r', 'g'},
                {'y', 'p', 'r'},
                {'g', 'p', 'p'}
        };

        PathwayCard wayCard = new PathwayCard(sArray, dummyInt);
        rotate(sArray, WEST);

        char[][] expectedResult = new char[][]{
                {'g', 'r', 'p'},
                {'r', 'p', 'y'},
                {'b', 'y', 'g'}
        };
        Assertions.assertArrayEquals(expectedResult, wayCard.getTiles());
    }
    @Test
    void testRotateEastFT() { //FireTile rotation east test
        char[][] sArray = new char[][]{
                {'f', 'f', 'f', 'N'},
                {'f', 'f', 'N', 'f'},
                {'f', 'N', 'f', 'N'}
        };

        FireTile fireTile = new FireTile(sArray,new int[]{3, 4}, true);
        fireTile.rotate(PathwayCard.Direction.EAST);

        char[][] expectedResult = new char[][]{
                {'f', 'N', 'f'},
                {'N', 'f', 'f'},
                {'f', 'N', 'f'},
                {'N', 'f', 'N'}
        };
        boolean expectedIsHorizontal = false;
        Assertions.assertArrayEquals(expectedResult, fireTile.getTiles());
        Assertions.assertEquals(expectedIsHorizontal, fireTile.getIsHorizontal());
    }
    @Test
    void testRotateWestFT() { //FireTile rotation west test
        char[][] sArray = new char[][]{
                {'f', 'f', 'f', 'f'},
                {'N', 'N', 'f', 'N'},
                {'f', 'f', 'f', 'f'}
        };

        FireTile fireTile = new FireTile(sArray, new int[]{3, 4},true);
        fireTile.rotate(PathwayCard.Direction.WEST);

        char[][] expectedResult = new char[][]{
                {'f', 'N', 'f'},
                {'f', 'f', 'f'},
                {'f', 'N', 'f'},
                {'f', 'N', 'f'}
        };
        boolean expectedIsHorizontal = false;
        Assertions.assertArrayEquals(expectedResult, fireTile.getTiles());
        Assertions.assertEquals(expectedIsHorizontal, fireTile.getIsHorizontal());
    }

    @Test
    void testRotateFlipFT() { //FireTile flip test
        char[][] sArray = new char[][]{
                {'f', 'N', 'f', 'f'},
                {'N', 'N', 'N', 'f'},
                {'f', 'f', 'N', 'N'}
        };

        FireTile fireTile = new FireTile(sArray, new int[]{3, 4}, true);
        fireTile.rotate(PathwayCard.Direction.FLIP);

        char[][] expectedResult = new char[][]{
                {'f', 'f', 'N', 'f'},
                {'f', 'N', 'N', 'N'},
                {'N', 'N', 'f', 'f'}
        };
        boolean expectedIsHorizontal = false;
        Assertions.assertArrayEquals(expectedResult, fireTile.getTiles());
        Assertions.assertEquals(expectedIsHorizontal, fireTile.getIsHorizontal());
    }
}
