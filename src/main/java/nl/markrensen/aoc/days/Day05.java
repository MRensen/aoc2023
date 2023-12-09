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
        ArrayList<Long[][]> sourcesCollection = new ArrayList<>();
        List<Long[]> sources = new ArrayList<>();
        String section = "";
        ArrayList<Long[]> currentMapping = new ArrayList<>();
        for(String s : input){

            if(s.startsWith("seeds")){
                Long[] tempsources = Arrays.stream(s.substring(7).trim().split(" ")).map(Long::parseLong).toArray(Long[]::new);
                for(int i = 0; i < tempsources.length; i+=2){
                    sources.add(new Long[]{tempsources[i], tempsources[i+1]});
                }
                sourcesCollection.add(sources.toArray(Long[][]::new));
//                // test input seed met alleen "82"
//                sources = new ArrayList<>();
//                sources.add(new Long[]{82L,1L});
            }
            else if(!s.isEmpty()) {
                if (!s.contains(":")) {
                    currentMapping.add(Arrays.stream(s.split(" ")).map(Long::parseLong).toArray(Long[]::new));
                } else {
                    convertSources2(sources, currentMapping, section);
                    currentMapping = new ArrayList<>();
                    System.out.println();
                    sourcesCollection.add(sources.toArray(Long[][]::new));
                    section = s;
                }
            }
        }
        convertSources2(sources, currentMapping, section);
        sourcesCollection.add(sources.toArray(Long[][]::new));

//        Arrays.sort(sources);
        sources.sort((a,b)->{return a[0].compareTo(b[0]);});
        return sources.get(0)[0];
    }

    private void convertSources2(List<Long[]> sourcesList, List<Long[]> mappingList, String s){
        int SOURCESLENGTH = 2;
        Long[][] sourcesArray = sourcesList.toArray(Long[][]::new);
        if (mappingList.isEmpty()){
            return;
        }

        long first = 0L;
        for (int index = 0; index < sourcesArray.length; index++) {
            Long[] sourceListGet = sourcesList.get(index);
           //sourcerange (sourceMin en sourceMax) zijn de seeds.
            Long sourceMin = sourceListGet[0];
           Long sourceMax = sourceMin + (sourceListGet[1]-1);

           boolean convert = true;
               for (Long[] mapping : mappingList) {
                   // Destinationrange vanuit de mapping
                   Long destinationMin = mapping[0];
                   Long destinationMax = mapping[0] + mapping[2] -1;
                   // sourcesrange vanuit de mapping
                   Long sourcesMin = mapping[1];
                   Long sourcesMax = mapping[1] + mapping[2] -1;
                   // split sourcerange op in 3 delen:  - voor de sourcerange (hoeft niet gemapt te worden)
                   //                                   - na de sourcerange (hoeft niet gemapt te worden)
                   //                                   - in de sourcerange(moet gemapt worden van sourcesrange naar destinationrange)
                   Long beforeSourceMax = (sourceMin < sourcesMin) ?
                           (sourceMax < sourcesMin) ?
                                   sourceMax
                                   :
                                   sourcesMin - 1
                           : -1;
                   Long beforeSourceMin = beforeSourceMax == -1 ? -1 : sourceMin;
                   Long afterSourceMin = (sourceMax > sourcesMax) ?
                           (sourceMin < sourcesMax) ?
                                   sourcesMax + 1
                                   :
                                   sourceMin
                           :
                           -1;
                   Long afterSourceMax = afterSourceMin == -1 ? -1 : sourceMax;
//                   Long toInt = sourcesMax - sourcesMin;
                   Long[] sources = new Long[SOURCESLENGTH];
                   sources[0] =
                           sourceMin < sourcesMin ?
                                   //sourcerange begint voor de sourcesrange
                                   sourceMax < sourcesMin ?
                                           // sourcerange eindigt voor de sourcesrange (geen sources[] en geen afterSource)
                                           -1
                                           :
                                           // sourcerange eindigt in of na de sourcesrange
                                           sourcesMin
                                   :
                                   // sourcerange begint na of in de sourcesrange
                                   sourceMin <= sourcesMax ?
                                           //sourcerange begint in de sourcesrange
                                           sourceMin
                                           :
                                           //sourcerange begint na de sourcesrange (geen beforeSource en geen sources[])
                                           -1


                   ;
                   sources[1] =
                           sourceMax < sourcesMin ?
                                   // sourcerange eindigd voor de sourcesrange (geen sources[] en geen afterSource)
                                   -1
                                   :
                                   sourceMin > sourcesMax ?
                                           // sourcerange begint na de sourcesrange (geen beforeSource en geen sources[])
                                           -1
                                           :
                                           sourceMax > sourcesMax ?
                                                   sourcesMax
                                                   :
                                                   sourceMax

                   ;
//                   for (Long i = sourceStart; i < sourceEnd; i++) {
//                       Long sourcesIndex = i - sourceStart;
//                       sources[sourcesIndex.intValue()] = i;
//                   }
                   for (int source = 0; source < SOURCESLENGTH; source++) {
                       Long j = sourcesMax - sources[source] >= 0 ? sources[source] - sourcesMin : -1;
                       if (j < 0L) {
                           continue;
                       } else {
                           sources[source] = destinationMin + j;
                       }
                   }
                   boolean isSet = false;
                   if(s.contains("to-temperature")){
                       System.out.println();
                   }
                   for (Long[] toSet : new Long[][]{
                           new Long[]{sources[0], (sources[1]-sources[0])+1},
                           new Long[]{beforeSourceMin, (beforeSourceMax-beforeSourceMin)+1},
                           new Long[]{afterSourceMin, (afterSourceMax-afterSourceMin)+1}}) {
                       isSet = setSourcesList(sourcesList, index, toSet, isSet);
                   }
                   if(!Arrays.equals(sourcesArray[index],(sourcesList.get(index)))){
                       break;
                   }




                   System.out.println();
//                   if(beforeSourceMin >= 0 && beforeSourceMax>=0) {
//                        sourcesList.add(new Long[]{beforeSourceMin, beforeSourceMax});
//                    }
//                    if(afterSourceMin >= 0 && afterSourceMax>=0){
//                        sourcesList.add(new Long[]{afterSourceMin, afterSourceMax});
//                    }


           }
        }
        System.out.println();
    }

    private static boolean setSourcesList(List<Long[]> sourcesList, int index, Long[] sources, boolean isSet) {
        if(sources[0]>=0 && sources[1]>=0) {
            if(isSet) {
                sourcesList.add(sources);
            } else {
                sourcesList.set(index, sources);
            }
            return true;
        }
        return isSet;
    }


}



