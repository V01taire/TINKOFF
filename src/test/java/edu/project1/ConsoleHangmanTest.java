package edu.project1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConsoleHangmanTest {
    @Test
    @DisplayName("Игра можно начать заново после проигрыша")
    void canRestartGameAfterLosing() {
        InputStream originalSystemIn = System.in;
        String input = "a\na\na\na\na\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Dictionary dictionary = new ConsoleDictionary();
        ConsoleHangman consoleHangman = new ConsoleHangman(dictionary);
        consoleHangman.run();

        System.setIn(originalSystemIn);
    }

    @Test
    @DisplayName("Нельзя ввести больше одной буквы за раз")
    void cannotEnterMoreThanOneLetterAtATime() {
        InputStream originalSystemIn = System.in;
        String input = "ab\na\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Dictionary dictionary = new ConsoleDictionary();
        ConsoleHangman consoleHangman = new ConsoleHangman(dictionary);
        consoleHangman.run();

        System.setIn(originalSystemIn);
    }

    @Test
    @DisplayName("При 5 неугадываниях проигрыш")
    void loseGameAfterFiveMisses() {
        InputStream originalSystemIn = System.in;
        String input = "a\nb\nc\nd\ne\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Dictionary dictionary = mock(Dictionary.class);
        when(dictionary.randomWord()).thenReturn("test");

        ConsoleHangman consoleHangman = new ConsoleHangman(dictionary);
        consoleHangman.run();

        System.setIn(originalSystemIn);
    }

    @Test
    @DisplayName("Победа при угадывании всех букв")
    void winGameWhenGuessingAllLetters() {
        Session session = new Session("word", 5);

        session.guess('w');
        session.guess('o');
        session.guess('r');
        session.guess('d');

        GuessResult result = session.guess('x');

        assertThat(result).isInstanceOf(GuessResult.Win.class);
        assertThat(new String(result.state())).isEqualTo("word");
        assertThat(result.message()).isEqualTo("You won! Congratulations! Number of attempts: 4");
    }
}


