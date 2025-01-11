package org.wigs;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StrOccurrenceFromListJava8 {

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("Chetan", "Jaipur", "Raj", "Chetan", "Jaipur", "Jaipur");

        Map<String, Long> occurrences = strs.stream().
                collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        System.out.println("occurrences : "+occurrences);
    }
}
