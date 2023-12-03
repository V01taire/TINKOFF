package edu.hw7.Task3_5Test;

import edu.hw7.Task3_5.Person;
import edu.hw7.Task3_5.PersonCachingServiceWithReadWriteLock;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonCachingServiceWithReadWriteLockTest {

    @Test
    void testAddPerson() {
        PersonCachingServiceWithReadWriteLock service = new PersonCachingServiceWithReadWriteLock();
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
        PersonCachingServiceWithReadWriteLock service = new PersonCachingServiceWithReadWriteLock();
        Person person = new Person(1, "John", "123 Main St", "555-1234");
        service.add(person);

        service.delete(1);

        List<Person> foundByName = service.findByName("John");
        List<Person> foundByAddress = service.findByAddress("123 Main St");
        List<Person> foundByPhone = service.findByPhone("555-1234");

        assertTrue(foundByName.isEmpty());
        assertTrue(foundByAddress.isEmpty());
        assertTrue(foundByPhone.isEmpty());
    }

    @Test
    void testFindByName() {
        PersonCachingServiceWithReadWriteLock service = new PersonCachingServiceWithReadWriteLock();
        Person person1 = new Person(1, "John", "123 Main St", "555-1234");
        Person person2 = new Person(2, "John", "456 Oak St", "555-5678");
        service.add(person1);
        service.add(person2);

        List<Person> foundByName = service.findByName("John");

        assertEquals(2, foundByName.size());
        assertTrue(foundByName.contains(person1));
        assertTrue(foundByName.contains(person2));
    }

    @Test
    void testFindByAddress() {
        PersonCachingServiceWithReadWriteLock service = new PersonCachingServiceWithReadWriteLock();
        Person person1 = new Person(1, "John", "123 Main St", "555-1234");
        Person person2 = new Person(2, "Jane", "123 Main St", "555-5678");
        service.add(person1);
        service.add(person2);

        List<Person> foundByAddress = service.findByAddress("123 Main St");

        assertEquals(2, foundByAddress.size());
        assertTrue(foundByAddress.contains(person1));
        assertTrue(foundByAddress.contains(person2));
    }

    @Test
    void testFindByPhone() {
        PersonCachingServiceWithReadWriteLock service = new PersonCachingServiceWithReadWriteLock();
        Person person1 = new Person(1, "John", "123 Main St", "555-1234");
        Person person2 = new Person(2, "Jane", "456 Oak St", "555-1234");
        service.add(person1);
        service.add(person2);

        List<Person> foundByPhone = service.findByPhone("555-1234");

        assertEquals(2, foundByPhone.size());
        assertTrue(foundByPhone.contains(person1));
        assertTrue(foundByPhone.contains(person2));
    }
}
