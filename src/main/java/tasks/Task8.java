package tasks;

import common.Person;

import java.util.*;
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

  private long count;

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    if (persons.size() == 0) {
      return Collections.emptyList();
    }

    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .toList();
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    // return getNames(persons).stream().distinct().collect(Collectors.toSet());
    // distinct и toSet явно избыточны друг к другу, да и вообще давайте без стрима)
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    // а тут лучше стримы
    return Stream.of(person.getFirstName(), person.getSecondName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {

    return persons.stream()
            .collect(Collectors.toMap(Person::getId, this::convertPersonToString));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    // предварительная проверка на пустоту коллеций, затем стрим
    if (persons1 == null || persons2 == null) {
      return false;
    }
    return persons1.stream()
            .anyMatch(persons2::contains);
  }

  //...
  public long countEven(Stream<Integer> numbers) {
    // no comments...
    return numbers.filter(num -> num % 2 == 0).count();
  }
}
