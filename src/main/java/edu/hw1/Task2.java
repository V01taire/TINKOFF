package edu.hw1;

public class Task2 {

    private static final int DIGITS = 10;

    public int countDigits(int number) {

        int numberToCountDigits = number;

        // Если число отрицательное, сделаем его положительным для удобства подсчета цифр
        if (numberToCountDigits < 0) {
            numberToCountDigits = -numberToCountDigits;
        }

        // Если число меньше 10, оно состоит из одной цифры
        if (numberToCountDigits < DIGITS) {
            return 1;
        }

        // Используем свойство десятичного логарифма для определения количества цифр в числе
        // Преобразуем результат в int, так как мы ищем количество цифр, а не точное значение логарифма
        return (int) (Math.log10(numberToCountDigits) + 1);
    }
}
