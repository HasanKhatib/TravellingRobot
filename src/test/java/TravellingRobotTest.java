import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Collectors;

public class TravellingRobotTest {

    @Test
    public void ApplyLeftMovementShouldAffectXWhenFacingNorth() {
        Position position = new Position(1, 1, Directions.NORTH.value);
        Robot robot = Mockito.spy(new Robot());
        Mockito.doReturn(4).when(robot).getyTiles();
        Mockito.doReturn(4).when(robot).getxTiles();
        Assert.assertEquals(
                robot.applyMovement('L', position).getPositionX(),
                0);
    }

    @Test
    public void ApplyForwardMovementShouldAffectYWhenFacingSouth() {
        Position position = new Position(1, 1, Directions.SOUTH.value);
        Robot robot = Mockito.spy(new Robot());
        Mockito.doReturn(4).when(robot).getyTiles();
        Mockito.doReturn(4).when(robot).getxTiles();

        Assert.assertEquals(
                robot.applyMovement('F', position).getPositionY(),
                0);
    }

    @Test
    public void ApplyRightMovementShouldAffectYWhenFacingWest() {
        Position position = new Position(1, 1, Directions.WEST.value);
        Robot robot = Mockito.spy(new Robot());
        Mockito.doReturn(4).when(robot).getyTiles();
        Mockito.doReturn(4).when(robot).getxTiles();

        Assert.assertEquals(
                robot.applyMovement('R', position).getPositionY(),
                2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void ApplyingCommandsThatWouldExceedRoomShouldThrowException() {
        Position position = new Position(1, 1, Directions.WEST.value);
        Robot robot = Mockito.spy(new Robot());
        Mockito.doReturn(4).when(robot).getyTiles();
        Mockito.doReturn(4).when(robot).getxTiles();

        List<Character> commands = "RFFFFFF".chars().mapToObj(ch -> (char) ch).collect(Collectors.toList());
        for (char command : commands)
            position = robot.applyMovement(command, position);
    }
}
