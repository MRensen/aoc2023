package nl.markrensen.aoc.days;

import nl.markrensen.aoc.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 implements Day<Integer> {

    @Override
    public Integer part1(List<String> input) {
        List<Integer> time = new ArrayList<>();
        List<Integer> distance = new ArrayList<>();
        Boolean isTime;
        for(String s : input){
            isTime = s.contains("Time");
            for( String i: s.split(" ")){
                if(!i.isEmpty()){
                    try{
                        Integer j = Integer.parseInt(i);
                        if(isTime){
                            time.add(j);
                        } else {
                            distance.add(j);
                        }

                    } catch(NumberFormatException ignored){}
                }
            }
        }
        List<Integer> winPresses = new ArrayList<>();
        for(int i = 0; i<time.size(); i++){
            try {
                winPresses.add(travelledDistance(time.get(i), distance.get(i)));
            }catch(RuntimeException ignore){}
        }

        int result = 1;
        for (int i : winPresses){
            result *= i;
        }


        return result;
    }

    Integer travelledDistance(int raceTime,  int distance ){
        List<Integer> winPresses = new ArrayList<>();
        for(int i = 1; i < raceTime; i++){
            int myDistance = i * (raceTime - i);
            if(myDistance > distance){
                winPresses.add(i);
            }
        }

        if(winPresses.isEmpty()){
            throw new RuntimeException();
        } else {
            return winPresses.size();
        }
    }




    @Override
    public Integer part2(List<String> input) {

        return null;
    }


}



