package edu.hw2;

import static org.assertj.core.api.Assertions.assertThat;

import edu.hw2.Task4.FunctionCaller;
import edu.hw2.Task4.FunctionCaller.CallingInfo;
import org.junit.jupiter.api.Test;

public class Task4Test {

    @Test
    void callingInfoShouldReturnCorrectClassNameAndMethodName() {
        CallingInfo callingInfo = FunctionCaller.callingInfo();

        String expectedClassName = Task4Test.class.getName();
        String expectedMethodName = "callingInfoShouldReturnCorrectClassNameAndMethodName";

        assertThat(callingInfo.className()).isEqualTo(expectedClassName);
        assertThat(callingInfo.methodName()).isEqualTo(expectedMethodName);
    }
}
