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
Оценить асимпотику работы
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {
    /*Так как в начале из множества persons создается словать
    с отображением id персоны в объект Person,
    то ассимптотика работы метода 0(n), точнее 0(2n).
    */
    Set<Person> persons = personService.findPersons(personIds);

    Map<Integer, Person> personToIdMap = persons.stream()
      .collect(Collectors.toMap(Person::getId, Function.identity()));

    return personIds.stream()
      .map(personToIdMap::get)
      .collect(Collectors.toList());
  }
}
