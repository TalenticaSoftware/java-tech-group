package org.talentica.records;

public class MathServiceV1 {

  private int[] getCoordinates() {
    return new int[] {5, 6};
  }

  public void doSomething() {
    int[] coordinates = getCoordinates();
    int x = coordinates[0];
    int y = coordinates[1];
  }

}
