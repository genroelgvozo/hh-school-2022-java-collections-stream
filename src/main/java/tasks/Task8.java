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
  // Исправил: проверка списка через метод isEmpty(), добавил тернарный оператор, добавил skip в стрим.
  public List<String> getNames(List<Person> persons) {
    return (persons.isEmpty()) ? Collections.emptyList() : persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .toList();
  }

  //ну и различные имена тоже хочется
  //Исправил: создание сэта без стримов
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  // Исправил: использовал StringBuilder вместо контекации строк,
  // Optional вместо проверки на null, заменил повторное использование имени на отчество
  public String convertPersonToString(Person person) {
    StringBuilder stringBuilder = new StringBuilder();
    Optional.ofNullable(person.getSecondName()).ifPresent(stringBuilder::append);
    Optional.ofNullable(person.getFirstName()).ifPresent(name -> stringBuilder.append(" ").append(name));
    Optional.ofNullable(person.getMiddleName()).ifPresent(name -> stringBuilder.append(" ").append(name));
    return stringBuilder.toString();
  }

  // словарь id персоны -> ее имя
  // Исправил: переписал на стримы
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream()
            .collect(Collectors.toMap(Person::getId, this::convertPersonToString));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  // Исправил: использовал метод коллекций retainAll
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    persons1.retainAll(persons2);
    return !persons1.isEmpty();
  }

  //...
  // Исправил: использовал метод count в стримах.
  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(number -> number % 2 == 0).count();
  }
}
