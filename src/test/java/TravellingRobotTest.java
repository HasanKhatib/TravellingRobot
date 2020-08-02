import org.junit.Test;
import org.junit.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class TravellingRobotTest {

    @Test
    public void ApplyLeftMovementShouldAffectXWhenFacingNorth() {
        TravellingRobot.Position position = new TravellingRobot.Position(1, 1, 'N');
        Assert.assertEquals(
                TravellingRobot.applyMovement('L', position, 4, 4).positionX,
                0);
    }

    @Test
    public void ApplyForwardMovementShouldAffectYWhenFacingSouth() {
        TravellingRobot.Position position = new TravellingRobot.Position(1, 1, 'S');
        Assert.assertEquals(
                TravellingRobot.applyMovement('F', position, 4, 4).positionY,
                0);
    }

    @Test
    public void ApplyRightMovementShouldAffectYWhenFacingWest() {
        TravellingRobot.Position position = new TravellingRobot.Position(1, 1, 'W');
        Assert.assertEquals(
                TravellingRobot.applyMovement('R', position, 4, 4).positionY,
                2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void ApplyingCommandsThatWouldExceedRoomShouldThrowException() {
        TravellingRobot.Position position = new TravellingRobot.Position(1, 1, 'W');
        List<Character> commands = "RFFFFFF".chars().mapToObj(ch -> (char) ch).collect(Collectors.toList());
        for (char command : commands)
            position = TravellingRobot.applyMovement(command, position, 4, 4);
    }
}
