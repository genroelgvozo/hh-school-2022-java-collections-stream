package tasks;

import common.Area;
import common.Person;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    Map<Integer, Area> areaMap = areas.stream()
        .collect(Collectors.toMap(Area::getId, Function.identity()));
    return persons.stream()
        .filter(person -> personAreaIds.containsKey(person.getId()))
        .flatMap(person -> personAreaIds.get(person.getId()).stream()
            .map(areaId -> getPersonDescription(person, areaMap.get(areaId)))
        )
        .collect(Collectors.toSet());
  }

  private static String getPersonDescription(Person person, Area area) {
    // рекомендую инкапсулировать логику форматирования, и передавать целые объекты, а не поля, пускай метод сам решает какие поля ему нужны
    return "%s - %s".formatted(person.getFirstName(), area.getName());
  }

  // тут задача на flatMap и соединение данных из разных источников, особенно когда всякие 1 ко многим
}
