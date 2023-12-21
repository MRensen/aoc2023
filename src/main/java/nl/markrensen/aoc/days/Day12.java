package nl.markrensen.aoc.days;

import nl.markrensen.aoc.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day12 implements Day<Long> {

    @Override
    public Long part1(List<String> input) {
        long count = 0;
       for(String line : input){
           count += getPossibleRepairs(line);
       }
       return count;
    }

    private long getPossibleRepairs(String line) {
        long count = 0L;
        String[] splitLine = line.split(" ");
        String[] record = splitLine[0].split("");
        String[] backup = splitLine[1].split(",");
        Integer[] questionmarkIndexes = countQuestionMarks(record);
        int qc = questionmarkIndexes.length-1;
        int qc2 = qc * qc;

        //naive oplossing (brute)
        List<String[]> fills = recursiveFill(qc, record, questionmarkIndexes, backup);

        for(String[] fill : fills){
            if(isLegal(backup, fill)){
//            if(equalsBackup(fill, backup, qc+1)){
                count++;
            }
        }

        return count;
    }

    private boolean isLegal(String[] backup, String[] fill) {
        return Arrays.equals(toQArray(fill, backup.length), backup);
    }

    private boolean isPossibleLegal(String[] backup, String[] fill) {
        String[] partBackup = Arrays.copyOfRange(backup, backup.length-fill.length, backup.length);
        return Arrays.equals(toQArray(fill, partBackup.length), partBackup);
    }

    private String[] toQArray(String[] fill, int length){
        List<Integer> poundGroup = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Integer lastQIndex = null;
        for(int i = 0; i< fill.length; i++){
            String s = fill[i];
            if(s.equals("#")){
                temp.add(i);
            } else {
                if (!temp.isEmpty()){
                    poundGroup.add(temp.size());
                    temp = new ArrayList<>();
                }
            }
        }
        if (!temp.isEmpty()){
            poundGroup.add(temp.size());
        }

        Integer[] integers = poundGroup.toArray(new Integer[0]);
        String[] strings = Arrays.stream(integers).map(String::valueOf).toArray(String[]::new);
        return strings;

    }

    private List<String[]> recursiveFill(int j, String[] springs, Integer[] Qindexes, String[] backup) {
        List<String[]> cum = new ArrayList<>();
        String[] sdot = Arrays.copyOf(springs, springs.length);
        String[] spound = Arrays.copyOf(springs, springs.length);
        sdot[Qindexes[j]] = ".";
        spound[Qindexes[j]] = "#";

//            String[] sdot = replaceNthQ(j, springs, ".");
//            String[] spound = replaceNthQ(j, springs, "#");

            if (containsQ(sdot)) {
                cum.addAll(recursiveFill(j - 1, sdot, Qindexes, backup));
            } else {
                cum.add(sdot);
            }

        if (containsQ(spound)) {
            cum.addAll(recursiveFill(j - 1, spound, Qindexes, backup));
        } else {
            cum.add(spound);
        }
        return cum;
    }

    private boolean containsQ(String[] sarray) {
        for(String s : sarray){
            if(s.equals("?")){
                return true;
            }
        }
        return false;
    }


    private Integer[] countQuestionMarks(String[] record) {
        List<Integer> count = new ArrayList<>();
        for(int i = 0; i < record.length; i++){
            String s = record[i];
            if(s.equals("?")){
                count.add(i);
            }
        }
        return count.toArray(new Integer[0]);
    }


    @Override
    public Long part2(List<String> input) {
        long count = 0;
//        for (String line : input) {
//            count += getPossibleRepairs2(line);
//        }
        return count;
    }

//    private long getPossibleRepairs2(String line) {
//        long count = 0L;
//        String[] splitLine = line.split(" ");
//        String[] record = (splitLine[0] + "?" + splitLine[0] + "?" + splitLine[0] + "?" + splitLine[0] + "?" + splitLine[0] + "?" + splitLine[0] ).split("");
//        String[] backup = (splitLine[1] + "," + splitLine[1] + "," + splitLine[1] + "," + splitLine[1] + "," + splitLine[1] + "," + splitLine[1] ).split(",");
//        Integer[] questionmarkIndexes = countQuestionMarks(record);
//        int qc = questionmarkIndexes.length-1;
//
//        //naive oplossing (brute)
//        List<String[]> fills = recursiveFill(qc, record, questionmarkIndexes, backup);
//
//        for(String[] fill : fills){
//            if(isLegal(backup, fill)){
////            if(equalsBackup(fill, backup, qc+1)){
//                count++;
//            }
//        }
//
//        return count;
//    }

}
