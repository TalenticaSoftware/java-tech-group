package org.talentica.records;

public class MathServiceV2 {

  private record Coordinate(int x, int y) {};

  public Coordinate getCoordinates() {
    return new Coordinate(5, 6 );
  }

  public void doSomething() {
    Coordinate coordinate = getCoordinates();
    int x = coordinate.x();
    int y = coordinate.y();
  }

}