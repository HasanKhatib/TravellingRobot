import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TravellingRobot {
    public static void main(String[] args) {
        startRobot();
    }

    private static void startRobot() {
        Scanner scanner = new Scanner(System.in);
        int xTiles, yTiles, positionX, positionY;
        char direction;
        List<Character> commandsList;
        boolean errorFlag;

        // Setting up the room!
        System.out.println("Enter the room dimensions (separated by space)!");
        String[] dimensions = scanner
                .nextLine()
                .split(" ");
        xTiles = Integer.parseInt(dimensions[0]);
        yTiles = Integer.parseInt(dimensions[1]);

        // Positioning the robot!
        do {
            System.out.println("Enter the robot's starting point and direction (separated by spaces {NUMBER} {NUMBER} {DIRECTION})");
            String[] startingPoint = scanner
                    .nextLine()
                    .toUpperCase()
                    .split(" ");
            positionX = Integer.parseInt(startingPoint[0]);
            positionY = Integer.parseInt(startingPoint[1]);
            direction = startingPoint[2].charAt(0);
            errorFlag = positionX > xTiles || positionY > yTiles;
            if (errorFlag)
                System.out.println("Enter valid position regarding room dimensions!");
        } while (errorFlag);

        // Fetch movement commands!
        System.out.println("Enter movement commands (one string of R or L or F)");
        commandsList = scanner
                .nextLine()
                .toUpperCase()
                .chars()
                .mapToObj(ch -> (char) ch).collect(Collectors.toList());

        // Apply movement commands
        Position finalPosition = new Position(positionX, positionY, direction);
        for (char command : commandsList)
            finalPosition = applyMovement(command, finalPosition, xTiles, yTiles);

        // Report final position
        System.out.println(String.format("%d %d %c",
                finalPosition.positionX,
                finalPosition.positionY,
                finalPosition.direction));
    }

    public static Position applyMovement(char command, Position finalPosition, int xTiles, int yTiles) {
        switch (finalPosition.direction) {
            case 'N':
                if (command == 'R') {
                    finalPosition.positionX++;
                    finalPosition.direction = Directions.EAST.value;
                } else if (command == 'L') {
                    finalPosition.positionX--;
                    finalPosition.direction = Directions.WEST.value;
                } else if (command == 'F') {
                    finalPosition.positionY++;
                }
                break;
            case 'E':
                if (command == 'R') {
                    finalPosition.positionY--;
                    finalPosition.direction = Directions.SOUTH.value;
                } else if (command == 'L') {
                    finalPosition.positionY++;
                    finalPosition.direction = Directions.NORTH.value;
                } else if (command == 'F') {
                    finalPosition.positionX++;
                }
                break;
            case 'S':
                if (command == 'R') {
                    finalPosition.positionX--;
                    finalPosition.direction = Directions.WEST.value;
                } else if (command == 'L') {
                    finalPosition.positionX++;
                    finalPosition.direction = Directions.EAST.value;
                } else if (command == 'F') {
                    finalPosition.positionY--;
                }
                break;
            case 'W':
                if (command == 'R') {
                    finalPosition.positionY++;
                    finalPosition.direction = Directions.NORTH.value;
                } else if (command == 'L') {
                    finalPosition.positionY--;
                    finalPosition.direction = Directions.SOUTH.value;
                } else if (command == 'F') {
                    finalPosition.positionX--;
                }
                break;
        }
        if(finalPosition.positionX > xTiles || finalPosition.positionY > yTiles)
            throw new IndexOutOfBoundsException("Robot's position exceeded room dimensions!");
        return finalPosition;
    }

    public static class Position {
        int positionX, positionY;
        char direction;

        public Position(int x, int y, char d) {
            positionX = x;
            positionY = y;
            direction = d;
        }

        public int getPositionX() {
            return positionX;
        }

        public void setPositionX(int positionX) {
            this.positionX = positionX;
        }

        public int getPositionY() {
            return positionY;
        }

        public void setPositionY(int positionY) {
            this.positionY = positionY;
        }

        public char getDirection() {
            return direction;
        }

        public void setDirection(char direction) {
            this.direction = direction;
        }
    }

    public enum Directions {
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
}
