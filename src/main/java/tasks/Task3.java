package tasks;

import common.Person;
import java.util.Collection;
import java.util.Comparator;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsFirst;
import java.util.List;
import java.util.stream.Collectors;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 {

  public static List<Person> sort(Collection<Person> persons) {
    return persons.stream()
        .sorted(Comparator.comparing(Person::getSecondName, nullsFirst(naturalOrder()))
            .thenComparing(Person::getFirstName, nullsFirst(naturalOrder()))
            .thenComparing(Person::getCreatedAt))
        .collect(Collectors.toList());
  }

  // тут вроде никто сразу не использовал nullsFirst/Last, либо не ожидали null, либо не ожидали что comparing не справится
  // с чего вдруг null в полях? ну во-первых никто не утверждал что они не null (аннотацией например))
  // во-вторых в 8 задаче в одном методе на null проверялись эти же поля, это был очень тонкий намек))
  // createdAt без проверки - потому что это скорее системное поле, и оно должно быть всегда
}
