package model.animal;

public class Animal {
  private AnimalType animalType;

  public Animal(AnimalType animalType) {
    this.animalType = animalType;
  }

  public AnimalType getAnimalType() {
    return animalType;
  }
}
