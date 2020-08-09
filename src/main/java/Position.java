import lombok.Data;

@Data
public class Position {
    private int positionX, positionY;
    private char direction;

    public Position() {
        positionX = 0;
        positionY = 0;
        direction = Directions.NORTH.value;
    }

    public Position(int x, int y, char c) {
        positionX = x;
        positionY = y;
        direction = c;
    }

    public Position(int x, int y) {
        positionX = x;
        positionY = y;
        direction = Directions.NORTH.value;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", positionX, positionY);
    }
}

enum Directions {
    NORTH('N'), EAST('E'), SOUTH('S'), WEST('W');

    char value;

    Directions(char value) {
        this.value = value;
    }

    static Directions getValue(char direction) {
        if (direction == 'N') return NORTH;
        else if (direction == 'E') return EAST;
        else if (direction == 'S') return SOUTH;
        else if (direction == 'W') return WEST;
        else throw new IllegalArgumentException(String.format("%s is not a valid direction!", direction));
    }
}