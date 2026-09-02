package entity;

// A common contract for anything that owes rent in the Fair Trade system.
// Both the Vendor hierarchy (GeneralStall/Pavilion/FoodCourtStall) and the
// unrelated Stall class implement this, so they can be treated polymorphically
// (e.g. summed together) even though they don't share a common superclass.
public interface Payable{
    double calculateRent();
    String getPaymentInfo();
}
