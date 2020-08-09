import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Robot {
    private int xTiles, yTiles;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        List<Character> commandsList;
        Position position;

        // Setting up the room!
        System.out.println("Enter the room dimensions (separated by space)!");
        String[] dimensions = scanner
                .nextLine()
                .split(" ");
        xTiles = Integer.parseInt(dimensions[0]);
        yTiles = Integer.parseInt(dimensions[1]);

        // Positioning the robot!
        System.out.println("Enter the robot's starting point and direction (separated by spaces {NUMBER} {NUMBER} {DIRECTION})");
        List<String> startingPoint = Arrays.stream(scanner
                .nextLine()
                .split(" ")).collect(Collectors.toList());

        position = new Position(
                Integer.parseInt(startingPoint.get(0)),
                Integer.parseInt(startingPoint.get(1)),
                startingPoint.get(2).charAt(0));

        // Check if the robots' position is valid to the room!
        validatePosition(position);

        // Fetch movement commands!
        System.out.println("Enter movement commands (one string of R or L or F)");
        commandsList = scanner
                .nextLine()
                .toUpperCase()
                .chars()
                .mapToObj(ch -> (char) ch).collect(Collectors.toList());

        // Apply movement commands
        for (char command : commandsList)
            position = applyMovement(command, position);

        // Report final position
        System.out.printf("%d %d %c\n", position.getPositionX(),
                position.getPositionY(),
                position.getDirection());
    }

    public Position applyMovement(char command, Position newPosition) {
        switch (newPosition.getDirection()) {
            case 'N':
                if (command == 'R') {
                    newPosition.setPositionX(newPosition.getPositionX() + 1);
                    newPosition.setDirection(Directions.EAST.value);
                } else if (command == 'L') {
                    newPosition.setPositionX(newPosition.getPositionX() - 1);
                    newPosition.setDirection(Directions.WEST.value);
                } else if (command == 'F') {
                    newPosition.setPositionY(newPosition.getPositionY() + 1);
                }
                break;
            case 'E':
                if (command == 'R') {
                    newPosition.setPositionY(newPosition.getPositionY() - 1);
                    newPosition.setDirection(Directions.SOUTH.value);
                } else if (command == 'L') {
                    newPosition.setPositionY(newPosition.getPositionY() + 1);
                    newPosition.setDirection(Directions.NORTH.value);
                } else if (command == 'F') {
                    newPosition.setPositionX(newPosition.getPositionX() + 1);
                }
                break;
            case 'S':
                if (command == 'R') {
                    newPosition.setPositionX(newPosition.getPositionX() - 1);
                    newPosition.setDirection(Directions.WEST.value);
                } else if (command == 'L') {
                    newPosition.setPositionX(newPosition.getPositionX() + 1);
                    newPosition.setDirection(Directions.EAST.value);
                } else if (command == 'F') {
                    newPosition.setPositionY(newPosition.getPositionY() - 1);
                }
                break;
            case 'W':
                if (command == 'R') {
                    newPosition.setPositionY(newPosition.getPositionY() + 1);
                    newPosition.setDirection(Directions.NORTH.value);
                } else if (command == 'L') {
                    newPosition.setPositionY(newPosition.getPositionY() - 1);
                    newPosition.setDirection(Directions.SOUTH.value);
                } else if (command == 'F') {
                    newPosition.setPositionX(newPosition.getPositionX() - 1);
                }
                break;
        }

        validatePosition(newPosition);

        return newPosition;
    }

    public void validatePosition(Position objPosition) {
        if (objPosition.getPositionX() < 0 || objPosition.getPositionX() >= getxTiles()
                || objPosition.getPositionY() < 0 || objPosition.getPositionY() >= getyTiles()) {
            System.out.println("[-1,-1]");
            throw new IndexOutOfBoundsException(String.format("Robot's position exceeded room dimensions! %s", objPosition.toString()));
        }
    }

    public int getxTiles() {
        return xTiles;
    }

    public int getyTiles() {
        return yTiles;
    }
}
