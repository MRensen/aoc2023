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
        char[][] board = new char[input.size()][input.get(0).length()];
        int SI = -1;
        int SJ = -1;
        for(int i = 0; i < input.size(); i++){
            board[i] = input.get(i).toCharArray();
            int SJcheck = input.get(i).indexOf("S");
            if(SJcheck>-1){
                SJ = SJcheck;
                SI = i;
            }
        }

//        // check welke richting op
//        System.out.println(SI);
//        System.out.println(SJ);
//        System.out.println("i+1 => 7 ? " + board[SI+1][SJ]); //omlaag
//        System.out.println("i-1 => 7 ? " + board[SI-1][SJ]); //omhoog
//        System.out.println("j-1 => J ? " + board[SI][SJ-1]); //links
//        System.out.println("j+1 => - ? " + board[SI][SJ+1]); //rechts

        List<Integer> iList = new ArrayList<>();
        List<Integer> jList = new ArrayList<>();

        iList.add(SI);
        jList.add(SJ);

        // current coords
        int XI = -1;
        int XJ = -1;

        // previous coords
        int PXI = -1;
        int PXJ = -1;


        while(XI!=SI || XJ!=SJ){
            if(XI == -1 && XJ == -1){
                XI = SI;
                XJ = SJ;
            }
            char node = board[XI][XJ];
            if(node == STA){
                boolean first, second, third, fourth;

                try {
                    first = board[XI+1][XJ] == NE || board[XI+1][XJ] == NW|| board[XI+1][XJ] == VER;
                } catch (IndexOutOfBoundsException e){ first = false;}
                try {
                    second = board[XI-1][XJ] == SE || board[XI-1][XJ] == SW|| board[XI-1][XJ] == VER;
                } catch (IndexOutOfBoundsException e){second = false;}

                try{
                    third = board[XI][XJ-1] == NW || board[XI][XJ-1] == SW|| board[XI][XJ-1] == HOR;
                } catch (IndexOutOfBoundsException e){third = false;}

                try{
                    fourth = board[XI][XJ+1] == NE || board[XI][XJ+1] == SE|| board[XI][XJ+1] == HOR;
                } catch (IndexOutOfBoundsException e){fourth = false;}

                    if(first){ // move down
                        XI = XI+1;
                    } else if(second){  //move up
                        XI=XI-1;
                    } else if(third){ //move left
                        XJ = XJ-1;
                    } else if(fourth){  //move right
                        XJ = XJ+1;
                    }
                    PXI = SI;
                    PXJ = SJ;
            } else {
                switch (node){
                    case NE ->  {
                        if(XJ == PXJ) {
                            PXJ = XJ;
                            PXI = XI;
                            XJ++;
                        } else {
                            PXI = XI;
                            PXJ=XJ;
                            XI--;
                        }
                    }
                    case NW -> {
                        if(XJ == PXJ) {
                            PXJ = XJ;
                            PXI=XI;
                            XJ--;
                        } else {
                            PXI = XI;
                            PXJ=XJ;
                            XI--;
                        }
                    }
                    case SE -> {
                        if (XJ == PXJ) {
                            PXI = XI;
                            PXJ=XJ;
                            XJ++; /**/
                        } else {
                            PXJ = XJ;
                            PXI=XI;
                            XI++;
                        }
                    }
                    case SW -> {
                        if(XJ == PXJ){
                            PXI = XI;
                            PXJ=XJ;
                            XJ--;
                        } else {
                            PXJ=XJ;
                            PXI=XI;
                            XI++;
                        }
                    }
                    case HOR -> {
                        if(PXJ < XJ){
                            PXJ = XJ;
                            PXI=XI;
                            XJ++;
                        } else {
                            PXJ=XJ;
                            PXI=XI;
                            XJ--;
                        }
                    }
                    case VER -> {
                        if(PXI < XI){
                            PXI = XI;
                            PXJ=XJ;
                            XI++;
                        } else {
                            PXI = XI;
                            PXJ=XJ;
                            XI--;
                        }
                    }
                    case GRO -> throw new RuntimeException("hit the ground");
                    default -> throw new RuntimeException("should not hit default");
                }
            }
            iList.add(XI);
            jList.add(XJ);
        }


        return (long)jList.size()/2;
    }


    @Override
    public Long part2(List<String> input) {
        return null;
    }
}
