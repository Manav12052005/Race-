package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private final char id; // Deck identifier (A, B, C, D)
    private final List<String> cards; // List to store card representations

    // Constructor
    public Deck(char id, List<String> cards) {
        this.id = id;
        this.cards = new ArrayList<>(cards); // Ensure that the list is mutable
    }

    public static String drawCard(Deck deck) {
        if (deck.getCards().isEmpty()) {
            return null;
        }
        // Remove and return a random card
        Random random = new Random();
        int randomIndex = random.nextInt(deck.getCards().size());
        String drawnCard = deck.removeCard(randomIndex);
        return drawnCard;
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
