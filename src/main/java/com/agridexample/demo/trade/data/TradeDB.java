package com.agridexample.demo.trade.data;


import com.agridexample.demo.trade.model.Trade;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.floor;
import static java.lang.Math.random;
import static java.lang.String.format;
import static java.util.Arrays.asList;


public class TradeDB {

    // add / remove products to change the data set
    private List<String> PRODUCTS = asList("Palm Oil", "Rubber", "Wool", "Amber", "Copper", "Lead", "Zinc", "Tin", "Aluminium",
            "Aluminium Alloy", "Nickel", "Cobalt", "Molybdenum", "Recycled Steel", "Corn", "Oats", "Rough Rice",
            "Soybeans", "Rapeseed", "Soybean Meal", "Soybean Oil", "Wheat", "Milk", "Coca", "Coffee C",
            "Cotton No.2", "Sugar No.11", "Sugar No.14");

    // add / remove portfolios to change the data set
    private List<String> PORTFOLIOS = asList("Aggressive", "Defensive", "Income", "Speculative", "Hybrid");

    // start the book id's and trade id's at some future random number, looks more realistic than starting them at 0
    private long nextBookId = 62472;
    private long nextTradeId = 24287;
    private long nextBatchId = 101;

    private List<Trade> createTradeData() {
        System.out.println("Creating trade data");
        List<Trade> trades = new ArrayList<>();
        long thisBatch = nextBatchId++;
        for (String product : PRODUCTS) {
            for (String portfolio : PORTFOLIOS) {
                for (int k = 0; k < numberBetween(10, 2000); k++) {
                    String book = createBookName();
                    for (int l = 0; l < numberBetween(10, 10000); l++) {
                        trades.add(createTradeRecord(product, portfolio, book, thisBatch));
                    }
                }
            }
        }
        return trades;
    }

    private Trade createTradeRecord(String product, String portfolio, String book, Long batch) {

        double current = (floor(random() * 100000) + 1000);
        double previous = current + (floor(random() * 10000) - 20000);

        return new Trade(
                product,
                portfolio,
                book,
                createTradeId(),
                randomNegation(numberBetween(10, 10000)),
                randomNegation(numberBetween(10, 10000)),
                numberBetween(1, 10) > 2 ? "Physical" : "Financial",
                numberBetween(1, 10) > 5 ? "Buy" : "Sell",
                randomNegation(current),
                randomNegation(previous),
                randomNegation(numberBetween(100, 100000)),
                randomNegation(numberBetween(100, 100000)),
                randomNegation(numberBetween(100, 100000)),
                randomNegation(numberBetween(100, 100000)),
                randomNegation(numberBetween(100, 100000)),
                batch);
    }

    private String createBookName() {
        return format("GL-%d", ++nextBookId);
    }

    private Long createTradeId() {
        return ++nextTradeId;
    }

    private Long numberBetween(long from, long to) {
        return ThreadLocalRandom.current().nextLong(from, to);
    }

    private long randomNegation(long number) {
        return (numberBetween(0, 2) == 0 ? -1 : 1) * number;
    }

    private double randomNegation(double number) {
        return (numberBetween(0, 2) == 0 ? -1 : 1) * number;
    }
}