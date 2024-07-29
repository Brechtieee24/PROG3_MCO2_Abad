import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class serves as the hotel management system.
 * <p>
 *     The user can create multiple independent hotels in this class
 *     which can store its unique rooms, reservations, and information.
 * </p>
 * @author Albrecht Gabriel Abad
 * @since July 2024
 * @version 2.0
 */
public class ManageHotel {
    private ArrayList<Hotel> hotels; // Stores the hotels created
    private Scanner sc; // Scanner for command line inputs

    /**
     * Constructor for ManageHotel
     */
    public ManageHotel(Scanner sc) {
        this.hotels = new ArrayList<>();
        this.sc = sc;
    }

    /**
     * Creates a hotel.
     *<p>
     *     The user is prompted to enter the desired name of the hotel
     *     and the total number of rooms. It also passes the scanner from
     *     'Driver': {@link Driver}.
     *</p>
     * <p>
     *     This also has a feature to check that the number of rooms
     *     does not exceed the specifications of only having 50 rooms
     *     at most.
     * </p>
     */
    public void createHotel(){
        String name, temp1, temp2;
        int numStd, numDlx, numExe, numOfRooms = 1, equals;

        do {
            equals = 0;
            System.out.print("Enter new hotel name: ");
            name = sc.nextLine();
            // Validates if the name is unique
            for(Hotel hotel : hotels){
                temp1 = hotel.getHotelName().toLowerCase();
                temp2 = name.toLowerCase();
                if (temp1.equals(temp2)) {
                    equals = -1;
                    System.out.println("Hotel already exists!");
                    System.out.print("\nPress enter to continue...");
                    sc.nextLine();
                    break;
                }
            }
        } while (equals == -1);

        do{
            if(numOfRooms > 50 || numOfRooms < 1 )
                System.out.println("\nYou can only have 1 to 50 rooms.");

            System.out.print("Enter number of Standard Rooms: ");
            numStd = sc.nextInt();
            System.out.print("Enter number of Deluxe Rooms: ");
            numDlx = sc.nextInt();
            System.out.print("Enter number of Executive Rooms: ");
            numExe = sc.nextInt();
            sc.nextLine();
            numOfRooms = numDlx + numExe + numStd;
        } while (numOfRooms > 50 || numOfRooms < 1);

        System.out.println("\nHotel Name: " + name);
        System.out.println("Standard Rooms: " + numStd);
        System.out.println("Deluxe Rooms: " + numDlx);
        System.out.println("Executive Rooms: " + numExe);
        System.out.println("Total rooms: " + numOfRooms);
        System.out.print("\nPress enter to continue...");
        sc.nextLine();

        hotels.add(new Hotel(name, numExe, numDlx, numStd));
        System.out.print("\033\143");
    }

    /**
     * Creates a hotel using GUI.
     * @param std The number of standard rooms.
     * @param dlx The number of deluxe rooms.
     * @param exe The number of executive rooms.
     * @param name The name of the hotel.
     */
    public void createHotel(int std, int dlx, int exe, String name){
        hotels.add(new Hotel(name, exe, dlx, std));
    }

    /**
     * Checks if the inputted value is a valid integer.
     * <p>
     *     This method reduces the number of lines needed as integer values
     *     are much needed in this project. It makes sure that the value is
     *     a valid integer then returns it.
     * </p>
     * @return The valid integer value input.
     */
    public int checkInt(){
        boolean check = false;

        int main = -1;

        while (!check) {
            if(sc.hasNextInt()) {
                check = true;
                main = sc.nextInt();
                sc.nextLine();
            }
            else {
                System.out.println("INVALID INPUT!");
                sc.next();
            }
        }
        return main;
    }

    /**
     * Gets the hotel index.
     *<p>
     *     From the list displayed, the index is the value provided by the user
     *     minus one to remain within the arraylist bounds. This index is then
     *     stored and passed to the methods in the menu.
     *</p>
     * @return The index of the hotel in the arraylist.
     */
    public int getIndex(){
        int index, num;

        System.out.print("Enter hotel number: ");
        num = checkInt();

        while (num < 1 || num > hotels.size()) {
            System.out.print("\nNumber selected is not on the list. Please enter a valid number: ");
            num = checkInt();
        }
        index = num - 1;
        return index;
    }

    /**
     * Checks if the hotel list is empty.
     *<p>
     *     This method checks if the hotel list is empty by
     *     calling isEmpty() function of an ArrayList.
     * </p>
     * <ul>
     *     <li>TRUE if there are no hotels.</li>
     *     <li>FALSE if there are hotels.</li>
     * </ul>
     * @return The boolean value of isEmpty().
     */
    public boolean hotelEmpty(){
        return hotels.isEmpty();
    }

    /**
     * Prints the hotel list.
     */
    public void displayHotels(){
        int ctr = 1;

        if(!hotels.isEmpty()) {
            for (Hotel hotel : hotels) {
                System.out.println(ctr + ". " + hotel.getHotelName());
                ctr++;
            }
        }
        else
            System.out.println("No hotels found\n");
    }

    /**
     * Gets the hotel name.
     * @param index The index of the hotel selected.
     * @return The name of the hotel.
     */
    public String getHotelName(int index){
        return hotels.get(index).getHotelName();
    }

    /**
     * Changes the name of the selected hotel.
     *<p>
     *     A method to change the name of the currently selected hotel. It
     *     has a prompt to continue the changing of name or it exits.
     *</p>
     * @param index The index of the hotel selected.
     */
    public void changeHotelName(int index){
        int choice, equals;
        String name, old = hotels.get(index).getHotelName(); // retrieve old name
        String temp1, temp2;

        do {
            equals = 0;
            System.out.print("Enter new hotel name: ");
            name = sc.nextLine();
            // Validates if the name is unique
            for(Hotel hotel : hotels){
                temp1 = hotel.getHotelName().toLowerCase();
                temp2 = name.toLowerCase();
                if (temp1.equals(temp2)) {
                    equals = -1;
                    System.out.println("Hotel already exists!");
                    System.out.print("\nPress enter to continue...");
                    sc.nextLine();
                    break;
                }
            }
        } while (equals == -1);

         System.out.println("\nAre you sure you want to change " + old + " to " + name);
         System.out.println("[1] YES (This is irreversible!)");
         System.out.println("[2] NO");
         System.out.print("Enter your choice: ");
         choice = checkInt();

         switch (choice) {
             case 1:
                 hotels.get(index).setHotelName(name);
                 System.out.println(old + " changed to " + hotels.get(index).getHotelName());
             case 2: break;
            }

        System.out.print("\nPress enter to continue...");
        sc.nextLine();
    }

    /**
     * Sets the hotel name when changed via GUI.
     * @param index The hotel index.
     * @param newName The new name of the hotel
     * @return A boolean value if the change operation is successful.
     */
    public boolean changeHotelNameGui(int index, String newName){
        String oldName;
        for(Hotel hotel : hotels){
            oldName = hotel.getHotelName().toLowerCase();
            newName = newName.toLowerCase();
            if (oldName.equals(newName)) {
                return false;
            }
        }
        hotels.get(index).setHotelName(newName);
        return true;
    }

    /**
     * Add rooms in the selected hotel.
     *<p>
     *     This method adds hotel rooms in a hotel. It has a boolean
     *     variable to check if adding of rooms is successful based
     *     from the method that adds room in 'Hotel':{@link Hotel}.
     *</p>
     * @param index The index of the hotel selected.
     */
    public void addHotelRooms(int index){
        int newNum, choice, roomType;
        boolean success = false;
        String type = "";

            while(!success) {
                System.out.println("Adding rooms at " + hotels.get(index).getHotelName());
                displayRoomCount(index);

                System.out.print("Enter number of rooms to be added: ");
                newNum = checkInt();
                System.out.println("\nSelect the type of Room:" +
                                    "\n [1] Standard Room" + "\n [2] Deluxe Room" + "\n [3] Executive Room");
                System.out.print("Enter choice: ");
                roomType = checkInt();

                type = switch (roomType) {
                    case 1 -> "Standard Room";
                    case 2 -> "Deluxe Room";
                    case 3 -> "Executive Room";
                    default -> type;
                };
                System.out.println("\nAre you sure you want to add " + newNum + " " + type + "?");
                System.out.println("[1] YES (This is irreversible!)");
                System.out.println("[2] NO");
                System.out.print("Enter your choice: ");
                choice = checkInt();

                switch (choice) {
                    case 1:
                        success = hotels.get(index).addRoom(newNum, roomType);
                        System.out.print("Press enter to continue...");
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
                System.out.print("\033\143");
            }
    }

    /**
     * Removes hotel rooms in the selected hotel.
     * <p>
     *     This method removes hotel rooms in a hotel. It bases its actions
     *     from the result of the method that removes rooms in in 'Hotel':{@link Hotel}.
     *     This method also does not allow the hotel to have less than one room.
     * </p>
     * <p>
     *     The following actions are done based from the returned value:
     * </p>
     * <ul>
     *     <li> If -1 which indicates that the room does not exist,
     *          it prompts the user to select another room. </li>
     *     <li> If 0 which means the room is occupied, it also
     *          prompts the user to select another room. </li>
     *     <li> If 1 the user is prompted to continue and go back
     *          to the hotel management interface. </li>
     * </ul>
     * @param index The index of the hotel selected.
     */
    public void removeHotelRooms(int index) {
        int remove, choice, type;
        boolean success = false;

        if (hotels.get(index).getRooms().size() == 1) {
            System.out.println("Hotel must have at least one room");
            System.out.print("\nPress enter to continue...");
            sc.nextLine();
            System.out.print("\033\143");
        } else {
            do {
                System.out.print("\033\143");
                System.out.println("\nRemove rooms at " + hotels.get(index).getHotelName());
                displayRoomCount(index);
                System.out.print("Enter room to be removed: ");
                remove = checkInt();

                System.out.println("\nAre you sure you want to remove Room " + remove + "?");
                System.out.println("[1] YES (This is irreversible!)");
                System.out.println("[2] NO");
                System.out.print("Enter your choice: ");
                choice = checkInt();

                switch (choice) {
                    case 1:
                        type = hotels.get(index).removeRoom(remove);
                        if (type == -1 || type == 0) {
                            System.out.println("Do you want to select another room?");
                            System.out.println("[1] YES ");
                            System.out.println("[2] NO");
                            System.out.print("Enter your choice: ");
                            choice = checkInt();
                            if (choice == 1) {
                                break;
                            }
                            else {
                                success = true;
                                System.out.print("\nPress enter to continue...");
                                sc.nextLine();
                                System.out.print("\033\143");
                            }
                        }

                        else if (type == 1) {
                            System.out.print("\nPress enter to continue...");
                            sc.nextLine();
                            success = true;
                            System.out.print("\033\143");
                        }
                        break;
                    case 2:
                        success = true;
                        System.out.print("\nPress enter to continue...");
                        sc.nextLine();
                        System.out.print("\033\143");
                        break;
                }
            } while (!success);
        }
    }

    /**
     * Displays room count
     * @param index The index of the hotel.
     */
    public void displayRoomCount(int index) {
        System.out.println("Current number of rooms: " + hotels.get(index).getRooms().size());
        System.out.println("Standard Rooms: " + hotels.get(index).numStandard() + " [101 to " + (100+ hotels.get(index).numStandard()) + "]");
        System.out.println("Deluxe Rooms: " + hotels.get(index).numDeluxe() + " [201 to " + (200+ hotels.get(index).numDeluxe()) + "]");
        System.out.println("Executive Rooms: " + hotels.get(index).numExec() + " [301 to " + (300+ hotels.get(index).numExec()) + "]");
    }

    /**
     * Sets base price for the selected hotel.
     *<p>
     *     This method prompts the user to update the base price of
     *     the rooms in the selected hotel. It also validates if the
     *     new price keeps with the specification of only allowing
     *     a base price greater than 100.0
     *</p>
     * @param index The index of the hotel selected.
     */
    public void setHotelBasePrice(int index){
        float price = 0.0f, dlxPrice = 0.0f, exePrice = 0.0f, stdPrice = 0.0f;
        boolean success = false;
        int choice;
        ArrayList<Room> rooms = hotels.get(index).getRooms();

        if(hotels.get(index).isReservationEmpty()) {
            do {
                System.out.print("\033\143");
                System.out.println("\nChanging base price at " + hotels.get(index).getHotelName());
                System.out.println("Current price per night: " + hotels.get(index).getBasePrice());
                while (price < 100.0f) {
                    System.out.print("\nEnter new room base price: ");
                    if (sc.hasNextFloat()) {
                        price = sc.nextFloat();
                        sc.nextLine();
                    } else {
                        System.out.println("Enter a valid price (>100.0)\n");
                        sc.nextLine();
                    }
                }

                System.out.println("\nAre you sure you want to change base price from " + hotels.get(index).getBasePrice()
                                    + " to " + price + "?");
                System.out.println("[1] YES (This is irreversible!)");
                System.out.println("[2] NO");
                System.out.print("Enter your choice: ");
                choice = checkInt();

                switch (choice) {
                    case 1:
                        hotels.get(index).setBasePrice(price); // Set base price
                        for(Room room: rooms){ // Assign the new room rates for visibility
                            if(room instanceof ExecutiveRoom)
                                exePrice = room.getRoomPrice();
                            else if (room instanceof DeluxeRoom)
                                dlxPrice = room.getRoomPrice();
                            else
                                stdPrice = room.getRoomPrice();
                        }

                        System.out.println("Successfully changed base price to " + hotels.get(index).getBasePrice());
                        System.out.println("Standard Room: " + String.format("%.2f", stdPrice));
                        System.out.println("Deluxe Room: " + String.format("%.2f", dlxPrice));
                        System.out.println("Executive Room: " + String.format("%.2f", exePrice));

                        System.out.print("\nPress enter to continue...");
                        sc.nextLine();
                        System.out.print("\033\143");
                        success = true;
                        break;
                    case 2:
                        success = true;
                        System.out.print("\nPress enter to continue...");
                        sc.nextLine();
                        System.out.print("\033\143");
                        break;
                }
            }while (!success);
        }
        else {
            System.out.println("\nHotel has reservation!");
            System.out.print("Press enter to continue...");
            sc.nextLine();
            System.out.print("\033\143");
        }
    }

    /**
     * Sets reservation at the selected hotel.
     *<p>
     *     Prompts the user to set a reservation in the hotel.
     *     It has a feature as well to validate if the check-in date
     *     is not on the 31st. There is also a validation whether the
     *     check-out date is after the check-in date in addition to
     *     not having a check-out on the 1st of the month.
     *</p>
     * @param index The index of the hotel selected.
     */
    public void setHotelReservation(int index){
        String guestName;
        int checkInDate, checkOutDate, roomOpt, choice, vouchChoice, vouchType;
        boolean success = false, checkOut;
        String roomType, voucher = "";
        float totalPrice;

        System.out.print("\033\143");
        System.out.println("Making a reservation at " + hotels.get(index).getHotelName());

        do {
            System.out.print("Enter guest name: ");
            guestName = sc.nextLine();

            do{
                System.out.print("Enter check in date: ");
                checkInDate = checkInt();
                if (checkInDate > 30 || checkInDate < 1){
                    System.out.println("\nCheck in date must be from 1 to 30 only");
                }
            } while (checkInDate > 30 || checkInDate < 1);

            do {
                System.out.print("Enter check out date: ");
                checkOutDate = checkInt();
                checkOut = !(checkInDate > checkOutDate || checkOutDate > 31 || checkOutDate < 2 || checkInDate == checkOutDate);
                if (!checkOut) {
                    System.out.println("\nCheck out date must be within the month (" + (checkInDate + 1) + " to 31 only)");
                    System.out.println("and must be after check in date");
                }
            } while (!checkOut);

            do{
                System.out.println("\nSelect room type ");
                System.out.println("[1] Standard Room");
                System.out.println("[2] Deluxe Room");
                System.out.println("[3] Executive Room");
                System.out.print("Enter selection: ");
                roomOpt = checkInt();
            } while (roomOpt < 1 || roomOpt > 3);

            roomType = switch (roomOpt) {
                case 1 -> "Standard Room";
                case 2 -> "Deluxe Room";
                case 3 -> "Executive Room";
                default -> throw new IllegalStateException("Unexpected value: " + roomOpt);
            };

            do{ // ASK FOR VOUCHER
                System.out.println("\nDo you have a voucher?");
                System.out.println("[1] Yes");
                System.out.println("[2] No");
                System.out.print("Enter your option: ");
                vouchChoice = checkInt();
                do {
                    if (vouchChoice == 1) {
                        System.out.print("Voucher Code: ");
                        voucher = sc.nextLine(); // CHECK VOUCHER
                        if (hotels.get(index).checkVoucher(voucher, checkInDate, checkOutDate) != 0) {
                            vouchType = hotels.get(index).checkVoucher(voucher, checkInDate, checkOutDate);
                            totalPrice = hotels.get(index).computeTotal(vouchType, checkInDate, checkOutDate, roomOpt);
                            vouchChoice = 2;
                        }
                        else { // VOUCHER INVALID
                            System.out.println("\nVoucher cannot be applied!");
                            System.out.println("Do you want to try another voucher?");
                            System.out.println("[1] Yes");
                            System.out.println("[2] No");
                            System.out.print("Enter your option: ");
                            vouchChoice = checkInt();
                            totalPrice = hotels.get(index).computeTotal(0, checkInDate, checkOutDate, roomOpt);
                        }
                    }
                    else
                        totalPrice = hotels.get(index).computeTotal(0, checkInDate, checkOutDate, roomOpt);

                } while (vouchChoice == 1);
            } while (vouchChoice != 2);

            do{
                System.out.println("\nReview reservation details");
                System.out.println("Guest name: "+guestName);
                System.out.println("Check in date: "+checkInDate);
                System.out.println("Check out date: "+checkOutDate);
                System.out.println("Type of room: " + roomType);
                System.out.println("Voucher Applied: " + voucher);
                System.out.println("TOTAL PRICE: " + String.format("%.2f", totalPrice));
                System.out.println("\n[1] Continue Reservation");
                System.out.println("[2] Cancel Reservation");
                System.out.print("Enter your choice: ");
                choice = checkInt();

                if (choice == 1){ // Continue to setting the reservation
                    success = hotels.get(index).setReservation(guestName, checkInDate, checkOutDate, roomOpt, totalPrice);
                    if (success) {
                        System.out.println("\nSuccessfully saved reservation!");
                        System.out.print("Press enter to continue...");
                        sc.nextLine();
                        choice = 2; // To terminate the loop
                        System.out.print("\033\143");
                    }

                    else {
                        System.out.println("\nNo available rooms on selected dates...\n");
                        do {
                            System.out.println("Do you want to set another reservation?");
                            System.out.println("[1] Yes");
                            System.out.println("[2] Go back to main menu");
                            System.out.print("Enter your option: ");
                            choice = checkInt();
                        } while (choice != 1 && choice != 2);

                        if (choice == 2) {
                            System.out.print("\033\143");
                            return;
                        }
                        System.out.println();
                        break;
                    }
                }
                else if (choice == 2) {
                    success = true; // To terminate the outermost loop
                    System.out.print("\033\143");
                }
            } while (choice != 2);
        } while(!success);
    }

    /**
     * Removes a reservation of the selected hotel.
     * <p>
     *     A method that calls the remove reservation method in
     *     the selected hotel. This will use the remove reservation
     *     in the 'Hotel':{@link Hotel}.
     * </p>
     * @param index The index of the hotel selected.
     */
    public void removeHotelReservation(int index){
        hotels.get(index).removeReservation(sc);
    }

    /**
     * Removes the selected hotel.
     * <p>
     *     This method prompts the user to remove a hotel. It will then return a value
     *     to terminate the loop in 'Driver':{@link Driver}.
     * </p>
     * <ul>
     *     <li>11 to end the loop if removing is successful</li>
     *     <li> 0 if the removing of hotel is cancelled</li>
     * </ul>
     * @param index The index of the hotel selected.
     * @return The corresponding value whether to continue the loop in 'Driver':{@link Driver}.
     */
    public int removeHotel (int index) {
        int choice = -1;
        String temp = hotels.get(index).getHotelName();

            if (hotels.get(index).isReservationEmpty()){
                do {
                    System.out.print("\033\143");
                    System.out.println("\nAre you sure you want to remove " + temp + "?");
                    System.out.println("[1] YES (This is irreversible!)");
                    System.out.println("[2] NO");
                    System.out.print("Enter your choice: ");

                    // Check if the input value is an integer
                    if(sc.hasNextInt())
                        choice = checkInt();
                    else
                        sc.nextLine();

                    if (choice == 1) {
                        hotels.remove(index);
                        System.out.println("\nSuccessfully removed " + temp);
                        return 13;
                    }

                } while (choice < 1 || choice > 2);
            }
            else // Hotel cannot be removed because there are reservations
                System.out.println(temp + " has reservations");

        System.out.print("\nPress enter to continue...");
        sc.nextLine();
        return 0;
    }

    /**
     * Removes a hotel using GUI.
     * @param index The hotel index.
     * @return The boolean value of the operation.
     */
    public boolean removeHotelgui (int index){
        if(hotels.get(index).isReservationEmpty()) {
            hotels.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Displays high-level information of the selected hotel.
     * @param index The index of the hotel selected.
     */
    public void viewHighLevelHotel(int index){
        System.out.print("\033\143");
        hotels.get(index).dispHighInfo();
        System.out.print("\nPress enter to exit...");
        sc.nextLine();
    }

    /**
     * Displays room information.
     *<p>
     *     Prompts the user to input the desired room to be checked.
     *     It also validates whether the room exists in the current
     *     list of rooms.
     *</p>
     *
     * @param index The index of the hotel selected.
     */
    public void viewRoomInformation(int index){
        int roomNum;
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter room number: ");
            roomNum = checkInt();

            for (Room room : hotels.get(index).getRooms()) {
                if (room.getRoomNum() == roomNum) {
                    valid = true;
                    hotels.get(index).viewRoomInfo(roomNum); // Display room information
                    System.out.print("\nPress enter to continue...");
                    sc.nextLine();
                    break;
                }
            }

            if (!valid) {
                System.out.println("Room not found!\n");
            }
        }

    }

    /**
     * Checks room availability on a certain date of the selected hotel.
     *<p>
     *     Prompts the user to input the date to be checked.
     *</p>
     * @param index The index of the hotel selected.
     */
    public void checkRoomAvailability(int index){
        int date, booked = 0, numRoom = hotels.get(index).getRooms().size(), std = 0, dlx = 0, exe = 0;

            do {
                System.out.print("Enter date to check availability: ");
                date = checkInt();
            } while (date < 1 || date > 31);

            // Iterates through the rooms to check the available rooms on the selected date
        for(Room room : hotels.get(index).getRooms()){
            for(Reservation reservation : room.getReservations()){
                if(date >= reservation.getCheckInDate() && date < reservation.getCheckOutDate()){
                    if(room instanceof StandardRoom)
                        std++;
                    if(room instanceof DeluxeRoom)
                        dlx++;
                    if(room instanceof ExecutiveRoom)
                        exe++;
                }
            }
        }
            System.out.println("\nStandard Room: " + (hotels.get(index).numStandard() - std)+ " available (" + std + " booked)" +
                "\nDeluxe Room: " + (hotels.get(index).numDeluxe() - dlx)+ " available (" + dlx + " booked)" +
                "\nExecutive Room: " + (hotels.get(index).numExec() - exe)+ " available (" + exe + " booked)");

            System.out.print("\nPress enter to continue...");
            sc.nextLine();
    }

    /**
     * Gets the number of available rooms on the selected date.
     * @param index The hotel index.
     * @param date The date to be checked.
     * @return The number of available rooms on the selected date.
     */
    public String checkRoomAvailabilityGUI(int index, int date){
        int std = 0, dlx = 0, exe = 0, numRoom = hotels.get(index).getRooms().size();
        // Iterates through the rooms to check the available rooms on the selected date
        for(Room room : hotels.get(index).getRooms()){
            for(Reservation reservation : room.getReservations()){
                if(date >= reservation.getCheckInDate() && date < reservation.getCheckOutDate()){
                    if(room instanceof StandardRoom)
                        std++;
                    if(room instanceof DeluxeRoom)
                        dlx++;
                    if(room instanceof ExecutiveRoom)
                        exe++;
                }
            }
        }
        return "\nStandard Room: " + (hotels.get(index).numStandard() - std)+ " available (" + std + " booked)" +
               "\nDeluxe Room: " + (hotels.get(index).numDeluxe() - dlx)+ " available (" + dlx + " booked)" +
                "\nExecutive Room: " + (hotels.get(index).numExec() - exe)+ " available (" + exe + " booked)";
    }

    /**
     * Checks the reservation details in the selected hotel.
     * <p>
     *     Prompts the user to input the reservation number in the list
     *     provided that there are current reservations in the selected
     *     hotel.
     * </p>
     * @param index The index of the hotel selected.
     */
    public void checkReservationInfo(int index){
       int choice;
       if (hotels.get(index).getReservations().isEmpty())
           System.out.println("\nNo reservations found");
       else {
           do {
               System.out.print("\033\143");
               hotels.get(index).dispRoomReservation();
               System.out.print("\nEnter reservation to check: ");
               choice = checkInt() - 1;
           } while (choice > hotels.get(index).getReservations().size() - 1 || choice < 0);
           hotels.get(index).viewReservationInfo(choice);
       }
        System.out.print("Press enter to continue...");
        sc.nextLine();
    }

    /**
     * Modifies the date using command line interface.
     * @param index The index of the hotel to be changed.
     */
    public void datePriceMod(int index){
        ArrayList<DateModifier> dateList = hotels.get(index).getDates();
        int dateChoice, option;
        float prevPct = 0.0f, newPct = 0.0f;

        hotels.get(index).displayDayRate();
        do {
            System.out.print("\nEnter date to modify: ");
            dateChoice = checkInt();
        } while (dateChoice < 1 || dateChoice > 31);

        for(DateModifier list : dateList){
            if (list.getDay() == dateChoice)
                prevPct = list.getPricePercent();
        }

        System.out.print("\033\143");
        System.out.println("Modifying day " + dateChoice);
        System.out.println("Previous rate: " + (prevPct*100) +"%");
        do {
            try {
                System.out.println("\nYou can only modify the rate from 50% to 150%");
                System.out.print("Enter new rate without % symbol \n[e.g. 100% = 100, 50% = 50, 23.3% = 23.3]: ");
                if (sc.hasNextFloat()) {
                    newPct = sc.nextFloat();
                } else {
                    System.out.println("Invalid input. Enter a valid float value.");
                    sc.nextLine();
                }
            } catch (Exception e) {
                System.out.println("ERROR! Enter a float value");
                sc.nextLine();
            }
        } while (newPct < 50.0f || newPct > 150.0f);

        do{
            System.out.println("\nModifying rate on day " + dateChoice);
            System.out.println("from " + (prevPct*100) + "% to " + newPct +"%");
            System.out.println("[1] CONTINUE (This is irreversible!)");
            System.out.println("[2] CANCEL MODIFICATION");
            System.out.print("Enter your choice: ");
            option = checkInt();

            if(option == 1) {
                hotels.get(index).modifyDate(dateChoice, newPct / 100);
                System.out.println("Successfully changed rate on Day " + dateChoice + " from " + (prevPct*100) + "% to " + newPct +"%");
                System.out.print("Press enter to continue...");
                sc.nextLine();
                option = 2;
            }
        } while (option != 2);
    }

    /**
     * List the rates of each day.
     * @param index The index of the hotel selected.
     */
    public void displayRate(int index){
        hotels.get(index).displayDayRate();
        System.out.print("\nPress enter to continue...");
        sc.nextLine();
    }

    /**
     * Getter for the hotels stored in this class.
     * @return The arraylist of 'Hotels': {@link Hotel}.
     */
    public ArrayList<Hotel> getHotels(){
        return hotels;
    }

    /**
     * Returns the hotel selected.
     * @param index The index of the hotel to be returned.
     * @return The hotel object.
     */
    public Hotel getHotel(int index){
        return hotels.get(index);
    }
}
