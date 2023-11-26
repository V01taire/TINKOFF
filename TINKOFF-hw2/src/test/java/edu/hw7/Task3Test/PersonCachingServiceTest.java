package edu.hw7.Task3Test;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonCachingService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonCachingServiceTest {

    @Test
    void testAddPerson() {
        PersonCachingService service = new PersonCachingService();
        Person person = new Person(1, "John", "123 Main St", "555-1234");

        service.add(person);

        List<Person> foundByName = service.findByName("John");
        List<Person> foundByAddress = service.findByAddress("123 Main St");
        List<Person> foundByPhone = service.findByPhone("555-1234");

        assertEquals(1, foundByName.size());
        assertEquals(1, foundByAddress.size());
        assertEquals(1, foundByPhone.size());

        assertEquals(person, foundByName.get(0));
        assertEquals(person, foundByAddress.get(0));
        assertEquals(person, foundByPhone.get(0));
    }

    @Test
    void testDeletePerson() {
        PersonCachingService service = new PersonCachingService();
        Person person = new Person(1, "John", "123 Main St", "555-1234");
        service.add(person);

        service.delete(1);

        List<Person> foundByName = service.findByName("John");
        List<Person> foundByAddress = service.findByAddress("123 Main St");
        List<Person> foundByPhone = service.findByPhone("555-1234");

        assertEquals(0, foundByName.size());
        assertEquals(0, foundByAddress.size());
        assertEquals(0, foundByPhone.size());
    }

    @Test
    void testFindByName() {
        PersonCachingService service = new PersonCachingService();
        Person person1 = new Person(1, "John", "123 Main St", "555-1234");
        Person person2 = new Person(2, "John", "456 Oak St", "555-5678");
        service.add(person1);
        service.add(person2);

        List<Person> foundByName = service.findByName("John");

        assertEquals(2, foundByName.size());
        assertEquals(person1, foundByName.get(0));
        assertEquals(person2, foundByName.get(1));
    }

    @Test
    void testFindByAddress() {
        PersonCachingService service = new PersonCachingService();
        Person person1 = new Person(1, "John", "123 Main St", "555-1234");
        Person person2 = new Person(2, "Jane", "123 Main St", "555-5678");
        service.add(person1);
        service.add(person2);

        List<Person> foundByAddress = service.findByAddress("123 Main St");

        assertEquals(2, foundByAddress.size());
        assertEquals(person1, foundByAddress.get(0));
        assertEquals(person2, foundByAddress.get(1));
    }

    @Test
    void testFindByPhone() {
        PersonCachingService service = new PersonCachingService();
        Person person1 = new Person(1, "John", "123 Main St", "555-1234");
        Person person2 = new Person(2, "Jane", "456 Oak St", "555-1234");
        service.add(person1);
        service.add(person2);

        List<Person> foundByPhone = service.findByPhone("555-1234");

        assertEquals(2, foundByPhone.size());
        assertEquals(person1, foundByPhone.get(0));
        assertEquals(person2, foundByPhone.get(1));
    }

    @Test
    void testAddNullPerson() {
        PersonCachingService service = new PersonCachingService();
        assertThrows(NullPointerException.class, () -> service.add(null));
    }

    @Test
    void testDeleteNonExistingPerson() {
        PersonCachingService service = new PersonCachingService();
        assertDoesNotThrow(() -> service.delete(1)); // Deleting a non-existing person should not throw an exception
    }
}

