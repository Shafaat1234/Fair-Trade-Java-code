package entity;
public interface FairTrade{
    void openEvent();
    void closeEvent();
    void registerNewStall(Stall stall) throws NoAvailableSlotException;
}
