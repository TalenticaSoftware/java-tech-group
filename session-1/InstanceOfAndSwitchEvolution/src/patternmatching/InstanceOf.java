package patternmatching;

import model.animal.Animal;
import model.animal.mammal.Cat;
import model.animal.mammal.Dog;

/**
 * Pattern Matching
 * This consists of 2 parts: Pattern & Matching
 * e.g.
 *  Type => instanceof XClass
 *  Record => instanceof XClass(int a)
 *  Array (future)
 *  in Type pattern for example
 *  o instanceof String s
 *  o is the target that is being matched with a type pattern [String]
 *  s is created only if o instanceof String is true
 *  String s => type of the pattern
 *  String => name of the pattern
 *
 */
public class InstanceOf {
  public String instanceOfBeforeJdk14(Animal animal){
    if(animal instanceof Dog){
      return ((Dog) animal).bark();
      // annoying but necessary;
      // as far as the compiler is concerned animal is still of type object, it won't magically change.
    }else if(animal instanceof Cat){
      return ((Cat) animal).mew();
    }
    return null;
  }

  /**
   * Compilation will fail for JDK before 14
   * preview feature since JDK 14, finalised in JDK 17, more patterns will be added ...
   * We should also mention that the variables cat and dog are only in scope
   * and assigned when the respective pattern match expressions return true.
   * Consequently, if we try to use either variable in another location, the code will generate compiler errors.
   * @param animal we check the instanceof this animal
   */
  public String instanceOfSinceJdk14(Animal animal){
    if(animal instanceof Dog dog){
      // SCOPE :: can use dog here
      return dog.bark();
    }else if(animal instanceof Cat cat){
      // SCOPE :: can't use dog here
      return cat.mew();
    }
    // SCOPE :: can't use dog here
    return null;
  }

  /**
   * Commented because compilation will fail for JDK before 19, without prefix enabled
   * Recording Matching is present as preview
   * Java will be supporting array matching etc.
   * @param object we check the instanceof this object
   */
//  public void instanceOfAfterJdk19(Object object){
//    if(object instanceof model.Book(var title, var edition)){
//      System.out.printf("This copy of %s is the %s edition.\n", title, edition);
//    }else{
//      throw new IllegalArgumentException("No idea what this is.");
//    }
//  }

}
