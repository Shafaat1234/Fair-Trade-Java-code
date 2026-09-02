package entity;

// Thrown when a Vendor or Stall is given invalid data (empty name, negative size, etc).
public class InvalidDataException extends Exception{
    public InvalidDataException(String message){
        super(message);
    }
}
