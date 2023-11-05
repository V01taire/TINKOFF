package edu.hw3;

import edu.hw3.Task5.Task5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task5Test {

    @Test
    @DisplayName("Тест сортировки контактов в порядке возрастания фамилий")
    void testSortContactsAscending() {
        // given
        String[] names = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String[] expectedSortedNames = {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"};

        // when
        String[] sortedNames = Task5.parseContacts(names, "ASC");

        // then
        assertArrayEquals(expectedSortedNames, sortedNames);
    }

    @Test
    @DisplayName("Тест сортировки контактов в порядке убывания фамилий")
    void testSortContactsDescending() {
        // given
        String[] names = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String[] expectedSortedNames = {"John Locke", "David Hume", "Rene Descartes", "Thomas Aquinas"};

        // when
        String[] sortedNames = Task5.parseContacts(names, "DESC");

        // then
        assertArrayEquals(expectedSortedNames, sortedNames);
    }

    @Test
    @DisplayName("Тест сортировки пустого массива контактов")
    void testSortEmptyContactsArray() {
        // given
        String[] names = {};

        // when
        String[] sortedNames = Task5.parseContacts(names, "ASC");

        // then
        assertArrayEquals(new String[0], sortedNames);
    }

    @Test
    @DisplayName("Тест сортировки массива с одним контактом")
    void testSortSingleContactArray() {
        // given
        String[] names = {"John Locke"};
        String[] expectedSortedNames = {"John Locke"};

        // when
        String[] sortedNames = Task5.parseContacts(names, "ASC");

        // then
        assertArrayEquals(expectedSortedNames, sortedNames);
    }

    @Test
    @DisplayName("Тест сортировки массива с несколькими одинаковыми именами")
    void testSortContactsWithSameFirstName() {
        // given
        String[] names = {"Alice Johnson", "Alice Smith", "Alice Brown", "Alice Doe"};
        String[] expectedSortedNames = {"Alice Brown", "Alice Doe", "Alice Johnson", "Alice Smith"};

        // when
        String[] sortedNames = Task5.parseContacts(names, "ASC");

        // then
        assertArrayEquals(expectedSortedNames, sortedNames);
    }

    @Test
    @DisplayName("Тест сортировки массива с разным регистром букв")
    void testSortContactsWithDifferentCase() {
        // given
        String[] names = {"Alice Johnson", "Bob Smith", "alice Doe", "Charlie Brown"};
        String[] expectedSortedNames = {"Charlie Brown", "alice Doe", "Alice Johnson", "Bob Smith"};

        // when
        String[] sortedNames = Task5.parseContacts(names, "ASC");

        // then
        assertArrayEquals(expectedSortedNames, sortedNames);
    }
}

