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
    if (persons.size() < 2) {
      return Collections.emptyList();
    }
    return persons.stream().skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toList()); //Метод skip() вместо удаления из List ускорит выполнение метода и сделает его более понятным. Создаем stream, только если будет не пустой вывод
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons)); // Конструктор HashSet и так гарантирует уникальность объектов
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    return  Stream.of(person.getFirstName(),person.getMiddleName(),person.getSecondName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));  //добален MiddleName, убран лишний SecondName, укорочена запись с помощью Stream
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream()
            .collect(Collectors.toMap(Person::getId, this::convertPersonToString,
                    (oldVal, newVal) -> oldVal)); //укорочена запись с помощью Stream. оставил изначальный вариант, с оставлением первого пришедшего значения, в случае совпадения Id
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    Set<Person> notDuplicate = new HashSet<>();
    return Stream.concat(persons1.stream(), persons2.stream())
            .filter(n -> !notDuplicate.add(n))
            .collect(Collectors.toSet())
            .size() > 0;  //Укорочена запись. Время работы метода O(n)
  }

  //...
  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0).count();  //Укорочена запись
  }
}
