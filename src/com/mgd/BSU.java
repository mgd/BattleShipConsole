package com.mgd;

import java.lang.reflect.Array;
import java.util.*;

public class BSU {

    public static HashMap<Player.SHIPS, ArrayList<String>> populateShipMap(HashMap<Player.SHIPS,
            ArrayList<String>> shipList, ArrayList<ArrayList<String>> playerMap) {

        for (Player.SHIPS ship :Player.SHIPS.values()) {
            shipList.put(ship, getCoordinates(ship, playerMap));
        }
        return shipList;
    }

    public static ArrayList<String> getCoordinates(Player.SHIPS ship, ArrayList<ArrayList<String>> playerMap) {

        while (true) {
            System.out.println("Please enter in " + ship.getSize() + " coordinates for the " + ship.getName());
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            ArrayList<String> input = new ArrayList<>(Arrays.asList(line.split(",")));
            Collections.sort(input);

            if (input.size() != ship.getSize()) {
                System.out.println("User entered incorrect number of arguments");
                continue;
            }

            switch (checkAll(input, playerMap)) {
                case 0:
                    // Correct do nothing user entered coordinates correctly
                    break;
                case 1:
                    System.out.println("Coordinates have already been used");
                    continue;
                case 2:
                    System.out.println("Coordinates must be horizontal or vertical");
                    continue;
                case 3:
                    System.out.println("Coordinates are not in map");
                    continue;
                case 4:
                    System.out.println("Input is not valid for the given map");
                    continue;
            }

            // Add guesses to guessList
            for(String item : input){
                guessList.add(item);
            }
            return input;
        }
    }

    /**
     * Function checks for a malformed, out of bounds, not horz or vertical, or already entered coordinate
     * @param input
     *          User's input
     * @param playerMap
     *          Gambit of possible valid user entries
     * @return
     */
    public static int checkAll(ArrayList<String> input, ArrayList<ArrayList<String>> playerMap) {

        if (checkAsci(input)) {
            if (coordsInMap(input, playerMap)) {
                if (vertical(input) || horizontal(input)) {
                    if(coordNotTaken(input)) {
                        return 0;
                    }
                    else {
                        // Case where input is taken
                        return 1;
                    }
                }
                else {
                    // Case not a vertical or horizontal input
                    return 2;
                }
            }
            else {
                // Case coordinates not in map
                return 3;
            }
        }
        else{
            // Case where asci input is not valid
            return 4;
        }
    }

    /**
     * Checks if the user entered coordinates exist on the map
     * @param input
     * @param playerMap
     * @return
     */
    public static boolean coordsInMap (ArrayList<String> input, ArrayList<ArrayList<String>> playerMap) {

        boolean retVal;
        for (String coord : input) {
            retVal = false;
            for (ArrayList<String> list : playerMap) {
                if (list.contains(coord)) {
                    retVal = true;
                    break;
                }
            }
            if (!retVal) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the coordinates entered represent a contigous vertical ship
     * @param input Coordinates of the ship
     * @return true if a contiguous vertical ship, otherwise false
     */
    public static boolean vertical(ArrayList<String> input) {

        String col = Character.toString(input.get(0).charAt(1));

        for (int i = 0; i < input.size() - 1; i++) {
            if (!input.get(i).contains(col)) {
                return false;
            }
            int lastRow = (int) input.get(i).charAt(0);
            int nextRow = (int) input.get(i + 1).charAt(0);
            if ((lastRow + 1) != nextRow) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the user entered cordinates represent a continuous horizontal ship
     * @param input Coordinates for the ship
     * @return True if a contigous horizontal ship, otherwise false
     */
    public static boolean horizontal(ArrayList<String> input) {

        String row = Character.toString(input.get(0).charAt(0));

        // Check for consecutive horizontal entries
        for (int i = 0; i < input.size() - 1; i++) {
            if (!input.get(i).contains(row)) {
                return false;
            }
            int lastCol = (int) input.get(i).charAt(1);
            int nextCol = (int) input.get(i + 1).charAt(1);
            if ((lastCol + 1) !=  nextCol) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks the asci range value of the coordinates to the given range
     * @return
     */
    public static boolean checkAsci(ArrayList<String> input) {

        for (String item: input) {
            int letter = (int )item.charAt(0);
            int num = (int) item.charAt(1);

            if (letter < Player.A || letter > Player.LAST_LETTER) {
                return false;
            }
            if (num < Player.ZERO || num > Player.LAST_NUM) {
                return false;
            }
        }
        return true;
    }

    public static boolean coordNotTaken(ArrayList<String> input) {
        boolean retVal = true;
        for(String coord : input) {
            for(String guess : guessList) {
                if(coord.equals(guess)) {
                    retVal = false;
                    break;
                }
            }
            if (retVal == false) {
                break;
            }
        }
        return retVal;
    }
}
