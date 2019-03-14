package hotel;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * An implementation of the Hotel interface for a hotel management system.
 *
 * ECM1410 - Object Oriented Programming
 * Continuous Assessment 2019 - Hotel management system
 *
 * @author1 680063381 - mtj202 - 008203
 * @author2 680062814 - da376 - ADD YOUR CANDIDATE NUMBER PLS, idk if it matters but do it anyway lol
 * @version 13/03/2019
 *
 */



/*  So... before doing anything compile this code and run it, it should compile and run (and print some test stuff, like a list with all the rooms).


    Make all of the methods which have not been added yet unless I commented next to them and said to not do them

    Test your stuff in HotelImpl


    The way I designed the code (probably shit lmao):

    All the rooms, guests, bookings, payments data is stored as an object in the respective arrays/lists (around line 170)
    So, e.g. the rooms array/list has a room object for each room
    each room object has the data: roomNumber, roomType, roomPrice, roomCapacity, roomFacilities
    Same concept for guests, bookings and payments
    Each, or at least most, of the methods revolve around that ^


    Whenever you're making a method look in the Hotel.java file to see what hongpong wrote about how the method works.
    Then look at the CA sheet to make sure you cover all of her additional demands, e.g. special discounts for VIP...
    ...or not being able to remove/add/change something because of something else etc..


    Start by rewriting the Guest class so it has super and subclass like she demands
    Then, make the addGuest method. After that, make all of the others ones.

    You can edit the importGuestData after you change the Guest class if it doesn't make your code work (but maybe it will work anyway)
    Do not change any of other the methods I made or might mess my code up whilst im adding stuff on my end

 */


public class HotelImpl implements Hotel {

    public static void main(String[] args){
        HotelImpl init = new HotelImpl();
        init.HotelImpl("rooms.txt", "guests.txt", "bookings.txt", "payments.txt");

        // NEED TO REMOVE 'MAIN' for final version because Hongpingpong
        // will have her own 'main' function in her own test app.
        // So, need to make this whole thing work without 'main'.

        // Need to make an appropriate constructor in HotelImpl for it to work. Do this later.

        // Also, need to add java documentation comments - do that at the end.

    }

    public void HotelImpl(String roomsTxtFileName, String guestsTxtFileName,
                      String bookingsTxtFileName, String paymentsTxtFileName){

        // Loads data from all of the four text files.

        importRoomsData(roomsTxtFileName);
        importGuestsData(guestsTxtFileName);
        importBookingsData(bookingsTxtFileName);
        importPaymentsData(paymentsTxtFileName);


        // // // // // // // // // // TEST CHAMBERS // // // // // // // // // //

        // Lol
        System.out.println("--== Epic ==--");


        // Test your stuff here







        // Adding 2 rooms - should be added.
        addRoom(405, RoomType.SINGLE, 50, 2, "Shared bathroom");
        addRoom(406, RoomType.SINGLE, 50, 2, "Shared bathroom");

        // Adding a room which already exists - should NOT be added.
        addRoom(101, RoomType.DOUBLE, 65, 2, "Shared bathroom");

        // Removing 2 rooms which exists
        removeRoom(103);
        removeRoom(406);

        // Removing a room which does NOT exist
        removeRoom(508);

        // Print All Rooms

        // EITHER
        for (Room r : rooms) { System.out.println(r); }
        // OR
        // rooms.stream().forEach(System.out::println);  // This can be used instead of the for loop, same goes for other ones.

        // Print All Guests
        for (Guest g : guests) { System.out.println(g); }
        // need toString method in Guest

        // Print All Bookings
        for (Booking b : bookings) { System.out.println(b); }

        // Print All Payments
        for (Payment p : payments) { System.out.println(p); }

        /*
        // Room 405 check
        System.out.println("\n\n--== Test: Room 405 (added) ==--\n");
        Room room = rooms.stream()
                .filter(r -> r.getRoomNumber() == 405)
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

        // Booking 100009 check
        System.out.println("\n\n--== Test: Booking 100009 ==--\n");
        Booking booking = bookings.stream()
                .filter(b -> b.getID() == 100009)
                .findFirst().orElseThrow();

        System.out.println("Booking ID " + booking.getID());
        System.out.println("Guest ID " + booking.getGuestID());
        System.out.println("Room No. " + booking.getRoomNumber());
        System.out.println("Date Booked " + booking.getBookingDate());
        System.out.println("Check in " + booking.getCheckInDate());
        System.out.println("Check out " + booking.getCheckOutDate());
        */
    }
    /*
     * Main Attributes
     */
    private List<Room> rooms = new ArrayList<>();
    private List<Guest> guests = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();

    /*
     * Main Methods
     */

    // the switch part doesn't work properly, every room is assigned the 'TWIN' RoomType enum. F
    public boolean importRoomsData(String roomsTxtFileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(roomsTxtFileName));
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");

                int roomNumber = Integer.valueOf(data[0]);
                RoomType roomType = null;

                switch (data[1]){
                    case "single":
                        roomType = RoomType.SINGLE;

                    case "double":
                        roomType = RoomType.DOUBLE;

                    case "family":
                        roomType = RoomType.FAMILY;

                    case "twin":
                        roomType = RoomType.TWIN;
                }
                double roomPrice = Double.valueOf(data[2]);
                int roomCapacity = Integer.valueOf(data[3]);
                String roomFacilities = data[4];

                line = reader.readLine();

                Room r = new Room(roomNumber, roomType, roomPrice, roomCapacity, roomFacilities);
                rooms.add(r);
            }
            reader.close();
            return true;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean importGuestsData(String guestsTxtFileName){
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
            return true;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean importBookingsData(String bookingsTxtFileName){

        try {
            BufferedReader reader = new BufferedReader(new FileReader(bookingsTxtFileName));
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");

                int id = Integer.valueOf(data[0]);
                int guestID = Integer.valueOf(data[1]);
                int roomNumber = Integer.valueOf(data[2]);
                LocalDate bookingDate = LocalDate.parse(data[3]);
                LocalDate checkinDate = LocalDate.parse(data[4]);
                LocalDate checkoutDate = LocalDate.parse(data[5]);
                double totalCost = Double.valueOf(data[6]);

                line = reader.readLine();

                Booking b = new Booking(id, guestID, roomNumber, bookingDate, checkinDate, checkoutDate, totalCost); //Add correct parameters in Booking(id, guestID, ..etc)
                bookings.add(b);
            }
            reader.close();
            return true;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean importPaymentsData(String paymentsTxtFileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(paymentsTxtFileName));
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");

                LocalDate date = LocalDate.parse(data[0]);
                int guestID = Integer.valueOf(data[1]);
                double amount = Double.valueOf(data[2]);
                String payReason = data[3];

                line = reader.readLine();

                Payment p = new Payment(date, guestID, amount, payReason);
                payments.add(p);
            }
            reader.close();
            return true;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void displayAllRooms(){} // Leave this until the end

    public void displayAllGuests(){} // Leave this until the end

    public void displayAllBookings(){} // Leave this until the end

    public void displayAllPayments(){} // Leave this until the end

    public boolean addRoom(int roomNumber, RoomType roomType, double price, int capacity, String facilities) {

        try{
            if(doesRoomExist(roomNumber)){
                System.out.println("ACTION DENIED: Cannot add room " + roomNumber + ". This room already exists!");
                return false;
            }
            Room r = new Room(roomNumber, roomType, price, capacity, facilities);
            rooms.add(r);
            System.out.println("Room " + roomNumber + " has been added successfully.");
            return true;

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;

        // All parts of the code below work
        /*
        // ALTERNATIVE 1:
        for(Room room: rooms){
            // System.out.println("Room: " + room);
            if(room.getRoomNumber() == 101){
                System.out.println("Room exists!");
                return false;;
            } else{
                System.out.println("Room DOES NOT exist!");
                return true;
            }
        }*/

        /* THE CODE BELOW WORKS (old version)
        // ALTERNATIVE 2:
        try{
            Room room = rooms.stream()
                    .filter(r -> r.getRoomNumber() == roomNumber)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException());
            System.out.println("Error: Room " + roomNumber + " already exists.");

        } catch(NoSuchElementException ex){
            Room r = new Room(roomNumber, roomType, price, capacity, facilities);
            rooms.add(r);
            return true;
        }*/
    }

    public boolean removeRoom(int roomNumber){
        try{
            if(doesRoomExist(roomNumber)) {
                rooms.remove(getRoomObject(roomNumber));
                System.out.println("Room " + roomNumber + " has successfully been removed.");
                return true;
            } else{
                System.out.println("ACTION DENIED: Cannot remove room " + roomNumber + ". This room does not exist!");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    // Sort out the guest class first before doing anything with addGuest
    public boolean addGuest(String fName, String lName, LocalDate dateJoin){return true;}

    public boolean addGuest(String fName, String lName, LocalDate dateJoin, LocalDate VIPstartDate, LocalDate VIPexpiryDate){return true;}

    public boolean removeGuest(int guestID){return true;} // Leave this one

    public boolean isAvailable(int roomNumber, LocalDate checkin, LocalDate checkout){return true;}

    public int[] availableRooms(RoomType roomType, LocalDate checkin, LocalDate checkout){return null;}

    public int bookOneRoom(int guestID, RoomType roomType, LocalDate checkin, LocalDate checkout){return 1;}

    public boolean checkOut(int bookingID, LocalDate actualCheckoutDate){return true;}

    public boolean cancelBooking(int bookingID){return true;}

    public int[] searchGuest(String firstName, String lastName){return null;}

    public void displayGuestBooking(int guestID){} // Leave this until the end

    public void displayBookingsOn(LocalDate thisDate){} // Leave this until the end

    public void displayPaymentsOn(LocalDate thisDate){} // Leave this until the end

    // I'll (try to) do the 4 save data ones below
    public boolean saveRoomsData(String roomsTxtFileName){return true;} // Leave this one

    public boolean saveGuestsData(String guestsTxtFileName){return true;} // Leave this one

    public boolean saveBookingsData(String bookingsTxtFileName){return true;} // Leave this one

    public boolean savePaymentsData(String paymentsTxtFileName){return true;} // Leave this one

    /*
     *  Supporting Methods
     */

    // Add any of your own methods here

    private boolean doesRoomExist(int roomNumber){
        return rooms.stream().anyMatch(r -> r.getRoomNumber() == roomNumber);
    }

    private Object getRoomObject(int roomNumber){
        Room room = rooms.stream()
                .filter(r -> r.getRoomNumber() == roomNumber)
                .findFirst().orElseThrow();
        return room;
    }

}


