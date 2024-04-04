package comp1110.ass2;

public class Cat {
    private String colour;
    private Location location;
    private boolean exhausted;

    // Constructor
    public Cat(String colour, Location location){
        this.colour = colour;
        this.location = location;
        this.exhausted = false;
    }
    public boolean isExhausted(){
        return false;
    }
    public boolean setExhausted(){
        return false;
    }
}
