package model.animal.mammal;

import model.animal.Animal;
import model.animal.AnimalType;

public class Cat extends Animal {
  public Cat() {
    super(AnimalType.MAMMAL);
  }

  public String mew(){
    return "MEAOW";
  }
}
