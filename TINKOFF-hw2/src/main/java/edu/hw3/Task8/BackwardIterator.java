package edu.hw3.Task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BackwardIterator<T> implements Iterator<T> {
    private static final Logger LOGGER = LogManager.getLogger(BackwardIterator.class);
    private ListIterator<T> listIterator;

    public BackwardIterator(Collection<T> collection) {
        List<T> list = List.copyOf(collection);
        this.listIterator = list.listIterator(list.size());
    }

    @Override
    public boolean hasNext() {
        return listIterator.hasPrevious();
    }

    @Override
    public T next() {
        T element = listIterator.previous();
        LOGGER.info("Next element: " + element);
        return element;
    }
}
