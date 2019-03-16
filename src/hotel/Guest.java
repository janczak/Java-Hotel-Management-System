package hotel;

import java.time.LocalDate;
/**
 * Used to create and store guest information.
 * Has methods which can return a guest's ID, first name, last name, date joined.
 * It is extended by the VIP class, designed to handle VIP guests.
 *
 */
public class Guest {

    private int guestID;
    private String fName;
    private String lName;
    private LocalDate dateJoin;

    public Guest(int guestID, String fName, String lName, LocalDate dateJoin){

        this.guestID = guestID;
        this.fName = fName;
        this.lName = lName;
        this.dateJoin = dateJoin;

    }
    public int getGuestID(){ return guestID; }
    public String getfName(){ return fName; }
    public String getlName(){ return lName; }
    public LocalDate getDateJoin(){ return dateJoin; }

    @Override
    // Converts every guest information into a single string. Convenient when displaying all guests or writing to file.
    public String toString(){
        return this.guestID + "," + this.fName + "," + this.lName + "," + this.dateJoin;
    }
}





