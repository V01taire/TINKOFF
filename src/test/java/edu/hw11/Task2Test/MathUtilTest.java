package edu.hw11.Task2Test;

import edu.hw11.Task2.MathUtil;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MathUtilTest {

    public static int sum (int a, int b) {
        return a * b;
    }

    @ParameterizedTest
    @MethodSource("multiplyProvider")
    void testByteBuddyDelegate(int first, int second, int expected) {

        ByteBuddyAgent.install();

        new ByteBuddy()
            .redefine(MathUtil.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(MathUtilTest.class))
            .make()
            .load(
                MathUtil.class.getClassLoader(),
                ClassReloadingStrategy.fromInstalledAgent()
            );


        int result = MathUtil.sum(first, second);

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> multiplyProvider() {
        return Stream.of(
            Arguments.of(3, 3, 9),
            Arguments.of(10, 20, 200),
            Arguments.of(15, 15, 225)
        );
    }
}
