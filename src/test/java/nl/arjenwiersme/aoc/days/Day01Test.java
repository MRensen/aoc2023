package nl.arjenwiersme.aoc.days;


import static org.junit.Assert.assertEquals;

import nl.arjenwiersme.aoc.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import nl.arjenwiersme.aoc.common.Day;

public class Day01Test {
    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(01);

    @Test
    public void part1() {
        Day<Integer> day = new Day01();
        assertEquals(Integer.valueOf(1), day.part1(input.getLines()));
    }
    
    @Test
    public void part2() {
        Day<Integer> day = new Day01();
        assertEquals(Integer.valueOf(1), day.part2(input.getLines()));
    }
}

