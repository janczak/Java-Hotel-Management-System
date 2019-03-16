package hotel;

//import hotel.Hotel;
//import hotel.HotelImpl;

import java.time.LocalDate;

public class test {
    public static void main(String[] args){

        String roomsTxtFile = "rooms.txt";
        String guestsTxtFile = "guests.txt";
        String bookingsTxtFile = "bookings.txt";
        String paymentsTxtFile = "payments.txt";

        HotelImpl test = new HotelImpl(roomsTxtFile, guestsTxtFile, bookingsTxtFile, paymentsTxtFile);

        test.displayAllRooms();
        test.displayAllGuests();
        test.bookOneRoom(10002, RoomType.FAMILY, LocalDate.of(2019, 3, 25), LocalDate.of(2019, 3, 30));
        test.bookOneRoom(10003, RoomType.SINGLE, LocalDate.of(2019, 4, 1), LocalDate.of(2019, 4, 3));
        test.addGuest("John", "Smith", LocalDate.of(2019, 3, 15), LocalDate.of(2019, 3, 15), LocalDate.of(2020, 3, 15));
        test.displayAllBookings();
        test.displayAllPayments();
        test.saveGuestsData("testdata.txt");

        // all other have been tested and work in a proper test app
    }
}
