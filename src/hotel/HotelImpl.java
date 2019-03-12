package hotel;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the Hotel interface for a hotel management system.
 *
 * ECM1410 - Object Oriented Programming
 * Continuous Assessment 2019 - Hotel management system
 *
 * @author1 680063381 - mtj202 - 008203
 * @author2 680062814 - da376 -
 * @version 10/03/2019
 *
 */

// MAKE public boolean importBookingsData(String bookingsTxtFileName){...}
// AND public boolean importPaymentsData(String paymentsTxtFileName){...}
// they are below, around line 150-200
// You'll also need to make the Booking class and Payment class, see the comments I wrote in importBookingsData(..){...}
// If you do it correctly you should be able to run the entire code without any errors.


public class HotelImpl implements Hotel {
    /*
     * You are required to at least provide one constructor as follows
     * which initialises the hotel with all the data from the four text
     * files.
     */

    // I haven’t included any Java documentation comments, but you need add them.

    public static void main(String[] args){
        HotelImpl init = new HotelImpl();
        init.HotelImpl("rooms.txt", "guests.txt", "bookings.txt", "payments.txt");
    }

    public void HotelImpl(String roomsTxtFileName, String guestsTxtFileName,
                      String bookingsTxtFileName, String paymentsTxtFileName){

        // Loads data from all of the four text files.
        importRoomsData(roomsTxtFileName);
        importGuestsData(guestsTxtFileName);
        importBookingsData(bookingsTxtFileName);
        importPaymentsData(paymentsTxtFileName);


        // // // // // // // // // TEST CHAMBERS // // // // // // // // //

        // Lol
        System.out.println("--== Epic ==--");

        // Room 201 check
        System.out.println("\n\n--== Test: Room 201 ==--\n");
        Room room = rooms.stream()
                .filter(r -> r.getRoomNumber() == 201)
                .findFirst().orElseThrow();

        System.out.println("Room Number " + room.getRoomNumber());
        System.out.println("Type " + room.getRoomType());
        System.out.println("Cost " + room.getRoomPrice());
        System.out.println("Capacity " + room.getRoomCapacity());
        System.out.println("Facilities " + room.getRoomFacilities());

        // Guest 10003 (VIP) check
        System.out.println("\n\n--== Test: Guest 10003 (VIP) ==--\n");
        Guest guest = guests.stream()
                .filter(g -> g.getGuestID() == 10003)
                .findFirst().orElseThrow();

        System.out.println("Guest ID " + guest.getGuestID());
        System.out.println("First Name " + guest.getfName());
        System.out.println("Last Name " + guest.getlName());
        System.out.println("Date Joined " + guest.getDateJoin());
        System.out.println("VIP Start Date " + guest.getVIPstartDate());
        System.out.println("VIP Expiry Date " + guest.getVIPexpiryDate());
        
        
        // Test other stuff here
        
    }
    /*
     * Main Attributes
     */
    public List<Room> rooms = new ArrayList<>();
    public List<Guest> guests = new ArrayList<>();
    public List<Booking> bookings = new ArrayList<>();
    public List<Payment> payments = new ArrayList<>();


    /*
     * Main Methods
     */

    public boolean importRoomsData(String roomsTxtFileName){
        boolean isSuccessful = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(roomsTxtFileName));
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");

                int roomNumber = Integer.valueOf(data[0]);
                String roomType = data[1];
                double roomPrice = Double.valueOf(data[2]);
                int roomCapacity = Integer.valueOf(data[3]);
                String roomFacilities = data[4];

                line = reader.readLine();

                Room r = new Room(roomNumber, roomType, roomPrice, roomCapacity, roomFacilities);
                rooms.add(r);
            }
            reader.close();
            isSuccessful = true;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return isSuccessful;
    }

    public boolean importGuestsData(String guestsTxtFileName){
        boolean isSuccessful = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(guestsTxtFileName));
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");

                int guestID = Integer.valueOf(data[0]);
                String fName = data[1];
                String lName = data[2];
                LocalDate dateJoin = LocalDate.parse(data[3]);
                LocalDate VIPstartDate = null;
                LocalDate VIPexpiryDate = null;

                if (data.length == 6) {
                    VIPstartDate = LocalDate.parse(data[4]);
                    VIPexpiryDate = LocalDate.parse(data[5]);
                }
                line = reader.readLine();

                Guest g = new Guest(guestID, fName, lName, dateJoin, VIPstartDate, VIPexpiryDate);
                guests.add(g);
            }
            reader.close();
            isSuccessful = true;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return isSuccessful;
    }

    public boolean importBookingsData(String bookingsTxtFileName){
        boolean isSuccessful = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(bookingsTxtFileName));
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");

                // Look at importRoomsData, the concept is exactly the same.

                // Add stuff here, use data[0], data[1], data[2].. etc like before
                // e.g. String name = data[0];

                // or if its a number, int or double, then
                // e.g. int name = Integer.valueOf(data[0]);

                // if it's a date then use e.g. LocalData name = LocalData.parse(data[3]);

                // Use the CA sheet to see how the data in the file is stored
                // id, guestID, roomNumber, bookingDate, checkinDate, checkoutDate, totalAmount

                line = reader.readLine();

                // Make a new class called Booking, same concept as the Room class

                Booking b = new Booking(...); //Add correct parameters in Booking(id, guestID, ..etc)
                bookings.add(b);
            }
            reader.close();
            isSuccessful = true;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return isSuccessful;
    }

    public boolean importPaymentsData(String paymentsTxtFileName){
        boolean isSuccessful = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(paymentsTxtFileName));
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");

                // Add stuff here, same as before.

                line = reader.readLine();

                // Add a 'Payment' class same, same as before.
                Payment p = new Payment(...);
                bookings.add(p);
            }
            reader.close();
            isSuccessful = true;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return isSuccessful;
    }

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

    /*
     *  Supporting Methods
     */

    //...if any...


    // Note: readFile scrapped - at least for now.
    // Note: this is the first W.I.P version anyway and is not fully complete.
    // Note: used in the loadData class - removed... at least for now.
    /*
    private void readFile(String fileName){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            //String data[] = null;
            while (line != null){
                System.out.println(line);
                String[] data = line.split(",");
                String data1 = data[0];
                String data2 = data[1];
                String data3 = data[2];
                String data4 = data[3];
                System.out.println(data1);
                System.out.println(data2);
                System.out.println(data3);
                System.out.println(data4);
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    */
}


