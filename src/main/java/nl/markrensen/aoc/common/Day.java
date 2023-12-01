package nl.markrensen.aoc.common;

import java.util.List;

public interface Day<T> {
    T part1(List<String> input);
    T part2(List<String> input);
}
