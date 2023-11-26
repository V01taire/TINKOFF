package edu.hw7.Task3_5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonCachingServiceWithReadWriteLock implements PersonDatabaseWithReadWriteLock {

    private final List<Person> persons = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            persons.add(person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            persons.removeIf(person -> person.id() == id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return persons.stream()
                .filter(person -> person.name().equals(name))
                .toList();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return persons.stream()
                .filter(person -> person.address().equals(address))
                .toList();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return persons.stream()
                .filter(person -> person.phoneNumber().equals(phone))
                .toList();
        } finally {
            lock.readLock().unlock();
        }
    }
}

