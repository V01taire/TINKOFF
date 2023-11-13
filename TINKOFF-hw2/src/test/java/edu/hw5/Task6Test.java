package edu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {

    @Test
    @DisplayName("Проверка наличия подпоследовательности")
    void isSubsequence() {
        // given
        String s = "abc";
        String t = "achfdbaabgabcaabg";

        // when & then
        assertThat(Task6.isSubsequence(s, t)).isTrue();  // s является подпоследовательностью t
    }

    @Test
    @DisplayName("Проверка отсутствия подпоследовательности")
    void isNotSubsequence() {
        // given
        String s = "xyz";
        String t = "achfdbaabgabcaabg";

        // when & then
        assertThat(Task6.isSubsequence(s, t)).isFalse();  // s не является подпоследовательностью t
    }
}
