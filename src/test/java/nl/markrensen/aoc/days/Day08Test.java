package nl.markrensen.aoc.days;


import nl.markrensen.aoc.common.Day;
import nl.markrensen.aoc.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day08Test {
    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(8);

    @Test
    public void part1() {
        Day<Long> day = new Day08();
        assertEquals(Long.valueOf(6440), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Long> day = new Day08();
        assertEquals(Long.valueOf(46), day.part2(input.getLines()));
    }
}

