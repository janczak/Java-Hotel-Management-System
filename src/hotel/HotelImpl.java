package hotel;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * An implementation of the Hotel interface for a hotel management system.
 *
 * ECM1410 - Object Oriented Programming
 * Continuous Assessment 2019 - Hotel management system
 *
 * @author 680063381 - mtj202 - 008203
 * @version 15/03/2019
 *
 *
 */

public class HotelImpl implements Hotel {

    public HotelImpl(String roomsTxtFileName, String guestsTxtFileName,
                      String bookingsTxtFileName, String paymentsTxtFileName){

        // Loads data from all of the four text files.
        importRoomsData(roomsTxtFileName);
        importGuestsData(guestsTxtFileName);
        importBookingsData(bookingsTxtFileName);
        importPaymentsData(paymentsTxtFileName);
    }
    /*
     * Main Attributes
     */
    // The data from the rooms, guests, bookings and payments files is stored here.
    private List<Room> rooms = new ArrayList<>();
    private List<Guest> guests = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();

    /*
     * Main Interface Methods
     */

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
                        break;

                    case "double":
                        roomType = RoomType.DOUBLE;
                        break;

                    case "family":
                        roomType = RoomType.FAMILY;
                        break;

                    case "twin":
                        roomType = RoomType.TWIN;
                        break;
                }
                double roomPrice = Double.valueOf(data[2]);
                int roomCapacity = Integer.valueOf(data[3]);
                String roomFacilities = data[4];

                line = reader.readLine();

                Room r = new Room(roomNumber, roomType, roomPrice, roomCapacity, roomFacilities);
                rooms.add(r);

                assert rooms != null;
            }
            reader.close();
            return true;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
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

                if (data.length == 6) {
                    LocalDate VIPstartDate = LocalDate.parse(data[4]);
                    LocalDate VIPexpiryDate = LocalDate.parse(data[5]);
                    Guest g = new VIP(guestID, fName, lName, dateJoin, VIPstartDate, VIPexpiryDate);
                    guests.add(g);

                } else {
                    Guest g = new Guest(guestID, fName, lName, dateJoin);
                    guests.add(g);
                }
                line = reader.readLine();
                assert guests != null;
            }
            reader.close();
            return true;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
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
                assert bookings != null;
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
            assert payments != null;
            return true;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void displayAllRooms(){
        for(Room r : rooms){
            System.out.println(r);
        }
    }

    public void displayAllGuests(){
        for(Guest g : guests){
            System.out.println(g);
        }
    }

    public void displayAllBookings(){
        for(Booking b : bookings){
            System.out.println(b);
        }
    }

    public void displayAllPayments(){
        for(Payment p : payments){
            System.out.println(p);
        }
    }

    public boolean addRoom(int roomNumber, RoomType roomType, double price, int capacity, String facilities) {
        try{
            if(doesRoomExist(roomNumber)){
                System.out.println("ACTION DENIED: Cannot add room " + roomNumber + ". This room already exists!");
                return false;
            }
            Room r = new Room(roomNumber, roomType, price, capacity, facilities);
            rooms.add(r);
            System.out.println("Room " + roomNumber + " has been added successfully.");

            assert r.getRoomNumber() == roomNumber;
            assert r.getRoomType() == roomType;
            assert r.getRoomPrice() == price;
            assert r.getRoomCapacity() == capacity;
            assert r.getRoomFacilities() == facilities;

            return true;

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
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
                return false;
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean addGuest(String fName, String lName, LocalDate dateJoin){
        try{
            // Uses my generateGuestID() method (my support methods are at the bottom, after the main interface ones).
            int guestID = generateGuestID();
            Guest g = new Guest(guestID, fName, lName, dateJoin);
            guests.add(g);
            System.out.println("Guest " + fName + " " + lName + " has been registered with new Guest ID: " + guestID);

            assert g.getGuestID() == guestID;
            assert g.getfName() == fName;
            assert g.getlName() == lName;
            assert g.getDateJoin() == dateJoin;

            return true;

        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean addGuest(String fName, String lName, LocalDate dateJoin, LocalDate VIPstartDate, LocalDate VIPexpiryDate){
        try{
            // Uses my generateGuestID() method (my support methods are at the bottom, after the main interface ones).
            int guestID = generateGuestID();
            Guest g = new VIP(guestID, fName, lName, dateJoin, VIPstartDate, VIPexpiryDate);
            guests.add(g);
            System.out.println("VIP Guest " + fName + " " + lName + " has been registered with Guest ID: " + guestID);

            // Annual fee of £50 as part of being registered as a VIP guest
            Payment p = new Payment(LocalDate.now(), guestID, 50.00, "VIPmembership");
            payments.add(p);

            return true;

        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean removeGuest(int guestID){
        try{
            boolean doesGuestHaveBooking = bookings.stream().anyMatch(b -> b.getGuestID() == guestID);
            // Uses my doesGuestExist(id) method. All my additional methods are below the main interface ones.
            if(doesGuestExist(guestID) && doesGuestHaveBooking) {
                guests.remove(getGuestObject(guestID));
                System.out.println("Guest " + guestID + " has been removed successfully.");
                return true;
            } else{
                System.out.println("ACTION DENIED: Cannot remove guest " + guestID
                        + ". This guest does not exist or has a room currently booked!");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean isAvailable(int roomNumber, LocalDate checkin, LocalDate checkout) {
        try {
            if(!isRoomBooked(roomNumber)) {
                /*System.out.println("Room " + roomNumber + " will be available between "
                        + checkin + " and " + checkout);*/
                return true;
            }
            for(Booking b : bookings) {
                int bookedRoomNumber = b.getRoomNumber();
                if (bookedRoomNumber == roomNumber){
                    LocalDate bookedCheckinDate = b.getCheckInDate();
                    LocalDate bookedCheckoutDate = b.getCheckOutDate();

                    if (bookedRoomNumber == roomNumber && !isDateWithinRangeExclusive(bookedCheckinDate, checkin, checkout)
                            && !isDateWithinRangeExclusive(bookedCheckoutDate, checkin, checkout)) {
                        /*System.out.println("Room " + roomNumber + " will be available between "
                                + checkin + " and " + checkout);*/
                        return true;
                    }
                    break;
                }
            }
            return false;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        // System.out.println("Room " + roomNumber + " will NOT be available between " + checkin + " and " + checkout);
    }

    public int[] availableRooms(RoomType roomType, LocalDate checkin, LocalDate checkout){
        try {
            ArrayList<Integer> availableRoomNumbers = new ArrayList<>();
            for (Room r : rooms) {
                int roomNumber = r.getRoomNumber();
                if (r.getRoomType() == roomType && isAvailable(roomNumber, checkin, checkout)) {
                    availableRoomNumbers.add(roomNumber);
                }
            }
            return availableRoomNumbers.stream().mapToInt(Integer::valueOf).toArray();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public int bookOneRoom(int guestID, RoomType roomType, LocalDate checkin, LocalDate checkout){
        try{
            if(!doesGuestExist(guestID)) {
                System.out.print("Booking unsuccessful: There is no such guest with ID " + guestID);
                return -1;
            }
            if(LocalDate.now().isAfter(checkin)) {
                System.out.print("Booking unsuccessful: The check-in date must be a date from today or onwards.");
                return -1;
            }
            int [] availableRooms = availableRooms(roomType, checkin, checkout);
            if(availableRooms.length > 0){
                // If there is an available room of the such roomType, it will book the first room from the array.
                int roomNumber = availableRooms[0];
                int bookingID = generateBookingID();
                LocalDate bookingDate = LocalDate.now();

                // Finds the price of the available room number.
                Room room = rooms.stream()
                        .filter(r -> r.getRoomNumber() == roomNumber)
                        .findFirst().orElseThrow(NoSuchElementException::new);
                double roomPrice = room.getRoomPrice();

                // Takes number of days stayed and a possible VIP discount into consideration for the final price
                long daysStayed = DAYS.between(checkin, checkout);
                double discount = 1;

                Guest guest = guests.stream()
                        .filter(g -> g.getGuestID() == guestID)
                        .findFirst().orElseThrow(NoSuchElementException::new);

                if(isGuestVIP(guestID)){

                    LocalDate VIPexpiryDate = ((VIP)guest).getVIPexpiryDate();
                    if(bookingDate.isBefore(VIPexpiryDate)) {
                        discount = 0.9;
                    }
                }
                double totalAmount = roomPrice * daysStayed * discount;

                Booking b = new Booking(bookingID, guestID, roomNumber, bookingDate, checkin, checkout, totalAmount);
                bookings.add(b);

                Payment p = new Payment(bookingDate, guest.getGuestID(), totalAmount, "booking");
                payments.add(p);

                System.out.println("A booking with ID " + bookingID + " has been created for room " + roomNumber
                        + " with room type " + roomType + " from " + checkin + " till " + checkout);
                return roomNumber;
            }
            System.out.println("There are no bookings available for '" + roomType + "' room types.");
            return -1;

        } catch(Exception ex){
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    public boolean checkOut(int bookingID, LocalDate actualCheckoutDate){
        try{
            if(doesBookingExist(bookingID)){
                Booking booking = bookings.stream()
                        .filter(b -> b.getID() == bookingID)
                        .findFirst().orElseThrow(NoSuchElementException::new);

                LocalDate checkin = booking.getCheckInDate();
                LocalDate checkout = booking.getCheckOutDate();

                if(isDateWithinRangeInclusive(actualCheckoutDate, checkin, checkout)){
                    bookings.remove(booking);
                    System.out.println("Check-out with booking ID " + bookingID + " successful.");
                    return true;
                } else {
                    System.out.println("Check-out unsuccessful: a check-out must be made during the booked period.");
                    return false;
                }

            } else {
                System.out.println("Check-out unsuccessful: There is no such booking with ID " + bookingID);
                return false;
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean cancelBooking(int bookingID){
        try{
            if(doesBookingExist(bookingID)) {
                for (Booking b : bookings) {
                    if (b.getID() == bookingID){
                        bookings.remove(b);
                        System.out.println("Booking with ID " + bookingID + " has been cancelled successfully.");

                        // Checks for a possible refund if cancellation is made 2 days or before the check-in date.
                        LocalDate currentDate = LocalDate.now();

                        if (currentDate.isBefore(b.getCheckInDate().minusDays(2))){
                            int guestID = b.getGuestID();
                            double totalAmount = b.getTotalAmount();
                            double refundAmount = totalAmount - 2*totalAmount; // Makes it negative (as it's refunded)
                            Payment p = new Payment(currentDate, guestID, refundAmount, "refund");
                            payments.add(p);
                            System.out.println("Refund of £" + refundAmount + " has been given.");
                        }
                        return true;
                    }
                }
                return false;
            } else {
                System.out.println("Cannot cancel booking: There is no such booking with ID " + bookingID);
                return false;
            }
        }catch(Exception ex){
            System.out.print(ex.getMessage());
            return false;
        }
    }

    public int[] searchGuest(String firstName, String lastName){
        try {
            ArrayList<Integer> guestIDs = new ArrayList<>();
            for (Guest g : guests) {
                int guestID = g.getGuestID();
                if (g.getfName() == firstName && g.getlName() == lastName) {
                    guestIDs.add(guestID);
                }
            }
            return guestIDs.stream().mapToInt(Integer::valueOf).toArray();
        }
        catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void displayGuestBooking(int guestID){
        try {
            System.out.println("Booking information for guest " + guestID + ":");
            for (Booking b : bookings) {
                if (b.getGuestID() == guestID){
                    System.out.println(b);
                }
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void displayBookingsOn(LocalDate thisDate){
        try {
            System.out.println("All bookings on " + thisDate + ":");
            for (Booking b : bookings) {
                if (b.getBookingDate() == thisDate){
                    System.out.println(b);
                }
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void displayPaymentsOn(LocalDate thisDate){
        try {
            System.out.println("All payments on " + thisDate + ":");
            for (Payment p : payments) {
                if (p.getDate() == thisDate){
                    System.out.println(p);
                }
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public boolean saveRoomsData(String roomsTxtFileName){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("testdata.txt"));
            for (Room r : rooms){
                bufferedWriter.write(String.valueOf(r).toLowerCase()); // all lower case like in the original file.
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean saveGuestsData(String guestsTxtFileName){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("testdata.txt"));
            for (Guest g : guests){
                bufferedWriter.write(String.valueOf(g));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean saveBookingsData(String bookingsTxtFileName){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("testdata.txt"));
            for (Booking b : bookings){
                bufferedWriter.write(String.valueOf(b));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean savePaymentsData(String paymentsTxtFileName){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("testdata.txt"));
            for (Payment p : payments){
                bufferedWriter.write(String.valueOf(p));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /*
     *   Supporting Methods
     */

    public boolean doesRoomExist(int roomNumber){
        return rooms.stream().anyMatch(r -> r.getRoomNumber() == roomNumber);
    }

    public boolean doesGuestExist(int guestID){
        return guests.stream().anyMatch(g -> g.getGuestID() == guestID);
    }

    public boolean doesBookingExist(int bookingID){
        return bookings.stream().anyMatch(b -> b.getID() == bookingID);
    }

    public boolean isRoomBooked(int roomNumber){
        return bookings.stream().anyMatch(b -> b.getRoomNumber() == roomNumber);
    }

    public boolean isGuestVIP(int guestID){
        for (Guest g : guests) {
            if(g.getGuestID() == guestID && ((VIP)g).getVIPexpiryDate() != null){
                return true;
            }
        }
        return false;
    }

    public Object getRoomObject(int roomNumber){
        Room roomObject = rooms.stream()
                .filter(r -> r.getRoomNumber() == roomNumber)
                .findFirst().orElseThrow(NoSuchElementException::new);
        return roomObject;
    }

    public Object getGuestObject(int guestID){
        Guest guestObject = guests.stream()
                .filter(g -> g.getGuestID() == guestID)
                .findFirst().orElseThrow(NoSuchElementException::new);
        return guestObject;
    }

    public Object getBookingObject(int bookingID){
        Booking bookingObject = bookings.stream()
                .filter(b -> b.getID() == bookingID)
                .findFirst().orElseThrow(NoSuchElementException::new);
        return bookingObject;
    }

    // Checks whether dateTested lies between firstDate and secondDate.
    public boolean isDateWithinRangeExclusive(LocalDate dateTested, LocalDate firstDate, LocalDate secondDate){
        return firstDate.compareTo(dateTested) * dateTested.compareTo(secondDate) > 0;
    }

    // Checks whether dateTested lies between firstDate and secondDate inclusive.
    public boolean isDateWithinRangeInclusive(LocalDate dateTested, LocalDate firstDate, LocalDate secondDate){
        return firstDate.compareTo(dateTested) * dateTested.compareTo(secondDate) >= 0;
    }

    public int generateBookingID(){
        // The last booking in the bookings list will have the largest booking ID value.
        // So, one value higher does not exist and that will be the new generated booking ID.
        // This will also make the bookings look coherent as each booking ID goes up by a value of 1.
        Booking lastBooking = bookings.get(bookings.size() - 1);
        int lastID = lastBooking.getID();
        return lastID + 1;
    }

    public int generateGuestID(){
        // Same concept here as in generateBookingID() above.
        Guest lastGuest = guests.get(guests.size() - 1);
        int lastID = lastGuest.getGuestID();
        return lastID + 1;
    }
}

