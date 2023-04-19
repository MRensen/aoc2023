#!/bin/bash

DAY=$(printf %02d $1)

TARGET=src/main/java/nl/arjenwiersma/aoc/days/Day$DAY.java

if [ -f "$TARGET" ]; then
    echo "Watching for changes in $TARGET (update Day$DAY.java to run its tests)"
else 
    echo "$TARGET does not exist, exiting"
    exit
fi

inotifywait -q -m -e close_write $TARGET |
    while read -r filename event; do
        ./mvnw -q test -Dtest="nl.arjenwiersma.aoc.days.Day${DAY}Test"
    done
