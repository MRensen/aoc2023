package nl.markrensen.aoc.days;

import nl.markrensen.aoc.common.Day;
import org.junit.platform.commons.util.StringUtils;

import java.util.*;

public class Day03 implements Day<Integer> {
    int MAX_LENGTH = 3;
    Set<Character> symbols;
    int counter = 0;
    Map<String, Integer> rejects = new TreeMap<>();

    @Override
    public Integer part1(List<String> input) {
        symbols = findAllSymbols(input);
        String[][] board = new String[input.size()][input.get(0).length()];
        Set<Integer> neigbours = new HashSet<>();
        for (int i = 0; i < input.size(); i++){
            String[] row = input.get(i).split("");
            board[i] = row;
        }
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                String current = board[i][j];
                if(isSymbol(current)) {
                    try {
                        for (int ii = i - 1; ii <= i + 1; ii++) {
                            for (int jj = j - 1; jj <= j + 1; jj++) {
                                String check = board[ii][jj];
                                if (!check.equals(".") && !check.equals(current)) {
                                    int fullneighbour = getFullNeighbour(ii, jj, board);
                                    if(fullneighbour>0){
//                                        if(!neigbours.add(fullneighbour)){
//                                            //een debug ding
//                                            counter++;
//                                        }
                                        rejects.put("["+i+"]["+j+"]"+fullneighbour, fullneighbour);
                                    }
                                    // een debug ding
                                }
                            }
                        }
                    } catch (IndexOutOfBoundsException ignored){}
                }
            }
        }
        int sum = 0;
        System.out.println(neigbours);
        for(int i : rejects.values()){
            sum += i;
        }
        return sum;
    }

    Set<Character> findAllSymbols(List<String> input){
        StringBuilder sb = new StringBuilder();
        for (String s : input){
            sb.append(s);
        }
        String s = sb.toString().replaceAll("\\.","").replaceAll("\\d","");
        Set<Character> set = new HashSet<>();
        for (Character c : s.toCharArray()){
            set.add(c);
        }
        return set;
    }

    boolean isSymbol(String s){
        char[] ca = s.toCharArray();
        assert ca.length == 1;
        return symbols.contains(ca[0]);
    }

    int getFullNeighbour(int i, int j, String[][]board){
        String input = board[i][j];
        StringBuilder before = new StringBuilder();
        StringBuilder after = new StringBuilder();
            int min = 1;
            try {
                while (!board[i][j - min].equals(".") && !isSymbol(board[i][j - min])) {
                    before.append(board[i][j - min]);
                    min++;
                }
            } catch (IndexOutOfBoundsException ignored) {
                System.out.println("Index out of bounds for [" + i + "][" + (j-min) + "]");
            }
            min = 1;
            try {
                while (!board[i][j + min].equals(".") && !isSymbol(board[i][j + min])) {
                    after.append(board[i][j + min]);
                    min++;
                }
            } catch (IndexOutOfBoundsException ignored) {
                System.out.println("Index out of bounds for [" + i + "][" + (j-min) + "]");
            }

        // Deze code werkt, maar niet goed.
//                for(int m = -MAX_LENGTH; m <= MAX_LENGTH; m++){
//                    try {
//                        String check = board[i][j + m];
//                        if(check.equals(".") && !sb.isEmpty()){
//                            break;
//                        }
//                        if (!check.equals(".") && !isSymbol(check) && betweenHereAndZeroIsNoDot(i,j,m,board)) {
//                            sb.append(check);
//                        }
//                    } catch (IndexOutOfBoundsException ignored){}
//                }
//        try {
//            return Integer.parseInt(sb.toString());
//        } catch (NumberFormatException e){
//            return -1;
//        }
        String resultString = before.reverse().toString() + input + after.toString();
//        System.out.println(resultString);
        try {
            return Integer.parseInt(resultString);
        } catch (NumberFormatException e){
            return -1;
        }

    }

    boolean betweenHereAndZeroIsNoDot(int i, int j, int m, String[][] board) throws IndexOutOfBoundsException {
        if(m>j){
                if (m-j==1){
                    return !board[i][j + (m - 1)].equals(".");
                } else {
                    return !board[i][j+(m-2)].equals(".");
                }
        } else if (m<j){
                if(j-m==1){
                    return !board[i][j - (m-1)].equals(".");
                } else {
                    return !board[i][j - (m-2)].equals(".");
                }
        } else {
            return true;
        }
    }




    @Override
    public Integer part2(List<String> input) {

        return null;
    }
    
}
