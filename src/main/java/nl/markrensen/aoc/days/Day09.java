package nl.markrensen.aoc.days;

import nl.markrensen.aoc.common.Day;

import java.util.*;

public class Day09 implements Day<Long> {

    @Override
    public Long part1(List<String> input) {

        long sum = 0;
        for(String s: input){
            long parsedS = parse(s, false);
            sum += parsedS;
        }

        return sum;
    }

    private long parse(String s, boolean part2) {
        List<Long> sList = new ArrayList<>(Arrays.stream(s.split(" ")).map(Long::parseLong).toList());
        List<List<Long>> conversions = new ArrayList<>();
        conversions.add(new ArrayList<>(sList));
        List<Long> tempList = new ArrayList<>();
        while(!allZeroes(tempList)) {
            tempList = new ArrayList<>();
            for (int i = 1; i < sList.size(); i++) {
                tempList.add(sList.get(i)-sList.get(i-1));
            }
            conversions.add(new ArrayList<>(tempList));
            sList = new ArrayList<>(tempList);
        }
        long below = 0;
        for (int i = conversions.size()-1; i >= 0; i--) {
            List<Long> list = conversions.get(i);

            below = part2 ? list.get(0) - below : list.get(list.size()-1) + below;
        }
        return below;
    }


    private boolean allZeroes(List<Long> l){
        boolean bool = true;
        if(l == null || l.isEmpty()){
            return false;
        }
        for(long i : l){
            if(i!=0){
                return false;
            }
        }
        return bool;
    }


    @Override
    public Long part2(List<String> input) {

        long sum = 0;
        for(String s: input){
            long parsedS = parse(s, true);
            sum += parsedS;
        }

        return sum;    }
}
