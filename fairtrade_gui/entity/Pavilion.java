package entity;
public class Pavilion extends Vendor{
 
    protected int stallNumber;
    protected int pavilion;
    protected String location;
    protected String locationType;
    protected static final double PAVILION_BASE_RATE = 300.0;
 
    public Pavilion(){
        super();
        System.out.println("Empty Constructor of Pavilion");
    }
 
    public Pavilion(String vendorID, String contactName, String companyName, String password, int stallNumber, int pavilion, String location, String locationType) throws InvalidDataException{
        super(vendorID, contactName, companyName, password);
        System.out.println("Parameterized Constructor of Pavilion");
        setStallNumber(stallNumber);
        setPavilion(pavilion);
        setLocation(location);
        setLocationType(locationType);
    }
 
    public void setStallNumber(int stallNumber) throws InvalidDataException{
        if (stallNumber > 0){
            this.stallNumber = stallNumber;
        } else {
            throw new InvalidDataException("Stall number should be positive");
        }
    }
 
    public int getStallNumber(){
        return stallNumber;
    }
 
    public void setPavilion(int pavilion) throws InvalidDataException{
        if (pavilion > 0) {
            this.pavilion = pavilion;
        } else{
            throw new InvalidDataException("Pavilion block should be positive");
        }
    }
 
    public int getPavilion(){
        return pavilion;
    }
 
    public void setLocation(String location){
        this.location = location;
    }
 
    public String getLocation(){
        return location;
    }
 
    public void setLocationType(String locationType){
        this.locationType = locationType;
    }
 
    public String getLocationType(){
        return locationType;
    }
 
    public void showPavilion(){
        System.out.println("-------Pavilion Info---------");
        System.out.println("Company: " + getCompanyName());
        System.out.println("Stall Number: " + stallNumber);
        System.out.println("Pavilion Block: " + pavilion);
        System.out.println("Location: " + location);
        System.out.println("-----------------------------------\n");
    }

    public String getPavilionAsString(){
        return getPaymentInfo();
    }

    // polymorphism: Pavilion's own rent formula, based on which pavilion block it's in
    @Override
    public double calculateRent(){
        return PAVILION_BASE_RATE * pavilion;
    }

    @Override
    public String getPaymentInfo(){
        String data = "";
        data += "-------Pavilion Info---------" + "\n";
        data += "Company: " + getCompanyName() + "\n";
        data += "Stall Number: " + stallNumber + "\n";
        data += "Pavilion Block: " + pavilion + "\n";
        data += "Location: " + location + "\n";
        data += "Rent: " + calculateRent() + "\n";
        data += "-----------------------------------" + "\n";
        return data;
    }
}
