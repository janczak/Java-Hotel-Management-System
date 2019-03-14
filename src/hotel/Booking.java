package hotel;

import java.time.LocalDate;
/**
 * Used to create and store booking information.
 * Has methods which can return specific information about a booking.
 *
 */
public class Booking {

    private int id;
    private int guestID;
    private int roomNumber;
    private LocalDate bookingDate;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private double totalAmount;

    public Booking(int id, int guestID, int roomNumber, LocalDate bookingDate,
                   LocalDate checkinDate, LocalDate checkoutDate, double totalAmount){
        this.id = id;
        this.guestID = guestID;
        this.roomNumber = roomNumber;
        this.bookingDate = bookingDate;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.totalAmount = totalAmount;
    }
    public int getID(){ return id; }
    public int getGuestID(){ return guestID; }
    public int getRoomNumber(){ return roomNumber; }
    public LocalDate getBookingDate(){ return bookingDate; }
    public LocalDate getCheckInDate(){ return checkinDate; }
    public LocalDate getCheckOutDate(){ return checkoutDate; }
    public double getTotalAmount(){ return totalAmount; }

    @Override
    public String toString(){
        return "Booking ID: " + this.id + ", Guest ID: " + this.guestID + ", Room: " + this.roomNumber
                + ", Booking Date: " + this.bookingDate + ", Checkin Date: " + this.checkinDate
                + ", Checkout Date: " + this.checkoutDate + ", Total Amount: £" + this.totalAmount;
    }
}
