package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task8 {
    public List<String> getRealNames(List<Person> persons) {
        if (persons == null) {
            return List.of();
        }
        return persons.stream()
                .map(Person::getFirstName)
                .skip(1)
                .toList();
    }

    public Set<String> getUniqueNames(List<Person> persons) {
        return new HashSet<>(getRealNames(persons));
    }

    public String getFullNameOfPerson(Person person) {
        if (person == null) {
            return "";
        }
        StringBuilder fullName = new StringBuilder();

        appendNameIfNotNullWithSpace(person.getFirstName(), fullName);
        appendNameIfNotNullWithSpace(person.getMiddleName(), fullName);
        appendNameIfNotNullWithSpace(person.getSecondName(), fullName);

        return fullName.toString();
    }

    private void appendNameIfNotNullWithSpace(String name, StringBuilder fullName) {
        if (name != null) {
            if (!fullName.isEmpty()) {
                fullName.append(" ");
            }
            fullName.append(name);
        }
    }

    public Map<Integer, String> getMapIdWithPersonNames(Collection<Person> persons) {
        if (persons == null) {
            return Map.of();
        }

        return persons.stream()
                .collect(
                        Collectors.toMap(
                                Person::getId,
                                this::getFullNameOfPerson,
                                (person1, person2) -> person1)
                );
    }

    public boolean hasSomePersonInTwoCollection(Collection<Person> persons1, Collection<Person> persons2) {
        if (persons1 == null || persons2 == null) {
            return false;
        }
        return !Collections.disjoint(persons1, persons2);
    }

    public long countEvenElements(Stream<Integer> numbers) {
        return numbers.filter(num -> num % 2 == 0).count();
    }
}
