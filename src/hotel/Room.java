package hotel;
/**
 * Used to create and store room information.
 * Has methods which can return a room's number, type, price, capacity or facilities.
 *
 */
public class Room {

    private int roomNumber;
    private RoomType roomType;
    private double roomPrice;
    private int roomCapacity;
    private String roomFacilities;

    public Room(int roomNumber, RoomType roomType, double roomPrice, int roomCapacity, String roomFacilities){
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomCapacity = roomCapacity;
        this.roomFacilities = roomFacilities;
    }
    public int getRoomNumber(){ return roomNumber; }
    public RoomType getRoomType(){ return roomType; }
    public double getRoomPrice(){ return roomPrice; }
    public int getRoomCapacity(){ return roomCapacity; }
    public String getRoomFacilities(){ return roomFacilities; }

    @Override
    // Converts every room information into a single string. Convenient when displaying all rooms or writing to file.
    public String toString() {
        return this.roomNumber + "," + this.roomType + "," + this.roomPrice + "," + this.roomCapacity
                + "," + this.roomFacilities;
    }
}