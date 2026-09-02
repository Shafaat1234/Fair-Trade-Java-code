package entity;
// Vendor is now abstract: it was never meant to be created on its own anyway
// (only GeneralStall/FoodCourtStall ever get built) - calculateRent() and
// getPaymentInfo() are left for each subclass to define (abstraction),
// and every subclass provides a different implementation of them (polymorphism).
public abstract class Vendor implements Payable{
    private String vendorID;
    private String contactName;
    private String companyName;
    private String password;
	public static int vendorCount;

    public Vendor(){
        System.out.println("Empty Constructor of Vendor");
        vendorCount++;
    }
 
    // Parameterized constructor
    public Vendor(String vendorID, String contactName, String companyName, String password) throws InvalidDataException{
        System.out.println("Parameterized Constructor of Vendor");
        setVendorID(vendorID);
        setContactName(contactName);
        setCompanyName(companyName);
        setPassword(password);
        vendorCount++;
    }
 
    public void setVendorID(String vendorID) throws InvalidDataException{
        if (vendorID != null && !vendorID.isEmpty()){
            this.vendorID = vendorID;
        } else {
            throw new InvalidDataException("Vendor ID cannot be empty");
        }
    }
 
    public String getVendorID(){
        return vendorID;
    }
 
    public void setContactName(String contactName){
        this.contactName = contactName;
    }
 
    public String getContactName(){
        return contactName;
    }
 
    public void setCompanyName(String companyName) throws InvalidDataException{
        if (companyName != null && !companyName.isEmpty()){
            this.companyName = companyName;
        } else {
            throw new InvalidDataException("Company name cannot be empty");
        }
    }
 
    public String getCompanyName(){
        return companyName;
    }

    public String getPassword(){
        return password;
    }
 
    public void setPassword(String password) throws InvalidDataException{
        if (password != null && password.length() >= 4){
            this.password = password;
        } else{
            throw new InvalidDataException("Password must be at least 4 characters");
        }
    }
 
    public void register(){
        if (vendorID == null){
            vendorID = "V" + (100 + vendorCount);
        }
        System.out.println("Vendor Registered: " + companyName + " (ID: " + vendorID + ")");
    }
 
    public boolean login(String enteredPassword){
        if (this.password != null && this.password.equals(enteredPassword)){
            System.out.println("Login Successful for " + companyName);
            return true;
        } else{
            System.out.println("Login Failed - Incorrect Password");
            return false;
        }
    }
 
    public void showDetails(){
        System.out.println("------------------");
        System.out.println("Vendor ID: " + vendorID);
        System.out.println("Contact Name: " + contactName);
        System.out.println("Company Name: " + companyName);
        System.out.println("------------------");
    }

    public String getVendorAsString(){
        String data = "";
        data += "------------------" + "\n";
        data += "Vendor ID: " + vendorID + "\n";
        data += "Contact Name: " + contactName + "\n";
        data += "Company Name: " + companyName + "\n";
        data += "------------------" + "\n";
        return data;
    }

    // ---- abstraction: every concrete vendor type must define these for itself ----
    public abstract double calculateRent();
    public abstract String getPaymentInfo();
}
