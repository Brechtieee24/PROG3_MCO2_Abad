import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controller class.
 * <p>
 *     Using MVC Architecture, this class links the Model {@link ManageHotel} and View {@link GUI}.
 * </p>
 * @author Albrecht Gabriel Abad
 * @since July 2024
 * @version 1.0
 */
public class Controller {
    private ManageHotel hotelManager; // The model of this program
    private GUI gui; // The view of this program

    /**
     * Constructor for the controller.
     * @param gui The view.
     * @param hotelManager The model.
     */
    public Controller(GUI gui, ManageHotel hotelManager) {
        this.hotelManager = hotelManager;
        this.gui = gui;

        // Action listener when reserve button is clicked
        this.gui.setReserveBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getHotelNames().length == 0){
                    JOptionPane.showMessageDialog(null, "No hotels in the System", "Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    gui.setHotelListDropDown(getHotelNames());
                    gui.setReservationPanel();
                }
            }
        });

        // Submit button listener when making a reservation
        this.gui.setSubmitReserveBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guestName, voucher;
                int checkInDate, checkOutDate, index, roomType, vouchValid, choice;
                float total;
                boolean success;

                index = gui.getHotelListDropDown().getSelectedIndex();
                checkInDate = gui.getCheckIn();
                checkOutDate = gui.getCheckOut();
                guestName = gui.getGuestNameInput().getText().trim();
                voucher = gui.getVoucherInput().getText().trim();
                roomType = gui.getTypesOfRoomDropdown().getSelectedIndex() + 1;

                if (!guestName.isEmpty()) { // Ensure guest name is not empty
                    if (voucher.isEmpty()) { // No voucher inputted
                        total = hotelManager.getHotel(index).computeTotal(0, checkInDate, checkOutDate, roomType);
                        success = hotelManager.getHotel(index).setReservation(guestName, checkInDate, checkOutDate, roomType, total);
                    } else { // Voucher inputted
                        vouchValid = hotelManager.getHotel(index).checkVoucher(voucher, checkInDate, checkOutDate);
                        if (vouchValid == 0) { // Invalid voucher
                            choice = JOptionPane.showConfirmDialog(null, "Voucher inapplicable! Do you want to use another voucher?", "Cannot use voucher", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION)
                                return;
                        }
                        total = hotelManager.getHotel(index).computeTotal(vouchValid, checkInDate, checkOutDate, roomType);
                        success = hotelManager.getHotel(index).setReservation(guestName, checkInDate, checkOutDate, roomType, total);
                    }
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Successfully saved reservation!");
                        gui.returnToMenu();
                    } else
                        JOptionPane.showMessageDialog(null, "Cannot complete reservation!");
                } else
                    JOptionPane.showMessageDialog(null, "Guest name cannot be empty!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            });

        //Action listener for checking price
        this.gui.setCheckPriceBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = gui.getHotelListDropDown().getSelectedIndex(), roomType = gui.getTypesOfRoomDropdown().getSelectedIndex() + 1, voucher;
                float price;
                if(gui.getVoucherInput() != null && !gui.getVoucherInput().getText().isEmpty()) {
                     voucher = hotelManager.getHotel(index).checkVoucher(gui.getVoucherInput().getText(), gui.getCheckIn(), gui.getCheckOut());
                     if(voucher == 0)
                         JOptionPane.showMessageDialog(null, "Invalid Voucher!", "Cannot apply voucher", JOptionPane.ERROR_MESSAGE);
                     price = hotelManager.getHotel(index).computeTotal(voucher, gui.getCheckIn(), gui.getCheckOut(), roomType);
                } else {
                     price = hotelManager.getHotel(index).computeTotal(0, gui.getCheckIn(), gui.getCheckOut(), roomType);
                }
                gui.setPriceEstimateLbl(String.format("%.2f", price));
            }
        });

        //Action listener for adding hotel
        this.gui.setAddHotelBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.addHotelPanel();
            }
        });

        //Action listener for creating a new hotel
        this.gui.setCreateHotelBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalRooms, std, dlx, exe;
                String hotelName;
                boolean nameIsUnique = true;
                ArrayList<Hotel> hotels = hotelManager.getHotels();

                std = gui.getStdRooms();
                dlx = gui.getDlxRooms();
                exe = gui.getExeRooms();
                totalRooms = std+dlx+exe;
                hotelName = gui.getNewHotelName().getText();
                for(Hotel hotel: hotels){
                    if(hotel.getHotelName().equalsIgnoreCase(hotelName)) {
                        nameIsUnique = false;
                        break;
                    }
                }

                if(!hotelName.isEmpty() && nameIsUnique && (totalRooms > 0 && totalRooms < 50)){
                    hotelManager.createHotel(std, dlx, exe, hotelName);
                    JOptionPane.showMessageDialog(null, "Successfully added " + hotelName);
                    gui.setHotelListDropDown(getHotelNames());
                    gui.modifyHotelPanelAfterHome();
                }
                else if(totalRooms > 50)
                    JOptionPane.showMessageDialog(null, "Hotel can only have at most 50 rooms", "Cannot add hotel",
                            JOptionPane.ERROR_MESSAGE);
                else if (totalRooms == 0)
                    JOptionPane.showMessageDialog(null, "Hotel must have at least one room", "Cannot add hotel",
                            JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Hotel name must be unique", "Cannot add hotel",
                            JOptionPane.ERROR_MESSAGE);

            }
        });

        // Set up the ActionListener for check-in date dropdown
        this.gui.getChkInDatesDropdown().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.updateChkOutDatesDropdown();
            }
        });

        // Action listener when managing hotel
        this.gui.setManageHotelBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                gui.setHotelListDropDown(getHotelNames());
                gui.modifyHotelPanelAfterHome();
            }
        });

        // Action listener when back is pressed
        this.gui.setBackBtnManage1stListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                gui.returnToMenu();
            }
        });

        // Submit button action listener when modifying hotel
        this.gui.setSubmitBtnManage1stListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int hotelIndex, actionIndex, roomNum;
                String response, prev, newName, booked;
                hotelIndex = gui.getHotelListDropDown().getSelectedIndex();
                actionIndex = gui.getHotelActionsDropdown().getSelectedIndex();

                if(hotelIndex >= 0) {
                    switch (actionIndex) {
                        case 0: // Change hotel name
                            prev = hotelManager.getHotelName(hotelIndex);
                            newName = JOptionPane.showInputDialog("Enter new hotel name");
                            if (newName != null && !newName.trim().isEmpty()) { // Check if user cancelled or entered empty name
                                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to change " + prev +
                                        " to " + newName + "?", "Confirm selection", JOptionPane.YES_NO_OPTION);
                                if (choice == JOptionPane.YES_OPTION) {
                                    boolean doesExist = hotelManager.changeHotelNameGui(hotelIndex, newName);
                                    if (doesExist) { // Change name status
                                        JOptionPane.showMessageDialog(null, "Successfully changed name!");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Name must be unique!", "Whoops", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            } else if (newName != null) {
                                JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Input Error", JOptionPane.ERROR_MESSAGE);
                            }
                            gui.setHotelListDropDown(getHotelNames());
                            gui.modifyHotelPanelAfterHome();
                            break;

                        case 1: // Add hotel rooms
                            String output;
                            int stdRoom, dlxRoom, exeRoom;
                            stdRoom = hotelManager.getHotel(hotelIndex).numStandard();
                            dlxRoom = hotelManager.getHotel(hotelIndex).numDeluxe();
                            exeRoom = hotelManager.getHotel(hotelIndex).numExec();
                            output = "Standard rooms: " + stdRoom +
                                    "\n Deluxe rooms: " + dlxRoom +
                                    "\n Executive rooms: " + exeRoom;
                            gui.addRooms(hotelManager.getHotel(hotelIndex).getRooms().size(), output);
                            break;

                        case 2: // Remove rooms
                            response = JOptionPane.showInputDialog(null, "Input room number to be removed");
                            if(response == null) {
                                break;
                            }
                            try {
                                roomNum = Integer.parseInt(response);
                                int result = hotelManager.getHotel(hotelIndex).removeRoom(roomNum);
                                if (result == -1)
                                    JOptionPane.showMessageDialog(null, "Room does not exist", "Cannot remove room", JOptionPane.INFORMATION_MESSAGE);
                                else if (result == 0)
                                    JOptionPane.showMessageDialog(null, "Room is occupied", "Cannot remove room", JOptionPane.ERROR_MESSAGE);
                                else if (result == -2)
                                    JOptionPane.showMessageDialog(null, "There must be at least one room!", "Cannot remove room", JOptionPane.ERROR_MESSAGE);
                                else
                                    JOptionPane.showMessageDialog(null, "Successfully removed Room " + roomNum);
                            } catch (Exception ex){
                                JOptionPane.showMessageDialog(null, "Input a valid numerical value!", "Cannot remove room", JOptionPane.ERROR_MESSAGE);

                            }
                            break;

                        case 3: // Check room availability via date (roomNum will be used as the date selected)
                            response = JOptionPane.showInputDialog(null, "Input date to be checked");
                            if(response == null) {
                                break;
                            }
                            try {
                                roomNum = Integer.parseInt(response);
                                if (roomNum > 31 || roomNum < 1)
                                    JOptionPane.showMessageDialog(null, "Date must be between 1 to 31 only", "INVALID DATE", JOptionPane.ERROR_MESSAGE);
                                else { // Action index refers to the available rooms
                                    booked = hotelManager.checkRoomAvailabilityGUI(hotelIndex, roomNum);
                                    JOptionPane.showMessageDialog(null, "Day " + roomNum + booked);
                                }
                            } catch (Exception ex){
                                JOptionPane.showMessageDialog(null, "Input a valid numerical value!", "INVALID DATE", JOptionPane.ERROR_MESSAGE);
                            }
                            break;

                        case 4: // View Room Information
                            int daysOccupied;
                            roomNum = gui.inputRoomToCheck();
                            float price;
                            if(roomNum == -1) // user cancelled
                                break;
                            if (hotelManager.getHotel(hotelIndex).isRoomFound(roomNum)) {
                                price = hotelManager.getHotel(hotelIndex).getRoomPrice(roomNum);
                                daysOccupied = hotelManager.getHotel(hotelIndex).daysOccupied(roomNum);
                                gui.checkRoomInformation(roomNum, 31 - daysOccupied, daysOccupied, price);
                            } else
                                JOptionPane.showMessageDialog(null, "Room not found.");
                            break;

                        case 5: // Set base price
                            float curr = hotelManager.getHotel(hotelIndex).getBasePrice();
                            String input = JOptionPane.showInputDialog(null, "Enter new base rate (Current is " + curr + ")");

                            if (input == null) { // Cancelled operation
                                break;
                            }
                            try {
                                float newPrice = Float.parseFloat(input);
                                if (newPrice < 100.0f) {
                                    JOptionPane.showMessageDialog(null, "Base price must be >= 100.0", "INVALID RATE", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    hotelManager.getHotel(hotelIndex).setBasePrice(newPrice);
                                    JOptionPane.showMessageDialog(null, "Successfully changed base rate to " + newPrice);
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Please enter a valid numeric value!", "Input Error", JOptionPane.ERROR_MESSAGE);
                            }
                            break;

                        case 6: // Date price modifier
                            float std, dlx, exe, rate;
                            int day;
                            gui.dateModifierPanel();
                            hotelIndex = gui.getHotelListDropDown().getSelectedIndex();
                            day = gui.getDatesDropdown().getSelectedIndex() + 1;
                            rate = hotelManager.getHotel(hotelIndex).getRate(day);
                            std = hotelManager.getHotel(hotelIndex).getBasePrice() * rate;
                            dlx = std * 1.2f;
                            exe = std * 1.35f;
                            gui.ratesOnDayView(std, dlx, exe, rate);
                            break;

                        case 7: // Check reservations
                            if (!hotelManager.getHotel(hotelIndex).isReservationEmpty()) {
                                gui.showReservation(hotelManager.getHotel(hotelIndex).getReservations().getFirst().toString());
                                gui.viewReservations(hotelManager.getHotel(hotelIndex).getReservations(), hotelManager.getHotelName(hotelIndex));
                            }
                            else
                                JOptionPane.showMessageDialog(null, "No reservations found");
                            break;

                        case 8: // High-level information
                            gui.initHighLevelInfoPanel(hotelManager.getHotel(hotelIndex).getHotelName(), hotelManager.getHotel(hotelIndex).getHotelEarnings(),
                                    hotelManager.getHotel(hotelIndex).getRooms().size(), hotelManager.getHotel(hotelIndex).numStandard(),
                                    hotelManager.getHotel(hotelIndex).numDeluxe(), hotelManager.getHotel(hotelIndex).numExec());
                            break;

                        case 9: // Remove Hotel
                            int option;
                            if(hotelManager.getHotel(hotelIndex).isReservationEmpty()) {
                                option = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove "+ hotelManager.getHotel(hotelIndex).getHotelName() + "?",
                                        "Confirm deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE );
                                if(option == 0)
                                    hotelManager.removeHotelgui(hotelIndex);
                            } else
                                JOptionPane.showMessageDialog(null, "Hotel has bookings!", "Cannot delete " + hotelManager.getHotel(hotelIndex).getHotelName(),
                                        JOptionPane.ERROR_MESSAGE);
                            gui.setHotelListDropDown(getHotelNames()); // Update hotel list
                            gui.modifyHotelPanelAfterHome();
                            break;
                        default:
                            gui.modifyHotelPanelAfterHome();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Please add hotel");
            }
        });

        // Action listener when change price rate button is clicked
        this.gui.setChangeRateBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float newBasePrice;
                int date = gui.getDatesDropdown().getSelectedIndex() + 1;
                int hotelIndex = gui.getHotelListDropDown().getSelectedIndex();
                float std, dlx, exe, rate;
                int day;

                try {
                    String input = JOptionPane.showInputDialog(null, "Enter new base rate (e.g. 100 = 100, 50 = 50)");
                    if (input != null && !input.trim().isEmpty()) {
                        newBasePrice = Float.parseFloat(input);
                        // Check if the new base price is within the desired range
                        if (newBasePrice >= 50 && newBasePrice <= 150) {
                            newBasePrice /= 100; // Convert percentage to a decimal fraction
                            hotelManager.getHotel(hotelIndex).modifyDate(date, newBasePrice);
                            JOptionPane.showMessageDialog(null, String.format("Successfully changed base rate to %.2f%%", newBasePrice*100));

                            hotelIndex = gui.getHotelListDropDown().getSelectedIndex();

                            day = gui.getDatesDropdown().getSelectedIndex() + 1;
                            rate = hotelManager.getHotel(hotelIndex).getRate(day);
                            std = hotelManager.getHotel(hotelIndex).getBasePrice() * rate;
                            dlx = std * 1.2f;
                            exe = std * 1.35f;
                            gui.dateModifierPanel();
                            gui.ratesOnDayView(std, dlx, exe, rate);
                        } else {
                            throw new NumberFormatException("Base rate must be between 50 and 150");
                        }
                    } else {
                        throw new NumberFormatException("Empty or null input");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input: " + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action listener for adding rooms
        this.gui.setSubmitAddRoomListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int addedRooms, roomType, hotelIndex;
                String response;
                boolean success;
                hotelIndex = gui.getHotelListDropDown().getSelectedIndex();
                roomType = gui.getTypesOfRoomDropdown().getSelectedIndex() + 1;
                response = gui.getNumOfRoomsInput().getText();

                try {
                    addedRooms = Integer.parseInt(response);
                    success = hotelManager.getHotel(hotelIndex).addRoom(addedRooms, roomType);

                    if (success) {
                        String output;
                        int stdRoom, dlxRoom, exeRoom;
                        stdRoom = hotelManager.getHotel(hotelIndex).numStandard();
                        dlxRoom = hotelManager.getHotel(hotelIndex).numDeluxe();
                        exeRoom = hotelManager.getHotel(hotelIndex).numExec();
                        output = "Standard rooms: " + stdRoom +
                                "\n Deluxe rooms: " + dlxRoom +
                                "\n Executive rooms: " + exeRoom;
                        gui.addRooms(hotelManager.getHotel(hotelIndex).getRooms().size(), output);
                        JOptionPane.showMessageDialog(null, "Successfully added " + addedRooms + " rooms!");
                    }else
                        JOptionPane.showMessageDialog(null, "Hotel rooms should only be <= 50", "Cannot add room",
                                JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Enter a valid numerical value!", "Cannot add room",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action listener when there is a change in the selected date in the dropdown
        this.gui.setDatesDropdownListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float std, dlx, exe, rate;
                int hotelIndex, day;
                hotelIndex = gui.getHotelListDropDown().getSelectedIndex();
                day = gui.getDatesDropdown().getSelectedIndex() + 1;
                rate = hotelManager.getHotel(hotelIndex).getRate(day);
                std = hotelManager.getHotel(hotelIndex).getBasePrice() * rate;
                dlx = std * 1.2f;
                exe = std * 1.35f;
                gui.ratesOnDayView(std, dlx, exe, rate);
            }
        });

        // Action listener for the reservation list dropdown
        this.gui.setReservationListDropdownListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = gui.getHotelListDropDown().getSelectedIndex();
                int reserveIndex = gui.getReservationListDropdown().getSelectedIndex();
                if(reserveIndex >= 0)
                    gui.showReservation(hotelManager.getHotel(index).getReservations().get(reserveIndex).toString());
            }
        });

        // Action listener for remove reservation button when deleting a reservation
        this.gui.setDeleteReservationBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hotelIndex = gui.getHotelListDropDown().getSelectedIndex(),
                    reserveIndex = gui.getReservationListDropdown().getSelectedIndex();
                String guestName = hotelManager.getHotel(hotelIndex).getReservations().get(reserveIndex).getGuestName();
                int choice1 = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete reservation for " + guestName + "?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (choice1 == 0) {
                    hotelManager.getHotel(hotelIndex).removeReservationGui(reserveIndex);
                    JOptionPane.showMessageDialog(null, "Successfully remove reservation for " + guestName);
                    if(!hotelManager.getHotel(hotelIndex).isReservationEmpty())
                        gui.viewReservations(hotelManager.getHotel(hotelIndex).getReservations(), hotelManager.getHotelName(hotelIndex));
                    else { // No reservation found update view
                        JOptionPane.showMessageDialog(null, "No reservations found");
                        gui.closeReservationFrame();
                    }
                }
            }
        });
    }

    /**
     * Gets the hotel names in hotel manager
     * @return The hotel names.
     */
    public String[] getHotelNames(){
        String[] hotelNames = new String[hotelManager.getHotels().size()];
        for (int i = 0; i < hotelManager.getHotels().size(); i++) {
            hotelNames[i] = hotelManager.getHotelName(i);
        }
        return hotelNames;
    }


}
