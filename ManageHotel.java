import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class serves as the hotel management system.
 * <p>
 *     The user can create multiple independent hotels in this class
 *     which can store its unique rooms, reservations, and information.
 * </p>
 * @author Albrecht Gabriel Abad
 * @since June 2024
 * @version 1.0
 */
public class ManageHotel {
    private ArrayList<Hotel> hotels; // Stores the hotels created

    /**
     * Constructor for ManageHotel
     */
    public ManageHotel() {
        this.hotels = new ArrayList<>();
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
     * @param sc Passes the scanner from 'Driver': {@link Driver}.
     */
    public void createHotel(Scanner sc){
        String name, temp1, temp2;
        int numOfRooms, equals;

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

        System.out.print("Enter number of rooms: ");
        do{
            numOfRooms = checkInt(sc);
            if(numOfRooms <= 0 || numOfRooms > 50) {
                System.out.println("You can only have 1 to 50 rooms");
                System.out.print("Enter number of rooms: ");
            }
        } while (numOfRooms <= 0 || numOfRooms > 50);
        hotels.add(new Hotel(name, numOfRooms));
        System.out.print("\033\143");
    }

    /**
     * Checks if the inputted value is a valid integer.
     * <p>
     *     This method reduces the number of lines needed as integer values
     *     are much needed in this project. It makes sure that the value is
     *     a valid integer then returns it.
     * </p>
     * @param sc Passes the scanner from 'Driver': {@link Driver}.
     * @return The valid integer value input.
     */
    public int checkInt(Scanner sc){
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
     * @param sc Passes the scanner from 'Driver': {@link Driver}.
     * @return The index of the hotel in the arraylist.
     */
    public int getIndex(Scanner sc){
        int index, num;

        System.out.print("Enter hotel number: ");
        num = checkInt(sc);

        while (num < 1 || num > hotels.size()) {
            System.out.print("\nNumber selected is not on the list. Please enter a valid number: ");
            num = checkInt(sc);
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
     * @param sc Passes the scanner from 'Driver':{@link Driver}.
     */
    public void changeHotelName(int index, Scanner sc){
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
         choice = checkInt(sc);

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
     * Add rooms in the selected hotel.
     *<p>
     *     This method adds hotel rooms in a hotel. It has a boolean
     *     variable to check if adding of rooms is successful based
     *     from the method that adds room in 'Hotel':{@link Hotel}.
     *</p>
     * @param index The index of the hotel selected.
     * @param sc Passes the scanner from 'Driver':{@link Driver}.
     */
    public void addHotelRooms(int index, Scanner sc){
        int newNum, choice, old = hotels.get(index).getRooms().size();
        boolean success = false;

            do {
                System.out.println("Add rooms at " + hotels.get(index).getHotelName());
                System.out.println("Current number of rooms: " + hotels.get(index).getRooms().size());
                System.out.print("Enter number of rooms to be added: ");
                newNum = checkInt(sc);

                System.out.println("\nAre you sure you want to set the rooms from " + old + " to " + (newNum + old));
                System.out.println("[1] YES (This is irreversible!)");
                System.out.println("[2] NO");
                System.out.print("Enter your choice: ");
                choice = checkInt(sc);

                switch (choice) {
                    case 1:
                        success = hotels.get(index).addRoom(newNum);
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
                System.out.print("\033\143");
            } while (!success);
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
     * @param sc Passes the scanner from 'Driver':{@link Driver}.
     */
    public void removeHotelRooms(int index, Scanner sc) {
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
                System.out.println("Current number of rooms: " + hotels.get(index).getRooms().size());
                System.out.print("Enter room to be removed: ");
                remove = checkInt(sc);

                System.out.println("\nAre you sure you want to remove Room " + remove + "?");
                System.out.println("[1] YES (This is irreversible!)");
                System.out.println("[2] NO");
                System.out.print("Enter your choice: ");
                choice = checkInt(sc);

                switch (choice) {
                    case 1:
                        type = hotels.get(index).removeRoom(remove);
                        if (type == -1 || type == 0) {
                            System.out.println("Do you want to select another room?");
                            System.out.println("[1] YES ");
                            System.out.println("[2] NO");
                            System.out.print("Enter your choice: ");
                            choice = checkInt(sc);
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
     * Sets base price for the selected hotel.
     *<p>
     *     This method prompts the user to update the base price of
     *     the rooms in the selected hotel. It also validates if the
     *     new price keeps with the specification of only allowing
     *     a base price greater than 100.0
     *</p>
     * @param index The index of the hotel selected.
     * @param sc Passes the scanner from 'Driver':{@link Driver}.
     */
    public void setHotelBasePrice(int index, Scanner sc){
        float price = 0.0f;
        boolean success = false;
        int choice;

        if(hotels.get(index).isReservationEmpty()) {
            do {
                System.out.print("\033\143");
                System.out.println("\nChanging base price at " + hotels.get(index).getHotelName());
                System.out.println("Current price per night: " + hotels.get(index).getBasePrice(0));
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

                System.out.println("\nAre you sure you want to change base price from " + hotels.get(index).getBasePrice(0)
                                    + " to " + price + "?");
                System.out.println("[1] YES (This is irreversible!)");
                System.out.println("[2] NO");
                System.out.print("Enter your choice: ");
                choice = checkInt(sc);

                switch (choice) {
                    case 1:
                        hotels.get(index).setBasePrice(price);
                        System.out.println("Successfully changed base price to " + hotels.get(index).getBasePrice(0));
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
     * @param sc Passes the scanner from 'Driver':{@link Driver}.
     */
    public void setHotelReservation(int index, Scanner sc){
        String guestName;
        int checkInDate, checkOutDate;
        boolean success, checkOut;

        System.out.print("\033\143");
        System.out.println("Making a reservation at " + hotels.get(index).getHotelName());

        do {
            System.out.print("Enter guest name: ");
            guestName = sc.nextLine();

            do{
                System.out.print("Enter check in date: ");
                checkInDate = checkInt(sc);
                if (checkInDate > 30 || checkInDate < 1){
                    System.out.println("\nCheck in date must be from 1 to 30 only");
                }
            } while (checkInDate > 30 || checkInDate < 1);

            do {
                System.out.print("Enter check out date: ");
                checkOutDate = checkInt(sc);
                checkOut = !(checkInDate > checkOutDate || checkOutDate > 31 || checkOutDate < 2 || checkInDate == checkOutDate);
                if (!checkOut) {
                    System.out.println("\nCheck out date must be within the month (" + (checkInDate + 1) + " to 31 only)");
                    System.out.println("and must be after check in date");
                }
            } while (!checkOut);

            success = hotels.get(index).setReservation(guestName, checkInDate, checkOutDate);

            // Adding of reservation is successful
            if (success) {
                System.out.println("\nSuccessfully saved reservation!");
                System.out.print("Press enter to continue...");
                sc.nextLine();
                System.out.print("\033\143");
            }

            else {
                System.out.println("\nNo available rooms on selected dates...\n");
                int choice;
                do {
                    System.out.println("Do you want to set another reservation?");
                    System.out.println("[1] Yes");
                    System.out.println("[2] Go back to main menu");
                    System.out.print("Enter your option: ");
                    choice = checkInt(sc);
                } while (choice != 1 && choice != 2);

                if (choice == 2) {
                    System.out.print("\033\143");
                    break;
                }
            }
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
     * @param sc Passes the scanner from 'Driver':{@link Driver}.
     */
    public void removeHotelReservation(int index, Scanner sc){
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
     * @param sc Passes the scanner from 'Driver':{@link Driver}.
     * @return The corresponding value whether to continue the loop in 'Driver':{@link Driver}.
     */
    public int removeHotel (int index, Scanner sc) {
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
                        choice = checkInt(sc);
                    else
                        sc.nextLine();

                    if (choice == 1) {
                        hotels.remove(index);
                        System.out.println("\nSuccessfully removed " + temp);
                        return 11;
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
     * Displays high-level information of the selected hotel.
     *
     * @param index The index of the hotel selected.
     * @param sc Passes the scanner from 'Driver':{@link Driver}.
     */
    public void viewHighLevelHotel(int index, Scanner sc){
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
     * @param sc Passes the scanner from 'Driver':{@link Driver}.
     */
    public void viewRoomInformation(int index, Scanner sc){
        int roomNum;
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter room number: ");
            roomNum = checkInt(sc);

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
     * @param sc Passes the scanner from 'Driver':{@link Driver}.
     */
    public void checkRoomAvailability(int index, Scanner sc){
        int date, booked = 0, numRoom = hotels.get(index).getRooms().size();

            do {
                System.out.print("Enter date to check availability: ");
                date = checkInt(sc);
            } while (date < 1 || date > 31);

            // Iterates through the rooms to check the available rooms on the selected date
            for(Room room : hotels.get(index).getRooms()){
                for(Reservation reservation : room.getReservations()){
                    if(date >= reservation.getCheckInDate() && date < reservation.getCheckOutDate()){
                        booked++;
                    }
                }
            }

            System.out.println("\nNumber of available rooms: " + (numRoom - booked));
            System.out.println("Number of booked rooms: " + booked);

            System.out.print("\nPress enter to continue...");
            sc.nextLine();
    }

    /**
     * Checks the reservation details in the selected hotel.
     * <p>
     *     Prompts the user to input the reservation number in the list
     *     provided that there are current reservations in the selected
     *     hotel.
     * </p>
     * @param index The index of the hotel selected.
     * @param sc Passes the scanner from 'Driver':{@link Driver}.
     */
    public void checkReservationInfo(int index, Scanner sc){
       int choice;

       if (hotels.get(index).getReservations().isEmpty()){
           System.out.println("\nNo reservations found");
        }

       else {
           do {
               System.out.print("\033\143");
               hotels.get(index).dispRoomReservation();
               System.out.print("\nEnter reservation to check: ");
               choice = checkInt(sc) - 1;
           } while (choice > hotels.get(index).getReservations().size() - 1 || choice < 0);
           hotels.get(index).viewReservationInfo(choice);
       }

        System.out.print("Press enter to continue...");
        sc.nextLine();

    }

}
