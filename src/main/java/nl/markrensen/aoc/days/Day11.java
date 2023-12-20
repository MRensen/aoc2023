package nl.markrensen.aoc.days;

import nl.markrensen.aoc.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day11 implements Day<Long> {

    private int poundCount = 0;
    private int expansionNumber = 2;

    @Override
    public Long part1(List<String> input) {
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
        return (long) calculateRoutes(makePairs(board));


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
        int i = 1;
        for(Point[] pair : pairs){
            int i1 = pair[0].i;
            int i2 = pair[1].i;
            int j1 = pair[0].j;
            int j2 = pair[1].j;

                int counti = Math.max(i1,i2) - Math.min(i1,i2);
                int countj = Math.max(j1,j2) - Math.min(j1,j2);
            System.out.println(i + " : " + (counti+countj));
            i++;
            count = count+counti+countj;

        }
        return count;
    }

    // dubbel de lege rijen
    private Point[][] stretchRows(Point[][] board) {
        List<Point[]> newBoard = new ArrayList<>();
        for (Point[] row : board) {
            if (!containsPound(row)) {
                newBoard.add(row);
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
                    newBoard.add(col);
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
    public Long part2(List<String> input) {
        // hoeveel rijden moeten er tussen worden gezet?
        expansionNumber = 1000000;
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

        // Ga niet meer werkelijk het board stretchen (dan krijg je heap overflow), maar bereken de hypothetische stretch
//        board = stretchColumns(stretchRows(board));
        return calculateRoutes2(makePairs(board), board);
    }

    private static int globalCounter = 0; // testspul
    private long calculateRoutes2(List<Point[]> pairs, Point[][] board){
        List<Integer> emptyCols = findEmptyCols(board);
        List<Integer> emptyRows = findEmptyRows(board);
        long count = 0;
        for(Point[] pair : pairs){
            globalCounter ++;
            int i1 = pair[0].i;
            int i2 = pair[1].i;
            int j1 = pair[0].j;
            int j2 = pair[1].j;
            int maxi = Math.max(i1,i2);
            int mini = Math.min(i1,i2);
            int maxj = Math.max(j1,j2);
            int minj = Math.min(j1,j2);

            // bereken of er een wit kolom.rij tussen min en max zit en dus of en hoevaak er 100000 bij opgeteld moet worden.
            long[] imult;
            imult = getMult(emptyRows, maxi, mini);
            long[] jmult;
            jmult = getMult(emptyCols, maxj, minj);

            // Tel 1000000 er bij op zo vaak er witruite tussen zit.
            int correctionNumber = expansionNumber -1; // Dit heb ik gedaan om dat 1 miljoen makkelijker is dan 1 miljoen -1
            long counti = (maxi + (correctionNumber*imult[0])) - (mini + (correctionNumber*imult[1]));
            long countj = (maxj + (correctionNumber*jmult[0])) - (minj + (correctionNumber*jmult[1]));

            long toAdd = counti + countj;
//            System.out.println(globalCounter + " : " + toAdd); //testspul
            count = count + toAdd;
//            if(globalCounter == 6){ //testspul
//                System.out.println();
//            }
        }
        return count;
    }

    private static long[] getMult(List<Integer> emptyRows, int max, int min) {
        for(int i = 0; i < emptyRows.size(); i++){
            if((min >= emptyRows.get(i) && i == emptyRows.size()-1) || min >= emptyRows.get(i) && min <= emptyRows.get(i+1)){
                for(Integer rowend : emptyRows){
                   if(max <= rowend){
                       return new long[]{emptyRows.indexOf(rowend)-1, emptyRows.indexOf(emptyRows.get(i))};
                   }
                }
                return new long[]{emptyRows.size()-1, emptyRows.indexOf(emptyRows.get(i))};
            }
        }
        if(max > emptyRows.get(0)) {
            for (Integer row : emptyRows) {
                if (max <= row) {
                    return new long[]{emptyRows.indexOf(row), 0};
                }
            }
            return new long[]{emptyRows.size(), 0};
        } else {
            return new long[]{0, 0};
        }
    }

    // gebaseerd op "stretchRows"
    private List<Integer> findEmptyRows(Point[][] board) {
        List<Integer> rows = new ArrayList<>();
        for (int i = 0; i < board.length; i++){
            if(!containsPound(board[i])){
                rows.add(i);
            }
        }
        return rows;
    }

    // gebaseerd op "StretchCols"
    private List<Integer> findEmptyCols(Point[][] board) {
        List<Integer> cols = new ArrayList<>();
        List<Point[]> newBoard = new ArrayList<>();
        for (int i = 0; i < board[0].length; i++) {
            Point[] col = getCol(board, i);
            newBoard.add(col);
        }
        for(int i = 0; i < newBoard.size(); i++){
            if(!containsPound(newBoard.get(i))){
                cols.add(i);
            }
        }
        return cols;
    }


}
