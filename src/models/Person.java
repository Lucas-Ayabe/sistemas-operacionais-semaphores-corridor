package models;

public class Person {

    private int id;
    private int walkedDistance = 0;

    public Person(final int id) {
        this.id = id;
    }

    public Person walk(final int distance) {
        walkedDistance += distance;
        return this;
    }

    public int walkedDistance() {
        return walkedDistance;
    }

    @Override
    public String toString() {
        return "Pessoa #" + id;
    }
}
