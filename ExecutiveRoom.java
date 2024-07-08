public class ExecutiveRoom extends Room{
    public ExecutiveRoom(int num){
        super(num);
        setRoomPrice(super.getRoomPrice());
    }

    @Override
    public void setRoomPrice(float roomPrice) {
       roomPrice = roomPrice + (roomPrice * 0.35f);
       super.setRoomPrice(roomPrice);
    }
}
