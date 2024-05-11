package comp1110.ass2;

import java.util.*;

public class Hand {
    private static final double SQUARE_WIDTH = Square.SQUARE_WIDTH;

    private ArrayList<Card> cards;

    // Constructor
    public Hand(String handString) {
        ArrayList<String> hands = handToCards(handString);
        ArrayList<Card> cards = new ArrayList<>();

        for (int i = 0; i < hands.size(); i++) {
            Card card = new Card(hands.get(i));
            cards.add(card);
        }

        this.cards = cards;
    }

    /**
     *
     * @param hand given hand string (see the STRING_REPRESENTATION.md)
     * @return ret an ArrayList of Strings with each card in hand represented as a String element
     */
    public static ArrayList<String> handToCards(String hand) {
        ArrayList<String> ret = new ArrayList<>();
        char[] handChar = hand.toCharArray();
        String[] deck;

        for (int i = 0; i < handChar.length; i++) {
            if (handChar[i] == 'A' || handChar[i] == 'B' || handChar[i] == 'C' || handChar[i] == 'D') {
                deck = switch (handChar[i]) {
                    case 'A' -> Utility.DECK_A;
                    case 'B' -> Utility.DECK_B;
                    case 'C' -> Utility.DECK_C;
                    case 'D' -> Utility.DECK_D;
                    default -> throw new IllegalStateException("Unexpected value: " + handChar[i]);
                };
                int j = i + 1;
                while (j < handChar.length && handChar[j] != 'A' && handChar[j] != 'B' &&
                        handChar[j] != 'C' && handChar[j] != 'D') {
                    for (String str : deck) {
                        if (str.charAt(0) == handChar[j]) {
                            ret.add(str.substring(1));
                        }
                    }
                    j++;
                }
            }
        }
        return ret;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public static String generateHand() {
        Map<Character, List<Character>> handMap = new HashMap<>();
        List<Character> lowerCaseChars = new ArrayList<>();
        for (char c = 'a'; c <= 'f'; c++) {
            lowerCaseChars.add(c);
        }
        Collections.shuffle(lowerCaseChars);

        List<Character> upperCaseChars = Arrays.asList('A', 'B', 'C', 'D');
        Collections.shuffle(upperCaseChars);

        for (int i = 0; i < 6; i++) {
            Character upperCaseChar = upperCaseChars.get(new Random().nextInt(upperCaseChars.size()));
            if (!lowerCaseChars.isEmpty()) {
                char lowerCaseChar = lowerCaseChars.remove(lowerCaseChars.size() - 1);
                handMap.putIfAbsent(upperCaseChar, new ArrayList<>());
                handMap.get(upperCaseChar).add(lowerCaseChar);
            }
        }

        StringBuilder hand = new StringBuilder();
        upperCaseChars.sort(Comparator.naturalOrder());
        for (Character upperCaseChar : upperCaseChars) {
            if (handMap.containsKey(upperCaseChar)) {
                hand.append(upperCaseChar);
                for (Character lowerCaseChar : handMap.get(upperCaseChar)) {
                    hand.append(lowerCaseChar);
                }
            }
        }

        return hand.toString();
    }

//     for testing use
    public static void main(String[] args) {
        System.out.println(generateHand());
//        String hand = "AfhkDahw";
//        ArrayList<String> list = new ArrayList<>();
//        list = handToCards(hand);
//        for (String str : list) {
//            System.out.println(str);
//        }
    }
}
