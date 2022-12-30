package tasks;

import common.Person;
import common.PersonService;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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
    Map<Integer, Person> idPersonMap = personService.findPersons(personIds).stream()
        .collect(Collectors.toMap(Person::getId, Function.identity()));
    return personIds.stream()
        .map(idPersonMap::get)
        .filter(Objects::nonNull) // кандидат может не найтись
        .collect(Collectors.toList());
  }

  // тут вроде все доходили в итоге до решения, поясню лишь оценку решения через сортировку
  // многие писали сначала так:
  public List<Person> badFindOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = personService.findPersons(personIds);
    return persons.stream()
        .sorted(Comparator.comparing(person -> personIds.indexOf(person.getId())))
        .collect(Collectors.toList());
  }
  // и думали что сортировка это O(n log n) - но это не так, сортировка действительно занимает n log n СРАВНЕНИЙ
  // а у вас одно сравнение это не O(1), это целая операция indexOf, которая стоит O(n)
  // получается O(n^2 log n)

  // когда такая задача вылезает? когда на сортировку данных влияет иная система, чем та, в которой данные лежат
  // например - в базе есть кандидаты, но поиск устроен через elasticsearch (потому что там крутые индексы, нечеткий поиск и все такое)
  // и мы из эластика получаем айди кандидатов, которые нашлись, в нужной сортировке (от самого подходящего).
  // Нам нужно лишь обогатить данными из базы для отображения, но она то выдает в своем порядке, наш она не знает

  // вопрсо - зачем фильтр по nonNull? в примере с эластиком очень просто - индексация может иметь лаг, и кандидата могли удалить из системы,
  // но из индекса он еще не пропал, поэтому фильтруем
}
