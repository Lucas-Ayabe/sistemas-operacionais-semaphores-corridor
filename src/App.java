import java.util.stream.IntStream;
import models.Corridor;
import models.Person;
import views.RoomWithOneDoorSimulation;

public class App {

    public static void main(String[] args) {
        new RoomWithOneDoorSimulation(
            IntStream
                .range(1, 5)
                .mapToObj(id -> new Corridor(new Person(id)))
                .toArray(Corridor[]::new)
        );
    }
}
