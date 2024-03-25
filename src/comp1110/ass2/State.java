package comp1110.ass2;

import java.util.Random;

public class State {
    private Deck crossDeck;
    private Deck squareDeck;
    private Deck circleDeck;
    private Deck triangleDeck;
    private Island boardState;
    public Deck fireTileBag;

    public Deck getFireTileBag() {
        return fireTileBag;
    }

    public Card drawCard(Deck deck) {
        if (deck.isEmpty()) {
            return null;
        }
        // Remove and return a random tile
        Random random = new Random();
        int randomIndex = random.nextInt(deck.size());
        Card drawnCard = deck.remove(randomIndex);
        return drawnCard;
    }
}

    public void placePathwayCard(PathwayCard card, int row, int col) {
        boardState.placePathwayCard(card, row, col);
    }

    public void placeFireTile(int row, int col) {
        boardState.placeFireTile(row, col);
    }

    public void moveCat(Cat cat, int newRow, int newCol) {
        boardState.moveCat(cat, newRow, newCol);
    }

    // Method to check win condition
    public boolean checkWin() {
        return boardState.areAllCatsOnRaft();
    }
    //Fields
    //Card[] hand;

    //Methods
    //deckState()
    //boardState()
    //checkWin()
    //drawCards(Deck[] decks)
    //playCard()
    //discardCard()
}
