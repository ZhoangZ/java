package oop;

public class Cat extends Animal{

	public Cat(double height, double weight, int legs) {
		super(height, weight, legs);
		this.legs=legs;
	}
	public void nameClass() {
		System.out.println("Cat");
	}
	
}
