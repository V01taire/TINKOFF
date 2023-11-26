package edu.hw3;

import edu.hw3.Task1.Task1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {

    @Test
    @DisplayName("Тест шифрования строки с использованием шифра Атбаш")
    void testAtbashCipher() {
        // given
        String originalString = "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";
        String expectedCipheredString = "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";

        // when
        String cipheredString = Task1.atbashCipher(originalString);

        // then
        assertEquals(expectedCipheredString, cipheredString);
    }

    @Test
    @DisplayName("Тест шифрования строки с использованием шифра Атбаш (с большими буквами)")
    void testAtbashCipherWithUppercase() {
        // given
        String originalString = "Hello World!";
        String expectedCipheredString = "Svool Dliow!";

        // when
        String cipheredString = Task1.atbashCipher(originalString);

        // then
        assertEquals(expectedCipheredString, cipheredString);
    }

    @Test
    @DisplayName("Тест шифрования строки с не латинскими символами")
    void testAtbashCipherWithNonLatinCharacters() {
        // given
        String originalString = "Hello, world! 123";
        String expectedCipheredString = "Svool, dliow! 123";

        // when
        String cipheredString = Task1.atbashCipher(originalString);

        // then
        assertEquals(expectedCipheredString, cipheredString);
    }
}

