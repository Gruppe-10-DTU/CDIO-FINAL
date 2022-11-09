package game.models;

public class Player {

    private int location=0;

    public int getLocation() {
        return location;
    }

    public void setLocation(int location, int newLocation) {
        this.location = (location + newLocation);
    }
}
