package comp1110.ass2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChallengeTest {

    private static Challenge challenge;

    // Initialize the test challenge
    @BeforeAll
    public static void setUp() {
        // You can create a specific challenge string here to test
        String challengeString = "LNSNLASAF000300060012001503030903C112033060340009R01215";
        challenge = new Challenge(challengeString);
    }

    // Test getDifficulty() method
    @Test
    void testGetDifficulty() {
        // Assert the expected difficulty value
        assertEquals(0, challenge.getDifficulty());
    }

    // Test getName() method
    @Test
    void testGetName() {
        // Assert the expected name value
        assertEquals("First steps", challenge.getName());
    }
}
