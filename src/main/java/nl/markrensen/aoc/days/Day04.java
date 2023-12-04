package nl.markrensen.aoc.days;

import nl.markrensen.aoc.common.Day;

import java.util.*;

public class Day04 implements Day<Integer> {

    @Override
    public Integer part1(List<String> input) {
        int result = 0;
        for (String s : input) {
            String[] inputArray = s.split(":");
            String fullgame = inputArray[0].trim();
            String[] numbersArray = inputArray[1].split(" \\| ");
            List<String> luckyNumbers = Arrays.stream(numbersArray[0].trim().split(" ")).toList();
            List<String> myNumbers = Arrays.stream(numbersArray[1].trim().split(" ")).toList();
            int product = 0;
            for (String n : myNumbers) {
                if (!n.isBlank() || !n.isEmpty()) {
                    if (luckyNumbers.contains(n)) {
                        if (product == 0) {
                            product = 1;
                        } else {
                            product *= 2;
                        }
                    }
                }
            }
            result += product;
//            System.out.println();
        }

        return result;
    }


    @Override
    public Integer part2(List<String> input) {
        int result = 0;
        Map<Integer, String[]> gamesMap = new TreeMap<>();
        // vul de map van alle nummers, zodat deze geraadpleegd kan worden in de "matchesForGame" methode
        for (String s : input) {
            String[] inputArray = s.split(":");
            String fullgame = inputArray[0].trim();
            Integer fullgameNumber = Integer.parseInt(fullgame.substring(fullgame.indexOf(" ")).trim());
            String[] numbersArray = inputArray[1].split(" \\| ");
            gamesMap.put(fullgameNumber, numbersArray);
        }
        // bereken voor elke game hoeveel nieuwe kaarten het oplevert.
        for(Integer i : gamesMap.keySet()){
            result += matchesForGame(gamesMap, i);
        }

        // return hoevel kaarten je hebt gekregen + hoeveel kaarten je al had.
        return result + gamesMap.size();
    }

    public Integer matchesForGame(Map<Integer, String[]> gamesMap, Integer gameNumber){
        int result = 0;
        List<String> luckyNumbers = Arrays.stream(gamesMap.get(gameNumber)[0].trim().split(" ")).toList();
        List<String> myNumbers = Arrays.stream(gamesMap.get(gameNumber)[1].trim().split(" ")).toList();
        int product = 0;
        // check of en hoeveel kaarten je krijgt voor deze game.
        for (String n : myNumbers) {
            if (!n.isBlank() || !n.isEmpty()) {
                if (luckyNumbers.contains(n)) {
                    product++;
                }
            }
        }
        // als je kaarten krijgt deze game, kijk dan ook of die nieuwe kaarten nog iets opleveren.
        for(int i = 1; i <= product; i++){
            result += matchesForGame(gamesMap, gameNumber+i);
        }
        // return hoeveel nieuwe kaarten je totaal hebt gekregen.
        return result + product;
    }


//        int result = 0;
//        Map<Integer, String[]> gamesMap = new TreeMap<>();
//        for (String s : input) {
//            String[] inputArray = s.split(":");
//            String fullgame = inputArray[0].trim();
//            Integer fullgameNumber = Integer.parseInt(fullgame.substring(fullgame.indexOf(" ")).trim());
//            String[] numbersArray = inputArray[1].split(" \\| ");
//            gamesMap.put(fullgameNumber, numbersArray);
//        }
//            Map<Integer, String[]> tempMap = new HashMap<>(gamesMap);
//            while (!tempMap.isEmpty()) {
//                result += tempMap.size();
//                Map<Integer, String[]> temptempMap = new HashMap<>();
//                for(Map.Entry<Integer, String[]> entry : tempMap.entrySet()) {
//                    Integer fullgameNumber = entry.getKey();
//                    List < String > luckyNumbers = Arrays.stream(tempMap.get(fullgameNumber)[0].trim().split(" ")).toList();
//                    List<String> myNumbers = Arrays.stream(tempMap.get(fullgameNumber)[1].trim().split(" ")).toList();
//                    temptempMap.putAll(calculatePoints(luckyNumbers, myNumbers, fullgameNumber, gamesMap));
//                }
//                tempMap = new HashMap<>(temptempMap);
//            }
//            System.out.println();
//
//        return result;
//    }
//
//    Map<Integer, String[]> calculatePoints(List<String> luckyNumbers, List<String> myNumbers, Integer gameNumber, Map<Integer, String[]> gamesMap) {
//        Map<Integer, String[]> map2 = new HashMap<>();
//        int amount = 0;
//        for (String n : myNumbers) {
//            if (!n.isBlank() || !n.isEmpty()) {
//                if (luckyNumbers.contains(n)) {
//                    amount++;
//                }
//            }
//        }
//        if(amount!=0) {
//            for (int i = gameNumber; i <= gameNumber + amount; i++) {
//                map2.put(i, gamesMap.get(i));
//            }
//        }
//        System.out.println(map2.size());
//        return map2;
//    }
}

