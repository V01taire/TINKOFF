package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    static Stream<Arguments> rectangles() {
        return Stream.of(
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        );
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rectangle) {

        rectangle = rectangle.setWidth(20);
        rectangle = rectangle.setHeight(10);

        assertThat(rectangle.area()).isEqualTo(200.0);
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleWithSameWidthAndHeight(Rectangle rectangle) {

        String result = "class edu.hw2.Task2.Square";

        rectangle = rectangle.setWidth(20);
        rectangle = rectangle.setHeight(20);

        assertThat(rectangle.getClass().toString())
            .isEqualTo(result);
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleWithDifferentWidthAndHeight(Rectangle rectangle) {

        String result = "class edu.hw2.Task2.Rectangle";

        rectangle = rectangle.setWidth(20);
        rectangle = rectangle.setHeight(10);

        assertThat(rectangle.getClass().toString())
            .isEqualTo(result);
    }
}
