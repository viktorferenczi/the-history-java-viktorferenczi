package com.codecool.thehistory;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTheHistoryPerformance {
    private TheHistory theHistory;

    public TestTheHistoryPerformance(String name) {
        switch (name) {
            case "Array":
                theHistory = new TheHistoryArray();
                break;
            case "ArrayList":
                theHistory = new TheHistoryArrayList();
                break;
            case "LinkedList":
                theHistory = new TheHistoryLinkedList();
                break;
        }
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection data() {
        return Arrays.asList(new String[]{"Array", "ArrayList", "LinkedList"});
    }

    private String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

    String readIliad() throws IOException {
        Path iliadPath = FileSystems.getDefault().getPath("src/main/resources/Iliad.txt");
        return new String(Files.readAllBytes(iliadPath));
    }

    @Test(timeout = 5000)
    public void remove() {
        int n = 250_000;
        theHistory.add(repeat("foo bar", n));
        theHistory.removeWord("bar");
        assertEquals(n, theHistory.size());
    }

    @Test(timeout = 5000)
    public void replaceOneWord() {
        int n = 250_000;
        theHistory.add(repeat("foo bar", n));
        theHistory.replace("bar", "baz");
        assertEquals(n * 2, theHistory.size());
    }

    @Test(timeout = 5000)
    public void replaceMultipleWords__delete() {
        int n = 250_000;
        theHistory.add(repeat("x", n));
        theHistory.replace("x x x x x", "foo");
        assertEquals(n/5, theHistory.size());
    }

    @Test(timeout = 5000)
    public void replaceMultipleWords__insert() {
        int n = 250_000;
        theHistory.add(repeat("x", n));
        theHistory.replace("x x", "1 2 3 4");
        assertEquals(n * 2, theHistory.size());
    }

    @Test(timeout = 5000)
    public void replaceMultipleWords__equal() {
        int n = 250_000;
        theHistory.add(repeat("x", n));
        theHistory.replace("x x", "y y");
        assertEquals(n, theHistory.size());
    }

    @Test(timeout = 5000)
    public void removeInIliad() throws IOException {
        theHistory.add(repeat(readIliad(), 10));
        theHistory.removeWord("king");
        theHistory.removeWord("Zeus");
        theHistory.removeWord("Apollo");
        theHistory.removeWord("it");
    }

    @Test(timeout = 5000)
    public void replaceOneWordInIliad() throws IOException {
        theHistory.add(repeat(readIliad(), 10));
        theHistory.replace("Achilles", "Il");
        theHistory.replace("Agamemnon", "Il");
        theHistory.replace("Priam", "Trumm");
        theHistory.replace("chariot", "tank");
        theHistory.replace("bow", "missile");
        theHistory.replace("arrow", "nuke");
        theHistory.replace("the", "the");
    }
}