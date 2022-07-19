package qurban.javabean;

public class AnimalDetails {
	private int animalDetailsID;	
	private String animalType;		// Jenis haiwan
	private double animalPrice;		// Harga seekor
	
	// Constructor -------------------------------------
	
	// With ID
	public AnimalDetails(int animalDetailsID, String animalType, double animalPrice) {

		this.animalDetailsID = animalDetailsID;
		this.animalType = animalType;
		this.animalPrice = animalPrice;
	}
	
	// Without ID
	public AnimalDetails(String animalType, double animalPrice) {
		
		this.animalType = animalType;
		this.animalPrice = animalPrice;
	}
	
	// Default
	public AnimalDetails() {
		
	}


	// Setters ---------------------------------

	public void setAnimalDetailsID(int animalDetailsID) {
		this.animalDetailsID = animalDetailsID;
	}
	
	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public void setAnimalPrice(double animalPrice) {
		this.animalPrice = animalPrice;
	}
	
	// Getters ---------------------------------
	
	public int getAnimalDetailsID() {
		return animalDetailsID;
	}

	public String getAnimalType() {
		return animalType;
	}

	public double getAnimalPrice() {
		return animalPrice;
	}

}
