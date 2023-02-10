package home.task;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class ElevatorSync {

    private final DigitPanel digitPanel; // Sequence of selected floors
    private final int floors; // Tech value
    private final int middleFloor; // Middle floor is a tech value
    private int currentFloor; // Store current elevator position
    private ElevatorDirection direction; // By default is running UP
    
    private Queue<Integer> floorSequence; // Store sequence of floors

    public ElevatorSync(int floors, DigitPanel digitPanel) {
        this.floors = floors;
        this.digitPanel = digitPanel;
        this.middleFloor = (int) Math.floor(this.floors / 2);
        this.currentFloor = 1;
        this.direction = ElevatorDirection.UP;
        this.floorSequence = new ArrayDeque<>();
    }

    public Queue<Integer> getFloorSequence() {
        return floorSequence;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setDirection(ElevatorDirection direction) {
        this.direction = direction;
    }

    // Method is used if floor sequence is empty (elevator doesn't run) and new floor was selected
    private void calcDirection() {
        this.direction = this.currentFloor <= this.middleFloor ? ElevatorDirection.UP : ElevatorDirection.DOWN;
    }

    public void calcFloorSequence() {

        // Get data from digit panel
        List<Integer> selectedFloors = digitPanel.getSelectedFloors();
        Collections.sort(selectedFloors);
        
        // Define elevator next floor (from floor sequence)
        
        Integer nextFloor = floorSequence.peek();
        if (nextFloor == null) {
            nextFloor = currentFloor;
        }

        // Based on elevator's next floor and direction build correct floor sequence
        switch (direction) {
            
            case UP -> {
                for (Integer selectedFloor : selectedFloors) {
                    if (selectedFloor > nextFloor) {
                        floorSequence.add(selectedFloor);
                    }
                }
            }
            
            case DOWN -> {
                for (int i = selectedFloors.size() - 1; i >= 0; i--) {
                    if (selectedFloors.get(i) < nextFloor) {
                        floorSequence.add(selectedFloors.get(i));
                    }
                }
            }
        }
        
        digitPanel.removeFloor(floorSequence.stream().toList());
        digitPanel.removeFloor(currentFloor);

    }

    // Below is method overloading (two methods with same name and different parameters)
    private void waiting() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void waiting(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    

    private void move() {
        // One by one
        for (Integer floor : this.floorSequence) {
            // New stop
            currentFloor = floor;
            // Wait a little, because our housemates are rather slow
            waiting(100);
            // Remove current stop
            this.floorSequence.poll();
        }
    }

    public void run() {

        // Start elevator

        do {

            // Calculate new sequence
            calcFloorSequence();

            // Print information to console
            printToConsole();
            
            // And go!
            move();

            // Recalculate direction
            calcDirection();

        } while (!digitPanel.getSelectedFloors().isEmpty());
    }

    private void printToConsole() {
        System.out.println("Digital Panel   : " + digitPanel.toString());
        System.out.println("Direction       : " + direction);
        System.out.println("Current floor   : " + currentFloor);
        System.out.println("Sequence Floors : " + floorSequence);
        System.out.println();
    }

    
}

