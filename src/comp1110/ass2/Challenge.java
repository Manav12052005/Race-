package comp1110.ass2;

public class Challenge {
    private int difficulty;
    private String name;

    public Challenge(String challengeString) {
        // Set difficulty and name based on the challengeString
        if (challengeString.startsWith("LNSNLASAF000300060012001503030903C112033060340009R01215")) {
            this.difficulty = 0;
            this.name = "First steps";
        } else if (challengeString.startsWith("LNSNLASAF000300090015030309031203C106033000650012R11215")) {
            this.difficulty = 0;
            this.name = "Ancient trail";
        } else if (challengeString.startsWith("LNSNLASAF000300060012001506030903C000093030341203R11215")) {
            this.difficulty = 0;
            this.name = "Crossed paths";
        } else if (challengeString.startsWith("LNSNLASAF00030006001203030903C00015100091120350603R21215")) {
            this.difficulty = 0;
            this.name = "Down the river";
        }
        // Add cases for difficulty 1 challenges
        else if (challengeString.startsWith("SNSNSASAF00030015C00006000094030350012R30909")) {
            this.difficulty = 1;
            this.name = "Early morning";
        } else if (challengeString.startsWith("SASASASAF0009001203000600C10006300154000050900R10915")) {
            this.difficulty = 1;
            this.name = "The protector";
        } else if (challengeString.startsWith("SNSNSASAF000300060012C00303100094090350015R30915")) {
            this.difficulty = 1;
            this.name = "Wet paws";
        } else if (challengeString.startsWith("SNSASASAF000600090012C10015306004000350900R10915")) {
            this.difficulty = 1;
            this.name = "Mind the gap";
        }
        // Add cases for difficulty 2 challenges
        else if (challengeString.startsWith("LASALASAF000000030009001503000900C00012312004060050006R20915")) {
            this.difficulty = 2;
            this.name = "Waiting for friends";
        } else if (challengeString.startsWith("LNSNLASAF0003000600120903120312061215C00303100094001551212R10915")) {
            this.difficulty = 2;
            this.name = "In the way";
        } else if (challengeString.startsWith("LNSALASAF000300090015C00012100061120050900R20915")) {
            this.difficulty = 2;
            this.name = "The old Teruvian";
        } else if (challengeString.startsWith("LNSNLASAF0003000600090303C0060311203303064001250015R11215")) {
            this.difficulty = 2;
            this.name = "Like a rainbow";
        }
        // Add cases for difficulty 3 challenges
        else if (challengeString.startsWith("SNSNSASAF0003000903030903C00006100153060340012R30906")) {
            this.difficulty = 3;
            this.name = "Down in the valley";
        } else if (challengeString.startsWith("SNSNSASAFC1000930003300154000650012R30909")) {
            this.difficulty = 3;
            this.name = "Narrow spaces";
        } else if (challengeString.startsWith("LNSNLASAF000300060009001203030903C00912100151060351203R21215")) {
            this.difficulty = 3;
            this.name = "Tricky waters";
        } else if (challengeString.startsWith("SNSNSASAF00030009001503030915C10012306034000650903R10615")) {
            this.difficulty = 3;
            this.name = "All over the place";
        }
        // Add cases for difficulty 4 challenges
        else if (challengeString.startsWith("LALAF1500C000032000630000R11506")) {
            this.difficulty = 4;
            this.name = "Running into fire";
        } else if (challengeString.startsWith("LNSNLASAF000300060012001503030312060306121203C109033120640009R20315")) {
            this.difficulty = 4;
            this.name = "Hot sands";
        } else if (challengeString.startsWith("LASAFC100003000640003R20906")) {
            this.difficulty = 4;
            this.name = "Clear the way";
        } else if (challengeString.startsWith("LNSNLASSF00030006001203031203C00009106033001540903R11212")) {
            this.difficulty = 4;
            this.name = "Running in circles";
        }
        // Add cases for difficulty 5 challenges
        else if (challengeString.startsWith("LNSNLNSAFC1090330003300154000660012R31209")) {
            this.difficulty = 5;
            this.name = "In the wrong place";
        } else if (challengeString.startsWith("SASNSNSAF0000001206090903C2000630300R20015")) {
            this.difficulty = 5;
            this.name = "Drawing straws";
        } else if (challengeString.startsWith("SNSNSASAF00030009060309030915C10303300124000650015R30912")) {
            this.difficulty = 5;
            this.name = "Mixed messages";
        } else if (challengeString.startsWith("LESALASAF001200151200120912121215C00600100093030041203R20615")) {
            this.difficulty = 5;
            this.name = "Straight dash";
        }
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getName() {
        return name;
    }
}
