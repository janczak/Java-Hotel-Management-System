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
 * @author2 680062814 - da376 -
 * @version 13/03/2019
 *
 */

public class HotelImpl implements Hotel {

    public static void main(String[] args){
        HotelImpl init = new HotelImpl();
        init.HotelImpl("rooms.txt", "guests.txt", "bookings.txt", "payments.txt");

        // remove in final version

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


        //if(isDateWithinRange(LocalDate.parse("25-01-2019"), LocalDate.parse("24-01-2019"), LocalDate.parse("26-01-2019"))) {
        //    System.out.println("\nBob\n");
        //}




        // Adding 2 rooms - should be added.
        addRoom(405, RoomType.SINGLE, 50, 2, "Shared bathroom");
        addRoom(406, RoomType.SINGLE, 50, 2, "Shared bathroom");

        // Adding a room which already exists - should NOT be added.
        addRoom(101, RoomType.DOUBLE, 65, 2, "Shared bathroom");

        // Removing 2 rooms which exists
        removeRoom(103); // Room is booked, so shouldn't be removed
        removeRoom(201); // Room not booked
        removeRoom(406);

        // Removing a room which does NOT exist
        removeRoom(508);

        //isAvailable(405, LocalDate.parse("2019-02-10"), LocalDate.parse("2019-04-10"));

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

                Booking b = new Booking(id, guestID, roomNumber, bookingDate, checkinDate, checkoutDate, totalCost);
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
            if(doesRoomExist(roomNumber) && !isRoomBooked(roomNumber)) {
                Object room = getRoomObject(roomNumber);
                rooms.remove(room);
                System.out.println("Room " + roomNumber + " has been removed successfully.");
                return true;
            } else{
                System.out.println("ACTION DENIED: Cannot remove room " + roomNumber
                        + ". This room does not exist or is currently booked!");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean addGuest(String fName, String lName, LocalDate dateJoin){return true;}

    public boolean addGuest(String fName, String lName, LocalDate dateJoin, LocalDate VIPstartDate, LocalDate VIPexpiryDate){return true;}

    public boolean removeGuest(int guestID){
        try{
            if(doesGuestExist(guestID)) {
                guests.remove(getGuestObject(guestID));
                System.out.println("Guest " + guestID + " has been removed successfully.");
                return true;
            } else{
                System.out.println("ACTION DENIED: Cannot remove guest " + guestID + ". This guest does not exist!");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean isAvailable(int roomNumber, LocalDate checkin, LocalDate checkout) {
        try {
            if (!isRoomBooked(roomNumber)) {
                System.out.println("Room " + roomNumber + " will be available between " + checkin + " and " + checkout);
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public int[] availableRooms(RoomType roomType, LocalDate checkin, LocalDate checkout){return null;}

    public int bookOneRoom(int guestID, RoomType roomType, LocalDate checkin, LocalDate checkout){return 1;}

    public boolean checkOut(int bookingID, LocalDate actualCheckoutDate){return true;}

    public boolean cancelBooking(int bookingID){return true;}

    public int[] searchGuest(String firstName, String lastName){return null;}

    public void displayGuestBooking(int guestID){} // Leave this until the end

    public void displayBookingsOn(LocalDate thisDate){} // Leave this until the end

    public void displayPaymentsOn(LocalDate thisDate){} // Leave this until the end

    public boolean saveRoomsData(String roomsTxtFileName){return true;}

    public boolean saveGuestsData(String guestsTxtFileName){return true;}

    public boolean saveBookingsData(String bookingsTxtFileName){return true;}

    public boolean savePaymentsData(String paymentsTxtFileName){return true;}

    /*
     *  Supporting Methods
     */

    private boolean doesRoomExist(int roomNumber){
        return rooms.stream().anyMatch(r -> r.getRoomNumber() == roomNumber);
    }

    private boolean doesGuestExist(int guestID){
        return guests.stream().anyMatch(g -> g.getGuestID() == guestID);
    }

    private boolean isRoomBooked(int roomNumber){
        return bookings.stream().anyMatch(b -> b.getRoomNumber() == roomNumber);
    }

    private Object getRoomObject(int roomNumber){
        Room roomObject = rooms.stream()
                .filter(r -> r.getRoomNumber() == roomNumber)
                .findFirst().orElseThrow(NoSuchElementException::new);
        return roomObject;
    }

    private Object getGuestObject(int guestID){
        Guest guestObject = guests.stream()
                .filter(g -> g.getGuestID() == guestID)
                .findFirst().orElseThrow(NoSuchElementException::new);
        return guestObject;
    }

    private boolean isDateWithinRange(LocalDate dateChecked, LocalDate firstDate, LocalDate secondDate){
        return firstDate.compareTo(dateChecked) * dateChecked.compareTo(secondDate) >= 0;
    }
}


