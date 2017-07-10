package com.noveogroup.university_android_task2.data;

import com.noveogroup.university_android_task2.data.model.Gender;
import com.noveogroup.university_android_task2.data.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonProvider {
    private static PersonProvider INSTANCE = new PersonProvider();
    private final static List<String> POSSIBLE_NAMES = new ArrayList<>();
    private final Random random;

    static {
        POSSIBLE_NAMES.add("Tyrion");
        POSSIBLE_NAMES.add("Jamie");
        POSSIBLE_NAMES.add("Jon");
        POSSIBLE_NAMES.add("Joffrey");
        POSSIBLE_NAMES.add("Drogo");
        POSSIBLE_NAMES.add("Eddard");
        POSSIBLE_NAMES.add("Cersei");
        POSSIBLE_NAMES.add("Daenerys");
        POSSIBLE_NAMES.add("Sansa");
        POSSIBLE_NAMES.add("Arya");
        POSSIBLE_NAMES.add("Catelyn");
        POSSIBLE_NAMES.add("Nymeria");
    }

    public static PersonProvider getInstance() {
        return INSTANCE;
    }

    private PersonProvider() {
        random = new Random();
    }

    public Person getPerson() {
        final String name = POSSIBLE_NAMES.get(random.nextInt(POSSIBLE_NAMES.size()));
        final int age = random.nextInt(30);
        final Gender gender = random.nextBoolean() ? Gender.MALE : Gender.FEMALE;
        return new Person(name, age, gender);
    }

    public List<Person> getPersonsList() {
        final List<Person> result = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            result.add(getPerson());
        }

        return result;
    }
}
