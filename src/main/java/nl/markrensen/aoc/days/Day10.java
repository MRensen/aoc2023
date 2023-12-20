package nl.markrensen.aoc.days;

import nl.markrensen.aoc.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day10 implements Day<Long> {

    final char VER = '|';
    final char HOR = '-';
    final char NE = 'L';
    final char NW = 'J';
    final char SW = '7';
    final char SE = 'F';
    final char GRO = '.';
    final char STA = 'S';


    @Override
    public Long part1(List<String> input) {
        return parse(input, false);
    }


    @Override
    public Long part2(List<String> input) {
        return parse(input, true);
    }

    public enum Orientation{
        UP,DOWN,LEFT,RIGHT, NONE
    }
    public class Point{
        public int i;
        public int j;
        public char value;
        public Point previous;
        public Orientation orientation;
        public int id = -1;

        public Point(){
            this.i=-1;
            this.j=-1;
        };
        public Point(int i, int j, char value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }
    }


    private long parse(List<String> input, boolean part2) {
        Point[][] board = new Point[input.size()][input.get(0).length()];
        boolean[][] visited = new boolean[board.length][board[0].length];
//        int SI = -1;
//        int SJ = -1;
        Point S = new Point();
        for(int i = 0; i < input.size(); i++){
            char[] chars = input.get(i).toCharArray();
            for(int j = 0; j < input.get(0).length(); j++) {
                board[i][j] = new Point(i,j,chars[j]);
                int SJcheck = input.get(i).indexOf("S");
                if (SJcheck > -1) {
                    S.j = SJcheck;
                    S.i = i;
                    S.value = 'S';
                }
            }
        }

//        // check welke richting op
//        System.out.println(SI);
//        System.out.println(SJ);
//        System.out.println("i+1 => 7 ? " + board[SI+1][SJ]); //omlaag
//        System.out.println("i-1 => 7 ? " + board[SI-1][SJ]); //omhoog
//        System.out.println("j-1 => J ? " + board[SI][SJ-1]); //links
//        System.out.println("j+1 => - ? " + board[SI][SJ+1]); //rechts

        /*
        i+ -> omlaag
        i- -> omhoog
        j+ -> rechts
        j- -> links
         */

//        List<Integer> iList = new ArrayList<>();
//        List<Integer> jList = new ArrayList<>();
        List<Point> pList = new ArrayList<>();


//        iList.add(SI);
//        jList.add(SJ);
        pList.add(S);

        // current coords
//        int X.i = -1;
//        int X.j = -1;
        Point X = new Point(-1,-1,'0');
        X.previous = new Point(-1,-1,'0');

        // previous coords
//        int X.previous.i = -1;
//        int X.previous.j = -1;


        while(X.i!=S.i || X.j!=S.j){
            if(X.i == -1 && X.j == -1){
                X.i = S.i;
                X.j = S.j;
            }
            char node = board[X.i][X.j].value;
            if(node == STA){
                boolean first, second, third, fourth;

                try {
                    first = board[X.i+1][X.j].value == NE || board[X.i+1][X.j].value == NW|| board[X.i+1][X.j].value == VER;
                } catch (IndexOutOfBoundsException e){ first = false;}
                try {
                    second = board[X.i-1][X.j].value == SE || board[X.i-1][X.j].value == SW|| board[X.i-1][X.j].value == VER;
                } catch (IndexOutOfBoundsException e){second = false;}

                try{
                    third = board[X.i][X.j-1].value == NW || board[X.i][X.j-1].value == SW|| board[X.i][X.j-1].value == HOR;
                } catch (IndexOutOfBoundsException e){third = false;}

                try{
                    fourth = board[X.i][X.j+1].value == NE || board[X.i][X.j+1].value == SE|| board[X.i][X.j+1].value == HOR;
                } catch (IndexOutOfBoundsException e){fourth = false;}

                if(first){ // move down
                    X.i = X.i+1;
                } else if(second){  //move up
                    X.i=X.i-1;
                } else if(third){ //move left
                    X.j = X.j-1;
                } else if(fourth){  //move right
                    X.j = X.j+1;
                }
                X.previous.i = S.i;
                X.previous.j = S.j;
            } else {
                switch (node){
                    case NE ->  {
                        if(X.j == X.previous.j) {
                            X.previous.j = X.j;
                            X.previous.i = X.i;
                            X.j++;
                        } else {
                            X.previous.i = X.i;
                            X.previous.j=X.j;
                            X.i--;
                        }
                    }
                    case NW -> {
                        if(X.j == X.previous.j) {
                            X.previous.j = X.j;
                            X.previous.i=X.i;
                            X.j--;
                        } else {
                            X.previous.i = X.i;
                            X.previous.j=X.j;
                            X.i--;
                        }
                    }
                    case SE -> {
                        if (X.j == X.previous.j) {
                            X.previous.i = X.i;
                            X.previous.j=X.j;
                            X.j++; /**/
                        } else {
                            X.previous.j = X.j;
                            X.previous.i=X.i;
                            X.i++;
                        }
                    }
                    case SW -> {
                        if(X.j == X.previous.j){
                            X.previous.i = X.i;
                            X.previous.j=X.j;
                            X.j--;
                        } else {
                            X.previous.j=X.j;
                            X.previous.i=X.i;
                            X.i++;
                        }
                    }
                    case HOR -> {
                        if(X.previous.j < X.j){
                            X.previous.j = X.j;
                            X.previous.i=X.i;
                            X.j++;
                        } else {
                            X.previous.j=X.j;
                            X.previous.i=X.i;
                            X.j--;
                        }
                    }
                    case VER -> {
                        if(X.previous.i < X.i){
                            X.previous.i = X.i;
                            X.previous.j=X.j;
                            X.i++;
                        } else {
                            X.previous.i = X.i;
                            X.previous.j=X.j;
                            X.i--;
                        }
                    }
                    case GRO -> throw new RuntimeException("hit the ground");
                    default -> throw new RuntimeException("should not hit default");
                }
            }
            Point p = board[X.i][X.j];
            visited[X.i][X.j] = true;
            p.previous = pList.get(pList.size()-1);
            p.id = 0;
            p.orientation = getOrientationLeft(X);
            pList.add(p);
        }

        // close the loop.
        S.previous = board[X.i][X.j];
        S.orientation = getOrientationLeft(S);

        pList.remove(0);

        if(part2){
//            boolean keepgoing = true;
//            int currentId = 0;
//            while (keepgoing){
//                boolean[][] visited = new boolean[board.length][board[0].length];
//                for(Point[] row : board){
//                    Point usePoint = null;
//                    for (Point p : row){
//                        if(isValidPoint(p, board, -1, visited)){
//                            usePoint = p;
//                        }
//                    }
//                    if(usePoint == null){
//                        keepgoing = false;
//                        break;
//                    } else {
//                        currentId++;
//                        visited = new boolean[board.length][board[0].length];
//                        floodFill(usePoint, currentId, board, visited);
//                    }
//                }
//            }

            boolean keepgoing = true;


//            while (keepgoing) {
            int id = 1;
                for(Point p : pList.reversed()){
//                    Point p = pList.get(i);
                    checkLeft(p, id, board, visited);
                }

//                keepgoing = containsUnvisited(board);
//            }


            // loop while still -1 id's
            // do fill-algoritm as soon as you find a -1 id to change all -1 id's in reach to a new id (2)
            // id++
            // do another search for for a -1 id and do a fill algortim on that to change the id (to 3)
            // id++
            // etc
            // check how many different id's have been used and count how many fields each id has. (id 1 is for the tubes in the actual loop)
            // combine and make a guess.

            printboard(board);
                return countnests(board);
        }



        return (long) pList.size() / 2;
    }

    private long countnests(Point[][] board) {
        long count = 0L;
        for (Point[] ps : board){
            for (Point p: ps){
                if(p.id == 1 && p.i >0 && p.j > 0){
                    count++;
                }
                // 441 -> too high
                // 341 -> wrong
                // 162 -> too low
            }
        }
        return count;
    }

    private void checkLeft(Point p, int id, Point[][] board, boolean[][] visited) {
        Point leftp = null;
              /*
        i+ -> omlaag
        i- -> omhoog
        j+ -> rechts
        j- -> links
         */
        if(p.i==8 && p.j==5){
            System.out.println();
        }
//        System.out.println("( " + p.i + " , " + p.j + " )");
//        printboard(board);
        switch(p.orientation){
            case LEFT -> {
                if (isValid(p.i+1, p.j, board, visited)) {
//                    visited[p.i+1][p.j] = true;
                    leftp = board[p.i+1][p.j];
                }
            }
            case RIGHT -> {
                if(isValid(p.i-1, p.j, board, visited)){
//                    visited[p.i-1][p.j] = true;
                    leftp = board[p.i-1][p.j];
                }
            }
            case UP -> {
                if(isValid(p.i, p.j-1, board, visited)){
//                    visited[p.i][p.j-1] = true;
                    leftp = board[p.i][p.j-1];
                }
            }
            case DOWN -> {
                if(isValid(p.i, p.j+1, board, visited)){
//                    visited[p.i][p.j+1] = true;
                    leftp = board[p.i][p.j+1];
                }
            }
            default -> throw new RuntimeException("what is going on?!!!");
        }
        if(leftp != null) {
            try {
                List<Point> toPaint = floodFill(leftp, id, board, visited);
                for(Point paint : toPaint){
                    paint.id=1;
                }
            }catch(FloodException ignore){}
        }
    }

    private boolean containsUnvisited(Point[][] board){
        for(Point[] ps : board){
            for(Point p : ps){
                if(p.id == -1){
                    return true;
                }
            }
        }
        return false;
    }

    private Orientation getOrientationLeft(Point x){
                 /*
        i+ -> omlaag
        i- -> omhoog
        j+ -> rechts
        j- -> links
         */

        if(x.previous.i > x.i){
            return Orientation.UP;
        }
        if(x.previous.i < x.i){
            return Orientation.DOWN;
        }
        if(x.previous.j < x.j){
            return Orientation.RIGHT;
        }
        if(x.previous.j > x.j){
            return Orientation.LEFT;
        }


        return Orientation.NONE;
    }

    private Orientation getOrientationRight(Point x) {
                /*
        i+ -> omlaag
        i- -> omhoog
        j+ -> rechts
        j- -> links
         */

        if(x.previous.i > x.i){
            return Orientation.DOWN;
        }
        if(x.previous.i < x.i){
            return Orientation.UP;
        }
        if(x.previous.j < x.j){
            return Orientation.LEFT;
        }
        if(x.previous.j > x.j){
            return Orientation.RIGHT;
        }


        return Orientation.NONE;
    }

    private class FloodException extends RuntimeException{
    }
    private List<Point> floodFill (Point p, int id, Point[][] board, boolean[][] visited){
        List<Point> points = new ArrayList<>();
        int[] deltai = new int[]{1,-1,0,0};
        int[] deltaj = new int[]{0,0,1,-1};
        visited[p.i][p.j] = true;
        //TODO set "id" or "1"
        p.id = 1;
        for(int i = 0; i < deltai.length; i++){
            int newi =p.i + deltai[i];
            int newj = p.j + deltaj[i];
            if(isValid(newi, newj, board, visited)){
                Point n = board[newi][newj];
                points.addAll(floodFill(n, id, board, visited));
            } else if(newi < 1 || newj < 1 || newi > board.length -1 || newj > board[0].length -1){
                throw new FloodException();
            }
        }
        return points;
    }

    private boolean isValid(int i, int j, Point[][] board, boolean[][] visited){
        if (i < 1 || i >= board.length-1){
            return false;
        }
        if (j < 1 || j >= board[0].length-1){
            return false;
        }
        Point p = board[i][j];
        if(p.id != -1 || visited[p.i][p.j]){
            return false;
        }
        return true;
    }

    private boolean isValidPoint(Point p, Point[][] board, int id, boolean[][] visited){
        if (p.i < 0 || p.i > board.length){
            return false;
        }
        if (p.j < 0 || p.j > board[0].length){
            return false;
        }
        if(p.id != id || visited[p.i][p.j]){
            return false;
        }
        return true;
    }

    private void printboard(Point[][] board){
        int i = 0;
        System.out.print("   |");
        for(int j = 0; j < board[0].length; j++){
            if(j<10){
                System.out.print(" " + j);
            } else{
                System.out.print(j);
            }
        }
        System.out.print("\n");
        for(Point[] points : board){
            if(i < 10){
                System.out.print(" ");
            }
            System.out.print(i + " |");
            for (Point p : points){
                if(p.id>=0){
                    System.out.print(" ");
                }
                System.out.print(p.id);
            }
            System.out.print("\n");
            i++;
        }
    }
}


