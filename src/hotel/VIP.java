package hotel;

import java.time.LocalDate;
/**
 * Used to create and store guest information for VIP guests.
 * Has methods which can return a guest's ID, first name, last name, date joined, VIP start and expiry date.
 * Inherits from Guest class.
 *
 */
public class VIP extends Guest{

    private LocalDate VIPstartDate;
    private LocalDate VIPexpiryDate;

    public VIP(int guestID, String fName, String lName, LocalDate dateJoin, LocalDate VIPstartDate, LocalDate VIPexpiryDate){

        super(guestID, fName, lName, dateJoin);

        this.VIPstartDate = VIPstartDate;
        this.VIPexpiryDate = VIPexpiryDate;
    }
    public LocalDate getVIPstartDate(){ return VIPstartDate; }
    public LocalDate getVIPexpiryDate(){ return VIPexpiryDate; }

    @Override
    public String toString(){
        return super.toString() + "," + this.VIPstartDate + "," + this.VIPexpiryDate;
    }
}
