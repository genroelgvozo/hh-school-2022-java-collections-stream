package tasks;

import common.Person;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task8Test {
    private Task8 task8;
    private Person person1;
    private Person person2;
    private Person person3;
    private Person person4;
    private Person person5;
    private Person person6;

    @BeforeEach
    void before() {
        task8 = new Task8();
        person1 = new Person(1, "Oleg", "Ivanov", Instant.now());
        person2 = new Person(2, "Vasya", "Petrov", Instant.now().plusSeconds(1));
        person3 = new Person(3, "Pavel", "Sidorov", Instant.now().plusSeconds(2));
        person4 = new Person(4, "Oleg", null, Instant.now().plusSeconds(3));
        person5 = new Person(5, null, "Petrov", Instant.now().plusSeconds(4));
        person6 = new Person(6, null, null, Instant.now().plusSeconds(5));
    }

    @Test
    void test1() {
        assertEquals(List.of("Vasya", "Pavel"), task8.getNames(List.of(person1, person2, person3)));
    }

    @Test
    void test1e() {
        assertEquals(Collections.emptyList(), task8.getNames(Collections.emptyList()));
    }

    @Test
    void test2() {
        assertEquals(
                Set.of("Oleg", "Vasya", "Pavel"),
                task8.getDifferentNames(List.of(person1, person2, person3, person4)));
    }

    @Test
    void test3() {
        assertEquals("Ivanov Oleg", task8.convertPersonToString(person1));
    }

    @Test
    void test3n() {
        assertEquals("Oleg", task8.convertPersonToString(person4));
    }

    @Test
    void test3nn() {
        assertEquals("", task8.convertPersonToString(person6));
    }

    @Test
    void test3N() {
        assertEquals("", task8.convertPersonToString(null));
    }

    @Test
    void test4() {
        assertEquals(Map.of(
                        1, "Oleg",
                        2, "Vasya",
                        3, "Pavel",
                        4, "Oleg"),
                task8.getPersonNames(List.of(person1, person2, person3, person4, person5, person6
                        , person1, person2, person3, person4, person5, person6)));
    }

    @Test
    void test5t() {
        assertTrue(task8.hasSamePersons(
                List.of(person1, person2, person3),
                List.of(person1, person2, person3)));
    }

    @Test
    void test5f() {
        assertFalse(task8.hasSamePersons(
                List.of(person1, person2),
                List.of(person3, person4)));
    }

    @Test
    void test6() {
        assertEquals(4, task8.countEven(Stream.of(3, 4, 5, 9, 2, 2, 8)));
    }

    @Test
    void test6n() {
        assertEquals(0, task8.countEven(null));
    }
}