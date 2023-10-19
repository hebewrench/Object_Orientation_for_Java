class Pet {
	public void animalFact() {
	    System.out.println("Pets are real");
	  }
}

class Fish extends Pet {
  public void animalSound() {
    System.out.println("There are many fish in the sea");
  }
}

class Dog extends Pet {
  public void animalSound() {
    System.out.println("Dogs can be big or small");
  }
}


class MyMainClass {
	  public static void main(String[] args) {
	    Pet myAnimal = new Pet();  // Create a Animal object
	    Pet myFish = new Fish();  // Create a Pig object
	    Pet myDog = new Dog();  // Create a Dog object
	    myAnimal.animalFact();
	    myFish.animalFact();
	    myDog.animalFact();
	  }
	}