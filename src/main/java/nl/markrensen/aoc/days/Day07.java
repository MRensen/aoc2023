package nl.markrensen.aoc.days;

import nl.markrensen.aoc.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day07 implements Day<Long> {
    List<Character> cards = new ArrayList<>(List.of('2','3','4','5','6','7','8','9','T','J','Q','K','A'));

    @Override
    public Long part1(List<String> input) {
        List<Hand> hands = new ArrayList<>();
        for(String s : input){
            String[] play = s.split(" ");
            char[] handChars = play[0].trim().toCharArray();
            Integer bid = Integer.parseInt(play[1].trim());
            Integer[] hand = new Integer[play[0].length()];
            for(int i = 0; i < handChars.length; i++){
                hand[i] = cards.indexOf(handChars[i]);
            }
            hands.add(new Hand(hand, bid));
        }
        hands.sort((h1, h2)->{
            if(h1.type.equals(h2.type)){

                for(int i = 0; i < h1.cardvalues.length; i++) {
                    if (!h1.cardvalues[i].equals(h2.cardvalues[i])) {
                        return h1.cardvalues[i].compareTo(h2.cardvalues[i]);
                    }
                }
            } else {
                return h1.type.compareTo(h2.type);
            }
            return 0;
        });

        long result = 0L;

        for(int i = 0; i < hands.size(); i++){
            Hand h = hands.get(i);
            h.bidReturn = (long) h.bid * (i+1);
            result += h.bidReturn;
        }

    return result;
    }

    public class Hand{
        Integer[] cardvalues;
        String cardString = "";
        int bid;
        Integer type;
        long bidReturn;

        public Hand(Integer[] cardvalues, int bid) {
            this.cardvalues = cardvalues;
            this.cardString = getCardString(cardvalues);
            this.bid = bid;
            this.type = getType(cardvalues);
        }

        private String getCardString(Integer[] cardvalues){
            StringBuilder sb = new StringBuilder();
            for(int i : cardvalues){
                sb.append(cards.get(i));
            }
            return sb.toString();
        }

        private Integer getType(Integer[] cardvalues){
            List<Integer> valueList = new ArrayList<>();
             Integer setValueOne = null;
             Integer setValueTwo = null;
             Integer setAmountOne = null;
             Integer setAmountTwo = null;
            for (Integer value : cardvalues){
                if(!valueList.contains(value)){
                    valueList.add(value);
                } else{
                    if(setValueOne==null){
                        setValueOne = value;
                        setAmountOne = 2;
                    } else {
                        if(setValueOne.equals(value)){
                            setAmountOne++;
                        } else if (setValueTwo != null){
                            if(setValueTwo.equals(value)){
                                setAmountTwo++;
                            } else {
                                throw new RuntimeException("Not supposed to get here");
                            }
                        } else {
                            setValueTwo = value;
                            setAmountTwo = 2;
                        }
                    }
                }
            }
            if(setAmountOne == null){
                // high card
                return 0;
            }
            assert setAmountOne <= 5;
            if(setAmountOne.equals(5)){
                // 5 of a kind
                return 6;
            }
            if(setAmountOne.equals(4)){
                // 4 of a kind
                return 5;
            }
            if(setAmountTwo != null) {
                if ((setValueOne.equals(2) && setAmountTwo.equals(3)) || (setAmountOne.equals(3) && setAmountTwo.equals(2))) {
                    // full house
                    return 4;
                }
                if(setAmountTwo.equals(3)){
                    // 3 of a kind
                    return 3;
                }
                if(setAmountOne.equals(2) && setAmountTwo.equals(2)){
                    // 2 pair
                    return 2;
                }
                if(setAmountTwo.equals(2)){
                    // 1 pair
                    return 2;
                }

            }
            if(setAmountOne.equals(3)){
                // 3 of a kind
                return 3;
            }

            if(setAmountOne.equals(2)){
                // 1 pair
                return 1;
            }

            throw new RuntimeException("could not determine type");
        }
    }



    @Override
    public Long part2(List<String> input) {

       return null;
    }


}



