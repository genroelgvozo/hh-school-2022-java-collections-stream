package tasks;

import common.Person;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 {
  public static List<Person> sort(Collection<Person> persons) {
    if (persons == null) {
      return Collections.emptyList();
    } else {
      return persons
              .stream()
              .sorted(Comparator.comparing(Person::getFirstName, Comparator.nullsFirst(Comparator.naturalOrder()))
                      .thenComparing(Person::getSecondName, Comparator.nullsFirst(Comparator.naturalOrder()))
                      .thenComparing(Person::getCreatedAt, Comparator.nullsFirst(Comparator.naturalOrder())))
              .toList();
    }
  }
}
