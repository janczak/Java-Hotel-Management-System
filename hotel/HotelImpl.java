package hotel;

import java.time.LocalDate;

public class HotelImpl implements Hotel {
    /*
     * You are required to at least provide one constructor as follows
     * which initialises the hotel with all the data from the four text
     * files.
     */

    // I haven’t included any Java documentation comments, but you need add them.

    public static void main(String[] args){
        System.out.println("Lol");
    }

    public void HotelImpl(String roomsTxtFileName, String guestsTxtFileName,
                          String bookingsTxtFileName, String paymentsTxtFileName){

    }
    /*
     * You may add any necessary attributes here
     */
    //int roomNumber;

    /*
     * Copy all the methods from the Hotel interface. First give non-
     * functioning implemention for all the methods to make the
     * program compiled successfully. Then you may start to add each
     * method body one by one. You may define new classes to support
     * it if necessary.
     */
    // ... all the methods in the Hotel interface must be implemented

    public boolean importRoomsData(String roomsTxtFileName){return true;}
    public boolean importGuestsData(String guestsTxtFileName){return true;}
    public boolean importBookingsData(String bookingsTxtFileName){return true;}
    public boolean importPaymentsData(String paymentsTxtFileName){return true;}
    public void displayAllRooms(){}
    public void displayAllGuests(){}
    public void displayAllBookings(){}
    public void displayAllPayments(){}
    public boolean addRoom(int roomNumber, RoomType roomType, double price, int capacity, String facilities){return true;}
    public boolean removeRoom(int roomNumber){return true;}
    public boolean addGuest(String fName, String lName, LocalDate dateJoin){return true;}
    public boolean addGuest(String fName, String lName, LocalDate dateJoin, LocalDate VIPstartDate, LocalDate VIPexpiryDate){return true;}
    public boolean removeGuest(int guestID){return true;}
    public boolean isAvailable(int roomNumber, LocalDate checkin, LocalDate checkout){return true;}
    public int[] availableRooms(RoomType roomType, LocalDate checkin, LocalDate checkout){return null;}
    public int bookOneRoom(int guestID, RoomType roomType, LocalDate checkin, LocalDate checkout){return 1;}
    public boolean checkOut(int bookingID, LocalDate actualCheckoutDate){return true;}
    public boolean cancelBooking(int bookingID){return true;}
    public int[] searchGuest(String firstName, String lastName){return null;}
    public void displayGuestBooking(int guestID){}
    public void displayBookingsOn(LocalDate thisDate){}
    public void displayPaymentsOn(LocalDate thisDate){}
    public boolean saveRoomsData(String roomsTxtFileName){return true;}
    public boolean saveGuestsData(String guestsTxtFileName){return true;}
    public boolean saveBookingsData(String bookingsTxtFileName){return true;}
    public boolean savePaymentsData(String paymentsTxtFileName){return true;}
}

/*
 * You may add any other necessary methods here.
 */
//}

