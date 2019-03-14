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

    private int initGuestID;
    private String initfName;
    private String initlName;
    private LocalDate initDateJoin;
    private LocalDate initVIPstartDate;
    private LocalDate initVIPexpiryDate;

    public Guest(int guestID, String fName, String lName, LocalDate dateJoin, LocalDate VIPstartDate, LocalDate VIPexpiryDate){

        initGuestID = guestID;
        initfName = fName;
        initlName = lName;
        initDateJoin = dateJoin;
        initVIPstartDate = VIPstartDate;
        initVIPexpiryDate = VIPexpiryDate;

    }
    public int getGuestID(){ return initGuestID; }
    public String getfName(){ return initfName; }
    public String getlName(){ return initlName; }
    public LocalDate getDateJoin(){ return initDateJoin; }
    public LocalDate getVIPstartDate(){ return initVIPstartDate; }
    public LocalDate getVIPexpiryDate(){ return initVIPexpiryDate; }

    // @Override
    // toString method

}
