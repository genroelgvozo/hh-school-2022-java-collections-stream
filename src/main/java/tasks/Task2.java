package tasks;

import common.Person;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Task2 {

    public static List<Person> combineAndSortWithLimit(Collection<Person> persons1,
                                                       Collection<Person> persons2,
                                                       int limit) {

        return Stream.concat(persons1.stream(), persons2.stream())
                .distinct()
                .sorted(Comparator.comparing(Person::getCreatedAt))
                .limit(limit)
                .toList();
    }
}
