package nl.markrensen.aoc.days;

import nl.markrensen.aoc.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day11 implements Day<Integer> {

    private int poundCount = 0;
    private boolean part2 = false;
    private int expansionNumber = 2;

    @Override
    public Integer part1(List<String> input) {
        Point[][] board = new Point[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            char[] chars = s.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if(chars[j] == '#'){
                    poundCount++;
                    board[i][j] = new Point(i, j, chars[j], poundCount);
                } else {
                    board[i][j] = new Point(i, j, chars[j]);
                }
            }
        }

        board = stretchColumns(stretchRows(board));
        return calculateRoutes(makePairs(board));


    }

    // Maakt paartjes van alle pounds en corrigeert alle i en j waardes.
    private List<Point[]> makePairs(Point[][] board){
        List<Point> pounds = new ArrayList<>();
        for (int i = 0; i < board.length; i++){
            Point[] row = board[i];
            for (int j = 0; j < row.length; j++){
                Point p = row[j];
                p.i = i;
                p.j = j;
                if(p.val == '#'){
                    pounds.add(p);
                }
            }
        }
        List<Point[]> pairs = new ArrayList<>();
        while(!pounds.isEmpty()){
            Point p1 = pounds.remove(0);
            for (Point p2 : pounds){
//                if(p1 != p2) {
                    pairs.add(new Point[]{p1, p2});
//                }
            }
        }
        return pairs;
    }

    private int calculateRoutes(List<Point[]> pairs){
        int count = 0;
        for(Point[] pair : pairs){
            int i1 = pair[0].i;
            int i2 = pair[1].i;
            int j1 = pair[0].j;
            int j2 = pair[1].j;

                count += Math.max(i1,i2) - Math.min(i1,i2);
                count += Math.max(j1,j2) - Math.min(j1,j2);

        }
        return count;
    }

    // dubbel de lege rijen
    private Point[][] stretchRows(Point[][] board) {
        List<Point[]> newBoard = new ArrayList<>();
        for (Point[] row : board) {
            if (!containsPound(row)) {
                    for(int e = 0; e < expansionNumber-1; e++){
                        newBoard.add(row);
                    }
            }
            newBoard.add(row);
        }

        return newBoard.toArray(board);
    }

    // dubbel de lege kolommen
    private Point[][] stretchColumns(Point[][] board) {
        List<Point[]> newBoard = new ArrayList<>();
        for (int i = 0; i < board[0].length; i++) {
            Point[] col = getCol(board, i);
            if (!containsPound(col)) {
                for(int e = 0; e < expansionNumber-1; e++){
                    newBoard.add(col);
                }
            }
            newBoard.add(col);
        }
        Point[][] originalBoard = new Point[newBoard.get(0).length][newBoard.size()];
        for(int i = 0; i < newBoard.size(); i++){
            var col = newBoard.get(i);
            for(int j = 0; j < col.length; j++){
                originalBoard[j][i] = col[j];
            }
        }

        printBoard(originalBoard);
        return originalBoard;
    }

    // checkt of er een # in de rij of kolom staat
    private static boolean containsPound(Point[] set) {
        var containsPound = false;
        for (Point p : set) {
            if (p.val == '#') {
                containsPound = true;
            }
        }
        return containsPound;
    }

    // set alle Points uit eenzelfde kolom in een Point[]
    private Point[] getCol(Point[][] board, int j) {
        Point[] col = new Point[board.length];
        for (int x = 0; x < board.length; x++) {
            col[x] = board[x][j];
        }
        return col;
    }

    private void printBoard(Point[][] board) {
        if(part2){
            return;
        }
        int i = 0;
        System.out.print("   |");
        for (int j = 0; j < board[0].length; j++) {
            if (j < 10) {
                System.out.print(" " + j + " ");
            } else {
                System.out.print( j + " ");
            }
        }
        System.out.print("\n");
        for (Point[] points : board) {
            if (i < 10) {
                System.out.print(" ");
            }
            System.out.print(i + " |");
            for (Point p : points) {

                System.out.print("[" + p.val + "]");
            }
            System.out.print("\n");
            i++;
        }
    }



    public class Point{
        int i;
        int j;
        char val;
        int id;

        public Point(int i, int j, char val) {
            this.i = i;
            this.j = j;
            this.val = val;
            id = 0;
        }

        public Point(int i, int j, char val, int id) {
            this.i = i;
            this.j = j;
            this.val = val;
            this.id = id;
        }
    }


    @Override
    public Integer part2(List<String> input) {
        part2 = true;
        expansionNumber = 2;
        Point[][] board = new Point[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            char[] chars = s.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if(chars[j] == '#'){
                    poundCount++;
                    board[i][j] = new Point(i, j, chars[j], poundCount);
                } else {
                    board[i][j] = new Point(i, j, chars[j]);
                }
            }
        }

        board = stretchColumns(stretchRows(board));
        return calculateRoutes(makePairs(board));
    }
    
}
