package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void handToCards() {
        ArrayList<String> result = Hand.handToCards("AfhkBCDahw");
        ArrayList<String> actual = new ArrayList<>(Arrays.asList("ryrryrryr", "bbrbyrbyb", "rrygrygrr",
            "ppbgpbgpp", "yypgypgyy", "rpbrpbrpb"));
        assertEquals(result, actual, "Hand of \"AfhkBCDahw\" doesn't match! Should be " + actual);
    }
}
