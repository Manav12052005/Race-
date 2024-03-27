package comp1110.ass2;

import java.util.Random;

public class PathwayCard {
    public static String[] cardPickUp(String decks, String hand, String drawRequest){

        //Creating individual deck strings
        String deckA = decks.substring(decks.indexOf('A'), decks.indexOf('B'));
        String deckB = decks.substring(decks.indexOf('B'), decks.indexOf('C'));
        String deckC = decks.substring(decks.indexOf('C'), decks.indexOf('D'));
        String deckD = decks.substring(decks.indexOf('D'));

        //Creating corresponding individual draw requests
        int reqA = Integer.parseInt(drawRequest.substring(drawRequest.indexOf('A')+1, drawRequest.indexOf('B')));
        int reqB = Integer.parseInt(drawRequest.substring(drawRequest.indexOf('B')+1, drawRequest.indexOf('C')));
        int reqC = Integer.parseInt(drawRequest.substring(drawRequest.indexOf('C')+1, drawRequest.indexOf('D')));
        int reqD = Integer.parseInt(drawRequest.substring(drawRequest.indexOf('D')+1));

        Random random = new Random();

        //Creating new hand to be passed to altered gameState
        String newHand = "";

        newHand += cardPick(reqA, deckA);
        newHand += cardPick(reqB, deckB);
        newHand += cardPick(reqC, deckC);
        newHand += cardPick(reqD, deckD);


        private String[] cardPick(int request, String deck){
            StringBuilder pickedCards = new StringBuilder();
            pickedCards.append(deck.charAt(0));
            StringBuilder modDeck = new StringBuilder();

            for (int i = 0; i < request; i++){
                int randomIndex = random.nextInt(modDeck.length()-1)+1;
                char pickedCard = modDeck.charAt(randomIndex);
                pickedCards.append(pickedCard);

                modDeck.deleteCharAt(randomIndex);}

            return new String[] {modDeck.toString(), pickedCards.toString()};
        }


        return null;
    }

}
