package qurban.javabean;

public class AnimalOrder {
	
	int animalOrderID;
	String dependentName;		// Nama yg dikorbankan
	int bookingID;
	int animalDetailsID;
	
	// Constructors -------------------------------
	
	//default
	public AnimalOrder() {
		
	}
	
	// with ID
	public AnimalOrder(int animalOrderID, String dependentName, int bookingID, int animalDetailsID) {
		
		this.animalOrderID = animalOrderID;
		this.dependentName = dependentName;
		this.bookingID = bookingID;
		this.animalDetailsID = animalDetailsID;
	}
	
	// without ID
	public AnimalOrder(String dependentName, int bookingID, int animalDetailsID) {
		
		this.dependentName = dependentName;
		this.bookingID = bookingID;
		this.animalDetailsID = animalDetailsID;
	}
	
	// Setters ---------------------------
	public void setAnimalOrderID(int animalOrderID) {
		this.animalOrderID = animalOrderID;
	}

	public void setDependentName(String dependentName) {
		this.dependentName = dependentName;
	}
	
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
	
	public void setAnimalDetailsID(int animalDetailsID) {
		this.animalDetailsID = animalDetailsID;
	}
	
	// Getters ------------------------------
	public int getAnimalOrderID() {
		return animalOrderID;
	}

	public String getDependentName() {
		return dependentName;
	}

	public int getBookingID() {
		return bookingID;
	}
	
	public int getAnimalDetailsID() {
		return animalDetailsID;
	}

	
}
