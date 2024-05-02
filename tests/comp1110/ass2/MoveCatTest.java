package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class MoveCatTest {

      private void testBoard(String[] gameState, String placement, String[] expected) {
        String[] out = RaceToTheRaft.moveCat(gameState, placement);
        Assertions.assertEquals(expected[0], out[0]);
    }

    private void testExhausted(String[] gameState, String placement, String[] expected) {
        String[] out = RaceToTheRaft.moveCat(gameState, placement);
        Assertions.assertEquals(expected[3], out[3]);
    }

    @Test
    public void testMoveCat() {
        for (int i = 0; i < gameStates.length; i++) {
            testBoard(gameStates[i], placement[i], expected[i]);
        }
    }

    @Test
    public void testCatExhausted() {
        for (int i = 0; i < gameStates.length; i++) {
            testExhausted(gameStates[i], placement[i], expected[i]);
        }
    }

    private final String[][] gameStates = new String[][]{
            new String[]{
                    """
                fffffffffffyffffff
                ffffffffffYyffffff
                fffffffffyyyffffff
                fffffffrggypgpypbg
                fffffffffpyrybgpry
                ffffffbfpyyryyrygb
                fffgffbpbbyryggbpg
                fffgGfgygbrypbyryb
                fffgggbrrgybgybrgy
                ffffffprgbrpygybgy
                ffffffybygrbgyrpbg
                ffffffbrrgyppbyyrp
                ffffffgypybygbrwww
                fffbBbygggyrpgbwow
                fffbbbbrypbbgrpwww
                """,
                    "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefhklmnpqrstuwyDabcdefghijklmnopqrstuvwxy", "ABCgijovD", "", "abcdefghijklmnopqrstuvwxzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffbBbfffrRf
                fffffffffbbbfffrrf
                ffffffffppbgbpbrgg
                fffffffffybbbbgryy
                ffffffpfypbyyrybgg
                ffffffffpgbrgpprpb
                ffffPffyppbpygbgby
                ffffpppppyybpyrpyr
                ffffffbgryybpbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdefghijklnopqrstuvwxyCabcdefghiklmnopqrstuvwxyDabcefghijklmnopqrstuvwxy", "AhBcmCjD", "", "bcdefghijklmnoprstuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffbBbfffrRf
                fffffffffbbbfffrrf
                ffffffffppbgbpbrgg
                fffffffffybbbbgryy
                ffffffpfypbyyrybgg
                ffffffffpgbrgpprpb
                ffffpffypPbpygbgby
                ffffpppppyybpyrpyr
                ffffffbgryybpbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdefghijklnopqrstuvwxyCabcdefghiklmnopqrstuvwxyDabcefghijklmnopqrstuvwxy", "AhBcmCD", "p0709", "bcdefghijklmnoprstuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffbbbfffrRf
                fffffffffbbbfffrrf
                ffffffffppbgbpbrgg
                fffffffffybbbbgryy
                ffffffpfypbyyrybgg
                ffffffffpgbrgpprpb
                ffffpffypPBpygbgby
                ffffpppppyybpyrpyr
                ffffffbgryybpbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdefghijklnopqrstuvwxyCabcdefghiklmnopqrstuvwxyDabcefghijklmnopqrstuvwxy", "AhBcCD", "b0710p0709", "bcdefghijklmnoprstuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffffbfffrRf
                fffffffffffbfffrrf
                ffffffffffbgbpbrgg
                fffffffffybbbBgryy
                ffffffpfypbyyrbrbg
                ffffffffpgbrgpbrbb
                fffffffypPppbgbrby
                ffffffffpyypbyrpyr
                fffffffgryybbbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdeghijklnoqrstvwxCabdefghiklmnopqrstvwxyDabcefghijklmnopqrstuvwxy", "ABpyCcuD", "", "bcdefghiklmnoprtuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffffbfffrRf
                fffffffffffbfffrrf
                ffffffffffbgbpbrgg
                fffffffffybbbBgryy
                ffffffpfypbyyrbrbg
                ffffffffpgbrgpbrbb
                fffffffyppppbgbrby
                ffffffffpyyPbyrpyr
                fffffffgryybbbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdeghijklnoqrstvwxCabdefghiklmnopqrstvwxyDabcefghijklmnopqrstuvwxy", "AByCcuD", "p0811", "bcdefghiklmnoprtuvwxyzABCDE"},


    };

    private final String[] placement = new String[]{
            "y01100610Cv",
            "p07040709Cj",
            "b01100710Bm",
            "b07100413AhBc",
            "p07090811Bp",
            "r01160715By",
    };

    private final String[][] expected = new String[][]{
            new String[]{
                    """
                fffffffffffyffffff
                ffffffffffyyffffff
                fffffffffyyyffffff
                fffffffrggypgpypbg
                fffffffffpyrybgpry
                ffffffbfpyyryyrygb
                fffgffbpbbYryggbpg
                fffgGfgygbrypbyryb
                fffgggbrrgybgybrgy
                ffffffprgbrpygybgy
                ffffffybygrbgyrpbg
                ffffffbrrgyppbyyrp
                ffffffgypybygbrwww
                fffbBbygggyrpgbwow
                fffbbbbrypbbgrpwww
                """,
                    "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefhklmnpqrstuwyDabcdefghijklmnopqrstuvwxy", "ABCgijoD", "y0610", "abcdefghijklmnopqrstuvwxzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffbBbfffrRf
                fffffffffbbbfffrrf
                ffffffffppbgbpbrgg
                fffffffffybbbbgryy
                ffffffpfypbyyrybgg
                ffffffffpgbrgpprpb
                ffffpffypPbpygbgby
                ffffpppppyybpyrpyr
                ffffffbgryybpbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdefghijklnopqrstuvwxyCabcdefghiklmnopqrstuvwxyDabcefghijklmnopqrstuvwxy", "AhBcmCD", "p0709", "bcdefghijklmnoprstuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffbbbfffrRf
                fffffffffbbbfffrrf
                ffffffffppbgbpbrgg
                fffffffffybbbbgryy
                ffffffpfypbyyrybgg
                ffffffffpgbrgpprpb
                ffffpffypPBpygbgby
                ffffpppppyybpyrpyr
                ffffffbgryybpbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdefghijklnopqrstuvwxyCabcdefghiklmnopqrstuvwxyDabcefghijklmnopqrstuvwxy", "AhBcCD", "b0710p0709", "bcdefghijklmnoprstuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffbbbfffrRf
                fffffffffbbbfffrrf
                ffffffffppbgbpbrgg
                fffffffffybbbBgryy
                ffffffpfypbyyrybgg
                ffffffffpgbrgpprpb
                ffffpffypPbpygbgby
                ffffpppppyybpyrpyr
                ffffffbgryybpbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdefghijklnopqrstuvwxyCabcdefghiklmnopqrstuvwxyDabcefghijklmnopqrstuvwxy", "ABCD", "b0413p0709", "bcdefghijklmnoprstuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffffbfffrRf
                fffffffffffbfffrrf
                ffffffffffbgbpbrgg
                fffffffffybbbBgryy
                ffffffpfypbyyrbrbg
                ffffffffpgbrgpbrbb
                fffffffyppppbgbrby
                ffffffffpyyPbyrpyr
                fffffffgryybbbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdeghijklnoqrstvwxCabdefghiklmnopqrstvwxyDabcefghijklmnopqrstuvwxy", "ABpCcuD", "p0811", "bcdefghiklmnoprtuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffffbfffrrf
                fffffffffffbfffrrf
                ffffffffffbgbpbrgg
                fffffffffybbbBgryy
                ffffffpfypbyyrbrbg
                ffffffffpgbrgpbrbb
                fffffffyppppbgbRby
                ffffffffpyyPbyrpyr
                fffffffgryybbbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdeghijklnoqrstvwxCabdefghiklmnopqrstvwxyDabcefghijklmnopqrstuvwxy","ABCcuD","p0811r0715","bcdefghiklmnoprtuvwxyzABCDE"},
    };
}

