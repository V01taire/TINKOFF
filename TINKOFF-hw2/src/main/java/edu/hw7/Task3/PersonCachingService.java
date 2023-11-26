package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PersonCachingService implements PersonDatabase {

    private final ConcurrentHashMap<Integer, Person> idToPersonMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, List<Person>> nameIndex = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, List<Person>> addressIndex = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, List<Person>> phoneIndex = new ConcurrentHashMap<>();

    @Override
    public void add(Person person) {
        idToPersonMap.put(person.id(), person);

        addToIndex(nameIndex, person.name(), person);
        addToIndex(addressIndex, person.address(), person);
        addToIndex(phoneIndex, person.phoneNumber(), person);
    }

    @Override
    public void delete(int id) {
        Person person = idToPersonMap.remove(id);
        if (person != null) {
            removeFromIndex(nameIndex, person.name(), person);
            removeFromIndex(addressIndex, person.address(), person);
            removeFromIndex(phoneIndex, person.phoneNumber(), person);
        }
    }

    @Override
    public List<Person> findByName(String name) {
        return nameIndex.getOrDefault(name, new ArrayList<>());
    }

    @Override
    public List<Person> findByAddress(String address) {
        return addressIndex.getOrDefault(address, new ArrayList<>());
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return phoneIndex.getOrDefault(phone, new ArrayList<>());
    }

    private void addToIndex(ConcurrentHashMap<String, List<Person>> index, String key, Person person) {
        index.computeIfAbsent(key, k -> new ArrayList<>()).add(person);
    }

    private void removeFromIndex(ConcurrentHashMap<String, List<Person>> index, String key, Person person) {
        index.computeIfPresent(key, (k, v) -> {
            v.remove(person);
            return v.isEmpty() ? null : v;
        });
    }
}

