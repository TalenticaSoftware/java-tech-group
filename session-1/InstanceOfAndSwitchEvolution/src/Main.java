import model.Book;
import model.animal.Animal;
import model.animal.mammal.Cat;
import model.animal.mammal.Dog;
import model.animal.mammal.Lion;
import patternmatching.InstanceOf;
import patternmatching.SwitchEvolution;

public class Main {
  public static void main(String[] args) {
    InstanceOf instanceOfRun = new InstanceOf();

    Dog dog = new Dog(1, 3);
    Cat cat = new Cat();
    // Will only work JDK 8 onwards
    System.out.println(instanceOfRun.instanceOfBeforeJdk14(dog));
    // Will only work JDK 14 onwards
    System.out.println(instanceOfRun.instanceOfSinceJdk14(dog));
    // Will only work JDK 19 onwards
    Book book = new Book("Hamlet", "Third");
    //instanceOfRun.instanceOfAfterJdk19(book);

    SwitchEvolution switchEvolution = new SwitchEvolution();

    // multi case
    System.out.println(switchEvolution.multipleCasesBeforeJdk12(cat));
    System.out.println(switchEvolution.multipleCasesAfterJdk12(cat));
    System.out.println(switchEvolution.multipleCasesAfterJdk14(cat));

    // switch expression + pattern matching with guarded pattern
    Lion lion = new Lion(false);
    System.out.println(switchEvolution.beforeJdk14(lion));
    System.out.println(switchEvolution.switchSinceJdk17(lion));

    // switch expression + null handling
    System.out.println(switchEvolution.switchSinceJdk17(null));
  }
}