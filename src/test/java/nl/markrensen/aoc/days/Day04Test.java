package nl.markrensen.aoc.days;


import nl.markrensen.aoc.common.Day;
import nl.markrensen.aoc.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day04Test {
    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(04);

    @Test
    public void part1() {
        Day<Integer> day = new Day04();
        assertEquals(Integer.valueOf(13), day.part1(input.getLines()));
    }
    
    @Test
    public void part2() {
        Day<Integer> day = new Day04();
        assertEquals(Integer.valueOf(30), day.part2(input.getLines()));
    }
}

