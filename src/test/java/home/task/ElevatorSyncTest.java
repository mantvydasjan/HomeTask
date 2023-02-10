package home.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;


class ElevatorSyncTest {

    private DigitPanel digitPanel;

    private ElevatorSync elevatorSync;

    @BeforeEach
    void setUpBeforeEach() {

        digitPanel = new DigitPanel(25);
        elevatorSync = new ElevatorSync(25, digitPanel);

    }

    @Test
    void floorSequenceUpTest() {

        Queue<Integer> expectedFloorSequence = new ArrayDeque<>();
        expectedFloorSequence.add(6);
        expectedFloorSequence.add(7);
        expectedFloorSequence.add(8);

        digitPanel.selectFloor(7);
        digitPanel.selectFloor(6);
        digitPanel.selectFloor(8);

        elevatorSync.calcFloorSequence();
        Queue<Integer> actualFloorSequence = elevatorSync.getFloorSequence();

        Assertions.assertEquals(expectedFloorSequence.stream().toList(), actualFloorSequence.stream().toList());

    }

    @Test
    void floorSequenceDownTest() {

        Queue<Integer> expectedFloorSequence = new ArrayDeque<>();
        expectedFloorSequence.add(8);
        expectedFloorSequence.add(7);
        expectedFloorSequence.add(6);

        digitPanel.selectFloor(7);
        digitPanel.selectFloor(6);
        digitPanel.selectFloor(8);

        elevatorSync.setCurrentFloor(20);
        elevatorSync.setDirection(ElevatorDirection.DOWN);
        elevatorSync.calcFloorSequence();
        Queue<Integer> actualFloorSequence = elevatorSync.getFloorSequence();

        Assertions.assertEquals(expectedFloorSequence.stream().toList(), actualFloorSequence.stream().toList());

    }


    @Test
    void digitalPanelTest() {

        DigitPanel expectedDigitPanel = new DigitPanel(25);
        List<Integer> expectedSelectedFloors = expectedDigitPanel.getSelectedFloors();
        digitPanel.selectFloor(6);

        elevatorSync.run();
        List<Integer> actualSelectedFloors = digitPanel.getSelectedFloors();

        Assertions.assertEquals(expectedSelectedFloors, actualSelectedFloors);
    }

   
}