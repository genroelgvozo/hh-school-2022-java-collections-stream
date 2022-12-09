package tasks;

import common.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Instant;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Task8Test {

    Task8 task8 = new Task8();
    List<Person> p1;

    @BeforeEach
    void setUp() {
        p1 = new ArrayList<>(IntStream.range(0, 5)
                .mapToObj(i -> new Person(i + 1, "firstName" + i, "secondName", Instant.now())).toList());
    }

    @Test
    void getNames() {
        assertEquals(Collections.emptyList(), task8.getFullNames(Collections.emptyList()));
        assertEquals("firstName1 secondName", task8.getFullNames(p1).get(0));
    }

    @Test
    void getDifferentNames() {
        p1.add(new Person(10, "firstName1", "secondName", Instant.now()));
        p1.add(new Person(11, "firstName2", "secondName", Instant.now()));
        p1.add(new Person(12, "firstName3", "secondName", Instant.now()));
        assertEquals(8, p1.size());
        Set<String> strings = Set.of("firstName1", "firstName2", "firstName3", "firstName4");
        assertEquals(strings, task8.getDifferentNames(p1));

    }

    @ParameterizedTest
    @MethodSource("getTestPerson")
    void convertPersonToString(Person p) {
        assertEquals(3, task8.convertPersonToString(p).length());
    }

    @Test
    void getPersonNames() {
        assertEquals(5,task8.getPersonNames(p1).size());
        assertEquals("firstName0 secondName", task8.getPersonNames(p1).get(1));
    }

    @Test
    void hasNotSamePersons() {
        List<Person> p2 = IntStream.range(0, 3)
                .mapToObj(i -> new Person(i + 1, "firstName" + i, "secondName", Instant.now())).toList();
        assertFalse(task8.hasSamePersons(p1, p2));
    }


    @Test
    void countEven() {
        Stream<Integer> nums = Stream.of(1, 2, 3, 4, 5, 6, 7, 22);
        assertEquals(4, task8.countEven(nums));
    }

    private static Stream<Person> getTestPerson() {
        return Stream.of(
                new Person(1, "a", "b", null, Instant.now()),
                new Person(2, null, "b", "c", Instant.now()),
                new Person(3, "a", null, "c", Instant.now())
        );
    }
}