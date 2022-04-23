package com.mgd;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    public static final int ZERO = 48;
    public static final int A = 65;
    public static final int ROW = 10;
    public static final int COL = 10;
    public static final int LAST_NUM = ZERO + COL;
    public static final int LAST_LETTER = A + ROW;

    private ArrayList<ArrayList<String>> guessMap;
    private HashMap<SHIPS, ArrayList<String>> shipList;

    public enum SHIPS {

        BATTLESHIP("Battle Ship", 3, "B"),
        SUBMARINE("Submarine", 3, "S");

        private String name;
        private int size;
        private String id;

        SHIPS(String name, int size, String id) {
            this.name = name;
            this.size = size;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public int getSize() {
            return size;
        }

        public String getId() {
            return  id;
        }
    }

    /**
     * Creates the guess map,
     */
    public void initializePlayer() {

        // Create both guess and shipList
        guessMap = createMap();

        // Get user input to enter in ship locations
        BSU.populateShipMap(shipList, guessMap);
        if (shipList.isEmpty()) {
            System.out.println("shipList is empty dummy");
        }
    }

    /**
     *  Creates the map of a specified size
     * @return Map
     */
    public ArrayList<ArrayList<String>> createMap() {
        ArrayList<String> littleMap = new ArrayList<>();
        ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                littleMap.add(Character.toString((char) (A + i)) + Character.toString((char) (j + ZERO)));
            }
            map.add(littleMap);
            littleMap = new ArrayList<>();
        }
        return map;
    }

    /**
     * Prints the guesses map
     */
    public void printGuess() {

        for (int i = 0; i < ROW; i++) {
            System.out.println(guessMap.get(i));
        }
    }

    /**
     * Prints hit map
     */
    public void printHit() {

        for (int i = 0; i < ROW; i++) {
            System.out.println(guessMap.get(i));
        }
    }
}
