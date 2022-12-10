package tasks;

import common.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Task8Test {
private List<Person> allPersons;
private List<String> personNames;
private Task8 task8;

    @BeforeEach
    public void init() {
        task8 = new Task8();
    }

    @Test
    void getNamesSuccessWhenHavePersons() {
        int size = 5;
        personNames = generatePersonNames(size);
        allPersons = generatePersons(personNames, size);
        List<String> expectedPersonNames = allPersons.subList(1, allPersons.size()).stream()
                .map(Person::getFirstName)
                .collect(Collectors.toList());
        Task8 task8 = new Task8();
        assertEquals(expectedPersonNames, task8.getNames(allPersons));
    }

    @Test
    void getNamesEmptyWhenPersonListIsNullOrEmpty() {
        List<String> expectedPersonNames = new ArrayList<>();
        assertEquals(expectedPersonNames, task8.getNames(null));
        assertEquals(expectedPersonNames, task8.getNames(new ArrayList<>()));
    }

    private List<String> generatePersonNames(int size) {
        List<String> names = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            names.add("Oleg" + i);
        }
        return names;
    }

    private List<Person> generatePersons(List<String> personNames, int size) {
        return generatePersons(personNames, size, Instant.now());
    }

    private List<Person> generatePersons(List<String> personNames, int size, Instant instant) {
        List<Person> persons = new ArrayList<>(size);
        Iterator<String> personNameIterator = personNames.listIterator();
        for (int i = 0; i < size; i++) {
            String name = personNameIterator.hasNext() ? personNameIterator.next() : "Vasya";
            Person person = new Person(i, name, "Ivanov" + i, instant);
            persons.add(person);
        }
        return persons;
    }

    @Test
    void getDifferentNamesSuccess() {
        personNames = generatePersonNames(5);
        allPersons = generatePersons(personNames, 10);
        Set<String> personNamesExpected = allPersons.stream()
                .map(Person::getFirstName)
                .skip(1)
                .collect(Collectors.toSet());
        assertEquals(personNamesExpected, task8.getDifferentNames(allPersons));
    }

    @Test
    void getDifferentNamesSuccessWhenPersonListIsNull() {
        Set<String> personNamesExpected = Collections.emptySet();
        assertEquals(personNamesExpected, task8.getDifferentNames(null));
    }

    @Test
    void convertPersonToStringSuccessWhenHaveFullName() {
        String fullNamesExpected = "Backend Fronted Mobile";
        String [] names = fullNamesExpected.split(" ");
        Person person = new Person(1, names[1], names[0], names[2], Instant.now());
        assertEquals(fullNamesExpected, task8.convertPersonToString(person));
    }

    @Test
    void convertPersonToStringSuccessWhenNamesIsNull() {
        String fullNamesExpected = "";
        Person person = new Person(1, null, null, null, Instant.now());
        assertEquals(fullNamesExpected, task8.convertPersonToString(person));
    }

    @Test
    void convertPersonToStringSuccessWhenHaveNotFullName() {
        String name = "Oleg";
        String secondName = "Putin";
        String middleName = "Vladimirovich";
        Instant instant = Instant.now();
        Person person = new Person(1, name, null, middleName, instant);
        assertEquals(String.join(" ", name, middleName), task8.convertPersonToString(person));

        person = new Person(1, name, secondName, null, instant);
        assertEquals(String.join(" ", secondName, name), task8.convertPersonToString(person));

        person = new Person(1, null, secondName, middleName, instant);
        assertEquals(String.join(" ", secondName, middleName), task8.convertPersonToString(person));

        person = new Person(1, name, null, null, instant);
        assertEquals(name, task8.convertPersonToString(person));
    }

    @Test
    void getPersonNamesSuccessWhenPersonDifferent() {
        int size = 10;
        allPersons = generatePersons(generatePersonNames(size), size);
        Map<Integer, String> personNamesByIdExpected = allPersons.stream()
                .collect(Collectors.toMap(Person::getId, this::getFullName));
        assertEquals(personNamesByIdExpected, task8.getPersonNames(allPersons));
    }

    @Test
    void getPersonNamesSuccessWhenPersonHaveDuplicate() {
        int size = 3;
        allPersons = generatePersons(generatePersonNames(size), size);
        allPersons.addAll(generatePersons(generatePersonNames(size), size));
        Map<Integer, String> personNamesByIdExpected = allPersons.stream()
                .collect(Collectors.toMap(Person::getId, this::getFullName, (idOld, idNew) -> idOld));
        assertEquals(personNamesByIdExpected, task8.getPersonNames(allPersons));
    }

    @Test
    void getPersonNamesSuccessWhenPersonIsNull() {
        Map<Integer, String> personNamesByIdExpected = new HashMap<>();
        assertEquals(personNamesByIdExpected, task8.getPersonNames(null));
    }



    private String getFullName(Person person) {
        return task8.convertPersonToString(person);
    }

    @Test
    void hasSamePersonsFalseWhenPersonsListIsDifferent() {
        int size = 10;
        allPersons = generatePersons(generatePersonNames(size), size);
        List<Person> personsFirstPart = generatePersons(generatePersonNames(size), size);
        List<Person> personsSecondPart = generatePersons(generatePersonNames(size), size);
        assertFalse(task8.hasSamePersons(personsFirstPart, personsSecondPart));
    }

    @Test
    void hasSamePersonsFalseWhenAnyPersonsListIsNull() {
        int size = 10;
        allPersons = generatePersons(generatePersonNames(size), size);
        List<Person> personsFirstPart = generatePersons(generatePersonNames(size), size);
        List<Person> personsSecondPart = generatePersons(generatePersonNames(size), size);
        assertFalse(task8.hasSamePersons(null, personsSecondPart));

        assertFalse(task8.hasSamePersons(personsFirstPart, null));
    }

    @Test
    void hasSamePersonsTrueWhenPersonsListIsSame() {
        int size = 5;
        Instant instant = Instant.now();
        allPersons = generatePersons(generatePersonNames(size), size, instant);
        allPersons.addAll(generatePersons(generatePersonNames(size), size, instant));
        List<Person> personsFirstPart = allPersons.subList(0, allPersons.size() / 2);
        List<Person> personsSecondPart = allPersons.subList(allPersons.size() / 2 + 1, allPersons.size());
        assertTrue(task8.hasSamePersons(personsFirstPart, personsSecondPart));
    }

    @Test
    void countEvenSuccessWhenStreamHaveNaturalNumbers() {
        int count = 10;
        assertEquals(count / 2, task8.countEven(() -> Stream.iterate(0, x -> x +1).limit(count)));
    }

    @Test
    void countEvenSuccessWhenStreamHaveOneNumber() {
        int count = 1;
        assertEquals(0, task8.countEven(() -> Stream.iterate(1, x -> x +1).limit(count)));
    }

    @Test
    void countEvenFailWhenStreamAlreadyUsed() {
        int count = 10;
        Stream<Integer> integerStream = Stream.iterate(0, x -> x +1).limit(count);
        assertEquals(count / 2, task8.countEven(() -> integerStream));
        assertEquals(0, task8.countEven(() -> integerStream));
    }
}