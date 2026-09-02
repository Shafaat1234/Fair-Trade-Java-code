package entity;

// Thrown by FairTrade.registerNewStall() when there is no free slot left to hold a new stall.
public class NoAvailableSlotException extends Exception{
    public NoAvailableSlotException(String message){
        super(message);
    }
}
