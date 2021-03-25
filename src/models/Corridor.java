package models;

import java.util.concurrent.Semaphore;

public class Corridor extends Thread {

    private static final int LENGTH = 200;
    private Person person;
    private Semaphore door;
    private RandomInt speedTime = new RandomInt(4, 6);
    private RandomInt doorOpenTime = new RandomInt(1, 2);

    public Corridor(final Person person) {
        this.person = person;
    }

    public Corridor setDoor(final Semaphore door) {
        this.door = door;
        return this;
    }

    @Override
    public void run() {
        try {
            reportWalkProgress().andOpeningOfTheDoor();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            interrupt();
        }
    }

    private Corridor reportWalkProgress() throws InterruptedException {
        while (person.walkedDistance() < LENGTH) {
            var meters = speedTime.generate();

            sleep(1000);
            person.walk(meters);

            var walkedDistance = LENGTH - person.walkedDistance();
            var distanceToTheDoor = walkedDistance >= 0 ? walkedDistance : 0;

            System.out.println(
                person +
                " andou " +
                meters +
                " metros, faltam " +
                distanceToTheDoor +
                " metros até a porta."
            );
        }

        return this;
    }

    private void andOpeningOfTheDoor() {
        try {
            door.acquire();
            sleep((1000 * doorOpenTime.generate()));
            System.out.println(person + " cruzou a porta.");
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            interrupt();
        } finally {
            door.release();
        }
    }

    @Override
    public String toString() {
        return "Corredor com a " + person;
    }
}
