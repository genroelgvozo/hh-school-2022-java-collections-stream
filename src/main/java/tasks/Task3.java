package tasks;

import common.Person;
import java.util.Collection;
import java.util.List;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии,
по имени (при равной фамилии),
и по дате создания (при равных фамилии и имени)
 */
public class Task3 {

  public static List<Person> sort(Collection<Person> persons) {
    return persons.stream().sorted((person1, person2) -> {
      if (!person1.getSecondName().equals(person2.getSecondName())) {
        return person1.getSecondName().compareTo(person2.getSecondName());
      } else if (!person1.getFirstName().equals(person2.getFirstName())) {
        return person1.getFirstName().compareTo(person2.getFirstName());
      } else {
        return person1.getCreatedAt().compareTo(person2.getCreatedAt());
      }
    }).toList();
  }
}
