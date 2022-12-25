package tasks;

import common.Person;
import common.PersonService;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {
    Map<Integer, Person> personMap = personService.findPersons(personIds)
            .stream()
            .collect(Collectors.toMap(Person::getId, Function.identity()));
    return personIds.stream().map(personMap::get).toList();
  }
}
