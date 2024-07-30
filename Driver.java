import java.util.Scanner;

/**
 * The main driver of this system where it serves as the interactive interface of this system.
 * @author Albrecht Gabriel Abad
 * @since July 2024
 * @version 2.0
 */
public class Driver {
    /**
     * The main method of the hotel management system.
     * <p>
     *     This main method serves as the interactive place for the user
     *     and the system. It simulates a menu interface via CLI.
     * </p>
     * @param args Sequence of string arguments.
     */
    public static void main(String[] args) {

       int main, manage, manageHotel, index, reserveChoice;
       Scanner sc = new Scanner(System.in);
       ManageHotel mh = new ManageHotel(sc);

        GUI gui = new GUI();
        new Controller(gui, mh);

       do{
           System.out.println("\n====================================");
           System.out.println("===== Welcome to MyHotelSystem =====");
           System.out.println("====================================\n");
           System.out.println("[1] MAKE A RESERVATION");
           System.out.println("[2] MANAGE HOTELS");
           System.out.println("[3] EXIT");
           System.out.print("\nPlease enter your choice: ");

           main = mh.checkInt();

           // SET RESERVATION
           if(main == 1){
               if(mh.hotelEmpty()) {
                   System.out.print("\033\143");
                   System.out.println("No hotels available");
               }
               else{
                   System.out.print("\033\143");
                   System.out.println("Choose Hotel");
                   mh.displayHotels();
                   System.out.println();
                   reserveChoice = mh.getIndex();
                   mh.setHotelReservation(reserveChoice);
               }
           }

           // MANAGE HOTELS
           else if (main == 2) {
               System.out.print("\033\143");
               do {
                   //System.out.print("\033\143");
                   System.out.println("\nHotels in your system:");
                   mh.displayHotels();
                   System.out.println("\n[1] ADD HOTEL");
                   System.out.println("[2] VIEW HOTEL");
                   System.out.println("[3] MAIN MENU");
                   System.out.print("\nPlease enter your choice: ");
                   manage = mh.checkInt();

                   // Create Hotel
                   if(manage == 1){
                       System.out.print("\033\143");
                       mh.createHotel();
                   }

                   // Manage Hotels
                   if (manage == 2) {
                       // If the hotel list is not empty
                       if (!mh.hotelEmpty()) {
                           index = mh.getIndex();
                           do{
                           System.out.print("\033\143");
                           System.out.println(mh.getHotelName(index));
                           System.out.println("\n[1] CHANGE HOTEL NAME");
                           System.out.println("[2] ADD ROOMS");
                           System.out.println("[3] REMOVE ROOM");
                           System.out.println("[4] ROOM AVAILABILITY");
                           System.out.println("[5] ROOM INFORMATION");
                           System.out.println("[6] UPDATE BASE PRICE");
                           System.out.println("[7] MODIFY RATE");
                           System.out.println("[8] VIEW RATE PER NIGHT");
                           System.out.println("[9] CHECK RESERVATIONS");
                           System.out.println("[10] REMOVE RESERVATION");
                           System.out.println("[11] HIGH-LEVEL INFORMATION");
                           System.out.println("[12] REMOVE HOTEL");
                           System.out.println("[13] BACK");
                           System.out.print("\nPlease enter your choice: ");
                               manageHotel = mh.checkInt();
                               switch (manageHotel) {
                                   case 1:
                                       mh.changeHotelName(index);
                                       break;
                                   case 2:
                                       mh.addHotelRooms(index);
                                       break;
                                   case 3:
                                       mh.removeHotelRooms(index);
                                       break;
                                   case 4:
                                       mh.checkRoomAvailability(index);
                                       break;
                                   case 5:
                                       mh.viewRoomInformation(index);
                                       break;
                                   case 6:
                                       mh.setHotelBasePrice(index);
                                       break;
                                   case 7:
                                       mh.datePriceMod(index);
                                       break;
                                   case 8:
                                       mh.displayRate(index);
                                       break;
                                   case 9:
                                       mh.checkReservationInfo(index);
                                       break;
                                   case 10:
                                       mh.removeHotelReservation(index);
                                       break;
                                   case 11:
                                       mh.viewHighLevelHotel(index);
                                       break;
                                   case 12:
                                       manageHotel = mh.removeHotel(index);
                                       break;
                                   case 13:
                                       break;
                                   default:
                                       System.out.println("Invalid choice");
                               }
                           } while (manageHotel != 13);
                       }
                       else{
                           System.out.println("\nNo hotels found!");
                           System.out.print("\033\143");
                       }
                   }
                   System.out.print("\033\143");
               } while (manage != 3);
               System.out.print("\033\143");
           }
            // Input value is not between 1 and 3
           else if (main > 3 || main < 1) {
               System.out.println("ENTER A VALID OPTION");
               System.out.print("\033\143");
           }
       } while(main != 3); // Exit Loop if 3 is selected
       sc.close();
    }
}
