package tasks;

import common.Person;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 {

  public static List<Person> sort(Collection<Person> persons) {

    return persons.stream()
            .sorted(Comparator
                    .comparing(Person::getSecondName)
                    .thenComparing(Person::getSecondName)
                    .thenComparing(Person::getCreatedAt))
            .toList();
  }
}

// С кастомным компаратором было интереснее)
//class PersonsComparator implements Comparator<Person> {
//
//  @Override
//  public int compare(Person a, Person b) {
//    if (a.getFirstName().equals(b.getFirstName())) {
//      if (a.getSecondName().equals(b.getSecondName())) {
//        return a.getCreatedAt().compareTo(b.getCreatedAt());
//      }
//      return a.getSecondName().compareTo(b.getSecondName());
//    }
//    return a.getFirstName().compareTo(b.getSecondName());
//  }
//}