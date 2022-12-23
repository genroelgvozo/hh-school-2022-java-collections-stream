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
  // удаляем за ненадобностью
//  private long count;
  private final static long QUANTITY_FAKE_PERSON = 1L;

  //Не хотим выдавать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
//    if (persons.size() == 0) {
//      return Collections.emptyList();
//    }
//    persons.remove(0);
//    метод skip с параметром 1 заменит логику выше
    return persons.stream()
        .skip(QUANTITY_FAKE_PERSON)
        .map(Person::getFirstName)
        .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
//    метод distinct не требуется, так как собираем в Set
//    в подобном случае удобно использовать конструктор HashSet(Collection<? extends E> c)
//    return getNames(persons).stream().distinct().collect(Collectors.toSet());
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
//    String result = "";
//    if (person.getSecondName() != null) {
//      result += person.getSecondName();
//    }
//
//    if (person.getFirstName() != null) {
//      result += " " + person.getFirstName();
//    }
//    опечатка тут нужен заменить SecondName -> MiddleName
//    if (person.getSecondName() != null) {
//      result += " " + person.getSecondName();
//    }
//    return result;
//    удобно переписать на стримы избежим дублирование if-в и опечаток.
    return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
        .filter(Objects::nonNull)
        .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
//    Map<Integer, String> map = new HashMap<>(1);(initialCapacity = 1) - это шутка, так лучше не делать.
//    Если в друг нужна map с одним элементом делаем так Collections.singletonMap(1, "1")
//    for (Person person : persons) {
//      if не имеет смысла, если будут повторятся персоны метод put перепишет значение,
//      а так как все кладём в HashMap порядок в котором мы кладем будет нарушен всё равно
//      if (!map.containsKey(person.getId())) {
//        map.put(person.getId(), convertPersonToString(person));
//      }
//    }
//    return map;
//    Удобно метод выше переписать на стримы и с initialCapacity java сама разберется))
    return persons.stream().collect(Collectors.toMap(Person::getId, this::convertPersonToString));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  //1) перешли от асимптотики O(n^2) к асимптотике O(n)
  //2) повысили читаемость кода
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
//    boolean has = false;
//    for (Person person1 : persons1) {
//      for (Person person2 : persons2) {
//        if (person1.equals(person2)) {
//          has = true;
//        }
//      }
//    }
//    return has;
    Set<Person> persons = new HashSet<>(persons1);
    return persons2.stream().anyMatch(persons::contains);
  }

  //...
  public long countEven(Stream<Integer> numbers) {
//    count = 0; - избавляемся от лишней переменной и удаляем поле из класса за ненадобностью
//    numbers.filter(num -> num % 2 == 0).forEach(num -> count++); заменяем подсчет в цикле терминальной функцией count() из коробки
//    return count;
    return numbers.filter(num -> num % 2 == 0).count();
  }
}
