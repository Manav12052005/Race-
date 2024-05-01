package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class IsCatMovementValidTest {

    private void test(String[] gameState, String action, boolean expected) {
        boolean out = RaceToTheRaft.isCatMovementValid(gameState, action);
        Assertions.assertEquals(expected, out,
                "Expected " + expected + " for gameState " + Arrays.toString(gameState) + ", and action " + action +
                        ", but got " + out);
    }

    @Test
    public void validMovement() {
        String[] emptyBoard = new String[]{"g04040403", "g04040504", "g04040505"};
        for (String action : emptyBoard) {
            test(gamestates[0], action, true);
        }
        String[] moves = new String[]{"p01130513", "g01070608", "b07040606" };
        for (String action : moves) {
            test(gamestates[1], action, true);
        }
        test(gamestates[0], "g04049999", false);
    }

    @Test
    public void invalidMovement() {
        String[] emptyBoard = new String[]{"g04040506", "g04040507", "g04040304"};
        for (String action : emptyBoard) {
            test(gamestates[0], action, false);
        }
        String[] moves = new String[]{"p01130713", "g01070611", "g01070411", "b07040908"};
        for(String action: moves) {
            test (gamestates[1], action, false);
        }
        test(gamestates[0], "g04040504", true);
    }


    private static final String[][] gamestates = new String[][]{
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrRfffffff
fffffffffrrfffffff
fffgffpbgpgbgpgygy
fffgGfpryyggrgbpyp
fffgggygbgrybyybgr
ffffffbpgbgpygbrpb
ffffffrybpyrbbpygy
ffffffrgybryygpbrr
ffffffbbrgyybgpgpb
ffffffrypprbyrgbyr
ffffffyggyprgybpgy
fffffyypyggybrrgyp
ffffYypbbbrgpygrow
fffyyygyrbprgpbbww
""",
                    "AabcdfijklmnopqstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghiklnopqrstuvwxyDabcdefghijklmnopqrstuvwxy", "AeghrBCjmD", "", "abcdefghijklmnopqrstuvwxyzABCDE"},
            new String[]{
                    """
ffffffgffffffppfff
ffffffgGfffffPpfff
ffffffgggffffppfff
ffffffpbgggyypyffp
ffffffprygrggprfgy
ffffffygbgrggpyffp
ffffffbpggrgprgyry
fffbBbbyybrgypbbgb
fffbbbffggbybgryyb
fffffffbbyrbpbygpb
fffffffffggygrbpyr
fffffffgypygbybgrg
ffffffffbrrbygpgyp
ffffffffpybypbrrow
fffffffrygrpgrbbww
""",
                    "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqstuvwyCadefghijklmnopqrstuvwxyDabcdeghijklmnoqrstuvwxy","ABxCcDp","","acdefghijklmoprstuvwxyzABCDE"},

    };


}
