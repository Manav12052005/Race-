package comp1110.ass2;

public class Location {
    private int row;
    private int column;

    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }
    /**
     *getLocation()
     *setLocation()
     *isOnBoard()
     */
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isOnBoard() {
        return false; // check if the location is on board
    }
}