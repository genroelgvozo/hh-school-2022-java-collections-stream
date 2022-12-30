package tasks;

import common.Person;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Задача 2
На вход принимаются две коллекции объектов Person и величина limit
Необходимо объеденить обе коллекции
отсортировать персоны по дате создания и выдать первые limit штук.
 */
public class Task2 {

  public static List<Person> combineAndSortWithLimit(Collection<Person> persons1,
                                                     Collection<Person> persons2,
                                                     int limit) {
    return Stream.concat(persons1.stream(), persons2.stream())
        .sorted(Comparator.comparing(Person::getCreatedAt))
        .limit(limit)
        .collect(Collectors.toList());
  }
  // тут тоже мало проблем было, пример задачи - общая пагинация в данных, получаемых из разных сервисов
  // например в талантиксе есть вкладка с комментариями, где еще присутствуют оценки кандидатов и уведомления о встречах
  // таким образом в каждый сервис ходим с pageSize, потом все сортируем и обрезаем - получается такая вот страница
}
