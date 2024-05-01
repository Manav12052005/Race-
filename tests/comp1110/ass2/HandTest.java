package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

//    @Test
//    void handToCardsA() {
//        ArrayList<String> result = Hand.handToCards("AfhkBCDahw");
//        ArrayList<String> actual = new ArrayList<>(Arrays.asList("ryrryrryr", "bbrbyrbyb", "rrygrygrr",
//            "ppbgpbgpp", "yypgypgyy", "rpbrpbrpb"));
//        assertEquals(result, actual, "Hand of \"AfhkBCDahw\" doesn't match! Should be " + actual);
//    }
//
//    @Test
//    void handToCardsB() {
//        ArrayList<String> result = Hand.handToCards("BcdCaeDpw");
//        ArrayList<String> actual = new ArrayList<>(Arrays.asList("rbyrbyrby", "gggygrygr", "yyybyrbyr",
//            "pybpybpyb", "yyyybybbb", "rpbrpbrpb"));
//        assertEquals(result, actual, "Hand of \"BcdCaeDpw\" doesn't match! Should be " + actual);
//    }

    @Test
    void handToCardsA() {
        ArrayList<String> result = Hand.handToCards("AfhkBCDahw");
        ArrayList<String> actual = new ArrayList<>(Arrays.asList(
            Utility.DECK_A['f' - 'a'].substring(1),
            Utility.DECK_A['h' - 'a'].substring(1),
            Utility.DECK_A['k' - 'a'].substring(1),
            Utility.DECK_D['a' - 'a'].substring(1),
            Utility.DECK_D['h' - 'a'].substring(1),
            Utility.DECK_D['w' - 'a'].substring(1)
        ));
        assertEquals(result, actual, "Hand of \"AfhkBCDahw\" doesn't match! Should be " + actual);
    }

    @Test
    void handToCardsB() {
        ArrayList<String> result = Hand.handToCards("BcdCaeDpw");
        ArrayList<String> actual = new ArrayList<>(Arrays.asList(
            Utility.DECK_B['c' - 'a'].substring(1),
            Utility.DECK_B['d' - 'a'].substring(1),
            Utility.DECK_C['a' - 'a'].substring(1),
            Utility.DECK_C['e' - 'a'].substring(1),
            Utility.DECK_D['p' - 'a'].substring(1),
            Utility.DECK_D['w' - 'a'].substring(1)
        ));
        assertEquals(result, actual, "Hand of \"BcdCaeDpw\" doesn't match! Should be " + actual);
    }

    @Test
    void handToCardsC() {
        ArrayList<String> result = Hand.handToCards("CjklDfgh");
        ArrayList<String> actual = new ArrayList<>(Arrays.asList(
            Utility.DECK_C['j' - 'a'].substring(1),
            Utility.DECK_C['k' - 'a'].substring(1),
            Utility.DECK_C['l' - 'a'].substring(1),
            Utility.DECK_D['f' - 'a'].substring(1),
            Utility.DECK_D['g' - 'a'].substring(1),
            Utility.DECK_D['h' - 'a'].substring(1)
        ));
        assertEquals(result, actual, "Hand of \"CjklDfgh\" doesn't match! Should be " + actual);
    }

    @Test
    void handToCardsD() {
        ArrayList<String> result = Hand.handToCards("Dijklmno");
        ArrayList<String> actual = new ArrayList<>(Arrays.asList(
            Utility.DECK_D['i' - 'a'].substring(1),
            Utility.DECK_D['j' - 'a'].substring(1),
            Utility.DECK_D['k' - 'a'].substring(1),
            Utility.DECK_D['l' - 'a'].substring(1),
            Utility.DECK_D['m' - 'a'].substring(1),
            Utility.DECK_D['n' - 'a'].substring(1),
            Utility.DECK_D['o' - 'a'].substring(1)
        ));
        assertEquals(result, actual, "Hand of \"Dijklmno\" doesn't match! Should be " + actual);
    }
}
