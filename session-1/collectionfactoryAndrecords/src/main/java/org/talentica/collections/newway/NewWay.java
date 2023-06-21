package org.talentica.collections.newway;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class NewWay {

  public static void main(String[] args) {

    List<String> myList1 = List.of("Foo", "Bar");
    List<String> myList2 = List.of("Foo", "Bar", "FooBar");
    List<Integer> myList3 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

    Set<String> mySet = Set.of("Foo", "Bar");

    Map<String, Integer> myMap = Map.of("Foo", 1, "Bar", 2);
  }

}
