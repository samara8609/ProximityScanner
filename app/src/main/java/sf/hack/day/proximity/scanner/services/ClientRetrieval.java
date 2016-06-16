package sf.hack.day.proximity.scanner.services;

import java.util.HashMap;
import java.util.Map;
import sf.hack.day.proximity.scanner.model.Person;
import sf.hack.day.proximity.scanner.model.Name;
import sf.hack.day.proximity.scanner.model.Address;

/**
 * Created by aaron on 6/16/16.
 */

public final class ClientRetrieval {
    private static final Map<String, Person> people = new HashMap<>();

    static {
        people.put("968A3179", createIvan());
        people.put("4626BC6D", createAmber());
    }

    private ClientRetrieval() {}

    public static Person retrieve(String UUID) {
        return people.get(UUID);
    }

    private static Person createIvan() {
        Person person = new Person();
        Name name = new Name();
        name.first = "Ivan";
        name.last = "Hall";
        person.name = name;

        Address address = new Address();
        address.address1 = "1 Boulder Dr.";
        address.city = "Carlock";
        address.state = "IL";
        address.zip = "61725";
        person.address = address;

        return person;
    }

    private static Person createAmber() {
        Person person = new Person();
        Name name = new Name();
        name.first = "Ivan";
        name.last = "Hall";
        person.name = name;

        Address address = new Address();
        address.address1 = "1 Happy Way";
        address.city = "Downs";
        address.state = "IL";
        address.zip = "61703";
        person.address = address;

        return person;

    }
}
