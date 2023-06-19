package model.animal.mammal;

import model.animal.Animal;
import model.animal.AnimalType;

public class Lion extends Animal {
  private boolean isCaptive;

  public Lion(boolean isCaptive) {
    super(AnimalType.MAMMAL);
    this.isCaptive = isCaptive;
  }

  public String roar(){
    return "RAWR";
  }

  public String growl(){
    return "growl";
  }

  public boolean isCaptive() {
    return isCaptive;
  }
}
