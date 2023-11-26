package edu.hw7.Task3_5;

import java.util.List;

interface PersonDatabaseWithReadWriteLock {
    void add(edu.hw7.Task3_5.Person person);

    void delete(int id);

    List<edu.hw7.Task3_5.Person> findByName(String name);

    List<edu.hw7.Task3_5.Person> findByAddress(String address);

    List<edu.hw7.Task3_5.Person> findByPhone(String phone);
}
