package home.task;

import java.util.ArrayList;
import java.util.List;

public class DigitPanel {
    
    private int floors;
    private List<Integer> selectedFloors;
    
    public DigitPanel(int floors) {
        this.floors = floors;
        this.selectedFloors = new ArrayList<>();
    }

    public void removeFloor(int floor) {
        for (int i = 0; i < selectedFloors.size(); i++) {
            if (selectedFloors.get(i) == floor) {
                selectedFloors.remove(i);
            }
        }
    }

    public void removeFloor(List<Integer> removeFloors) {
        this.selectedFloors.removeAll(removeFloors);
    }

    public void selectFloor(int floor) {
        if (floor < 0 || floor > floors) {
            return;
        }
        if (this.selectedFloors.contains(floor)) {
            return;
        }
        this.selectedFloors.add(floor);
    }

    public List<Integer> getSelectedFloors() {
        return selectedFloors;
    }
    
    @Override
    public String toString() {
        return String.valueOf(selectedFloors);
    }
}
