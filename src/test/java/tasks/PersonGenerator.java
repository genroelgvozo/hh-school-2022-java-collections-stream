package tasks;

import common.Person;

import java.time.Instant;
import java.util.Random;
import java.util.function.Supplier;

public class PersonGenerator implements Supplier<Person> {
    @Override
    public Person get() {
        return new Person(new Random().nextInt(), getRandomName(), Instant.now());
    }

    private String getRandomName() {
        Random random = new Random();
        return random.ints('a', 'z' + 1)
                .limit(random.nextInt(1, 10))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
