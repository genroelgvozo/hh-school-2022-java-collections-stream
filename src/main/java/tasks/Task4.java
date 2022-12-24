package tasks;

import common.ApiPersonDto;
import common.Person;
import common.PersonConverter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
Задача 4
Список персон класса Person необходимо конвертировать в список ApiPersonDto
(предположим, что это некоторый внешний формат)
Конвертер для одной персоны - personConverter.convert()
FYI - DTO = Data Transfer Object - распространенный паттерн, можно по гуглить
 */
public class Task4 {

  private final PersonConverter personConverter;

  public Task4(PersonConverter personConverter) {
    this.personConverter = personConverter;
  }

  public List<ApiPersonDto> convert(List<Person> persons) {
    return persons.stream()
        .filter(Objects::nonNull)
        .map(personConverter::convert)
        .collect(Collectors.toList());
  }
}
