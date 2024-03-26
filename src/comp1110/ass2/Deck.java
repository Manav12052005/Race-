package comp1110.ass2;

import java.util.List;

public class Deck {
    private final char id; // Deck identifier (A, B, C, D)
    private final List<String> cards; // Array to store card representations

    // Constructor
    public Deck(char id, List<String> cards) {
        this.id = id;
        this.cards = cards;
    }

    // Getters
    public char getId() {
        return id;
    }

    public List<String> getCards() {
        return cards;
    }

    public String removeCard(int index) {
        if (index >= 0 && index < cards.size()) {
            return cards.remove(index);
        } else {
            return null; // Or throw an exception
        }
    }
    }
}
