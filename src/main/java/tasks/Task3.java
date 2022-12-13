package tasks;

import common.Person;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 {

  public static List<Person> sort(Collection<Person> persons) {
    Comparator<String> stringNullableComparator = Comparator.nullsLast(Comparator.naturalOrder());
    Comparator<Instant> instantNullableComparator = Comparator.nullsLast(Comparator.naturalOrder());
    return persons == null ? Collections.emptyList() : persons.stream()
            .sorted(Comparator.comparing(Person::getSecondName, stringNullableComparator)
                    .thenComparing(Person::getFirstName, stringNullableComparator)
                    .thenComparing(Person::getCreatedAt, instantNullableComparator)
            )
            .collect(Collectors.toList());
  }
}
