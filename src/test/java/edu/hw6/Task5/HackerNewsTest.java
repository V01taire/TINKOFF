package edu.hw6.Task5;

import java.io.IOException;
import edu.hw6.Task5.HackerNews;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HackerNewsTest {

    @Test
    void hackerNewsGetTopStoriesTest() {

        long[] result = HackerNews.hackerNewsTopStories();

        assertThat(result).isNotEmpty();
    }

    @Test
    void hackerNewsGet37570037StoryTest() throws IOException, InterruptedException {

        String result = HackerNews.news(37570037);

        assertThat(result).isEqualTo("JDK 21 Release Notes");
    }
}
