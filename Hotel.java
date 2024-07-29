import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a Hotel that can store the instances of its respective rooms
 * and reservations.
 * @author Albrecht Gabriel Abad
 * @since July 2024
 * @version 2.0
 */
public class Hotel {
    private String hotelName; // The name of this Hotel
    private ArrayList<Reservation> reservations; // The reservations list of this hotel
    private ArrayList<Room> rooms; // The rooms in this hotel
    private ArrayList<DateModifier> dates; // The dates and their respective rates

    /**
     * Constructor for Hotel class
     *<p>
     *     The hotel name and number of rooms are valid values
     *     passed from 'ManageHotel' {@link ManageHotel}.
     *</p>
     * @param hotelName Hotel name assigned to this class.
     * @param numExe The number of Executive rooms to be instantiated.
     * @param numDlx The number of Deluxe rooms to be instantiated.
     * @param numStd The number of Standard rooms to be instantiated.
     */
    public Hotel(String hotelName, int numExe, int numDlx, int numStd){
        this.hotelName = hotelName;
        this.reservations = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.dates = new ArrayList<>();

        for(int i = 1; i <= numExe; i++)
            rooms.add(new ExecutiveRoom(300 + i));

        for(int i = 1; i <= numDlx; i++)
            rooms.add(new DeluxeRoom(200 + i));

        for(int i = 1; i <= numStd; i++)
            rooms.add(new StandardRoom(100 + i));

        for(int i = 1; i <= 31; i++) // Set the dates by default to 100% rate
            dates.add(new DateModifier(i, 1.0f));
    }

    /**
     * Sets the hotel name.
     * <p>
     *     This method is called when a change of hotel name is
     *     initiated in 'ManageHotel': {@link ManageHotel}.
     * </p>
     * @param hotelName The desired hotel name.
     */
    public void setHotelName (String hotelName){
        this.hotelName = hotelName;
    }

    /**
     * Gets the hotel name of this Hotel.
     * @return The current hotel name assigned.
     */
    public String getHotelName(){
        return hotelName;
    }

    /**
     * Gets the earnings of this Hotel.
     * <p>
     *     The earnings is calculated by adding the total earnings of
     *     each 'Room': {@link Room} generated in this class.
     * </p>
     * @return The total earnings of this Hotel.
     */
    public float getHotelEarnings(){
        float earnings = 0.0f;
        for (Room room : rooms)
            earnings += room.getEarnings();
        return earnings;
    }

    /**
     * Returns the reservations list in this Hotel.
     * @return The reservations.
     */
    public ArrayList<Reservation> getReservations(){
        return reservations;
    }

    /**
     * Gets the rooms and the room details of this Hotel.
     * @return The list of 'Room':{@link Room} objects with its corresponding attributes.
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Returns the dates and its surcharge
     */
    public ArrayList<DateModifier> getDates(){
        return dates;
    }

    /**
     * Displays high level information of this hotel which includes
     * the hotel name, total earnings, and number of rooms.
     */
    public void dispHighInfo(){
        float earnings = getHotelEarnings();
        System.out.println("\nHotel Name: " + hotelName);
        System.out.println("Earnings for the month: " + String.format("%.2f", earnings));
        System.out.println("\nTotal number of rooms: " + rooms.size());
        if (numStandard() > 0) { // Displays the number of standard rooms
            System.out.print("Standard Rooms: " + numStandard());
            System.out.println(" [" + 101 + " to " + (100 + numStandard()) + "]");
        }
        if(numDeluxe() > 0) { // Displays the number of deluxe rooms
            System.out.print("Deluxe Rooms: " + numDeluxe());
            System.out.println(" [" + 201 + " to " + (200 + numDeluxe()) + "]");
        }
        if(numExec() > 0) { // Displays the number of executive rooms
            System.out.print("Executive Rooms: " + numExec());
            System.out.println(" [" + 301 + " to " + (300 + numExec()) + "]");
        }
    }

    /**
     * Displays the room information of the selected room in this hotel
     * such as the room’s name, price per night, and availability across
     * the entire month.
     * <p>
     *     The room number is obtained by passing the inputted value of the
     *     user in 'ManageHotel':{@link ManageHotel}.
     * </p>
     * <p>
     *     The availability of the room is computed by getting the reservations
     *     in the selected room and computing the total days by subtracting the
     *     check-in date to the check-out date.
     * </p>
     *
     * @param roomNum The room number to view room details.
     */
    public void viewRoomInfo(int roomNum){
        int daysAvailable, daysOccupied = 0, index = 0;

        System.out.println("\nRoom " + roomNum + ": ");
        for(Room room : rooms) {
            if(roomNum == room.getRoomNum())
                break;
            index++;
        }
        System.out.println("Price per night: " + rooms.get(index).getRoomPrice());

        for(Reservation reservation : reservations){
            if(reservation.getRoomNum() == roomNum)
                daysOccupied += reservation.getCheckOutDate() - reservation.getCheckInDate();
        }

        daysAvailable = 31 - daysOccupied;

        System.out.println("Total days available: " + daysAvailable);
        System.out.println("Total days with booking: " + daysOccupied);
    }

    /**
     * Checks if the room number inputted is a valid room number.
     * @param roomNum The room number to search.
     * @return The boolean value of the search result.
     */
    public boolean isRoomFound(int roomNum){
        for(Room room : rooms) {
            if(roomNum == room.getRoomNum())
                return true;
        }
        return false;
    }

    /**
     * Checks the number of days that a room is occupied.
     * @param roomNum The room to be checked.
     * @return The number of days occupied.
     */
    public int daysOccupied(int roomNum){
        int daysOccupied = 0;
        for(Reservation reservation : reservations){
            if(reservation.getRoomNum() == roomNum)
                daysOccupied += reservation.getCheckOutDate() - reservation.getCheckInDate();
        }
        return daysOccupied;
    }

    /**
     * Getter for the room price.
     * @param roomNum The room number to check.
     * @return The room price.
     */
    public float getRoomPrice(int roomNum){
        for(Room room: rooms){
            if(room.getRoomNum() == roomNum)
                return room.getRoomPrice();
        }
        return 0.0f;
    }

    /**
     * Sets a reservation in this hotel
     * <p>
     *     This sets a reservation to this Hotel by iterating through the
     *     rooms that are vacant on the desired dates.
     * </p>
     * <ul>
     *     <li> TRUE: If adding of reservation is successful. </li>
     *     <li> FALSE: If there are no rooms available on the selected dates.</li>
     * </ul>
     * <p>
     *     Room type are assigned as follows:
     * </p>
     * <ul>
     *     <li> 1: Standard Room </li>
     *     <li> 2: Deluxe Room </li>
     *     <li> 3: Executive Room </li>
     * </ul>
     * @param guestName Guest name.
     * @param checkInDate Check-in date in "DD" format.
     * @param checkOutDate Check-out date in "DD" format.
     * @param roomType The room type of this reservation.
     * @param totalPrice Total price of this reservation.
     * @return The boolean value of the status after adding a reservation.
     */
    public boolean setReservation(String guestName, int checkInDate, int checkOutDate, int roomType, float totalPrice){

        if(roomType == 1){ // Room is a standard room
            for(Room room : rooms){
                if(room instanceof StandardRoom){
                    if(room.isDateAvail(checkInDate, checkOutDate)) {
                        reservations.add(new Reservation(guestName, checkInDate, checkOutDate, room.getRoomNum(),
                                totalPrice));
                        room.addReservation(reservations.getLast());
                        return true;
                    }
                }
            }
        } else if (roomType == 2) { // Room is a deluxe room
            for(Room room : rooms){
                if(room instanceof DeluxeRoom){
                    if(room.isDateAvail(checkInDate, checkOutDate)) {
                        reservations.add(new Reservation(guestName, checkInDate, checkOutDate, room.getRoomNum(),
                                totalPrice));
                        room.addReservation(reservations.getLast());
                        return true;
                    }
                }
            }
        } else if (roomType == 3) { // Room is an executive room
            for(Room room : rooms){
                if(room instanceof ExecutiveRoom){
                    if(room.isDateAvail(checkInDate, checkOutDate)) {
                        reservations.add(new Reservation(guestName, checkInDate, checkOutDate, room.getRoomNum(),
                                totalPrice));
                        room.addReservation(reservations.getLast());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Removes a reservation in this hotel
     * <p>
     *     This method first checks if there are reservations in this hotel.
     *     If there are none, it will prompt a "No reservations found." Otherwise,
     *     it will display the reservations list and the user can choose from it
     *     the reservation number to be removed.
     * </p>
     *
     * @param sc Passes the scanner from 'Driver':{@link Driver} via 'ManageHotel':{@link ManageHotel}.
     */
    public void removeReservation(Scanner sc){
        int ctr = -1, choice;
        boolean success = false;
        ManageHotel intCheck = new ManageHotel(sc);

        if(!isReservationEmpty() && dispRoomReservation() == 1) {
            do {
                System.out.print("\033\143");
                dispRoomReservation();
                System.out.print("\nEnter reservation number to be removed: ");

                if (sc.hasNextInt())
                    ctr = sc.nextInt() - 1;

                if (ctr >= 0 && ctr < reservations.size()) {
                    System.out.println("\nAre you sure you want to remove reservation # " + (ctr + 1));
                    System.out.println("[1] YES (This is irreversible!)");
                    System.out.println("[2] NO");
                    System.out.print("Enter your choice: ");
                    choice = intCheck.checkInt();

                    switch (choice) {
                        case 1:
                            reservations.remove(ctr);
                            success = true;
                            System.out.print("\nSuccessfully removed reservation # " + (ctr + 1));
                            System.out.print("\nPress enter to continue...");
                            sc.nextLine();
                            System.out.print("\033\143");
                            break;
                        case 2:
                            success = true;
                            System.out.print("\nPress enter to continue...");
                            sc.nextLine();
                            System.out.print("\033\143");
                            break;
                    }
                }
                // Reservation number is not in the list
                else {
                    sc.nextLine();
                    System.out.println("\nPlease enter a valid reservation number");
                    System.out.print("Press enter to continue...");
                    sc.nextLine();
                }
            } while (!success);
        }
        // No reservations in the hotel
        else {
            System.out.println("No reservation found");
            System.out.print("\nPress enter to continue...");
            sc.nextLine();
            System.out.print("\033\143");
        }
    }

    /**
     * Removes a reservation through GUI input.
     * @param index The reservation index to be removed.
     */
    public void removeReservationGui(int index){
        int ctr, resSize, roomDate;
        int roomNum = reservations.get(index).getRoomNum();
        int checkIn = reservations.get(index).getCheckInDate();
        for(Room room: rooms){
            if (roomNum == room.getRoomNum()) {
                resSize = room.getReservations().size();
                ctr = 0; // Set to 0 as a start of the reservation list index.
                while (ctr < resSize) {
                    roomDate = room.getReservations().get(ctr).getCheckInDate();
                    if (roomDate == checkIn) { // return the index once the room date matches the check in date
                        room.removeReservation(ctr);
                        reservations.remove(index);
                        return;
                    }
                    ctr++;
                }
            }
        }
    }

    /**
     * Displays room reservation details.
     * <ul>
     *     <li>1 if there are reservations</li>
     *     <li>0 if there are no reservations</li>
     * </ul>
     * @return The corresponding value when there is a reservation in this hotel.
     */
    public int dispRoomReservation(){
        int ctr = 1, index = 0;
        System.out.println("Reservations at " + hotelName);
        if(!reservations.isEmpty()) { // check if arraylist is not empty
            for (Reservation ignored : reservations) {
                System.out.println(ctr + ". " + reservations.get(index).getGuestName());
                ctr++;
                index++;
            }
            return 1;
        }
        else
            System.out.println("No reservations found");
        return 0;
    }

    /**
     * Displays reservation info
     * @param index The index of the reservation passed from 'ManageHotel': {@link ManageHotel}.
     */
    public void viewReservationInfo(int index){
        System.out.println(reservations.get(index).toString());
    }

    /**
     * Checks if the reservations list is empty.
     * <p>
     *     This method checks if the reservation list is empty by
     *     calling isEmpty() function of an ArrayList.
     * </p>
     * <ul>
     *     <li>TRUE if there are no reservations.</li>
     *     <li>FALSE if there are reservations.</li>
     * </ul>
     * @return The boolean status of the reservations list.
     */
    public boolean isReservationEmpty(){
        return reservations.isEmpty();
    }

    /**
     * Adds additional rooms to this hotel.
     *<p>
     *     The number of rooms to be added is obtained from the inputted
     *     value of the user in 'ManageHotel': {@link ManageHotel}.
     *     This will then return the following values based from the result:
     * </p>
     * <ul>
     *     <li>TRUE: If adding of rooms is successful.</li>
     *     <li>FALSE: If desired number of rooms cannot be added.</li>
     * </ul>
     * <p>
     *     Room type are assigned as follows:
     * </p>
     * <ol>
     *     <li> Standard Room </li>
     *     <li> Deluxe Room </li>
     *     <li> Executive Room </li>
     * </ol>
     * @param num The number of rooms to add.
     * @param roomType The room type of the desired room to be added
     * @return The boolean status after adding the desired number of rooms.
     */
    public boolean addRoom(int num, int roomType){
        int ctr = 0;
        int avail = 50 - rooms.size();
        String type = "";

            // check if the inputted number is within 1 to 50
            if ((num > 0 && num < 50) && (num <= avail)) {
                    if (roomType == 1) {
                        while (ctr < num) {
                            rooms.add(new StandardRoom(100 + numStandard() + 1));
                            ctr++;
                        }
                        type = "Standard Rooms";
                    }
                    else if (roomType == 2) {
                        while (ctr < num) {
                            rooms.add(new DeluxeRoom(200 + numDeluxe() + 1));
                            ctr++;
                        }
                        type = "Deluxe Rooms";
                    }
                    else if (roomType == 3) {
                        while (ctr < num) {
                            rooms.add(new ExecutiveRoom(300 + numExec() + 1));
                            ctr++;
                        }
                        type = "Executive Rooms";
                    }

                System.out.println("\nSuccessfully added " + num + " " + type);
                System.out.println("Total rooms is now " + rooms.size());
                System.out.println("Standard Rooms: " + numStandard() );
                System.out.println("Deluxe Rooms: " + numDeluxe());
                System.out.println("Executive Rooms: " + numExec());
                return true;
            }

            else
                System.out.println("You must add at least one room and only " + avail + " rooms at most can be added!");
            return false;
    }

    /**
     * Removes a room in the hotel database.
     *<p>
     *     The room number is obtained from the inputted room of the user in
     *     'ManageHotel': {@link ManageHotel}. This will then return the
     *     following values based from the result:
     * </p>
     * <ul>
     *     <li> -1 if room does not exist. </li>
     *     <li> 0 if room is occupied. </li>
     *     <li> 1 if removing of room is successful. </li>
     * </ul>
     * @param room The room number to be removed.
     * @return The removal status of the selected room.
     */
    public int removeRoom(int room) {
        int index = -1;

        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomNum() == room) {
                index = i;
                break;
            }
        }
        // Room not found
        if (index == -1) {
            System.out.println("\nRoom " + room + " does not exist.");
            return -1;
        }

        // Check if there are any reservations for the room
        if (!rooms.get(index).getReservations().isEmpty()) {
            System.out.println("\nCannot remove Room " + room + " because it is occupied!");
            return 0;
        }

        rooms.remove(index);
        System.out.println("\nSuccessfully removed Room " + room);
        for (int j = index; j < rooms.size(); j++) {
            rooms.get(j).setRoomNum(rooms.get(j).getRoomNum() - 1);
        }
        return 1;
    }

    /**
     * Computes for the number of standard rooms.
     * @return The total number of standard rooms.
     */
    public int numStandard(){
        int ctr = 0;
        for(Room room : rooms){
            if(room instanceof StandardRoom)
                ctr++;
        }
        return ctr;
    }

    /**
     * Computes for the number of deluxe rooms.
     * @return The total number of deluxe rooms.
     */
    public int numDeluxe(){
        int ctr = 0;
        for(Room room : rooms){
            if(room instanceof DeluxeRoom)
                ctr++;
        }
        return ctr;
    }

    /**
     * Computes for the number of executive rooms.
     * @return The total number of executive rooms.
     */
    public int numExec(){
        int ctr = 0;
        for(Room room : rooms){
            if(room instanceof ExecutiveRoom)
                ctr++;
        }
        return ctr;
    }

    /**
     * Sets the base price for the rooms in this hotel.
     * @param price The new price set for each room that is >100.0.
     */
    public void setBasePrice(float price){
        for (Room room : rooms) {
            if(room instanceof ExecutiveRoom)
                room.setRoomPrice(price);
            else if(room instanceof DeluxeRoom)
                room.setRoomPrice(price);
            else if(room instanceof StandardRoom)
                room.setRoomPrice(price);
        }
    }

    /**
     * Gets the current base price of this hotel.
     * @return The current base price by getting the price of a standard room.
     */
    public float getBasePrice(){
        float price = 0.0f;
        for(Room room : rooms){
            if(room instanceof StandardRoom){
                price = room.getRoomPrice();
                break;
            }
        }
        return price;
    }

    /**
     * Checks if the voucher applied is valid
     * <p>
     *     This method will return the following values after checking the voucher
     * </p>
     * <ul>
     *     <li> 0 if voucher is invalid </li>
     *     <li> 1 if it matches "I_WORK_HERE" </li>
     *     <li> 2 if it matches "STAY4_GET1" </li>
     *     <li> 3 if it matches "PAYDAY" </li>
     * </ul>
     */
    public int checkVoucher(String voucher, int checkIn, int checkOut){

        // Flat 10% discount to the final price of a reservation
        if(voucher.equals("I_WORK_HERE")){
            return 1;
        }

        /*
            If the reservation has 5 days or more, the first day of the reservation is given
        for free
         */
        else if (voucher.equals("STAY4_GET1") && (checkOut - checkIn >= 5)) {
            return 2;
        }

        /*
            This gives a 7% discount to the overall price if reservation covers (but not as
        checkout) either day 15 or 30.
         */
        else if (voucher.equals("PAYDAY") && ( (15 >= checkIn && 15 < checkOut) || (30 >= checkIn && 30 < checkOut) )) {
            return 3;
        }
        return 0;
    }

    /**
     * Computes for the total reservation price
     * @param voucher The voucher type to be applied.
     * @param checkIn Check-in date of the reservation.
     * @param checkOut Check-out date of the reservation.
     * @param roomType Room type selected by the user.
     * @return The computed total price of the reservation.
     */
    public float computeTotal(int voucher, int checkIn, int checkOut, int roomType){
        float price = 0.0f, roomRate = 0.0f;

        roomRate = switch (roomType) {
            case 1 -> getBasePrice();
            case 2 -> getBasePrice() * 1.2f;
            case 3 -> getBasePrice() * 1.35f;
            default -> roomRate;
        };

        if(voucher == 1){ // I_WORK_HERE
            for (DateModifier date : dates){
                if (date.getDay() >= checkIn && date.getDay() < checkOut)
                    price += roomRate * date.getPricePercent();
            }
            return price * 0.9f; // Multiplied to 90% (100% - 10%)
        }
        else if (voucher == 2){ // STAY4_GET1
            for (DateModifier date : dates){ // Exclusive statement because 1st day is free
                if (date.getDay() > checkIn && date.getDay() < checkOut)
                    price += roomRate * date.getPricePercent();
            }
            return price;
        }
        else if (voucher == 3){ // PAYDAY
            for (DateModifier date : dates){
                if (date.getDay() >= checkIn && date.getDay() < checkOut)
                    price += roomRate * date.getPricePercent();
            }
            return price * 0.93f; // 100 - 93 = 7% off
        }
        else{ // No voucher applied
            for (DateModifier date : dates){
                if (date.getDay() >= checkIn && date.getDay() < checkOut)
                    price += roomRate * date.getPricePercent();
            }
            return price;
        }
    }

    /**
     * Modifies the rate for the selected date.
     * @param setDate The date to be modified.
     * @param percent The new rate to be applied on the selected day.
     */
    public void modifyDate(int setDate, float percent){
        for(DateModifier day : dates){
            if(setDate == day.getDay()){
                day.setPricePercent(percent);
                break;
            }
        }
    }

    /**
     * Gets the current rate of the selected day.
     * @param day The date to be checked
     * @return The rate for the selected day.
     */
    public float getRate(int day){
        day -= 1; // Index of the day in DateModifier list
        return dates.get(day).getPricePercent();
    }

    /**
     * Displays rate for each day
     */
    public void displayDayRate(){
        System.out.println("Day    Rate");
        for (DateModifier list : dates){
            System.out.println(String.format("%02d", list.getDay()) + " || " +
                    String.format("%.2f", list.getPricePercent()*100) +"%" +
                    "  == Std [" + String.format("%.2f", (getBasePrice() * list.getPricePercent())) + "]" +
                    "  Dlx [" + String.format("%.2f", (getBasePrice() * 1.2f * list.getPricePercent())) + "]" +
                    "  Exe [" + String.format("%.2f", (getBasePrice() * 1.35f * list.getPricePercent())) + "]");
        }
    }
}