import java.util.ArrayList;

/**
 * Represents a reservation with guest name, check-in date, check-out date,
 * total reservation price, and room assignment.
 * @author Albrecht Gabriel Abad
 * @since July 2024
 * @version 2.0
 */
public class Reservation {
    private String guestName; // Name of guest on reservation
    private int checkInDate; // Check-In date of reservation
    private int checkOutDate; // Check-out date of reservation
    private float totalPrice; // Total price of this reservation
    private int roomNum; // Room assigned to this reservation

    /**
     * Constructor for Reservation.
     * @param guestName Guest name.
     * @param checkInDate Check-in date in "DD" format.
     * @param checkOutDate Check-out date in "DD" format.
     * @param roomNum Room number assigned by the system.
     * @param price Current room price of the assigned room.
     */
    public Reservation (String guestName, int checkInDate, int checkOutDate, int roomNum, float price, ArrayList<DateModifier> dates){
       float total = 0.0f;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNum = roomNum;
        // Compute for the total price
        for (DateModifier date : dates){
            if (date.getDay() >= checkInDate && date.getDay() < checkOutDate)
                total += price * date.getPricePercent();
        }
        this.totalPrice = total;
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
     * Compiles the attributes of this class to a string to display the reservation details.
     * @return a string that will store the room reservation details to be displayed.
     */
    public String toString(){
        int lengthOfStay = checkOutDate - checkInDate;
        float pricePerNight = getTotalPrice() / lengthOfStay;

        return "\n[Room " + this.roomNum + "]\n" +
                "Guest Name: " + getGuestName() + "\n" +
                "Check-in Date: " + getCheckInDate() + "\n" +
                "Check-out Date: " + getCheckOutDate() + "\n" +
                "Length of Stay: " + lengthOfStay + " days\n" +
                "Price per night: " + String.format("%.2f", pricePerNight) + "\n\n" +
                "TOTAL PRICE: " + String.format("%.2f", getTotalPrice() ) + "\n";

    }
}
