package nl.markrensen.aoc.days;

import nl.markrensen.aoc.common.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day08 implements Day<Long> {

    @Override
    public Long part1(List<String> input) {
        Map<String, String[]> map = new HashMap<>();
        List<Integer> instructions = new ArrayList<>();
        for(char s : input.get(0).toCharArray()){
            if(s=='L'){
                instructions.add(0);
            } else {
                instructions.add(1);
            }
        }
        for(int i = 2; i<input.size(); i++) {
            String raw = input.get(i);
            String key = raw.trim().split("=")[0].trim();
            String LRString = raw.trim().split("=")[1].trim();
            String[] LR = new String[]{
                    LRString.split(", ")[0].trim().substring(1),
                    LRString.split(", ")[1].trim().substring(0, 3)
            };
            Object value = map.put(key,LR);
            if(value!=null) {
                System.out.println("something went wrong creating the map");
            }
        }

        int steps = 0;
        String current = "AAA";

        while (!current.equals("ZZZ")){
            for(int instruction : instructions){
                current = map.get(current)[instruction];
                steps++;
            }
        }



        return (long)steps;
    }


    @Override
    public Long part2(List<String> input) {
        Map<String, char[][]> map = new HashMap<>();
        List<Integer> instructions = new ArrayList<>();
        List<String> AStrings = new ArrayList<>();
        for(char s : input.get(0).toCharArray()){
            if(s=='L'){
                instructions.add(0);
            } else {
                instructions.add(1);
            }
        }
        for(int i = 2; i<input.size(); i++) {
            String raw = input.get(i);
            String key = raw.trim().split("=")[0].trim();
            if(key.toCharArray()[2] == 'A'){
                AStrings.add(key);
            }
            String LRString = raw.trim().split("=")[1].trim();
            char[][] LR = new char[][]{
                    LRString.split(", ")[0].trim().substring(1).toCharArray(),
                    LRString.split(", ")[1].trim().substring(0, 3).toCharArray()
            };
            Object value = map.put(key,LR);
            if(value!=null) {
                System.out.println("something went wrong creating the map");
            }
        }


//        int steps = 0;
//
//            while (!allZ(AStrings)) {
//
//                    for (int instruction : instructions) {
//                        for(int i = 0; i < AStrings.size(); i++) {
//                        AStrings.set(i, new String(map.get(AStrings.get(i))[instruction]));
//                        }
//                        steps++;
//                    }
//
//        }
        List<Integer> steps = new ArrayList<>();

       for(String a : AStrings){
           steps.add(getSteps(a, instructions, map));
       }
       long step = (long)steps.get(0);
       for(int i = 1; i<steps.size(); i++){
           step = lcm(step, steps.get(i));
       }


        return step;
    }

    public Integer getSteps(String current, List<Integer> instructions, Map<String, char[][]> map){
        char[] chars = current.toCharArray();
        int steps = 0;
        while (chars[2] != 'Z'){
            for(int instruction : instructions){
                chars = map.get(new String(chars))[instruction];
                steps++;
            }
        }
        return steps;
    }

    public static long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        long absNumber1 = Math.abs(number1);
        long absNumber2 = Math.abs(number2);
        long absHigherNumber = Math.max(absNumber1, absNumber2);
        long absLowerNumber = Math.min(absNumber1, absNumber2);
        long lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

    private Boolean allZ(List<String> AStrings){
        Boolean bool = true;
        for(String s: AStrings){
            if(s.equals("NGZ")){
                System.out.println();
            }
            if(s.toCharArray()[2] != 'Z'){
                return false;
            }
        }
        return bool;
    }

}
