/**
 * A subclass of 'Room': {@link Room} class that simulates a Deluxe room.
 *
 * @author Albrecht Gabriel Abad
 * @since July 2024
 * @version 1.0
 */

public class DeluxeRoom extends Room{

    /**
     * Constructor for a deluxe room.
     * @param num The room number assigned to this room.
     */
    public DeluxeRoom(int num){
        super(num);
        setRoomPrice(super.getRoomPrice());
    }

    /**
     * Sets the value of a deluxe room by increasing it 20% from the base price.
     * @param roomPrice The base price of the hotel.
     */
    @Override
    public void setRoomPrice(float roomPrice) {
        roomPrice = roomPrice * 1.2f;
        super.setRoomPrice(roomPrice);
    }
}
