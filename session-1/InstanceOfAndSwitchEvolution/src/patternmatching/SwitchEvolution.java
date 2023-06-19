package patternmatching;

import model.animal.Animal;
import model.animal.mammal.Cat;
import model.animal.mammal.Dog;
import model.animal.mammal.Lion;

public class SwitchEvolution {
  /**
   * JDK 7 Only supported Integers
   * JDK 8 added support for Strings & Enums
   */
  public String multipleCasesBeforeJdk12(Animal animal){
    String flightAbility = null;
    switch (animal.getAnimalType()){ // old multi case
      case AMPHIBIAN:
      case FISH:
      case MAMMAL:
      case REPTILE: flightAbility= "Most can't fly."; break;
      case BIRD: flightAbility = "MOST CAN FLY"; break;
    }
    return flightAbility;
  }

  /**
   * e.g. Switch Expression (with yield)
   * Introduced  as preview in JDK 12, enhanced in JDK 13, finalised in JDK 14
   * @param animal switch over its type
   * @return flight commentary
   */
  public String multipleCasesAfterJdk12(Animal animal){
    return switch (animal.getAnimalType()){
      case AMPHIBIAN:
      case FISH:
      case MAMMAL:
      case REPTILE: yield "Most can't fly.";
      case BIRD: yield "MOST CAN FLY";
    };
  }

  /**
   * e.g. Switch Expression (with arrow) + multiple cases
   * @param animal switch over its type
   * @return flight commentary
   */
  public String multipleCasesAfterJdk14(Animal animal){
    return switch (animal.getAnimalType()) { // old multi case
      case AMPHIBIAN, FISH, MAMMAL, REPTILE -> "Most can't fly.";
      case BIRD -> "MOST CAN FLY";
    };
  }



  /**
   * e.g. Conditionals on object before JDK 14 : switch can't support nulls, objects
   * @param animal we're performing conditional checks on this object
   * @return sound that animal makes
   */
  public String beforeJdk14(Animal animal){
    String sound;
    if (animal == null) {
      sound = null;
    } else if (animal instanceof Dog) {
      sound = ((Dog) animal).bark();
    } else if (animal instanceof Cat) {
      sound = ((Cat) animal).mew();
    } else if (animal instanceof Lion && ((Lion) animal).isCaptive()) {
      sound = ((Lion) animal).growl();
      // there is more to be done
    } else if (animal instanceof Lion && !((Lion) animal).isCaptive()) {
      sound = ((Lion) animal).roar();
      // there is more to be done
    } else {
      sound = "NO SOUND";
    }
    return sound;
  }

  /**
   * e.g. Conditionals on object after JDK 17 (finalised) :
   * switch can support nulls, objects, type pattern matching with guarded pattern
   * @param animal we're performing conditional checks on this object
   * @return sound that animal makes
   */
  public String switchSinceJdk17(Animal animal) {
    return switch (animal) {
      case null -> null; // we can now handle nulls!
      case Dog dog -> dog.bark(); // simple type pattern matching
      case Cat cat -> cat.mew();
      case Lion lion && lion.isCaptive() -> lion.growl(); // type pattern matching with guarded pattern
      case Lion lion && !lion.isCaptive() -> lion.roar();
      default -> "SILENCE";
    };
  }
}
