package entity;
public class FoodCourtStall extends Pavilion{

    private static final double FOOD_LICENSE_FEE = 150.0;
 
    public FoodCourtStall(){
        super();
        System.out.println("Empty Constructor of FoodCourtStall");
    }

    public FoodCourtStall(String vendorID, String contactName, String companyName, String password, int stallNumber, int pavilion, String location, String locationType) throws InvalidDataException{
        super(vendorID, contactName, companyName, password, stallNumber, pavilion, location, locationType);
        System.out.println("Parameterized Constructor of FoodCourtStall");
    }
 
    public void showFoodCourtStall(){
        System.out.println(getPaymentInfo());
    }

    // polymorphism: FoodCourtStall reuses Pavilion's formula via super, then adds its own fee on top
    @Override
    public double calculateRent(){
        return super.calculateRent() + FOOD_LICENSE_FEE;
    }

    @Override
    public String getPaymentInfo(){
        String data = "";
        data += "-------Food Court Stall Info---------" + "\n";
        data += "Company: " + getCompanyName() + "\n";
        data += "Stall Number: " + getStallNumber() + "\n";
        data += "Pavilion Block: " + getPavilion() + "\n";
        data += "Location: " + getLocation() + "\n";
        data += "Rent (incl. food license fee): " + calculateRent() + "\n";
        data += "-----------------------------------" + "\n";
        return data;
    }

    public String getFoodCourtStallAsString(){
        return getPaymentInfo();
    }
}
