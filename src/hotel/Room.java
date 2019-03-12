package hotel;
/**
 * Used to store room information, e.g. when loaded from file.
 * Has methods which can return a room's number, type, price, capacity or facilities.
 *
 */
public class Room {

    private int initRoomNumber;
    private String initRoomType;
    private double initRoomPrice;
    private int initRoomCapacity;
    private String initRoomFacilities;

    public Room(int roomNumber, String roomType, double roomPrice, int roomCapacity, String roomFacilities){
        initRoomNumber = roomNumber;
        initRoomType = roomType;
        initRoomPrice = roomPrice;
        initRoomCapacity = roomCapacity;
        initRoomFacilities = roomFacilities;
    }
    public int getRoomNumber(){ return initRoomNumber; }
    public String getRoomType(){ return initRoomType; }
    public double getRoomPrice(){ return initRoomPrice; }
    public int getRoomCapacity(){ return initRoomCapacity; }
    public String getRoomFacilities(){ return initRoomFacilities; }
}
