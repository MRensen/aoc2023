package nl.markrensen.aoc.days;

import nl.markrensen.aoc.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day07 implements Day<Long> {
    List<Character> cards = new ArrayList<>(List.of('2','3','4','5','6','7','8','9','T','J','Q','K','A'));
    List<Character> cards2 = new ArrayList<>(List.of('J','2','3','4','5','6','7','8','9','T','Q','K','A'));

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
            if(h.type == 3){
                System.out.println();
            }
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
            this.cardString = getCardString(cardvalues, false);
            this.bid = bid;
            this.type = getType(cardvalues, false);
        }
        public Hand(Integer[] cardvalues, int bid, boolean part2){
            this.cardvalues = cardvalues;
            this.cardString = getCardString(cardvalues, true);
            this.bid = bid;
            int amountJ = 0;
            for(char s : cardString.toCharArray()){
                if(s == 'J'){
                    amountJ++;
                }
            }
            if(amountJ > 0){
                List<Integer> types = new ArrayList<>();
                for(int index = 0; index < cards2.size(); index++) {
                    Integer[] newCardValue = new Integer[cardvalues.length];
                    for (int i = 0; i < cardvalues.length; i++) {
                        if (cardvalues[i] == 0) {
                            newCardValue[i] = index;
                        } else {
                            newCardValue[i] = cardvalues[i];
                        }
                    }
                    types.add(getType(newCardValue, true));
                }
                types.sort(Integer::compareTo);
                this.type = types.get(types.size()-1);
            } else {
                this.type = getType(cardvalues, true);
            }
        }

        private String getCardString(Integer[] cardvalues, boolean part2){
            StringBuilder sb = new StringBuilder();
            for(int i : cardvalues){
                if(part2){
                    sb.append(cards2.get(i));
                }else {
                    sb.append(cards.get(i));
                }
            }
            return sb.toString();
        }

        private Integer getType(Integer[] cardvalues, boolean part2){
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
                if ((setAmountOne.equals(2) && setAmountTwo.equals(3)) || (setAmountOne.equals(3) && setAmountTwo.equals(2))) {
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
        List<Hand> hands = new ArrayList<>();
        for(String s : input){
            String[] play = s.split(" ");
            char[] handChars = play[0].trim().toCharArray();
            Integer bid = Integer.parseInt(play[1].trim());
            Integer[] hand = new Integer[play[0].length()];
            for(int i = 0; i < handChars.length; i++){
                hand[i] = cards2.indexOf(handChars[i]);
            }
            hands.add(new Hand(hand, bid, true));
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
            if(h.type == 3){
                System.out.println();
            }
        }

        return result;
    }



}



