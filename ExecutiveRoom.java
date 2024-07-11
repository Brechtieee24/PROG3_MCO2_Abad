/**
 * A subclass of 'Room': {@link Room} class that simulates an Executive room.
 *
 * @author Albrecht Gabriel Abad
 * @since July 2024
 * @version 1.0
 */

public class ExecutiveRoom extends Room{
    /**
     * Constructor for an Executive room.
     * @param num The room number assigned.
     */
    public ExecutiveRoom(int num){
        super(num);
        setRoomPrice(super.getRoomPrice());
    }

    /**.
     * Sets the room price of an executive room which is 35% higher than the base price
     * @param roomPrice The base price of the hotel.
     */
    @Override
    public void setRoomPrice(float roomPrice) {
       roomPrice = roomPrice * 1.35f;
       super.setRoomPrice(roomPrice);
    }
}
