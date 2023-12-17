package edu.hw11.Task1Test;

import edu.hw11.Task1.ByteBuddyGenerator;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ByteBuddyGeneratorTest {

    @Test
    void testByteBuddy() throws InstantiationException, IllegalAccessException {
        Object object = ByteBuddyGenerator.byteBuddy.newInstance();

        assertThat(object.toString())
            .isEqualTo("Hello, ByteBuddy!");
    }
}
