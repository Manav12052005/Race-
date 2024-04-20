package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RotateTest {
    //tests whether the FireTile and PathwayCard rotate function works properly
    @Test
    void testRotateEast() {
        int[][] sArray = new int[][]{
                {0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}
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
        Assertions.assertArrayEquals(expectedResult, );
        Assertions.assertEquals(expectedIsHorizontal, fireTile.getIsHorizontal());
    }
}
