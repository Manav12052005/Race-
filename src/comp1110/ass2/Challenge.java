package comp1110.ass2;

public class Challenge {
    private int difficulty;
    private String name;
    private String boardState;

    public Challenge(int difficulty, String name, String boardState) {
        this.difficulty = difficulty;
        this.name = name;
        this.boardState = boardState;
    }

    // Getters and setters for difficulty, name, and boardState

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoardState() {
        return boardState;
    }

    public void setBoardState(String boardState) {
        this.boardState = boardState;
    }

    // Override toString() method to provide string representation of the challenge
    @Override
    public String toString() {
        return difficulty + name + boardState;
    }
}
