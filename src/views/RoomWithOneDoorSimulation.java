package views;

import java.util.concurrent.Semaphore;
import models.Corridor;

public class RoomWithOneDoorSimulation {

    private Semaphore door = new Semaphore(1);

    public RoomWithOneDoorSimulation(Corridor[] corridors) {
        for (var corridor : corridors) corridor.setDoor(door).start();
    }
}
