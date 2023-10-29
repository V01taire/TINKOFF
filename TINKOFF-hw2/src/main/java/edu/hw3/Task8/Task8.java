package edu.hw3.Task8;

import edu.hw3.Task4.Task4;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task8 {
    private static final Logger LOGGER = LogManager.getLogger(Task4.class);
    private static final int MAX_NUMBER = 3;


    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, MAX_NUMBER);
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(numbers);

        while (backwardIterator.hasNext()) {
            LOGGER.info("Processing next element");
            backwardIterator.next();
        }
    }
}
