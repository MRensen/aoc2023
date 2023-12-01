package nl.markrensen.aoc.days;


import static org.junit.Assert.assertEquals;

import nl.markrensen.aoc.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import nl.markrensen.aoc.common.Day;

public class Day01Test {
    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(01);

    @Test
    public void part1() {
        Day<Integer> day = new Day01();
        assertEquals(Integer.valueOf(142), day.part1(input.getLines()));
    }
    
    @Test
    public void part2() {
        Day<Integer> day = new Day01();
        assertEquals(Integer.valueOf(281), day.part2(input.getLines()));
    }
}

