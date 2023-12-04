package nl.markrensen.aoc.days;

import nl.markrensen.aoc.common.Day;

import java.util.*;

public class Day04 implements Day<Integer> {

    @Override
    public Integer part1(List<String> input) {
        int result = 0;
        for(String s : input){
            String[] inputArray = s.split(":");
            String fullgame = inputArray[0].trim();
            String[] numbersArray = inputArray[1].split(" \\| ");
            List<String> luckyNumbers = Arrays.stream(numbersArray[0].trim().split(" ")).toList();
            List<String> myNumbers = Arrays.stream(numbersArray[1].trim().split(" ")).toList();
            int product = 0;
            for(String n : myNumbers){
                if(!n.isBlank() || !n.isEmpty()){
                    if(luckyNumbers.contains(n)){
                        if(product == 0){
                            product = 1;
                        } else {
                            product *= 2;
                        }
                    }
                }
            }
            result += product;
            System.out.println();
        }

        return result;
    }




    @Override
    public Integer part2(List<String> input) {

        return null;
    }

}
