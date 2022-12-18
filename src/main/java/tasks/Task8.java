package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task8 {

  private long count;

  public List<String> getNames(List<Person> persons) {
    if (persons == null) {
      return Collections.emptyList();
    }
    if (persons.size() == 0) {
      return Collections.emptyList();
    }
    List<Person> personList = new ArrayList<>(persons);
    return personList
            .stream()
            .skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toList());
  }

  public Set<String> getDifferentNames(List<Person> persons) {
    if (persons == null) {
      return Collections.emptySet();
    }
    return new HashSet<>(getNames(persons));
  }

  public String convertPersonToString(Person person) {
    if (person == null) {
      return "";
    }
    return Stream.of(person.getSecondName(), person.getFirstName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }

  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    if (persons == null) {
      return Collections.emptyMap();
    }
    return persons
            .stream()
            .filter(person -> person.getFirstName() != null)
            .collect(Collectors.toMap(Person::getId, Person::getFirstName, (person1, person2) -> person1));
  }

  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    if (persons1 == null || persons2 == null) {
      return false;
    }
    HashSet<Person> tempPersonCollection = new HashSet<>(persons1);
    tempPersonCollection.retainAll(persons2);
    return !tempPersonCollection.isEmpty();
  }

  public long countEven(Stream<Integer> numbers) {
    if (numbers == null) {
      return 0;
    }
    return numbers.filter(num -> num % 2 == 0).count();
  }
}
