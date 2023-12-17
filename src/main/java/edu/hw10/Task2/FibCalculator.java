package edu.hw10.Task2;

interface FibCalculator {
    @Cache(persist = true)
    long fib(int number);

    default long publicFib(int number) {
        return fib(number);
    }

    String toString();
}
