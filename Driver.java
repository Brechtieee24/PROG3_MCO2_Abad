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
       ManageHotel mh = new ManageHotel();
       Scanner sc = new Scanner(System.in);

        Hotel one = new Hotel("One", 1, 1, 1);
        Hotel two = new Hotel("Two", 2, 2, 2);
        mh.temphotel(one);
        mh.temphotel(two);

       do{
           System.out.println("\n====================================");
           System.out.println("===== Welcome to MyHotelSystem =====");
           System.out.println("====================================\n");
           System.out.println("[1] MAKE A RESERVATION");
           System.out.println("[2] MANAGE HOTELS");
           System.out.println("[3] EXIT");
           System.out.print("\nPlease enter your choice: ");

           main = mh.checkInt(sc);

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
                   reserveChoice = mh.getIndex(sc);
                   mh.setHotelReservation(reserveChoice, sc);
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
                   manage = mh.checkInt(sc);

                   // Create Hotel
                   if(manage == 1){
                       System.out.print("\033\143");
                       mh.createHotel(sc);
                   }

                   // Manage Hotels
                   if (manage == 2) {
                       // If the hotel list is not empty
                       if (!mh.hotelEmpty()) {
                           index = mh.getIndex(sc);
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
                               manageHotel = mh.checkInt(sc);
                               switch (manageHotel) {
                                   case 1:
                                       mh.changeHotelName(index, sc);
                                       break;
                                   case 2:
                                       mh.addHotelRooms(index, sc);
                                       break;
                                   case 3:
                                       mh.removeHotelRooms(index, sc);
                                       break;
                                   case 4:
                                       mh.checkRoomAvailability(index, sc);
                                       break;
                                   case 5:
                                       mh.viewRoomInformation(index, sc);
                                       break;
                                   case 6:
                                       mh.setHotelBasePrice(index, sc);
                                       break;
                                   case 7:
                                       mh.datePriceMod(index, sc);
                                       break;
                                   case 8:
                                       mh.displayRate(index, sc);
                                       break;
                                   case 9:
                                       mh.checkReservationInfo(index,sc);
                                       break;
                                   case 10:
                                       mh.removeHotelReservation(index, sc);
                                       break;
                                   case 11:
                                       mh.viewHighLevelHotel(index, sc);
                                       break;
                                   case 12:
                                       manageHotel = mh.removeHotel(index, sc);
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
