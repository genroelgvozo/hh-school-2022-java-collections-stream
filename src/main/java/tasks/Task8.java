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

    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    return getNames(persons).stream().collect(Collectors.toSet());
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    String secondName = person.getSecondName();
    String firstName = person.getFirstName();
    String middleName = person.getMiddleName();

    String result = String.format("%s %s %s",
      secondName != null ? secondName : "",
      firstName != null ? firstName : "",
      middleName != null ? middleName : ""
    );

    return String.join(" ", result.trim().split("\\s+"));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    int initialCapacity = persons.size() + persons.size() / 2;

    Map<Integer, String> map = new HashMap<>(initialCapacity);
    for (Person person : persons) {
      if (!map.containsKey(person.getId())) {
        map.put(person.getId(), convertPersonToString(person));
      }
    }
    return map;
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    Set<Person> setPersons1 = new HashSet<>(persons1);
    Set<Person> setPersons2 = new HashSet<>(persons2);

    setPersons1.retainAll(setPersons2);
    if (setPersons1.size() == 0) {
      return false;
    }
    return true;
  }

  //...
  public long countEven(Stream<Integer> numbers) {
    count = numbers
      .filter(num -> num % 2 == 0)
      .count();
    return count;
  }
}
