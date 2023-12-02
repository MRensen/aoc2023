package nl.markrensen.aoc.days;

import nl.markrensen.aoc.common.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day02 implements Day<Integer> {
    int MAX_BLUE = 14;
    int MAX_GREEN = 13;
    int MAX_RED = 12;

    @Override
    public Integer part1(List<String> input) {
        Set<Integer> legalGames = new HashSet<>();
        for(String game : input) {
            // split gameid and hands
            boolean isLegal = true;
            String[] gameIdAndHands = game.split(":");
            assert gameIdAndHands.length == 2;
            int gameNumber = Integer.parseInt(gameIdAndHands[0].substring(5));
            //split hands per game
            for(String hand : gameIdAndHands[1].split(";")){
                // split blocks per hand
                int blue = 0;
                int green = 0;
                int red = 0;
                for(String numberAndBlock : hand.split(",")){

                   if(numberAndBlock.contains("blue")){
                       blue = Integer.parseInt(numberAndBlock.replaceAll("[^0-9]", ""));
                       if(blue == 15)
                       {
                           System.out.println();}
                   }
                   if(numberAndBlock.contains("green")){
                       green = Integer.parseInt(numberAndBlock.replaceAll("[^0-9]", ""));
                   }
                   if (numberAndBlock.contains("red")){
                       red = Integer.parseInt(numberAndBlock.replaceAll("[^0-9]", ""));
                   }
                    //check if hand is legal

                }
                if(blue > MAX_BLUE || green > MAX_GREEN || red > MAX_RED){
                    isLegal = false;
                }

            }

            if(isLegal){
                legalGames.add(gameNumber);
            }

        }
        System.out.println(legalGames);
        int sum = 0;
        for (int i : legalGames){
            sum += i;
        }
        return sum;
    }


    @Override
    public Integer part2(List<String> input) {
        int result = 0;
        for(String game : input) {
            String[] gameIdAndHands = game.split(":");
            int blue = 0;
            int green = 0;
            int red = 0;
            //split hands per game
            for(String hand : gameIdAndHands[1].split(";")){
                // split blocks per hand

                for(String numberAndBlock : hand.split(",")){

                    if(numberAndBlock.contains("blue")){
                        int currentblue = Integer.parseInt(numberAndBlock.replaceAll("[^0-9]", ""));
                        if(currentblue > blue)
                        {
                            blue = currentblue;
                        }
                    }
                    if(numberAndBlock.contains("green")){
                        int currentgreen = Integer.parseInt(numberAndBlock.replaceAll("[^0-9]", ""));
                        if (currentgreen > green){
                            green = currentgreen;
                        }
                    }
                    if (numberAndBlock.contains("red")){
                        int currentred = Integer.parseInt(numberAndBlock.replaceAll("[^0-9]", ""));
                        if (currentred > red){
                            red = currentred;
                        }
                    }
                }
            }
            result += (blue * green * red);
        }
        return result;
    }
    
}
