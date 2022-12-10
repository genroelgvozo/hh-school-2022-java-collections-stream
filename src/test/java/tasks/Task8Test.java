package tasks;

import common.Person;
import org.junit.jupiter.api.*;

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
    private List<Person> listWithNull;
    private final Instant clock = Instant.now();
    private final Person testPerson = new Person(15, "Misha", clock);
    private final Person testPerson2 = new Person(25, "Kolya", clock.plusSeconds(50));
    private final Person testPerson3 = new Person(35, "Nikita", clock.plusSeconds(90));
    private final Person nullNamePerson = new Person(35, null, clock.plusSeconds(120));
    private final Person nullIdPerson = new Person(null, "Nemo", clock.plusSeconds(240));
    private final Person nullTimePerson = new Person(300, "Nobody", null);
    private final Person strangeNamePerson = new Person(555, "null", clock.plusSeconds(90));

    @BeforeAll
    void initialization() {
        task8 = new Task8();

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
        listWithNull = new ArrayList<>() {{
            add(testPerson);
            add(testPerson2);
            add(null);
            add(testPerson3);
            add(nullNamePerson);
            add(nullIdPerson);
            add(nullTimePerson);
            add(strangeNamePerson);
        }};
    }

    @Test
    void getPersonsFirstNamesCompleteNullTest() {
        assertEquals(List.of("Kolya", "Nikita", "", "Nemo", "Nobody", "null"), task8.getPersonsFirstNames(listWithNull));
    }

    @Test
    void getPersonsFirstNamesShouldReturnEmptyListIfEmptyListSend() {
        assertEquals(Collections.emptyList(), task8.getPersonsFirstNames(emptyList));
    }

    @Test
    void getPersonsFirstNamesShouldReturnEmptyListIfListOfOneSend() {
        assertEquals(Collections.emptyList(), task8.getPersonsFirstNames(listOfOne));
    }

    @Test
    void getPersonsFirstNamesShouldReturnListOfOneIfListOfTwoSend() {
        assertEquals(List.of("Vova"), task8.getPersonsFirstNames(listOfTwo));
    }

    @Test
    void getPersonsFirstNamesShouldReturnListOfThreeIfListOfFourSend() {
        assertEquals(List.of("Vova", "Fedor", "Vova"), task8.getPersonsFirstNames(listOfFour));
    }

    @Test
    void getPersonsFirstNamesShouldReturnListOfManyWithReducedSizeIfListOfManySend() {
        for (int i = 0; i < 10; i++) {
            List<Person> listOfMany = Stream.generate(new PersonSupplier())
                    .limit(SIZE_OF_LIST_OF_MANY)
                    .collect(Collectors.toList());
            assertEquals(listOfMany.size() - 1, task8.getPersonsFirstNames(listOfMany).size());
        }
    }

    @Test
    void getUniqueFirstNamesCompleteNullTest() {
        assertEquals(Set.of("", "Kolya", "null", "Nikita", "Nobody", "Nemo"), task8.getUniqueFirstNames(listWithNull));
    }

    @Test
    void getUniqueFirstNamesShouldReturnDistinctNames() {
        assertEquals(Set.of("Vova", "Fedor"), task8.getUniqueFirstNames(listOfFour));
    }

    @Test
    void getUniqueFirstNamesShouldReturnEmptySetIfListOfOneSend() {
        assertEquals(Collections.emptySet(), task8.getUniqueFirstNames(listOfOne));
    }

    @Test
    void getUniqueFirstNamesShouldNotReturnFirstName() {
        assertFalse(task8.getUniqueFirstNames(listOfFour).contains("Misha"));
    }

    @Test
    void getFullNameOfPersonShouldReturnEmptyStringIfAllNamesAreNull() {
        assertEquals("", task8.getFullNameOfPerson(nullNamePerson));
    }

    @Test
    void getFullNameOfPersonShouldReturnSecondNameThenFirstNameThenMiddleName() {
        assertEquals("Ivanov Fedor Alexeevich", task8.getFullNameOfPerson(
                        new Person(1, "Fedor", "Ivanov", "Alexeevich", Instant.now())
                )
        );
    }

    @Test
    void getFullNameOfPersonShouldReturnSecondNameThenFirstName() {
        assertEquals("Ivanov Fedor", task8.getFullNameOfPerson(
                        new Person(1, "Fedor", "Ivanov", Instant.now())
                )
        );
    }

    @Test
    void getFullNameOfPersonShouldReturnFirstName() {
        assertEquals("Fedor", task8.getFullNameOfPerson(
                        new Person(1, "Fedor", Instant.now())
                )
        );
    }

    @Test
    void getPersonIdToFullNameWithNull() {
        Map<Integer, String> idToFullName = new HashMap<>() {{
            put(null, "Nemo");
            put(35, "Nikita");
            put(25, "Kolya");
            put(555, "null");
            put(300, "Nobody");
            put(15, "Misha");
        }};
        assertEquals(idToFullName, task8.getPersonIdToFullName(listWithNull));
    }
    @Test
    void getPersonIdToFullName() {
        Map<Integer, String> idToFullName = new HashMap<>() {{
            put(100, "Vova");
            put(15, "Misha");
            put(91, "Petrov Vova");
            put(47, "Ivanov Fedor Alexeevich");
        }};
        assertEquals(idToFullName, task8.getPersonIdToFullName(listOfFour));
    }

    @Test
    void haveIntersectionNull() {
        assertFalse(task8.haveIntersection(listOfFour, null));
        assertFalse(task8.haveIntersection(null, listOfFour));
        assertFalse(task8.haveIntersection(null, null));
    }

    @Test
    void haveIntersectionWithoutMatches() {
        assertFalse(task8.haveIntersection(listOfFour, listOfTwo));
    }

    @Test
    void haveIntersectionWithMatches() {
        assertTrue(task8.haveIntersection(listOfOne, listOfFour));
    }

    @Test
    void haveIntersectionEmptyLists() {
        assertFalse(task8.haveIntersection(List.of(), List.of()));
    }

    @Test
    void countEvenIntegersParallelPositiveEvenOnly() {
        assertEquals(4, task8.countEvenIntegersParallel(Stream.of(2, 4, 6, 8)));
    }

    @Test
    void countEvenIntegersParallelOddOnly() {
        assertEquals(0, task8.countEvenIntegersParallel(Stream.of(1, 3, 5, 7)));
    }

    @Test
    void countEvenIntegersParallelositive() {
        assertEquals(4, task8.countEvenIntegersParallel(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    @Test
    void countEvenIntegersParallelNegative() {
        assertEquals(4, task8.countEvenIntegersParallel(Stream.of(-1, -2, -3, -4, -5, -6, -7, -8, -9)));
    }

    @Test
    void countEvenIntegersParallelZero() {
        assertEquals(1, task8.countEvenIntegersParallel(Stream.of(0)));
    }

    @Test
    void countEvenIntegersParallelMixed() {
        assertEquals(5, task8.countEvenIntegersParallel(Stream.of(-1000, -800, -99, 0, 15, 44, 88)));
    }
}