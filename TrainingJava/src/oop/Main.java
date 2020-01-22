package oop;

public class Main {
	public static void main(String[] args) {
		Animal animal= new Animal(1, 2, 3);
		Animal cat = new Cat(0.3, 0.4, 4);
		animal.nameClass();
		System.out.println(animal.legs);
		cat.nameClass();
		System.out.println(cat.legs);
	}
}
