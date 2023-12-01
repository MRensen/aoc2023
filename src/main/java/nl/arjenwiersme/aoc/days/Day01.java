package nl.arjenwiersme.aoc.days;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import nl.arjenwiersme.aoc.common.Day;

public class Day01 implements Day<Integer> {

    @Override
    public Integer part1(List<String> input) {
        int sum = 0;
       for(String s : input){
           String result = parseString1(s);
           sum += Integer.parseInt(result);
       }
       return sum;
    }

    private String parseString1(String input) {
        char first = '0' -1;
        char last = '0' -1;
        StringBuilder sum = new StringBuilder();

            for (Character c : input.toCharArray()) {
                if (c >= '0' && c <= '9') {
                    if (first < '0') {
                        first = c;
                    } else {
                        last = c;
                    }
                }
            }

        if(first>='0'){
            sum.append(first);
        }
        if(last>='0'){
            sum.append(last);
        } else {
            sum.append(first);
        }
//        System.out.println("sum = " + sum);
        return sum.toString();

    }

    private String parseString2(String input){
        String[] strings = {"one", "two" , "three", "four", "five", "six", "seven", "eight", "nine"};
        char[] integers = {'1','2','3','4','5','6','7','8','9'};
        char first = '0';
        char last = '0';
        StringBuilder sum = new StringBuilder();

            for (int i = 0; i < strings.length; i++) {
                String num = strings[i];
                char ch = integers[i];
                int indexNum = input.indexOf(num);
                int indexCh = input.indexOf(integers[Integer.parseInt(String.valueOf(ch))-1]);
                int indexFirst = -1;
                int indexLast = -1;
                if(first>'0') {
                    if(input.contains(String.valueOf(integers[Integer.parseInt(String.valueOf(first)) - 1]))) {
                        int check = -1;
                        if(input.contains(strings[Integer.parseInt(String.valueOf(first)) - 1])){
                            check = input.indexOf(strings[Integer.parseInt(String.valueOf(first)) - 1]);
                        }
                        indexFirst = input.indexOf(integers[Integer.parseInt(String.valueOf(first)) - 1]);
                        if(check >= 0 && check < indexFirst){
                            indexFirst = check;
                        }
                    } else {
                        indexFirst = input.indexOf(strings[Integer.parseInt(String.valueOf(first)) - 1]);
                    }
                }
                if(last>'0'){
                    if(input.contains(String.valueOf(integers[Integer.parseInt(String.valueOf(last))-1]))) {
                        int check = -1;
                        if(input.contains(strings[Integer.parseInt(String.valueOf(first)) - 1])){
                            check = input.lastIndexOf(strings[Integer.parseInt(String.valueOf(first)) - 1]);
                        }
                        indexLast = input.lastIndexOf(integers[Integer.parseInt(String.valueOf(last)) - 1]);
                        if(check >= 0 && check < indexFirst){
                            indexFirst = check;
                        }
                    } else {
                        indexLast = input.lastIndexOf(strings[Integer.parseInt(String.valueOf(last)) - 1]);
                    }

                }
                if(input.equals("hvmbmqnxk4onesix29kdhrdqtcfx1znmjhfjx")){
//                    System.out.println();
                }

                if (input.contains(num)) {
//                    System.out.println(input + " contains " + num);

                    if ((indexFirst <0 || (indexNum < indexFirst &&  indexNum >= 0))) {
//                        System.out.println("1: " + first);
                        first = integers[i];
//                        System.out.println("2: " + first);
                    }
                    indexNum = input.lastIndexOf(num);
                    if ((indexLast <0) || (indexNum > indexLast)) {
//                        System.out.println("3: " + last);
                        last = integers[i];
//                        System.out.println("4: " + last);
                    }

                }
                if(input.contains(String.valueOf(ch))) {
//                    System.out.println(input + " contains " + ch);
                    if ((indexFirst <0)|| ((indexCh < indexFirst) && indexCh >= 0)) {
//                        System.out.println("1: " + first);
                        if(input.contains(num)){
                            if(indexNum < indexFirst || indexCh < indexFirst){
                                first = integers[i];
                            }
                        } else {
                            first = integers[i];
                        }
//                        System.out.println("2: " + first);
                    }
                    indexCh = input.lastIndexOf(String.valueOf(ch));
                    if ((indexLast <0) || (indexCh > indexLast) ) {
//                        System.out.println("1: " + first);
                        if(input.contains(num)){
                            if(indexNum > indexLast || indexCh > indexLast){
                                last = integers[i];
                            }
                        } else {
                            last = integers[i];
                        }
//                        System.out.println("2: " + first);
                    }

                }
//                System.out.println();
            }


        if(first>'0'){
            sum.append(first);
        }
        if(last>'0'){
            sum.append(last);
        } else {
            sum.append(first);
        }
//        System.out.println("sum = " + sum);
        return sum.toString();
    }


    @Override
    public Integer part2(List<String> input) {
        int sum = 0;
        for(String s : input){
            String result = parseString2(s);
            System.out.println("Out: " + result + ", In: " + s);
            sum += Integer.parseInt(result);
        }
        return sum;
    }
    
}
