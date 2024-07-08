/**
 * Represents a reservation with guest name, check-in date, check-out date,
 * total reservation price, and room assignment.
 * @author Albrecht Gabriel Abad
 * @since June 2024
 * @version 1.0
 */
public class Reservation {
    private final String guestName; // Name of guest on reservation
    private final int checkInDate; // Check-In date of reservation
    private final int checkOutDate; // Check-out date of reservation
    private float totalPrice; // Total price of this reservation
    private final int roomNum; // Room assigned to this reservation

    /**
     * Constructor for Reservation.
     * @param guestName Guest name.
     * @param checkInDate Check-in date in "DD" format.
     * @param checkOutDate Check-out date in "DD" format.
     * @param roomNum Room number assigned by the system.
     * @param price Current room price of the assigned room.
     */
    public Reservation (String guestName, int checkInDate, int checkOutDate, int roomNum, float price){
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = (checkOutDate - checkInDate) * price;
        this.roomNum = roomNum;
    }

    /**
     * Gets the guest name of this reservation.
     * @return The guest name.
     */
    public String getGuestName(){
        return guestName;
    }

    /**
     * Gets the check-in date of this reservation.
     * @return The check-in date.
     */
    public int getCheckInDate(){
        return checkInDate;
    }

    /**
     * Gets the check-out date of this reservation.
     * @return The check-out date.
     */
    public int getCheckOutDate(){
        return checkOutDate;
    }

    /**
     * Gets the total reservation price.
     * @return The total price for this reservation.
     */
    public float getTotalPrice(){
        return totalPrice;
    }

    /**
     * Gets the room number assigned to this reservation.
     * @return The room number assigned to reservation.
     */
    public int getRoomNum(){
        return roomNum;
    }

    /**
     * Calculates the total reservation price.
     *<p>
     * The reservation price is obtained by counting the number of days of stay
     * by subtracting the check-in date to the check-out date. This is then
     * multiplied to the current room rate of the hotel.
     *</p>
     * <p>
     *     The room rate is obtained by passing the current base price for each room
     *     in the 'Hotel': {@link Hotel}.
     * </p>
     * @param roomRate The room rate for each room in this system.
     */
    public void setTotalPrice(float roomRate){
        int daysOfStay = checkOutDate - checkInDate;
        this.totalPrice = daysOfStay * roomRate;
    }

    /**
     * Compiles the attributes of this class to a string to display the reservation details.
     * @return a string that will store the room reservation details to be displayed.
     */
    public String toString(){
        return "\n[Room " + this.roomNum +"]\n" +
                "Guest Name: " + getGuestName() + "\n" +
                "Check-in Date: " + getCheckInDate() + "\n" +
                "Check-out Date: " + getCheckOutDate() + "\n" +
                "Length of Stay: " + (checkOutDate - checkInDate) + " days\n" +
                "Price per night: " + (getTotalPrice()/((float)checkOutDate-checkInDate)) + "\n\n" +
                "TOTAL PRICE: " + getTotalPrice() + "\n";
    }
}
