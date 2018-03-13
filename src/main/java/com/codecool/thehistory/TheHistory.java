package com.codecool.thehistory;

import java.util.*;

public class TheHistory {

    private DataStructureType dataStructureType = DataStructureType.Array;

    private String[] wordsArray = new String[0];
    private List<String> wordsArrayList = new ArrayList<>();
    private List<String> wordsLinkedList = new LinkedList<>();

    public void add(String text) {
        switch (dataStructureType) {
            case Array:
                int origLength = wordsArray.length;
                String[] newArray = text.split("\\s+");
                wordsArray = Arrays.copyOf(wordsArray, origLength + newArray.length);
                System.arraycopy(newArray, 0, wordsArray, origLength, newArray.length);
                break;
            case ArrayList:
                Collections.addAll(wordsArrayList, text.split("\\s+"));
                break;
            case LinkedList:
                Collections.addAll(wordsLinkedList, text.split("\\s+"));
                break;
        }
    }

    public void removeWord(String wordToBeRemoved) {
        switch (dataStructureType) {
            case Array:
                // TODO - use wordsArray
                break;
            case ArrayList:
                // TODO - use wordsArrayList
                break;
            case LinkedList:
                // TODO - use wordsLinkedList
                break;
        }
    }

    private void replaceOneWord(String from, String to) {
        switch (dataStructureType) {
            case Array:
                // TODO - use wordsArray
                break;
            case ArrayList:
                // TODO - use wordsArrayList
                break;
            case LinkedList:
                // TODO - use wordsLinkedList
                break;
        }
    }

    private void replaceMoreWords(String[] fromWords, String[] toWords) {
        switch (dataStructureType) {
            case Array:
                // TODO - use wordsArray
                break;
            case ArrayList:
                // TODO - use wordsArrayList
                break;
            case LinkedList:
                // TODO - use wordsLinkedList
                break;
        }
    }

    public void replace(String from, String to) {
        String[] fromWords = from.split("\\s+");
        String[] toWords = to.split("\\s+");
        if (fromWords.length == 1 && toWords.length == 1) {
            replaceOneWord(from, to);
        } else {
            replaceMoreWords(fromWords, toWords);
        }
    }

    public int size() {
        int size = 0;
        switch (dataStructureType) {
            case Array:
                size = wordsArray.length;
                break;
            case ArrayList:
                size = wordsArrayList.size();
                break;
            case LinkedList:
                size = wordsLinkedList.size();
                break;
        }
        return size;
    }

    public void clear() {
        switch (dataStructureType) {
            case Array:
                wordsArray = new String[0];
                break;
            case ArrayList:
                wordsArrayList.clear();
                break;
            case LinkedList:
                wordsLinkedList.clear();
                break;
        }
    }

    public void setDataStructureType(DataStructureType dataStructureType) {
        this.dataStructureType = dataStructureType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (dataStructureType) {
            case Array:
                for (String word : wordsArray) {
                    sb.append(word).append(" ");
                }
                break;
            case ArrayList:
                for (String word : wordsArrayList) {
                    sb.append(word).append(" ");
                }
                break;
            case LinkedList:
                for (String word : wordsLinkedList) {
                    sb.append(word).append(" ");
                }
                break;
        }
        return sb.toString();
    }

}
