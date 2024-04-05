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
    public boolean getExhausted(){
        return this.exhausted;
    }
    public boolean setExhausted(){
        return this.exhausted;
    }
}
