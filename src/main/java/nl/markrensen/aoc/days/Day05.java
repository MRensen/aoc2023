package nl.markrensen.aoc.days;

import nl.markrensen.aoc.common.Day;

import java.util.*;

public class Day05 implements Day<Long> {

    @Override
    public Long part1(List<String> input) {
        ArrayList<Long[]> sourcesCollection = new ArrayList<>();
        Long[] sources = new Long[10];
        String section = "";
        ArrayList<Long[]> currentMapping = new ArrayList<>();
        for(String s : input){

            if(s.startsWith("seeds")){
                sources = Arrays.stream(s.substring(7).trim().split(" ")).map(Long::parseLong).toArray(Long[]::new);
            }
            else if(!s.isEmpty()) {
                    if (!s.contains(":")) {
                        currentMapping.add(Arrays.stream(s.split(" ")).map(Long::parseLong).toArray(Long[]::new));
                    } else {
                        convertSources(sources, currentMapping, section);
                        currentMapping = new ArrayList<>();
                        System.out.println();
                        sourcesCollection.add(Arrays.copyOf(sources, sources.length));
                        section = s;
                    }
            }
        }

        Arrays.sort(sources);
        return sources[0];
    }

    private void convertSources(Long[] sources, List<Long[]> mappingList, String s){
        if (mappingList.isEmpty()){
            return;
        }
        for (int source = 0; source < sources.length; source++) {
            for (Long[] mapping : mappingList) {
//                Long[] destinationRange = new Long[mapping[2].intValue()];
//                Long[] sourcesRange = new Long[mapping[2].intValue()];
                Long destinationMin = mapping[0];
                Long destinationMax = mapping[0]+mapping[2];
                Long sourcesMin = mapping[1];
                Long sourcesMax = mapping[1] + mapping[2];
//                for (int i = 0; i < mapping[2]; i++) {
//                    destinationRange[i] = mapping[0] + i;
//                    sourcesRange[i] = mapping[1] + i;
//                }

                Long j = sourcesMax - sources[source] >= 0?sources[source] - sourcesMin:-1;

//                int j = java.util.Arrays.asList(sourcesRange).indexOf(sources[source]);
                if (j < 0L) {
                    continue;
                } else {
                    sources[source] = destinationMin+j;
                    break;
                }
//                if (sources[source].equals(sourcesRange[j])) {
//                    sources[source] = destinationRange[j];
//                    break;
//                }

            }
        }
        System.out.println();
    }



    @Override
    public Long part2(List<String> input) {

        return null;
    }




}



