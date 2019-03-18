package com.codecool.thehistory;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTheHistoryCorrectness {
    private TheHistory theHistory;

    public TestTheHistoryCorrectness(String name) {
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
        return Arrays.asList(new String[] { "Array", "ArrayList", "LinkedList" });
    }

    @Test
    public void add() {
        theHistory.add("abc def ghi");
        assertEquals("abc def ghi", theHistory.toString());
        theHistory.add("x y z w");
        assertEquals("abc def ghi x y z w", theHistory.toString());
    }

    @Test
    public void size() {
        assertEquals(0, theHistory.size());
        theHistory.add("abc def ghi");
        assertEquals(3, theHistory.size());
    }

    @Test
    public void clear() {
        theHistory.add("abc def ghi");
        theHistory.clear();
        assertEquals("", theHistory.toString());
    }

    @Test
    public void replaceOneWord_simple() {
        theHistory.add("abc def ghi abc def ghi");
        theHistory.replace("abc", "ABC");
        theHistory.replace("def", "DEF");
        assertEquals("ABC DEF ghi ABC DEF ghi", theHistory.toString());
    }

    @Test
    public void replaceOneWord_partOfWordOnly() {
        theHistory.add("carpet car motorcar Nicaragua");
        theHistory.replace("car", "AUTOMOBILE");
        assertEquals("carpet AUTOMOBILE motorcar Nicaragua", theHistory.toString());
    }

    @Test
    public void removeWord_found() {
        theHistory.add("hello world hello hello world");
        theHistory.removeWord("hello");
        assertEquals("world world", theHistory.toString());
    }

    @Test
    public void removeWord_notFound() {
        theHistory.add("hello world hello hello world");
        theHistory.removeWord("x");
        assertEquals("hello world hello hello world", theHistory.toString());
    }

    @Test
    public void replaceMoreWords_simple() {
        theHistory.add("replace me replace me me replace me");
        theHistory.replace("replace me", "HAPPY FUN");
        assertEquals("HAPPY FUN HAPPY FUN me HAPPY FUN", theHistory.toString());
    }

    @Test
    public void replaceMoreWords_longerReplacement() {
        theHistory.add("x y z x y z w");
        theHistory.replace("x y", "X X Y Y");
        assertEquals("X X Y Y z X X Y Y z w", theHistory.toString());
    }

    @Test
    public void replaceMoreWords_shorterReplacement() {
        theHistory.add("x y z x y z w");
        theHistory.replace("x y", "XY");
        assertEquals("XY z XY z w", theHistory.toString());
    }

    @Test
    public void replaceMoreWords_notFound() {
        theHistory.add("replace me replace me me replace me");
        theHistory.replace("replace replace me", "HAPPY FUN");
        assertEquals("replace me replace me me replace me", theHistory.toString());
    }

    @Test
    public void replaceMoreWords_multipleRuns() {
        theHistory.add("x x x y x x x x y x x");
        theHistory.replace("x x", "XX");
        assertEquals("XX x y XX XX y XX", theHistory.toString());
    }

    @Test
    public void replaceMoreWords_everything() {
        String text = "this is the text that I would like to be replaced";
        theHistory.add(text);
        theHistory.replace(text, "everything");
        assertEquals("everything", theHistory.toString());
    }

    @Test
    public void replaceMoreWords_partOfWordOnly() {
        theHistory.add("foo bar baz");

        theHistory.replace("o b", "X");
        assertEquals("foo bar baz", theHistory.toString());

        theHistory.replace("foo b", "X");
        assertEquals("foo bar baz", theHistory.toString());

        theHistory.replace("o bar", "X");
        assertEquals("foo bar baz", theHistory.toString());

        theHistory.replace("o bar b", "X");
        assertEquals("foo bar baz", theHistory.toString());
    }
}