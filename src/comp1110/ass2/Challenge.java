package comp1110.ass2;

import java.util.Random;

public class Challenge {

    private int difficulty;
    private String name;

    private String island;
    private String fireSubstring;
    private String catSubstring;
    private String raftSubstring;
    // Add constants or enums for challenge identifiers
    private enum ChallengeId {
        FIRST_STEPS("LNSNLASAF000300060012001503030903C112033060340009R01215", "First steps"),
        ANCIENT_TRAIL("LNSNLASAF000300090015030309031203C106033000650012R11215", "Ancient trail"),
        CROSSED_PATHS("LNSNLASAF000300060012001506030903C000093030341203R11215", "Crossed paths"),
        DOWN_THE_RIVER("LNSNLASAF00030006001203030903C00015100091120350603R21215", "Down the river"),
        EARLY_MORNING("SNSNSASAF00030015C00006000094030350012R30909", "Early morning"),
        THE_PROTECTOR("SASASASAF0009001203000600C10006300154000050900R10915", "The protector"),
        WET_PAWS("SNSNSASAF000300060012C00303100094090350015R30915", "Wet paws"),
        MIND_THE_GAP("SNSASASAF000600090012C10015306004000350900R10915", "Mind the gap"),
        WAITING_FOR_FRIENDS("LASALASAF000000030009001503000900C00012312004060050006R20915", "Waiting for friends"),
        IN_THE_WAY("LNSNLASAF0003000600120903120312061215C00303100094001551212R10915", "In the way"),
        THE_OLD_TERUVIAN("LNSALASAF000300090015C00012100061120050900R20915", "The old Teruvian"),
        LIKE_A_RAINBOW("LNSNLASAF0003000600090303C0060311203303064001250015R11215", "Like a rainbow"),
        CLOSING_IN("LESALESAF1200120912121215C109003060040300R10615", "Closing in"),
        FUSSY_CATS("SNSASASAF0003000600090015C109003001240600R20315", "Fussy cats"),
        RIVER_RAFTS("LNSNLASAF0003000600090303090309121215C0001211203309153121240015R00603", "River rafts"),
        ME_FIRST("LASNLASAF00000003000903000909C30006300154001250600R31209", "Me first!"),
        DOWN_IN_THE_VALLEY("SNSNSASAF0003000903030903C00006100153060340012R30906", "Down in the valley"),
        NARROW_SPACES("SNSNSASAFC1000930003300154000650012R30909", "Narrow spaces"),
        TRICKY_WATERS("LNSNLASAF000300060009001203030903C00912100151060351203R21215", "Tricky waters"),
        ALL_OVER_THE_PLACE("SNSNSASAF00030009001503030915C10012306034000650903R10615", "All over the place"),
        STUCK_IN_THE_FOREST("SNSNSASAF00030006000906030915C00015109033001240303R10912", "Stuck in the forest"),
        MEET_THE_TWINS("SNSNSASAF000300150303C0000600009300123090340603R30912", "Meet the twins"),
        BURNING_TREES("LESNLASAF00090015060009060909C01209103003001251212R20615", "Burning trees"),
        YANNAS_CHILDREN("LNSNLASAF00030006000903030603C00012200153090351203R11215", "Yanna's children"),
        RUNNING_INTO_FIRE("LALAF1500C000032000630000R11506", "Running into fire"),
        HOT_SANDS("LNSNLASAF000300060012001503030312060306121203C109033120640009R20315", "Hot sands"),
        CLEAR_THE_WAY("LASAFC100003000640003R20906", "Clear the way"),
        RUNNING_IN_CIRCLES("LNSNLASSF00030006001203031203C00009106033001540903R11212", "Running in circles"),
        EVERYONE_IS_WELCOME("LNSNLSSSF000600121212C0120920003303064031251206R00009", "Everyone is welcome"),
        SWITCH_PLACES("LASNLASAF00000003000903000909C30006300154060050012R31209", "Switch places"),
        KEEPING_A_BALANCE("LASNLESAF0000000603001203C000030031530309309094060950600R31209", "Keeping a balance"),
        AROUND_THE_ISLAND("LNSNLSSSF0003000609091212C000093091250012R31209", "Around the island"),
        IN_THE_WRONG_PLACE("LNSNLNSAFC1090330003300154000660012R31209", "In the wrong place"),
        DRAWING_STRAWS("SASNSNSAF0000001206090903C2000630300R20015", "Drawing straws"),
        MIXED_MESSAGES("SNSNSASAF00030009060309030915C10303300124000650015R30912", "Mixed messages"),
        STRAIGHT_DASH("LESALASAF001200151200120912121215C00600100093030041203R20615", "Straight dash"),
        ITS_MEOW_OR_NEVER("LNSNLASAF000300060012001503030603061212031206C100093031240903R20315", "It's meow or never"),
        DOWN_THE_WATERFALL("LNSNLASAF0003000900150303030903150603090312091215C100122000661203R01212", "Down the waterfall"),
        BURNING_TAILS("LNSNLASAF00030006030306030606060909030906C000123000940015R31203", "Burning tails");

        private final String challengeString;
        private final String name;

        ChallengeId(String challengeString, String name) {
            this.challengeString = challengeString;
            this.name = name;
        }

        public String getChallengeString() {
            return challengeString;
        }

        public String getName() {
            return name;
        }
    }

    // Constructor that initializes difficulty and name based on challengeString
    public Challenge(String challengeString) {
        for (ChallengeId challengeId : ChallengeId.values()) {
            if (challengeString.startsWith(challengeId.getChallengeString())) {
                this.difficulty = getDifficultyFromChallengeId(challengeId);
                this.name = challengeId.getName();
                parseChallengeString(challengeString);
                break;
            }
        }
    }

    // Helper method to map ChallengeId to difficulty level
    private int getDifficultyFromChallengeId(ChallengeId challengeId) {
        // Implement logic to map challenge ID to difficulty level
        if (challengeId == ChallengeId.FIRST_STEPS ||
                challengeId == ChallengeId.ANCIENT_TRAIL ||
                challengeId == ChallengeId.CROSSED_PATHS ||
                challengeId == ChallengeId.DOWN_THE_RIVER) {
            return 0;
        } else if (challengeId == ChallengeId.EARLY_MORNING ||
                challengeId == ChallengeId.THE_PROTECTOR ||
                challengeId == ChallengeId.WET_PAWS ||
                challengeId == ChallengeId.MIND_THE_GAP) {
            return 1;
        } else if (challengeId == ChallengeId.WAITING_FOR_FRIENDS ||
                challengeId == ChallengeId.IN_THE_WAY ||
                challengeId == ChallengeId.THE_OLD_TERUVIAN ||
                challengeId == ChallengeId.LIKE_A_RAINBOW ||
                challengeId == ChallengeId.CLOSING_IN ||
                challengeId == ChallengeId.FUSSY_CATS ||
                challengeId == ChallengeId.RIVER_RAFTS ||
                challengeId == ChallengeId.ME_FIRST) {
            return 2;
        } else if (challengeId == ChallengeId.DOWN_IN_THE_VALLEY ||
                challengeId == ChallengeId.NARROW_SPACES ||
                challengeId == ChallengeId.TRICKY_WATERS ||
                challengeId == ChallengeId.ALL_OVER_THE_PLACE ||
                challengeId == ChallengeId.STUCK_IN_THE_FOREST ||
                challengeId == ChallengeId.MEET_THE_TWINS ||
                challengeId == ChallengeId.BURNING_TREES ||
                challengeId == ChallengeId.YANNAS_CHILDREN) {
            return 3;
        } else if (challengeId == ChallengeId.RUNNING_INTO_FIRE ||
                challengeId == ChallengeId.HOT_SANDS ||
                challengeId == ChallengeId.CLEAR_THE_WAY ||
                challengeId == ChallengeId.RUNNING_IN_CIRCLES ||
                challengeId == ChallengeId.EVERYONE_IS_WELCOME ||
                challengeId == ChallengeId.SWITCH_PLACES ||
                challengeId == ChallengeId.KEEPING_A_BALANCE ||
                challengeId == ChallengeId.AROUND_THE_ISLAND) {
            return 4;
        } else if (challengeId == ChallengeId.IN_THE_WRONG_PLACE ||
                challengeId == ChallengeId.DRAWING_STRAWS ||
                challengeId == ChallengeId.MIXED_MESSAGES ||
                challengeId == ChallengeId.STRAIGHT_DASH ||
                challengeId == ChallengeId.ITS_MEOW_OR_NEVER ||
                challengeId == ChallengeId.DOWN_THE_WATERFALL ||
                challengeId == ChallengeId.BURNING_TAILS) {
            return 5;
        }
        return -1; // Default difficulty level if no match found
    }

    // Helper method to parse challenge string into substrings
    private void parseChallengeString(String challengeString) {
        int indexF = challengeString.indexOf('F');
        int indexC = challengeString.indexOf('C');
        int indexR = challengeString.indexOf('R');

        // Extract substrings based on delimiter positions
        this.island = challengeString.substring(0, indexF);
        this.fireSubstring = challengeString.substring(indexF + 1, indexC);
        this.catSubstring = challengeString.substring(indexC + 1, indexR);
        this.raftSubstring = challengeString.substring(indexR + 1);
    }


    public int getDifficulty() {
        return difficulty;
    }

    public String getName() {
        return name;
    }

    public String getBoardSubstring() {
        return island;
    }

    public String getFireSubstring() {
        return fireSubstring;
    }

    public String getCatSubstring() {
        return catSubstring;
    }

    public String getRaftSubstring() {
        return raftSubstring;
    }

    /**
     * Given a challenge string, construct an initial board state of the island and return
     *
     * @author Simon Liu
     * @param challengeString string
     * @return Island substring
     */
    public static String getBoardSubstring(String challengeString) {
        Challenge challenge = new Challenge(challengeString);
        String islandChallenge = challenge.getBoardSubstring();

        // Print out the islandChallenge (For debugging use)
//        System.out.println(islandChallenge);

        int width = 0;
        int height = 0;
        char[] islandChar = islandChallenge.toCharArray();

        for (int i = 0; i < islandChar.length; i += 2) {
            if (i == 0) {
                if (islandChar[i] == 'L') {
                    width += 9;
                    height += 9;
                } else if (islandChar[i] == 'S') {
                    width += 9;
                    height += 6;
                }
            }
            if (i == 2) {
                if (islandChar[i] == 'L') {
                    height += 9;
                } else if (islandChar[i] == 'S') {
                    height += 6;
                }
            }
            if (i == 4) {
                width += 9;
            }
        }

        char[][] boardChar = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boardChar[i][j] = '0';
            }
        }

        int startX = 0;
        int startY = 0;

        for (int i = 0; i < islandChar.length; i += 2) {
            String[][] boards;
            if (islandChar[i] == 'L') {
                boards = Utility.SQUARE_BOARDS;
            } else { // 'S'
                boards = Utility.RECTANGLE_BOARDS;
            }

            // Randomly select an island from the chosen array
            int randomIndex = new Random().nextInt(boards.length);
            String selectedIsland;

            // If the next character is 'A', select the [x][1] element, otherwise select the [x][0] element
            if (islandChar[i + 1] == 'A') {
                selectedIsland = boards[randomIndex][1];
            } else {
                selectedIsland = boards[randomIndex][0];
            }

            // Convert the selected string into a 2D char array
            String[] rows = selectedIsland.split("\n");
            char[][] islandArray = new char[rows.length][];
            for (int j = 0; j < rows.length; j++) {
                islandArray[j] = rows[j].toCharArray();
            }

            //print out the islandArray (For debugging use)
//            for (int row = 0; row < islandArray.length; row++) {
//                for (int col = 0; col < islandArray[0].length; col++) {
//                    System.out.print(islandArray[row][col]);
//                }
//                System.out.println();
//            }
//            System.out.println();



            // Rotate the islandArray according to the challenge string
            islandArray = rotateIslandArray(islandArray, islandChar[i], islandChar[i + 1]);

//            for (int row = 0; row < islandArray.length; row++) {
//                for (int col = 0; col < islandArray[0].length; col++) {
//                    System.out.print(islandArray[row][col]);
//                }
//                System.out.println();
//            }
//            System.out.println();


            boolean f = false;
            for (int col = 0; col < boardChar[0].length; col++) {
                for (int row = 0; row < boardChar.length; row++) {
                    if (boardChar[row][col] == '0') {
                        startX = row;
                        startY = col;
                        f = true;
                        break;
                    }
                }
                if (f) {
                    break;
                }
            }

            // Fit the islandArray into the boardChar array
            for (int row = 0; row < islandArray.length; row++) {
                for (int col = 0; col < islandArray[0].length; col++) {
                    boardChar[row + startX][col + startY] = islandArray[row][col];
                }
            }
            // print out the boardChar (For debugging use)
//            for (int row = 0; row < boardChar.length; row++) {
//                for (int col = 0; col < boardChar[0].length; col++) {
//                    System.out.print(boardChar[row][col]);
//                }
//                System.out.println();
//            }
//            System.out.println();
        }

        // Put fire, cat and raft into the boardChar
        String fire = challenge.getFireSubstring();
        for (int i = 0; i < fire.length(); i += 4) {
            int x = Integer.parseInt(fire.substring(i + 0, i + 2));
            int y = Integer.parseInt(fire.substring(i + 2, i + 4));
            // print out the fire position (For debugging use)
//            System.out.println(x + " " + y);
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 3; j++) {
                    boardChar[x + k][y + j] = 'f';
                }
            }
        }

        String cat = challenge.getCatSubstring();
        for (int i = 0; i < cat.length(); i += 5) {
            int cardNo = Integer.parseInt(cat.substring(i + 0, i + 1));
            String catString = Utility.CAT_CARDS[cardNo].substring(1);
            char[][] catChar = new char[3][3];
            for (int j = 0; j < 3; j++) {
                catChar[j] = catString.substring(j * 3, j * 3 + 3).toCharArray();
            }
            int catX = Integer.parseInt(cat.substring(i + 1, i + 3));
            int catY = Integer.parseInt(cat.substring(i + 3, i + 5));
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    boardChar[j + catX][k + catY] = catChar[j][k];
                }
            }
        }

        String raft = challenge.getRaftSubstring();
        for (int i = 0; i < raft.length(); i += 5) {
            int cardNo = Integer.parseInt(raft.substring(i + 0, i + 1));
            String raftString = Utility.RAFT_CARDS[cardNo].substring(1);
            char[][] raftChar = new char[3][3];
            for (int j = 0; j < 3; j++) {
                raftChar[j] = raftString.substring(j * 3, j * 3 + 3).toCharArray();
            }
            int raftX = Integer.parseInt(raft.substring(i + 1, i + 3));
            int raftY = Integer.parseInt(raft.substring(i + 3, i + 5));
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    boardChar[j + raftX][k + raftY] = raftChar[j][k];
                }
            }
        }



        // print out the boardChar (For debugging use)
//        System.out.println(Board.charBoardToString(boardChar));

        return Board.charBoardToString(boardChar);
    }

   public static char[][] rotateIslandArray(char[][] islandArray, char size, char direction) {
    char[][] rotatedArray;
    switch (direction) {
        case 'N':
            return islandArray;
        case 'E':
            rotatedArray = new char[islandArray[0].length][islandArray.length];
            for (int i = 0; i < islandArray.length; i++) {
                for (int j = 0; j < islandArray[0].length; j++) {
                    rotatedArray[j][islandArray.length - 1 - i] = islandArray[i][j];
                }
            }
            return rotatedArray;
        case 'S':
            rotatedArray = new char[islandArray.length][islandArray[0].length];
            for (int i = 0; i < islandArray.length; i++) {
                for (int j = 0; j < islandArray[0].length; j++) {
                    rotatedArray[islandArray.length - 1 - i][islandArray[0].length - 1 - j] = islandArray[i][j];
                }
            }
            return rotatedArray;
        case 'W':
            rotatedArray = new char[islandArray[0].length][islandArray.length];
            for (int i = 0; i < islandArray.length; i++) {
                for (int j = 0; j < islandArray[0].length; j++) {
                    rotatedArray[islandArray[0].length - 1 - j][i] = islandArray[i][j];
                }
            }
            return rotatedArray;
        case 'A':
            char[] validDirections = new char[0];
            if (size == 'S') {
                validDirections = new char[]{'N', 'S'};
            } else if (size == 'L') {
                validDirections = new char[]{'N', 'E', 'S', 'W'};
            }
            Random random = new Random();
            return rotateIslandArray(islandArray, size, validDirections[random.nextInt(validDirections.length)]);
        default:
            return islandArray;
    }
}


    public static void main(String[] args) {
        // Example usage to create a Challenge object and access its substrings
        String challengeString = "LNSNLASAF000300060012001503030903C112033060340009R01215"; // Example challenge string
        Challenge challenge = new Challenge(challengeString);

        getBoardSubstring(challengeString); // Call getIsland method to extract island substring (not implemented yet)


        System.out.println("Island: " + challenge.getBoardSubstring());
        System.out.println("Fire Substring: " + challenge.getFireSubstring());
        System.out.println("Cat Substring: " + challenge.getCatSubstring());
        System.out.println("Raft Substring: " + challenge.getRaftSubstring());
    }
}
