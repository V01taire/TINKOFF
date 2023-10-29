package edu.hw3;


import edu.hw3.Task8.BackwardIterator;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BackwardIteratorTest {

    @Test
    void testBackwardIterator() {
        List<Integer> numbers = List.of(1, 2, 3);
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(numbers);

        assertEquals(3, backwardIterator.next());
        assertEquals(2, backwardIterator.next());
        assertEquals(1, backwardIterator.next());
    }

    @Test
    void testBackwardIteratorWithEmptyList() {
        List<Integer> emptyList = List.of();
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(emptyList);

        // Ensure there are no elements in an empty list
        assertEquals(false, backwardIterator.hasNext());
    }

    @Test
    void testBackwardIteratorWithSingleElement() {
        List<Integer> singleElementList = List.of(42);
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(singleElementList);

        assertEquals(42, backwardIterator.next());
    }
}
