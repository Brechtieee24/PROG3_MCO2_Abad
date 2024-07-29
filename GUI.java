import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * GUI modification of a Hotel Reservation System.
 * <p>
 *     Using MVC Architecture, this class serves as the view that implements a GUI of this
 *     Hotel Reservation System.
 * </p>
 * @author Albrecht Gabriel Abad
 * @since July 2024
 * @version 1.0
 */
public class GUI {
    private JPanel homeScreenPanel, reserveRoomPanel, modifyHotelPanel, panelCenter, reservSubmitBtnPanel,
                    newHotelPanel, roomInfoPanel, addRoomsPanel, dateModifierPanel,
                    checkReservationPanel, blankPanel;
    private JFrame frame, hiLvlInfoFrame, createHotelFrame, roomInfoFrame, addRoomsFrame,
                    dateModifierFrame, checkReservationFrame;
    private JLabel homeMenuTitle, modifyHotelMenuTitle, nameLbl, earningsLbl, numRoomLbl, numStdLbl, numDlxLbl, numExeLbl,
                    guestNameLbl, chkInLbl, chkOutLbl, vouchLbl, roomTypeLbl, totalPriceLbl, newHotelLbl,
                    newStdRoomLbl, newDlxRoomLbl, newExeRoomLbl, daysAvaiLbl, daysOccupLbl, roomNumLbl, roomPriceLbl,
                    selectDateLbl, existingRatesLbl, stdRateLbl, dlxRateLbl, exeRateLbl, priceEstimateLbl;
    private JButton reserveBtn, addHotelBtn, submitReserveBtn, manageHotelBtn, backBtnManage1st, submitBtnManage1st,
                    newHotelBtn, submitAddRoom, changeRateBtn, deleteReservationBtn, checkPriceBtn;
    private JComboBox<String> hotelListDropDown, hotelActionsDropdown, typesOfRoomDropdown, reservationListDropdown;
    private JComboBox<Integer> chkInDatesDropdown, chkOutDatesDropdown, newStdRoomDropdown, newDlxRoomDropdown, newExeRoomDropdown,
                                datesDropdown;
    private JTextField guestNameInput, voucherInput, newHotelName, numOfRoomsInput;
    private JTextArea checkReservationArea, roomCountArea;

    /**
     * Constructor for GUI
     * <p>
     *     This constructor instantiates the panels, frames, labels, buttons, combo boxes, text fields, and text area.
     * </p>
     */
    public GUI() {
        this.frame = new JFrame();
        this.hiLvlInfoFrame = new JFrame();


        //Initialization of panels
        this.homeScreenPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        this.reserveRoomPanel = new JPanel(new BorderLayout());
        this.modifyHotelPanel = new JPanel(new GridLayout(6, 1));
        this.blankPanel = new JPanel();
        this.panelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER)); // for making a reservation
        this.reservSubmitBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        // main menu
        homeMenuTitle = new JLabel("Albie's Hotel Reservation System");
        homeMenuTitle.setOpaque(true);
        homeMenuTitle.setBackground(Color.decode("#DEAC80"));
        homeMenuTitle.setFont(new Font("Lucida Handwriting", Font.PLAIN, 25));
        homeMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
        modifyHotelMenuTitle = new JLabel("MODIFYING SELECTED HOTEL");
        homeScreenPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        // CREATE THE BUTTONS
        this.reserveBtn = new JButton("RESERVE A ROOM");
        this.manageHotelBtn = new JButton("MANAGE HOTELS");
        this.submitReserveBtn = new JButton("SUBMIT BOOKING");
        this.addHotelBtn = new JButton("ADD NEW HOTEL");

        // Initialize other buttons
        backBtnManage1st = new JButton("BACK TO MAIN MENU");
        submitBtnManage1st = new JButton("SUBMIT");

        //Creating a new hotel
        this.createHotelFrame = new JFrame("Creating new hotel");
        this.newHotelPanel = new JPanel();
        newHotelName = new JTextField("");
        newHotelLbl = new JLabel("New Hotel Name: ");
        newStdRoomLbl = new JLabel("Standard Rooms: ");
        newDlxRoomLbl = new JLabel("Deluxe Rooms: ");
        newExeRoomLbl = new JLabel("Executive Rooms: ");
        newHotelBtn = new JButton("Create Hotel");
        newStdRoomDropdown = new JComboBox<>();
        newDlxRoomDropdown = new JComboBox<>();
        newExeRoomDropdown = new JComboBox<>();

        // for reservations
        chkInDatesDropdown = new JComboBox<>();
        chkOutDatesDropdown = new JComboBox<>();
        guestNameInput = new JTextField("");
        voucherInput = new JTextField("");
        guestNameLbl = new JLabel("Guest Name: ");
        chkInLbl = new JLabel("Check in Date: ");
        chkOutLbl = new JLabel("Check out Date: ");
        vouchLbl = new JLabel("Voucher Code: ");
        roomTypeLbl = new JLabel("Room Type: ");
        totalPriceLbl = new JLabel("TOTAL PRICE: ");
        checkPriceBtn = new JButton("CHECK PRICE");
        priceEstimateLbl = new JLabel("0.0");

        // for high level information
        nameLbl = new JLabel("HOTEL");
        earningsLbl = new JLabel("Earnings is now: " );
        numRoomLbl = new JLabel("Total number of rooms: " );
        numStdLbl = new JLabel("Total number of Standard rooms: " );
        numDlxLbl = new JLabel("Total number of Deluxe rooms: " );
        numExeLbl = new JLabel("Total number of Executive rooms: " );

        // for adding rooms
        addRoomsFrame = new JFrame();
        addRoomsPanel = new JPanel();
        submitAddRoom = new JButton();
        numOfRoomsInput = new JTextField();
        roomCountArea = new JTextArea();

        // for modifying/viewing rates
        dateModifierFrame = new JFrame();
        dateModifierPanel = new JPanel();
        selectDateLbl = new JLabel();
        existingRatesLbl = new JLabel();
        stdRateLbl = new JLabel();
        dlxRateLbl = new JLabel();
        exeRateLbl = new JLabel();
        changeRateBtn = new JButton();

        //Initialize manage hotel things
        String[] actions = {"CHANGE HOTEL NAME", "ADD ROOMS", "REMOVE ROOMS", "ROOM AVAILABILITY",
                "ROOM INFORMATION", "UPDATE BASE PRICE", "MODIFY RATE",
                "CHECK RESERVATIONS", "HIGH-LEVEL INFORMATION", "REMOVE HOTEL"};
        String[] roomTypes = {"Standard Room", "Deluxe Room", "Executive Room"};
        hotelListDropDown = new JComboBox<>(actions);
        hotelActionsDropdown = new JComboBox<>(actions);
        typesOfRoomDropdown = new JComboBox<>(roomTypes);

        //Room information
        roomNumLbl = new JLabel();
        daysAvaiLbl = new JLabel();
        daysOccupLbl = new JLabel();
        roomPriceLbl = new JLabel();
        roomInfoFrame = new JFrame();
        roomInfoPanel = new JPanel();

        // Check reservation
        checkReservationFrame = new JFrame();
        checkReservationPanel = new JPanel();
        reservationListDropdown = new JComboBox<>();
        checkReservationArea = new JTextArea();
        deleteReservationBtn = new JButton();
        datesDropdown = new JComboBox<>();

        for(int i = 1; i <= 31; i++)
            datesDropdown.addItem(i);

        initHomePanel();

        frame.setVisible(true);
        frame.setSize(600, 350);
        frame.setTitle("My Hotel Reservation System");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Sets the action listener for submit reservation button.
     * @param submitReserveBtnListener The action listener with its corresponding actions.
     */
    public void setSubmitReserveBtnListener(ActionListener submitReserveBtnListener){
        this.submitReserveBtn.addActionListener(submitReserveBtnListener);
    }

    /**
     * Sets the action listener for reserve button.
     * @param reserveBtnListener The action listener with its corresponding actions.
     */
    public void setReserveBtnListener(ActionListener reserveBtnListener){
        this.reserveBtn.addActionListener(reserveBtnListener);
    }

    /**
     * Setter for action listener of manage hotel button.
     * @param manageHotelBtnListener The action listener with its corresponding actions.
     */
    public void setManageHotelBtnListener(ActionListener manageHotelBtnListener) {
        manageHotelBtn.addActionListener(manageHotelBtnListener);
    }

    /**
     * Sets the action listener for the first back button.
     * @param backBtnManage1stListener The action listener with its corresponding actions.
     */
    public void setBackBtnManage1stListener(ActionListener backBtnManage1stListener) {
        backBtnManage1st.addActionListener(backBtnManage1stListener);
    }

    /**
     * Sets the action listener for the submit button of the hotel actions to do.
     * @param submitBtnManage1stListener The action listener with its corresponding actions.
     */
    public void setSubmitBtnManage1stListener(ActionListener submitBtnManage1stListener) {
        submitBtnManage1st.addActionListener(submitBtnManage1stListener);
    }

    /**
     * Sets the add hotel button listener.
     * @param listener The action listener with its corresponding actions.
     */
    public void setAddHotelBtnListener(ActionListener listener){
        this.addHotelBtn.addActionListener(listener);
    }

    /**
     * Sets the listener for the create hotel button.
     * @param listener The action listener with its corresponding actions.
     */
    public void setCreateHotelBtnListener(ActionListener listener){
        this.newHotelBtn.addActionListener(listener);
    }

    /**
     * Sets the add room button listener.
     * @param listener The action listener with its corresponding actions.
     */
    public void setSubmitAddRoomListener(ActionListener listener){
        this.submitAddRoom.addActionListener(listener);
    }

    /**
     * Sets the change rate button listener
     * @param listener The action listener with its corresponding actions.
     */
    public void setChangeRateBtn(ActionListener listener){
        this.changeRateBtn.addActionListener(listener);
    }

    /**
     * Sets the listener for dates dropdown.
     * @param listener The action listener with its corresponding actions.
     */
    public void setDatesDropdownListener(ActionListener listener){
        this.datesDropdown.addActionListener(listener);
    }

    /**
     * Sets the delete reservation button listener.
     * @param listener The action listener with its corresponding actions.
     */
    public void setDeleteReservationBtnListener (ActionListener listener){
        this.deleteReservationBtn.addActionListener(listener);
    }

    /**
     * Sets the reservation list dropdown listener.
     * @param listener The action listener with its corresponding actions.
     */
    public void setReservationListDropdownListener(ActionListener listener){
        this.reservationListDropdown.addActionListener(listener);
    }

    /**
     * Sets the check price button listener.
     * @param listener The action listener with its corresponding actions
     */
    public void setCheckPriceBtnListener (ActionListener listener){
        this.checkPriceBtn.addActionListener(listener);
    }

    /**
     * Initializes the home screen.
     */
    public void initHomePanel(){
        // MODIFY BUTTONS
        reserveBtn.setFont(new Font("Arial", Font.BOLD, 20));
        reserveBtn.setBackground(Color.decode("#914F1E"));
        reserveBtn.setForeground(Color.white);
        manageHotelBtn.setFont(new Font("Arial", Font.BOLD, 20));
        manageHotelBtn.setBackground(Color.decode("#914F1E"));
        manageHotelBtn.setForeground(Color.white);

        // ADD TO HOME PANEL
        homeScreenPanel.add(homeMenuTitle);
        homeScreenPanel.add(reserveBtn);
        homeScreenPanel.add(manageHotelBtn);

        frame.add(homeScreenPanel);
    }

    /**
     * Initialize the panel for displaying the high level information
     * @param hotelName The hotel name.
     * @param earnings The total earnings of the hotel.
     * @param numOfRooms The total number of rooms.
     * @param numStd The total number of standard rooms.
     * @param numDlx The total number of deluxe rooms.
     * @param numExe The total number of executive rooms.
     */
    public void initHighLevelInfoPanel(String hotelName, float earnings, int numOfRooms, int numStd,
                                       int numDlx, int numExe){
        hiLvlInfoFrame.setLayout(new GridLayout(6, 1, 5, 5));
        hiLvlInfoFrame.setVisible(true);
        hiLvlInfoFrame.setSize(280, 250);
        hiLvlInfoFrame.setResizable(false);
        hiLvlInfoFrame.setTitle(hotelName);
        hiLvlInfoFrame.setResizable(true);
        hiLvlInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        nameLbl.setText(hotelName);
        nameLbl.setHorizontalAlignment(SwingConstants.CENTER);
        nameLbl.setOpaque(true);
        nameLbl.setBackground(Color.decode("#914F1E"));
        nameLbl.setForeground(Color.white);

        earningsLbl.setText(String.format("Earnings is now: %.2f", earnings));
        earningsLbl.setOpaque(true);
        earningsLbl.setBackground(Color.decode("#DEAC80"));

        numRoomLbl.setText("Total number of rooms: " + numOfRooms);
        numRoomLbl.setOpaque(true);
        numRoomLbl.setBackground(Color.decode("#DEAC80"));

        if(numStd == 0)
            numStdLbl.setText("No Standard Rooms");
        else
            numStdLbl.setText(String.format("Standard rooms [Room 101 to %d]: %d room/s", 100+numStd, numStd));

        if(numDlx == 0)
            numDlxLbl.setText("No Deluxe Rooms");
        else
            numDlxLbl.setText(String.format("Deluxe rooms [Room 201 to %d]: %d room/s", 200+numDlx, numDlx));

        if(numExe == 0)
            numExeLbl.setText("No Executive Rooms");
        else
            numExeLbl.setText(String.format("Executive rooms [Room 301 to %d]: %d room/s", 300+numExe, numExe));


        numStdLbl.setOpaque(true);
        numStdLbl.setBackground(Color.decode("#DEAC80"));
        numDlxLbl.setOpaque(true);
        numDlxLbl.setBackground(Color.decode("#DEAC80"));
        numExeLbl.setOpaque(true);
        numExeLbl.setBackground(Color.decode("#DEAC80"));

        hiLvlInfoFrame.add(nameLbl);
        hiLvlInfoFrame.add(earningsLbl);
        hiLvlInfoFrame.add(numRoomLbl);
        hiLvlInfoFrame.add(numStdLbl);
        hiLvlInfoFrame.add(numDlxLbl);
        hiLvlInfoFrame.add(numExeLbl);

        hiLvlInfoFrame.revalidate();
        hiLvlInfoFrame.repaint();
    }

    /**
     * Sets the frame for the hotel modifications.
     */
    public void modifyHotelPanelAfterHome(){
        frame.getContentPane().removeAll(); // clear previous contents
        modifyHotelMenuTitle.setFont(new Font("Arial", Font.BOLD, 20));
        modifyHotelMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);

        modifyHotelPanel.setOpaque(true);
        modifyHotelPanel.setBackground(Color.decode("#914F1E"));
        modifyHotelMenuTitle.setForeground(Color.white);
        modifyHotelPanel.add(modifyHotelMenuTitle);
        modifyHotelPanel.add(hotelListDropDown);
        modifyHotelPanel.add(hotelActionsDropdown);
        modifyHotelPanel.add(submitBtnManage1st);
        modifyHotelPanel.add(addHotelBtn);
        modifyHotelPanel.add(backBtnManage1st);
        modifyHotelPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#F7DCB9"), 30));

        hotelListDropDown.setBackground(Color.decode("#DEAC80"));
        hotelActionsDropdown.setBackground(Color.decode("#DEAC80"));
        addHotelBtn.setForeground(Color.white);
        addHotelBtn.setBackground(Color.decode("#914F1E"));
        submitBtnManage1st.setForeground(Color.white);
        submitBtnManage1st.setBackground(Color.decode("#914F1E"));
        backBtnManage1st.setForeground(Color.white);
        backBtnManage1st.setBackground(Color.decode("#914F1E"));

        frame.add(modifyHotelPanel); // add panel to frame
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Sets the panel for making a room reservation.
     */
    public void setReservationPanel() {
        // Clear existing components
        frame.getContentPane().removeAll();

        // Panel setup
        panelCenter.setLayout(new GridLayout(0, 2, 5, 5));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding around the panel

        // Configure component sizes
        guestNameInput.setPreferredSize(new Dimension(150, 30));
        chkInDatesDropdown.setPreferredSize(new Dimension(150, 30));
        chkOutDatesDropdown.setPreferredSize(new Dimension(150, 30));
        voucherInput.setPreferredSize(new Dimension(150, 30));

        initializeDateDropdowns();
        nameLbl.setText("Hotel: ");
        guestNameInput.setText("");
        voucherInput.setText("");
        setPriceEstimateLbl("0.0");

        // Set the font
        nameLbl.setFont(new Font("Arial", Font.BOLD, 18));
        roomTypeLbl.setFont(new Font("Arial", Font.BOLD, 18));
        guestNameLbl.setFont(new Font("Arial", Font.BOLD, 18));
        chkInLbl.setFont(new Font("Arial", Font.BOLD, 18));
        chkOutLbl.setFont(new Font("Arial", Font.BOLD, 18));
        vouchLbl.setFont(new Font("Arial", Font.BOLD, 18));

        // change color
        nameLbl.setBackground(Color.decode("#DEAC80"));
        hotelListDropDown.setBackground(Color.decode("#DEAC80"));
        typesOfRoomDropdown.setBackground(Color.decode("#DEAC80"));
        chkInDatesDropdown.setBackground(Color.decode("#DEAC80"));
        chkOutDatesDropdown.setBackground(Color.decode("#DEAC80"));
        voucherInput.setBackground(Color.decode("#DEAC80"));
        guestNameInput.setBackground(Color.decode("#DEAC80"));


        // Add components to the center panel
        panelCenter.add(nameLbl);
        panelCenter.add(hotelListDropDown);
        panelCenter.add(roomTypeLbl);
        panelCenter.add(typesOfRoomDropdown);
        panelCenter.add(guestNameLbl);
        panelCenter.add(guestNameInput);
        panelCenter.add(chkInLbl);
        panelCenter.add(chkInDatesDropdown);
        panelCenter.add(chkOutLbl);
        panelCenter.add(chkOutDatesDropdown);
        panelCenter.add(vouchLbl);
        panelCenter.add(voucherInput);
        panelCenter.add(totalPriceLbl);
        panelCenter.add(priceEstimateLbl);
        panelCenter.setBorder(BorderFactory.createLineBorder(Color.decode("#DEAC80"), 10));
        panelCenter.setBackground(Color.decode("#F7DCB9"));

        // Modify submit button
        reservSubmitBtnPanel.setLayout(new GridLayout(2, 1));
        submitReserveBtn.setBackground(Color.decode("#173B45"));
        submitReserveBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#DEAC80"), 40));
        submitReserveBtn.setForeground(Color.white);
        submitReserveBtn.setHorizontalAlignment(SwingConstants.CENTER);
        submitReserveBtn.setPreferredSize(new Dimension(200, 20));
        reservSubmitBtnPanel.add(submitReserveBtn);

        //Modify check price button
        checkPriceBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#DEAC80"), 40));
        checkPriceBtn.setBackground(Color.decode("#173B45"));
        checkPriceBtn.setForeground(Color.white);
        checkPriceBtn.setHorizontalAlignment(SwingConstants.CENTER);
        checkPriceBtn.setPreferredSize(new Dimension(200, 20));
        reservSubmitBtnPanel.add(checkPriceBtn);

        // modify back button
        backBtnManage1st.setBackground(Color.decode("#914F1E"));
        backBtnManage1st.setForeground(Color.white);

        // Set up the reserveRoomPanel
        reserveRoomPanel.setLayout(new BorderLayout());
        reserveRoomPanel.setBackground(Color.PINK);
        reserveRoomPanel.add(panelCenter, BorderLayout.CENTER);
        reserveRoomPanel.add(reservSubmitBtnPanel, BorderLayout.EAST);
        reserveRoomPanel.add(backBtnManage1st, BorderLayout.SOUTH);

        // Add the reserveRoomPanel to the frame
        frame.add(reserveRoomPanel);
        frame.revalidate();
        frame.repaint();
    }

    public void setPriceEstimateLbl (String estimateLbl){
        priceEstimateLbl.setText(estimateLbl);
        reserveRoomPanel.revalidate();
        reserveRoomPanel.repaint();
    }

    /**
     * Creates the panel when adding a hotel.
     */
    public void addHotelPanel() {
        newStdRoomDropdown.removeAllItems();
        newDlxRoomDropdown.removeAllItems();
        newExeRoomDropdown.removeAllItems();
        newHotelPanel.removeAll();

        for (int i = 0; i <= 50; i++) {
            newStdRoomDropdown.addItem(i);
            newDlxRoomDropdown.addItem(i);
            newExeRoomDropdown.addItem(i);
        }

        createHotelFrame.setLayout(new BorderLayout());
        createHotelFrame.setResizable(false);
        createHotelFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        createHotelFrame.setPreferredSize(new Dimension(400, 200));
        createHotelFrame.setSize(500, 500);  // Set size explicitly

        newHotelPanel.setPreferredSize(new Dimension(400, 200));
        newHotelPanel.setLayout(new GridLayout(5, 2, 5, 5));
        newHotelPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#DEAC80"), 10));
        newHotelPanel.setBackground(Color.decode("#DEAC80"));

        //Set color
        newHotelBtn.setBackground(Color.decode("#914F1E"));
        newHotelBtn.setForeground(Color.white);
        newHotelName.setBackground(Color.decode("#F7DCB9"));
        newStdRoomDropdown.setBackground(Color.decode("#F7DCB9"));
        newDlxRoomDropdown.setBackground(Color.decode("#F7DCB9"));
        newExeRoomDropdown.setBackground(Color.decode("#F7DCB9"));
        blankPanel.setBackground(Color.decode("#DEAC80"));

        // Set font size
        newHotelLbl.setFont(new Font("Arial", Font.BOLD, 20));
        newStdRoomLbl.setFont(new Font("Arial", Font.PLAIN, 17));
        newDlxRoomLbl.setFont(new Font("Arial", Font.PLAIN, 17));
        newExeRoomLbl.setFont(new Font("Arial", Font.PLAIN, 17));

        newHotelName.setText("");
        newHotelPanel.add(newHotelLbl);
        newHotelPanel.add(newHotelName);
        newHotelPanel.add(newStdRoomLbl);
        newHotelPanel.add(newStdRoomDropdown);
        newHotelPanel.add(newDlxRoomLbl);
        newHotelPanel.add(newDlxRoomDropdown);
        newHotelPanel.add(newExeRoomLbl);
        newHotelPanel.add(newExeRoomDropdown);
        newHotelPanel.add(blankPanel);
        newHotelPanel.add(newHotelBtn);

        createHotelFrame.add(newHotelPanel, BorderLayout.CENTER);
        createHotelFrame.pack(); // Resize frame based on preferred sizes of components
        createHotelFrame.setLocationRelativeTo(null); // Center the frame on screen
        createHotelFrame.setVisible(true); // Make sure it's visible
    }

    /**
     * Sets the date dropdown values.
     */
    public void initializeDateDropdowns() {
        // Clear existing items
        chkInDatesDropdown.removeAllItems();
        chkOutDatesDropdown.removeAllItems();

        // Populate chkInDatesDropdown with dates from 1 to 30
        for (int i = 1; i <= 30; i++) {
            chkInDatesDropdown.addItem(i);
        }

        // Populate chkOutDatesDropdown with dates from 2 to 31
        updateChkOutDatesDropdown();
    }

    /**
     * Updates the values in the available checkout dates.
     */
    public void updateChkOutDatesDropdown() {
        Integer checkInDate = (Integer) chkInDatesDropdown.getSelectedItem();

        // Clear existing items
        chkOutDatesDropdown.removeAllItems();

        if (checkInDate != null) {
            // Populate chkOutDatesDropdown with dates starting from the selected check-in date + 1
            for (int i = checkInDate + 1; i <= 31; i++) {
                chkOutDatesDropdown.addItem(i);
            }
            reserveRoomPanel.revalidate();
            reserveRoomPanel.repaint();
        }
    }

    /**
     * Returns the frame to the main menu.
     */
    public void returnToMenu(){
        frame.getContentPane().removeAll();
        frame.add(homeScreenPanel);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Gets the room number to be checked.
     * @return The inputted room number.
     */
    public int inputRoomToCheck() {
        String response = JOptionPane.showInputDialog(null, "Enter room number:");
        int roomNum = -1;
        if (response != null) {
            try {
                roomNum = Integer.parseInt(response);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            }
        }
        return roomNum;
    }

    /**
     * Sets the frame to display the room information.
     * @param roomNum The room number selected.
     * @param daysAvail The total days without bookings.
     * @param daysOccupied Total days with booking.
     * @param price The room base price.
     */
    public void checkRoomInformation(int roomNum, int daysAvail, int daysOccupied, float price) {
        roomInfoFrame.getContentPane().removeAll(); // Clear existing contents

        roomInfoFrame.setSize(270, 180); // Set size of the frame
        roomInfoFrame.setResizable(false);
        roomInfoFrame.setTitle("Room " + roomNum);
        roomInfoFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        roomInfoPanel.setLayout(new GridLayout(4, 1, 3, 3));
        roomInfoPanel.setBackground(Color.decode("#DEAC80"));

        roomNumLbl.setOpaque(true); // change background color
        roomNumLbl.setBackground(Color.decode("#914F1E"));
        roomNumLbl.setFont(new Font("Arial", Font.BOLD, 20));
        roomNumLbl.setForeground(Color.white);
        roomNumLbl.setHorizontalAlignment(SwingConstants.CENTER);
        roomNumLbl.setText("Room " + roomNum);

        roomPriceLbl.setText(" Room rate is: " + price);
        roomPriceLbl.setFont(new Font("Arial", Font.BOLD, 15));
        daysAvaiLbl.setText(" Days Available: " + daysAvail);
        daysAvaiLbl.setFont(new Font("Arial", Font.BOLD, 15));
        daysOccupLbl.setText(" Days Occupied: " + daysOccupied);
        daysOccupLbl.setFont(new Font("Arial", Font.BOLD, 15));

        roomInfoPanel.add(roomNumLbl);
        roomInfoPanel.add(roomPriceLbl);
        roomInfoPanel.add(daysAvaiLbl);
        roomInfoPanel.add(daysOccupLbl);

        roomInfoFrame.add(roomInfoPanel); // Add the panel to the frame
        roomInfoFrame.setVisible(true); // Make the frame visible
    }

    /**
     * Initializes the date rate modifier panel.
     */
    public void dateModifierPanel(){
        dateModifierFrame.getContentPane().removeAll();
        dateModifierPanel.removeAll();
        dateModifierFrame.setResizable(false);
        dateModifierFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dateModifierFrame.setTitle("Date Rate Modifier");
        dateModifierFrame.setSize(new Dimension(300, 250));

        dateModifierPanel.setBackground(Color.decode("#E7D4B5"));
        dateModifierPanel.setLayout(new GridLayout(7, 1));
        dateModifierPanel.setPreferredSize(new Dimension(280, 230));

        selectDateLbl.setText("Choose from dates below");
        selectDateLbl.setOpaque(true);
        selectDateLbl.setBackground(Color.decode("#914F1E"));
        selectDateLbl.setForeground(Color.white);
        selectDateLbl.setFont(new Font("Arial", Font.BOLD, 18));
        selectDateLbl.setHorizontalAlignment(SwingConstants.CENTER);
        datesDropdown.setBackground(Color.decode("#F7DCB9"));
        existingRatesLbl.setText("Current rate: " );
        existingRatesLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        stdRateLbl.setText("Standard Room price: ");
        stdRateLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        dlxRateLbl.setText("Deluxe Room price: ");
        dlxRateLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        exeRateLbl.setText("Executive Room price: ");
        exeRateLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        changeRateBtn.setText("UPDATE RATE");
        changeRateBtn.setBackground(Color.decode("#914F1E"));
        changeRateBtn.setForeground(Color.white);
        changeRateBtn.setFont(new Font("Aharoni", Font.BOLD, 15));

        dateModifierPanel.add(selectDateLbl);
        dateModifierPanel.add(datesDropdown);
        dateModifierPanel.add(existingRatesLbl);
        dateModifierPanel.add(stdRateLbl);
        dateModifierPanel.add(dlxRateLbl);
        dateModifierPanel.add(exeRateLbl);
        dateModifierPanel.add(changeRateBtn);
        dateModifierPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#F7DCB9"), 10));

        dateModifierFrame.add(dateModifierPanel);
        dateModifierFrame.setVisible(true);
    }

    /**
     * Updates the panel for the date rate modification based on its current rates.
     * @param std The price of a standard room.
     * @param dlx The price of a deluxe room.
     * @param exe The price of an executive room.
     * @param rate The current day rate.
     */
    public void ratesOnDayView(float std, float dlx, float exe, float rate){
        rate *= 100;
        existingRatesLbl.setText(String.format("Current Rate: %.2f%% ", rate));
        stdRateLbl.setText(String.format("Standard room price: %.2f", std));
        dlxRateLbl.setText(String.format("Deluxe room price: %.2f", dlx));
        exeRateLbl.setText(String.format("Executive room price: %.2f", exe));
    }

    /**
     * Sets the frame for adding rooms.
     * @param currNum The current number of rooms.
     */
    public void addRooms(int currNum, String count){
        addRoomsFrame.getContentPane().removeAll();
        addRoomsFrame.setVisible(true);
        addRoomsFrame.setResizable(false);
        addRoomsFrame.setTitle("Adding rooms");
        addRoomsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addRoomsFrame.setSize(new Dimension(300, 280));

        addRoomsPanel.setBackground(Color.decode("#F7DCB9"));
        addRoomsPanel.setLayout(new GridLayout(4, 1, 13, 10));
        addRoomsPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#F7DCB9"), 10));

        roomCountArea.setText(count);
        roomCountArea.setFont(new Font("Arial", Font.BOLD, 13));
        roomCountArea.setEditable(false);
        roomCountArea.setBackground(Color.decode("#DEAC80"));
        typesOfRoomDropdown.setBackground(Color.decode("#DEAC80"));
        numOfRoomsInput.setText("At most " + (50-currNum) + " rooms only");
        numOfRoomsInput.setBackground(Color.decode("#DEAC80"));
        submitAddRoom.setText("ADD ROOMS");
        submitAddRoom.setForeground(Color.white);
        submitAddRoom.setBackground(Color.decode("#914F1E"));

        addRoomsPanel.add(roomCountArea);
        addRoomsPanel.add(typesOfRoomDropdown);
        addRoomsPanel.add(numOfRoomsInput);
        addRoomsPanel.add(submitAddRoom);

        addRoomsFrame.add(addRoomsPanel);


    }

    /**
     * Sets the dropdown for the list of hotels.
     * @param hotels The array of hotels.
     */
    public void setHotelListDropDown(String[] hotels){
        hotelListDropDown.removeAllItems();
        for(String hotelName : hotels){
            hotelListDropDown.addItem(hotelName);
        }
    }

    /**
     * Sets the view reservation panel of the selected hotel.
     * @param reservations The reservations list.
     * @param name The name of the hotel.
     */
    public void viewReservations(ArrayList<Reservation> reservations, String name){
        checkReservationFrame.getContentPane().removeAll();
        checkReservationFrame.setSize(350, 250);
        checkReservationFrame.setResizable(false);
        checkReservationFrame.setVisible(true);
        checkReservationFrame.setTitle("Viewing reservations at " + name);

        checkReservationPanel.setLayout(new BorderLayout());
        checkReservationPanel.setPreferredSize(new Dimension(220,130));
        checkReservationPanel.setBackground(Color.decode("#B6C7AA"));

        reservationListDropdown.removeAllItems();
        for(Reservation reservation: reservations)
            reservationListDropdown.addItem(reservation.getGuestName());

        checkReservationArea.setText(reservations.getFirst().toString());
        checkReservationArea.setBorder(BorderFactory.createLineBorder(Color.decode("#DEAC80"), 8));
        checkReservationArea.setFont(new Font("Arial", Font.BOLD, 14));
        deleteReservationBtn.setText("DELETE RESERVATION");
        deleteReservationBtn.setForeground(Color.white);
        deleteReservationBtn.setBackground(Color.decode("#914F1E"));
        deleteReservationBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#DEAC80"), 8));


        checkReservationPanel.add(reservationListDropdown, BorderLayout.NORTH);
        reservationListDropdown.setBackground(Color.decode("#F7DCB9"));
        reservationListDropdown.setBorder(BorderFactory.createLineBorder(Color.decode("#DEAC80"), 12));
        checkReservationPanel.add(checkReservationArea, BorderLayout.CENTER);
        checkReservationArea.setBackground(Color.decode("#F7DCB9"));
        checkReservationPanel.add(deleteReservationBtn, BorderLayout.SOUTH);

        checkReservationFrame.add(checkReservationPanel);
    }

    /**
     * Displays the reservation details from 'Reservation': {@link Reservation}.
     * @param details The reservation details.
     */
    public void showReservation(String details){
        checkReservationArea.setText(details);
        checkReservationArea.setFont(new Font("Arial", Font.BOLD, 14));
    }

    /**
     * Closes the view reservation frame.
     */
    public void closeReservationFrame(){
        checkReservationFrame.setVisible(false);
    }

    /**
     * Getter for the hotel list dropdown.
     * @return The hotel list dropdown.
     */
    public JComboBox<String> getHotelListDropDown() {
        return hotelListDropDown;
    }

    /**
     * Getter for the hotel modifications list
     * @return The hotel modifications dropdown.
     */
    public JComboBox<String> getHotelActionsDropdown() {
        return hotelActionsDropdown;
    }

    /**
     * Getter for the types of room list.
     * @return The types of room dropdown.
     */
    public JComboBox<String> getTypesOfRoomDropdown(){
        return typesOfRoomDropdown;
    }

    /**
     * Getter for the reservations list dropdown.
     * @return The reservations list dropdown.
     */
    public JComboBox<String> getReservationListDropdown(){
        return reservationListDropdown;
    }

    /**
     * Getter for the check-in dates dropdown.
     * @return The check-in dates dropdown.
     */
    public JComboBox<Integer> getChkInDatesDropdown(){
        return chkInDatesDropdown;
    }

    /**
     * Getter for the number of standard rooms to be added.
     * @return The number of standard rooms to be added.
     */
    public JComboBox<Integer> getNewStdRoomDropdown(){
        return newStdRoomDropdown;
    }

    /**
     * Getter for the number of deluxe rooms to be added.
     * @return The number of deluxe rooms to be added.
     */
    public JComboBox<Integer> getNewDlxRoomDropdown(){
        return newDlxRoomDropdown;
    }

    /**
     * Getter for the number of executive rooms to be added.
     * @return The number of executive rooms to be added.
     */
    public JComboBox<Integer> getNewExeRoomDropdown(){
        return newExeRoomDropdown;
    }

    /**
     * Getter for the selected dates' dropdown.
     * @return The dates dropdown list.
     */
    public JComboBox<Integer> getDatesDropdown(){
        return datesDropdown;
    }

    /**
     * Getter for the check-out dates dropdown.
     * @return The check-out dates dropdown.
     */
    public JComboBox<Integer> getChkOutDatesDropdown(){
        return chkOutDatesDropdown;
    }

    /**
     * Gets the check-in date selected.
     * @return The selected check-in date.
     */
    public int getCheckIn() {
        Object date = getChkInDatesDropdown().getSelectedItem();
        if (date instanceof Integer)
            return (Integer) date;
        return -1;
    }

    /**
     * Gets the selected check-out date.
     * @return The check-out date.
     */
    public int getCheckOut() {
        Object date = getChkOutDatesDropdown().getSelectedItem();
        if (date instanceof Integer)
            return (Integer) date;
        return -1;
    }

    /**
     * Gets the number of standard rooms.
     * @return The number of standard rooms.
     */
    public int getStdRooms(){
        Object std = getNewStdRoomDropdown().getSelectedItem();
        if(std instanceof Integer)
            return (Integer) std;
        return -1;
    }

    /**
     * Gets the number of deluxe rooms.
     * @return The number of deluxe rooms.
     */
    public int getDlxRooms(){
        Object std = getNewDlxRoomDropdown().getSelectedItem();
        if(std instanceof Integer)
            return (Integer) std;
        return -1;
    }

    /**
     * Gets the number of executive rooms.
     * @return The number of executive rooms.
     */
    public int getExeRooms(){
        Object std = getNewExeRoomDropdown().getSelectedItem();
        if(std instanceof Integer)
            return (Integer) std;
        return -1;
    }

    /**
     * Gets the text field of guest name.
     * @return The inputted guest name.
     */
    public JTextField getGuestNameInput(){
        return guestNameInput;
    }

    /**
     * Gets the text field of voucher input.
     * @return The inputted voucher.
     */
    public JTextField getVoucherInput(){
        return voucherInput;
    }

    /**
     * Gets the text field of hotel name.
     * @return The inputted hotel name.
     */
    public JTextField getNewHotelName(){
        return newHotelName;
    }

    /**
     * Gets the text field of number of rooms.
     * @return The inputted number of rooms.
     */
    public JTextField getNumOfRoomsInput(){
        return numOfRoomsInput;
    }

}
