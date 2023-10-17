package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    @ParameterizedTest
    @MethodSource("provideInputParameter")
    void isNestableTest(int[] firstArray, int[] secondArray, boolean expected) {

        boolean result = Task3.isNestable(firstArray, secondArray);

        assertThat(result)
                .isEqualTo(expected);
    }

    private static Stream<Arguments> provideInputParameter() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4}, new int[] {0, 6}, true),
                Arguments.of(new int[] {3, 1}, new int[] {4, 0}, true),
                Arguments.of(new int[] {9, 9, 8}, new int[] {8, 9}, false),
                Arguments.of(new int[] {1, 2, 3, 4}, new int[] {2, 3}, false),
                Arguments.of(new int[] {1, 2, 3}, new int[] {}, false),
                Arguments.of(new int[] {}, new int[] {1, 2, 3}, false),
                Arguments.of(new int[] {1}, new int[] {0, 3}, true),
                Arguments.of(new int[] {}, new int[] {2, 3}, false),
                Arguments.of(new int[] {1, 3}, new int[] {2}, false),
                Arguments.of(new int[] {}, new int[] {}, false)
        );
    }
}

