package tasks;

import common.Person;
import common.PersonService;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимптотику работы
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  //асимптотика O(f(n)) + O(n) + O(n) = O(f(n) + 2n)
  //если f(n)/n -> const при n -> infinity, тогда асимптотика равна O(n)
  //если f(n)/n -> infinity при n -> infinity, тогда асимптотика равна O(f(n))
  public List<Person> findOrderedPersons(List<Integer> personIds) {
    //асимптотика метода неизвестна - O(f(n))
    Set<Person> persons = personService.findPersons(personIds);
    if (Objects.isNull(persons)) {
      return Collections.emptyList();
    }
    Map<Integer, Person> idToPerson = persons.stream()
        .filter(Objects::nonNull)
        .collect(Collectors.toMap(Person::getId, Function.identity())); //O(n)
    return personIds.stream()
        .map(idToPerson::get)
        .collect(Collectors.toList()); //O(n)
  }

}
