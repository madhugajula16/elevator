import java.util.ArrayList;
import java.util.List;

// Enum representing the direction of the elevator
enum Direction {
    UP,
    DOWN
}

// Class representing a floor
class Floor {
    private int floorNumber;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}

// Class representing an elevator
class Elevator {
    private int currentFloor;
    private Direction direction;
    private List<Floor> destinationFloors;

    public Elevator() {
        currentFloor = 0;
        direction = Direction.UP;
        destinationFloors = new ArrayList<>();
    }

    public void addDestinationFloor(Floor floor) {
        destinationFloors.add(floor);
    }

    public void move() {
        while (!destinationFloors.isEmpty()) {
            Floor nextFloor = getNextFloor();
            if (nextFloor != null) {
                System.out.println("Elevator is at floor " + currentFloor);
                currentFloor = nextFloor.getFloorNumber();
                System.out.println("Elevator is moving to floor " + currentFloor);
                try {
                    Thread.sleep(1000); // 1 second stoppage at each floor
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Elevator has arrived at floor " + currentFloor);
                destinationFloors.remove(nextFloor);
            }
        }
    }

    private Floor getNextFloor() {
        if (destinationFloors.isEmpty()) {
            return null;
        }

        Floor nextFloor = null;
        if (direction == Direction.UP) {
            for (Floor floor : destinationFloors) {
                if (floor.getFloorNumber() >= currentFloor) {
                    nextFloor = floor;
                    break;
                }
            }
        } else if (direction == Direction.DOWN) {
            for (Floor floor : destinationFloors) {
                if (floor.getFloorNumber() <= currentFloor) {
                    nextFloor = floor;
                    break;
                }
            }
        }

        if (nextFloor == null) {
            // Change direction if no floors in the current direction
            direction = (direction == Direction.UP) ? Direction.DOWN : Direction.UP;
            nextFloor = getNextFloor();
        }

        return nextFloor;
    }
}

public class ElevatorSystem {
    public static void main(String[] args) {
        Elevator elevator = new Elevator();

        // Adding destination floors
        elevator.addDestinationFloor(new Floor(2));
        elevator.addDestinationFloor(new Floor(4));
        elevator.addDestinationFloor(new Floor(1));
        elevator.addDestinationFloor(new Floor(5));

        elevator.move();
    }
}
