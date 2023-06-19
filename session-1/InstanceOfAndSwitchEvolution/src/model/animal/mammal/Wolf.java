package model.animal.mammal;

import model.animal.Animal;
import model.animal.AnimalType;

public class Wolf extends Animal {
  private final int id;
  private final int ageInYears;
  public Wolf(int id, int ageInYears){
    super(AnimalType.MAMMAL);
    this.id = id;
    this.ageInYears = ageInYears;
  }
  public String bark(){
    return "WOOF WOOF";
  }

  public int getId() {
    return id;
  }

  public int getAgeInYears() {
    return ageInYears;
  }
}
