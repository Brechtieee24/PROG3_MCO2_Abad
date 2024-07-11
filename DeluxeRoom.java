public class DeluxeRoom extends Room{
    public DeluxeRoom(int num){
        super(num);
        setRoomPrice(super.getRoomPrice());
    }

    @Override
    public void setRoomPrice(float roomPrice) {
        roomPrice = roomPrice * 1.2f;
        super.setRoomPrice(roomPrice);
    }
}
