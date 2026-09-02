package entity;
public class GeneralStall extends Vendor{
 
    private int stallNumber;
    private int size;
    private String locationType;
    private static final double GENERAL_RATE = 200.0;
 
    public GeneralStall(){
        super();
        System.out.println("Empty Constructor of GeneralStall");
    }
 
    public GeneralStall(String vendorID, String contactName, String companyName, String password, int stallNumber, int size, String locationType) throws InvalidDataException{
        super(vendorID, contactName, companyName, password); 
        System.out.println("Parameterized Constructor of GeneralStall");
        setStallNumber(stallNumber);
        setSize(size);
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
 
    public void setSize(int size) throws InvalidDataException{
        if (size > 0){
            this.size = size;
        } else {
            throw new InvalidDataException("Size should be positive");
        }
    }
 
    public int getSize(){
        return size;
    }
 
    public void setLocationType(String locationType){
        this.locationType = locationType;
    }
 
    public String getLocationType(){
        return locationType;
    }
 
    // polymorphism: GeneralStall's own rent formula, based on floor size
    @Override
    public double calculateRent(){
        return size * GENERAL_RATE;
    }
 
    public void showGeneralStall(){
        System.out.println(getPaymentInfo());
    }

    @Override
    public String getPaymentInfo(){
        String data = "";
        data += "-------General Stall Info---------" + "\n";
        data += "Company: " + getCompanyName() + "\n";
        data += "Stall Number: " + stallNumber + "\n";
        data += "Size: " + size + "\n";
        data += "Location Type: " + locationType + "\n";
        data += "Rent: " + calculateRent() + "\n";
        data += "-----------------------------------" + "\n";
        return data;
    }

    public String getGeneralStallAsString(){
        return getPaymentInfo();
    }
}
