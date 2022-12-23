package tasks;

import common.Person;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task8Test {

  private final static Task8 task  = new Task8();
  private final static Instant now = Instant.now();

  @ParameterizedTest
  @MethodSource("generateDataForGetNamesOrGetDifferentNames")
  public void getNames(List<Person> persons, List<String> expectNames) {
    assertTrue(task.getNames(persons).equals(expectNames));
  }

  @ParameterizedTest
  @MethodSource("generateDataForGetNamesOrGetDifferentNames")
  public void getDifferentNames(List<Person> persons, List<String> expectNames) {
    assertTrue(task.getDifferentNames(persons).equals(new HashSet<>(expectNames)));
  }

  @ParameterizedTest
  @MethodSource("generateDataForConvertPersonToString")
  public void convertPersonToString(Person person, String expectPersonFullName) {
    assertTrue(task.convertPersonToString(person).equals(expectPersonFullName));
  }

  @ParameterizedTest
  @MethodSource("generateDataForGetPersonNames")
  public void getPersonNames(Collection<Person> persons, Map<Integer, String> expectIdToPersonFullNames) {
    assertTrue(task.getPersonNames(persons).equals(expectIdToPersonFullNames));
  }

  @ParameterizedTest
  @MethodSource("generateDataForHasSamePersonsResultTrue")
  public void hasSamePersonsResultTrue(Collection<Person> persons1, Collection<Person> persons2) {
    assertTrue(task.hasSamePersons(persons1, persons2));
  }

  @ParameterizedTest
  @MethodSource("generateDataForHasSamePersonsResultFalse")
  public void hasSamePersonsResultFalse(Collection<Person> persons1, Collection<Person> persons2) {
    assertFalse(task.hasSamePersons(persons1, persons2));
  }

  @ParameterizedTest
  @MethodSource("generateDataForCountEven")
  public void countEven(Stream<Integer> numbers, long expectEvenNumber) {
    assertEquals(expectEvenNumber, task.countEven(numbers));
  }

  private static Stream<Arguments> generateDataForGetNamesOrGetDifferentNames() {
    return Stream.of(
        Arguments.of(createPersons(Stream.of(1, 2, 3, 4)), getNames(Stream.of(2, 3, 4))),
        Arguments.of(createPersons(Stream.of(11, 21, 31, 41)), getNames(Stream.of(21, 31, 41))),
        Arguments.of(createPersons(Stream.of(1, 1, 2, 2, 3, 3, 4, 4)), getNames(Stream.of(1, 2, 2, 3, 3, 4, 4))),
        Arguments.of(createPersons(Stream.of(1)), getNames(Stream.of())),
        Arguments.of(createPersons(Stream.of()), getNames(Stream.of()))
    );
  }

  private static Stream<Arguments> generateDataForConvertPersonToString() {
    return Stream.of(
        Arguments.of(createPersonByParam(1, "Ivan", "Ivanov", "Ivanovich"), "Ivanov Ivan Ivanovich"),
        Arguments.of(createPersonByParam(1, null, "Ivanov", "Ivanovich"), "Ivanov Ivanovich"),
        Arguments.of(createPersonByParam(1, "Ivan", null, "Ivanovich"), "Ivan Ivanovich"),
        Arguments.of(createPersonByParam(1, "Ivan", "Ivanov", null), "Ivanov Ivan"),
        Arguments.of(createPersonByParam(1, null, null, "Ivanovich"), "Ivanovich"),
        Arguments.of(createPersonByParam(1, "Ivan", null, null), "Ivan"),
        Arguments.of(createPersonByParam(1, null, "Ivanov", null), "Ivanov"),
        Arguments.of(createPersonByParam(1, null, null, null), "")
    );
  }

  private static Stream<Arguments> generateDataForGetPersonNames() {
    List<Person> persons1 = new ArrayList<>() {{
      add(createPersonByParam(1, "Ivan", "Ivanov", "Ivanovich"));
      add(createPersonByParam(2, null, "Ivanov", "Ivanovich"));
      add(createPersonByParam(3, "Ivan", null, "Ivanovich"));
      add(createPersonByParam(4, "Ivan", "Ivanov", null));
      add(createPersonByParam(5, null, "Ivanov", null));
      add(createPersonByParam(6, null, null, "Ivanovich"));
      add(createPersonByParam(7, "Ivan", null, null));
      add(createPersonByParam(8, null, null, null));
    }};
    Map<Integer, String> idToPersonFullName1 = new HashMap<>() {{
      put(1, "Ivanov Ivan Ivanovich");
      put(2, "Ivanov Ivanovich");
      put(3, "Ivan Ivanovich");
      put(4, "Ivanov Ivan");
      put(5, "Ivanov");
      put(6, "Ivanovich");
      put(7, "Ivan");
      put(8, "");
    }};
    Person person1 = createPersonByParam(1, "Ivan", "Ivanov", "Ivanovich");
    Person person2 = createPersonByParam(2, null, "Ivanov", "Ivanovich");
    return Stream.of(
        Arguments.of(persons1, idToPersonFullName1),
        Arguments.of(new ArrayList<>(), new HashMap<>()),
        Arguments.arguments(Set.of(person1, person2), Map.of(1, "Ivanov Ivan Ivanovich", 2, "Ivanov Ivanovich"))
    );
  }

  private static Stream<Arguments> generateDataForCountEven() {
    return Stream.of(
        Arguments.of(Stream.of(), 0L),
        Arguments.of(Stream.of(3, 1, 2, -2, -2), 3L),
        Arguments.of(Stream.of(1, 11, 111, -1111, -11111), 0L),
        Arguments.of(Stream.of(5, 4, 3, 2, 1), 2L),
        Arguments.of(Stream.of(12, 2, 34, 42, -18), 5L)
    );
  }

  private static Stream<Arguments> generateDataForHasSamePersonsResultTrue() {
    return Stream.of(
        Arguments.of(createPersons(Stream.of(1, 2, 3, 4, 5, 6)), createPersons(Stream.of(6, 7, 8, 9))),
        Arguments.of(createPersons(Stream.of(1, 2, 3, 4, 5, 6)), createPersons(Stream.of(1, 2, 3, 4, 5, 6)))
    );
  }

  private static Stream<Arguments> generateDataForHasSamePersonsResultFalse() {
    return Stream.of(
        Arguments.of(createPersons(Stream.of(1, 2, 3, 4, 5, 6)), createPersons(Stream.of(7, 8, 9))),
        Arguments.of(createPersons(Stream.of(1, 2, 3, 4, 5, 6)), createPersons(Stream.of())),
        Arguments.of(createPersons(Stream.of()), createPersons(Stream.of(1, 2, 3, 4, 5, 6))),
        Arguments.of(createPersons(Stream.of()), createPersons(Stream.of()))
    );
  }

  private static List<String> getNames(Stream<Integer> integers) {
    return integers
        .map(i -> "firstName" + i)
        .collect(Collectors.toList());
  }

  private static Collection<Person> createPersons(Stream<Integer> integers) {
    return integers
        .map(Task8Test::createPerson)
        .collect(Collectors.toList());
  }

  private static Person createPerson(Integer id) {
    return new Person(id,
        "firstName" + id,
        "secondName" + id,
        "middleName" + id,
        now.plusMillis(id));
  }

  private static Person createPersonByParam(Integer id, String firstName, String secondName, String middleName) {
    return new Person(id, firstName, secondName, middleName, now.plusMillis(id));
  }

}