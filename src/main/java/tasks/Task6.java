package tasks;

import common.Area;
import common.Person;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task6 {

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    Map<Integer, Area> areaHashMap = areas.stream()
            .collect(Collectors.toMap(Area::getId, Function.identity()));
    return persons
            .stream()
            .flatMap(person -> personAreaIds.get(person.getId()).stream()
                    .map(id -> (person.getFirstName() + " - " + areaHashMap.get(id).getName())))
            .collect(Collectors.toSet());
  }
}
