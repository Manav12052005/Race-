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

    // Test parsing of island substring
    @Test
    void testGetIsland() {
        // Assert the expected island substring
        assertEquals("LNSNLASA", challenge.getIsland());
    }

    // Test parsing of fire substring
    @Test
    void testGetFireSubstring() {
        // Assert the expected fire substring
        assertEquals("000300060012001503030903", challenge.getFireSubstring());
    }

    // Test parsing of cat substring
    @Test
    void testGetCatSubstring() {
        // Assert the expected cat substring
        assertEquals("112033060340009", challenge.getCatSubstring());
    }

    // Test parsing of raft substring
    @Test
    void testGetRaftSubstring() {
        // Assert the expected raft substring
        assertEquals("01215", challenge.getRaftSubstring());
    }
}
