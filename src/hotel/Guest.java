package hotel;

import java.time.LocalDate;
/**
 * Used to create and store guest information.
 * Has methods which can return a guest's ID, first name, last name, date joined,
 * VIP start date and VIP expiry date.
 *
 */


// REMAKE THIS CLASS

// ADD SUPER CLASS / SUB CLASS FOR STANDARD AND VIP GUESTS like she wants..

// ALSO ADD A toString() override method like in the Room class (and other ones).


public class Guest {

    private int guestID;
    private String fName;
    private String lName;
    private LocalDate dateJoin;
    private LocalDate VIPstartDate;
    private LocalDate VIPexpiryDate;

    public Guest(int guestID, String fName, String lName, LocalDate dateJoin, LocalDate VIPstartDate, LocalDate VIPexpiryDate){

        this.guestID = guestID;
        this.fName = fName;
        this.lName = lName;
        this.dateJoin = dateJoin;
        this.VIPstartDate = VIPstartDate;
        this.VIPexpiryDate = VIPexpiryDate;

    }
    public int getGuestID(){ return guestID; }
    public String getfName(){ return fName; }
    public String getlName(){ return lName; }
    public LocalDate getDateJoin(){ return dateJoin; }
    public LocalDate getVIPstartDate(){ return VIPstartDate; }
    public LocalDate getVIPexpiryDate(){ return VIPexpiryDate; }

    // @Override
    // toString method

}
