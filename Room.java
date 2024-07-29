import java.util.ArrayList;

/**
 * Parent class of the various types of rooms. This contains functions that simulates a hotel room.
 * @author Albrecht Gabriel Abad
 * @since July 2024
 * @version 2.0
 */
public class Room {
    private int roomNum; // The room number of this room.
    private float roomPrice; // Current price of the room.
    private ArrayList<Reservation> reservations; // Stores the reservations of this room.

    /**
     * Constructor for Room class.
     * @param roomNum Room number assigned to this room.
     */
    public Room (int roomNum){
        this.roomNum = roomNum;
        this.roomPrice = 1299.0f;
        this.reservations = new ArrayList<>();
    }

    /**
     * Sets the room number of this room.
     *<p>
     * The room number is obtained from the class named 'Hotel': {@link Hotel}.
     *</p>
     * @param roomNum The room number obtained from 'Hotel': {@link Hotel}.
     */
    public void setRoomNum(int roomNum){
        this.roomNum = roomNum;
    }

    /**
     * Gets the room number of this room.
     * @return The room number assigned to this room.
     */
    public int getRoomNum(){
        return roomNum;
    }

    /**
     * Gets the room price of this room.
     * @return The base price of this room.
     */
    public float getRoomPrice(){
        return roomPrice;
    }

    /**
     * Modifies the room price.
     *<p>
     *     The price of this room is set from 'Hotel': {@link Hotel}
     *     using the method that sets the base price.
     *</p>
     * @param roomPrice Value for new room price.
     */
    public void setRoomPrice(float roomPrice){
        this.roomPrice = roomPrice;
    }

    /**
     * Gets the reservations of this room.
     * @return The reservations assigned in this room.
     */
    public ArrayList<Reservation> getReservations(){
        return reservations;
    }

    /**
     * Removes the reservation details stored in this room.
     * @param index The index of the reservation to be removed.
     */
    public void removeReservation(int index){
        reservations.remove(index);
    }

    /**
     * check if date is available
     * <p>
     *    Checks first if the reservation can be added by comparing the dates
     *    in the current reservations list then returns a boolean value.
     * </p>
     *    <ul>
     *      <li>TRUE: If reservation can be added.</li>
     *      <li>FALSE: If the selected dates are currently booked.</li>
     *    </ul>
     * @param checkIn The date of check-in.
     * @param checkOut The date of check-out.
     * @return The boolean value of the implementation.
     */
    public boolean isDateAvail(int checkIn, int checkOut){
        for(Reservation reservation: reservations){
            // check for existing reservation within the selected date
            if ((checkIn >= reservation.getCheckInDate() && checkIn < reservation.getCheckOutDate()) ||
                    (checkOut > reservation.getCheckInDate() && checkOut <= reservation.getCheckOutDate()) ||
                    (checkIn <= reservation.getCheckInDate() && checkOut >= reservation.getCheckOutDate())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates the total earnings of this room.
     * @return The total earnings of this room.
     */
    public float getEarnings(){
        float earnings = 0.0f;

        for(Reservation reservation: reservations){
            earnings += reservation.getTotalPrice();
        }
        return earnings;
    }

    /**
     * Adds a reservation to this room.
     * @param reservation This contains the reservation details set to this room.
     */
    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }
}
