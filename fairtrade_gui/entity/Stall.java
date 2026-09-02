package entity;
// Stall now implements the same Payable contract as the Vendor hierarchy,
// even though the two class trees are otherwise unrelated - this lets the GUI
// total up rent across both polymorphically without caring which one it is.
public class Stall implements Payable{
    private int stallNumber;
    private int size;
    private String locationType;
    public static int stallCount;
    private boolean available = true;

    public Stall(){
        System.out.println("Empty Constructor of Stall");
        stallCount++;
    }

    public Stall(int stallNumber, int size, String locationType) throws InvalidDataException{
        System.out.println("Parameterized Constructor of Stall");
        setStallNumber(stallNumber);
        setSize(size);
        setLocationType(locationType);
        stallCount++;
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

    public void allocateToVendor(Vendor vendor) throws InvalidDataException{
        if (vendor == null){
            throw new InvalidDataException("Cannot allocate stall " + stallNumber + " - no vendor provided");
        }

        System.out.println("Stall " + stallNumber + " allocated to " + vendor.getCompanyName());
    }

    public void updateAvailability(boolean status){
        this.available = status;
        if (status){
            System.out.println("Stall " + stallNumber + " is now AVAILABLE");
        } else {
            System.out.println("Stall " + stallNumber + " is now OCCUPIED");
        }
    }

    public boolean isAvailable(){
        return available;
    }

    @Override
    public double calculateRent(){
        final double RATE_PER_UNIT = 250.0;
        return size * RATE_PER_UNIT;
    }

    public void showDetails(){
        System.out.println(getPaymentInfo());
    }

    @Override
    public String getPaymentInfo(){
        String data = "";
        data += "------------------" + "\n";
        data += "Stall Number: " + stallNumber + "\n";
        data += "Size: " + size + "\n";
        data += "Location Type: " + locationType + "\n";
        data += "Availability: " + (available ? "AVAILABLE" : "OCCUPIED") + "\n";
        data += "Rent: " + calculateRent() + "\n";
        data += "------------------" + "\n";
        return data;
    }

    public String getStallAsString(){
        return getPaymentInfo();
    }
}
