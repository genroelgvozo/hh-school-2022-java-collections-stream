package tasks;

import common.Person;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Task8Test {

    private static final int SIZE_OF_LIST_OF_MANY = 50;
    private final List<Person> emptyList = Collections.emptyList();
    private Task8 task8;
    private List<Person> listOfOne;
    private List<Person> listOfTwo;
    private List<Person> listOfFour;

    @BeforeAll
    void initialization() {
        task8 = new Task8();
        Instant clock = Instant.now();
        Person testPerson = new Person(15, "Misha", clock);
        listOfOne = List.of(testPerson);
        listOfTwo = List.of(
                new Person(62, "Misha", clock.plusSeconds(1)),
                new Person(2, "Vova", clock.plusSeconds(2))
        );
        listOfFour = List.of(
                testPerson,
                new Person(91, "Vova", "Petrov", clock.plusSeconds(3)),
                new Person(47,
                        "Fedor",
                        "Ivanov",
                        "Alexeevich",
                        clock.plusSeconds(4)),
                new Person(100, "Vova", clock.plusSeconds(4))
        );
    }

    @Test
    void getNamesShouldReturnEmptyListIfEmptyListSend() {
        assertEquals(Collections.emptyList(), task8.getNames(emptyList));
    }

    @Test
    void getNamesShouldReturnEmptyListIfListOfOneSend() {
        assertEquals(Collections.emptyList(), task8.getNames(listOfOne));
    }

    @Test
    void getNamesShouldReturnListOfOneIfListOfTwoSend() {
        assertEquals(List.of("Vova"), task8.getNames(listOfTwo));
    }

    @Test
    void getNamesShouldReturnListOfThreeIfListOfFourSend() {
        assertEquals(List.of("Vova", "Fedor", "Vova"), task8.getNames(listOfFour));
    }

    @Test
    void getNamesShouldReturnListOfManyWithReducedSizeIfListOfManySend() {
        for (int i = 0; i < 10; i++) {
            List<Person> listOfMany = Stream.generate(new PersonSupplier())
                    .limit(SIZE_OF_LIST_OF_MANY)
                    .collect(Collectors.toList());
            assertEquals(listOfMany.size() - 1, task8.getNames(listOfMany).size());
        }
    }

    @Test
    void getDifferentNamesShouldReturnDistinctNames() {
        assertEquals(Set.of("Vova", "Fedor"), task8.getDifferentNames(listOfFour));
    }

    @Test
    void getDifferentNamesShouldReturnEmptySetIfListOfOneSend() {
        assertEquals(Collections.emptySet(), task8.getDifferentNames(listOfOne));
    }

    @Test
    void getDifferentNamesShouldNotReturnFirstName() {
        assertFalse(task8.getDifferentNames(listOfFour).contains("Misha"));
    }

    @Test
    void convertPersonToStringShouldReturnSecondNameThenFirstNameThenMiddleName() {
        assertEquals("Ivanov Fedor Alexeevich", task8.convertPersonToString(
                        new Person(1, "Fedor", "Ivanov", "Alexeevich", Instant.now())
                )
        );
    }

    @Test
    void convertPersonToStringShouldReturnSecondNameThenFirstName() {
        assertEquals("Ivanov Fedor", task8.convertPersonToString(
                        new Person(1, "Fedor", "Ivanov", Instant.now())
                )
        );
    }

    @Test
    void convertPersonToStringShouldReturnFirstName() {
        assertEquals("Fedor", task8.convertPersonToString(
                        new Person(1, "Fedor", Instant.now())
                )
        );
    }

    @Test
    void getPersonNames() {
        Map<Integer, String> idToFullName = new HashMap<>() {{
            put(100, "Vova");
            put(15, "Misha");
            put(91, "Petrov Vova");
            put(47, "Ivanov Fedor Alexeevich");
        }};
        assertEquals(idToFullName, task8.getPersonNames(listOfFour));
    }

    @Test
    void hasSamePersonsWithoutMatches() {
        assertFalse(task8.hasSamePersons(listOfFour, listOfTwo));
    }

    @Test
    void hasSamePersonsWithMatches() {
        assertTrue(task8.hasSamePersons(listOfOne, listOfFour));
    }

    @Test
    void hasSamePersonsEmptyLists() {
        assertFalse(task8.hasSamePersons(List.of(), List.of()));
    }

    @Test
    void countEvenPositive() {
        assertEquals(4, task8.countEven(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    @Test
    void countEvenNegative() {
        assertEquals(4, task8.countEven(Stream.of(-1, -2, -3, -4, -5, -6, -7, -8, -9)));
    }

    @Test
    void countEvenZero() {
        assertEquals(1, task8.countEven(Stream.of(0)));
    }

    @Test
    void countEvenMixed() {
        assertEquals(5, task8.countEven(Stream.of(-1000, -800, -99, 0, 15, 44, 88)));
    }
}