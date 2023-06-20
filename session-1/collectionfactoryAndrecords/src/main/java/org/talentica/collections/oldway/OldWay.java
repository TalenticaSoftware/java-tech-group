package org.talentica.collections.oldway;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OldWay {

  public static void main(String[] args) {
    //Verbose
    Set<String> mySet = new HashSet<>();
    mySet.add("Foo");
    mySet.add("Bar");

    Map<String, Integer> myMap = new HashMap<>();
    myMap.put("Foo", 1);
    myMap.put("Bar", 2);

    List<String> myList1 = new ArrayList<>();
    myList1.add("Foo");
    myList1.add("Bar");

    //Better, but not obvious since you are using the Arrays helper class to create a List!
    List<String> myList = Arrays.asList("Foo", "Bar");

    //Using Streams
    Set<String> mySet2 = Stream.of("Foo", "Bar")
        .collect(Collectors.toSet());

  }

}
