package home.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

class ElevatorSyncTest {

    private DigitPanel digitPanel;

    private ElevatorSync elevatorSync;

    @BeforeEach
    void setUpBeforeEach() {
        digitPanel = new DigitPanel(25);
        elevatorSync = new ElevatorSync(25, digitPanel);
        digitPanel.selectFloor(6);
        digitPanel.selectFloor(7);
    }

    @Test
    void run() {
        
    }

    

}