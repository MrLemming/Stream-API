import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Collection<Person> dataSource = generationPersons();

        int quantityOfTeenagers = (int) dataSource.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        List<String> listOfConscripts = dataSource.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        List<Person> listOfHumanResources = dataSource.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> (person.getSex() == Sex.MAN && person.getAge() < 65) || (person.getSex() == Sex.WOMAN && person.getAge() < 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }

    public static Collection<Person> generationPersons() {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        return persons;
    }
}
