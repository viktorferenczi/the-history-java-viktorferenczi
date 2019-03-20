package com.codecool.thehistory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TestWithJunit5 {

    private static Stream<TheHistory> theHistoryProvider() {
        return Stream.of(new TheHistoryArray(),
                         new TheHistoryArrayList(),
                         new TheHistoryLinkedList());
    }

    @ParameterizedTest
    @MethodSource("theHistoryProvider")
    public void add(TheHistory theHistory) {
        theHistory.add("abc def ghi");
        assertEquals("abc def ghi", theHistory.toString());
        theHistory.add("x y z w");
        assertEquals("abc def ghi x y z w", theHistory.toString());
    }

}
