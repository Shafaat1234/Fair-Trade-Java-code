package entity;

// Thrown when the GUI tries to update/remove/login/allocate a slot that has no record in it.
public class RecordNotFoundException extends Exception{
    public RecordNotFoundException(String message){
        super(message);
    }
}
