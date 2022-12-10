package tasks;

import common.Person;

import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {

  private static final Logger log = Logger.getLogger(Task8.class.getName());

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    return persons == null
            ? Collections.emptyList()
            : persons.stream()
            .map(Person::getFirstName)
            .skip(1)
            .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons == null
            ? Collections.emptyMap()
            : persons.stream()
            .collect(Collectors.toMap(Person::getId, this::convertPersonToString, (a, b) -> a));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    return persons1 != null && persons2 != null && persons1.stream().anyMatch(new HashSet<>(persons2)::contains);
  }

  //...
  public long countEven(Supplier<Stream<Integer>> streamSupplier) {
    try {
      return streamSupplier.get().filter(num -> num % 2 == 0)
              .count();
    } catch (IllegalStateException e) {
      log.log(Level.WARNING, e.getMessage(), e);
      return 0;
    }
  }
}
