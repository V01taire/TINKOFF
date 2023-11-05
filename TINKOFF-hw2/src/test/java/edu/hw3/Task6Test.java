package edu.hw3;


import edu.hw3.Task6.*;
import edu.hw3.Task6.Task6;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task6Test {

    private StockMarket stockMarket;

    @BeforeEach
    void setUp() {
        stockMarket = new Task6();
    }

    @Test
    @DisplayName("Тест добавления и получения самой ценной акции")
    void testAddAndMostValuableStock() {
        // тестовые данные
        Stock apple = new Stock("Apple", 150.0);
        Stock microsoft = new Stock("Microsoft", 200.0);
        Stock google = new Stock("Google", 180.0);

        // проверяем добавление акций и получение самой ценной акции
        stockMarket.add(apple);
        stockMarket.add(microsoft);
        stockMarket.add(google);
        assertEquals(microsoft, stockMarket.mostValuableStock());
    }

    @Test
    @DisplayName("Тест получения самой ценной акции из пустого рынка")
    void testMostValuableStockEmptyMarket() {
        // проверяем, что из пустого рынка возвращается null
        assertNull(stockMarket.mostValuableStock());
    }

}

