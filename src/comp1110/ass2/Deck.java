package comp1110.ass2;

public class Deck {
    private final String id; // Deck identifier (A, B, C, D)
    private final String[] cards; // Array to store card representations

    // Constructor
    public Deck(String id, String[] cards) {
        this.id = id;
        this.cards = cards;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String[] getCards() {
        return cards;
    }

    // Returns the number of cards remaining in the deck
    public int size() {
        return cards.length;
    }

    // Checks if the deck is empty
    public boolean isEmpty() {
        return size() == 0;
    }
}
