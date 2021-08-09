import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Driver {

    // class variables
	private static ArrayList<Ship> shipList = new ArrayList();
	private static ArrayList<Cruise> cruiseList = new ArrayList();
	private static ArrayList<Passenger> passengerList = new ArrayList();
	private static boolean addCruiseShipCheck = true;
	private static boolean addPassengerCheck = true;
	private static boolean addPassengerCruiseCheck = true;
	private static boolean addPassengerRoomCheck = true;
	private static boolean editShipCheck = true;
	private static boolean editCruiseCheck = true;
    
    public static void main(String[] args) {
    	String menuInput = "";
    	boolean check = true;
    	Scanner scnr = new Scanner(System.in);
        initializeShipList();       // initial ships
        initializeCruiseList();     // initial cruises
        initializePassengerList();  // initial passengers

        displayMenu();
        menuInput = scnr.nextLine();
        // loop unless valid input is entered. check numbers then letters
        while (check == true) {
        	try {
		        if (Integer.parseInt(menuInput) == 1) {
		        	addShip();
		        	displayMenu();
		        	menuInput = scnr.nextLine();
		        }
		        else if (Integer.parseInt(menuInput) == 2) {
		        	editShip();
		        	displayMenu();
		        	menuInput = scnr.nextLine();
		        }
		        else if (Integer.parseInt(menuInput) == 3) {
		        	addCruise();
		        	displayMenu();
		        	menuInput = scnr.nextLine();
		        }
		        else if (Integer.parseInt(menuInput) == 4) {
		        	editCruise();
		        	displayMenu();
		        	menuInput = scnr.nextLine();
		        }
		        else if (Integer.parseInt(menuInput) == 5) {
		        	addPassenger();
		        	displayMenu();
		        	menuInput = scnr.nextLine();
		        }
		        else if (Integer.parseInt(menuInput) == 6) {
		        	editPassenger();
		        	displayMenu();
		        	menuInput = scnr.nextLine();
		        }
		        else {
		        	System.out.println("Invalid input.");
		        	displayMenu();
		        	menuInput = scnr.nextLine();
		        }
        	}
        	catch (NumberFormatException a) {
		        if (menuInput.equalsIgnoreCase("a")) {
		        	printShipList("name");
		        	displayMenu();
		        	menuInput = scnr.nextLine();
		        }
		        else if (menuInput.equalsIgnoreCase("b")) {
		        	printShipList("active");
		        	displayMenu();
		        	menuInput = scnr.nextLine();
		        }
		        else if (menuInput.equalsIgnoreCase("c")) {
		        	printShipList("full");
		        	displayMenu();
		        	menuInput = scnr.nextLine();
		        }
		        else if (menuInput.equalsIgnoreCase("d")) {
		        	printCruiseList("list");
		        	displayMenu();
		        	menuInput = scnr.nextLine();
		        }
		        else if (menuInput.equalsIgnoreCase("e")) {
		        	printCruiseList("details");
		        	displayMenu();
		        	menuInput = scnr.nextLine();
		        }
		        else if (menuInput.equalsIgnoreCase("f")) {
		        	printPassengerList();
		        	displayMenu();
		        	menuInput = scnr.nextLine();
		        }
		        else if (menuInput.equalsIgnoreCase("x")) {
		        	System.out.println("Exiting...");
		        	check = false;
		        }
		        else {
		        	System.out.println("Invalid input.");
		        	displayMenu();
		        	menuInput = scnr.nextLine();
		        }
        	}
        }
    }
    // Initialize hardcoded ship data for testing
    public static void initializeShipList() {
        add("Candy Cane", 20, 40, 10, 60, true);
        add("Peppermint Stick", 10, 20, 5, 40, true);
        add("Bon Bon", 12, 18, 2, 24, false);
        add("Candy Corn", 12, 18, 2, 24, false);
    }

    // Initialize hardcoded cruise data for testing
    public static void initializeCruiseList() {
        Cruise newCruise = new Cruise("Southern Swirl", "Candy Cane", "Miami", "Cuba", "Miami");
        cruiseList.add(newCruise);
    }

    // Initialize hardcoded cruise data for testing
    public static void initializePassengerList() {
        Passenger newPassenger1 = new Passenger("Neo Anderson", "Southern Swirl", "Suite");
        passengerList.add(newPassenger1);

        Passenger newPassenger2 = new Passenger("Trinity", "Southern Swirl", "Suite");
        passengerList.add(newPassenger2);

        Passenger newPassenger3 = new Passenger("Morpheus", "Southern Swirl", "Balcony");
        passengerList.add(newPassenger3);
    }

    // custom method to add ships to the shipList ArrayList
    public static void add(String tName, int tBalcony, int tOceanView,
                           int tSuite, int tInterior, boolean tInService) {
        Ship newShip = new Ship(tName, tBalcony, tOceanView, tSuite, tInterior, tInService);
        shipList.add(newShip);
    }


    public static void printShipList(String listType) {
        // prints list of ships from the shipList ArrayList
        // name - prints a list of ship names only
        // active - prints a list of ship names that are "in service"
        // full - prints tabbed data on all ships

        if (shipList.size() < 1) {
            System.out.println("\nThere are no ships to print.");
            return;
        }
        if (listType == "name") {
            System.out.println("\n\nSHIP LIST - Name");
            for (int i = 0; i < shipList.size(); i++) {
                System.out.println(shipList.get(i));
            }
        } else if (listType == "active") {
            System.out.println("\n\nSHIP LIST - Active");

            // for each ship if inService = true then print
            for (int i = 0; i < shipList.size(); i++) {
            	if (shipList.get(i).getInService() == true) {
            		System.out.println(shipList.get(i));
            	}
            }
        } else if (listType == "full") {
            System.out.println("\n\nSHIP LIST - Full");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("\t\t\t\t\tNumber of Rooms");
            System.out.print("SHIP NAME" + "\t    " + "Balcony" + "\t" + "OceanView" + "\t" + "Suite" + "\t" + "Interior" + "\t" + "inService");
            System.out.println("\n---------------------------------------------------------------------------------");
            for (Ship eachShip: shipList)
                eachShip.printShipData();
        } else
            System.out.println("\n\nError: List type not defined.");
    }

    public static void printCruiseList(String listType) {
    	// prints list of cruises from the cruiseList ArrayList
    	// list - prints names of cruises
    	// details - prints cruises and related ships, departures, destinations and returns
    	
        if (cruiseList.size() < 1) {
            System.out.println("\nThere are no cruises to print.");
            return;
        }
        if (listType == "list") {
            System.out.println("\n\nCRUISE LIST");
            for (int i=0; i < cruiseList.size(); i++) {
                System.out.println(cruiseList.get(i));
            }
        } else if (listType == "details") {
            System.out.println("\n\nCRUISE LIST - Details");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("                                      |----------------------PORTS-----------------------|");
            System.out.print("CRUISE NAME         SHIP NAME           DEPARTURE           DESTINATION         RETURN");
            System.out.println("\n-----------------------------------------------------------------------------------------");
            for (Cruise eachCruise: cruiseList)
                eachCruise.printCruiseDetails();
        } else
            System.out.println("\n\nError: List type not defined.");
    }

    public static void printPassengerList() {
    	// prints list of passengers from the passengerList ArrayList
    	// prints names and related cruises and room types
    	
        if (passengerList.size() < 1) {
            System.out.println("\nThere are no passengers to print.");
            return;
        }
        System.out.println("\n\nPASSENGER LIST");
        System.out.println("-----------------------------------------------------");
        System.out.print("PASSENGER NAME      CRUISE              ROOM TYPE");
        System.out.println("\n-----------------------------------------------------");
        for (Passenger eachPassenger: passengerList)
            eachPassenger.printPassenger();
    }

    // display text-based menu
    public static void displayMenu() {
        System.out.println("\n\n");
        System.out.println("\t\t\tLuxury Ocean Cruise Outings");
        System.out.println("\t\t\t\t\tSystem Menu\n");
        System.out.println("[1] Add Ship            [A] Print Ship Names");
        System.out.println("[2] Edit Ship           [B] Print Ship In Service List");
        System.out.println("[3] Add Cruise          [C] Print Ship Full List");
        System.out.println("[4] Edit Cruise         [D] Print Cruise List");
        System.out.println("[5] Add Passenger       [E] Print Cruise Details");
        System.out.println("[6] Edit Passenger      [F] Print Passenger List");
        System.out.println("[x] Exit System");
        System.out.println("\nEnter a menu selection: ");
    }

    // Add a New Ship
    public static void addShip() {
        Scanner newShipInput = new Scanner(System.in);
        System.out.println("Enter the new ship's name: ");
        String newShipName = newShipInput.nextLine();
        
        // ensure new ship name does not already exist
        for (Ship eachShip: shipList) {
            if (eachShip.getShipName().equalsIgnoreCase(newShipName)) {
                System.out.println("That ship is already in the system. Exiting to menu...");
                return; // quits addShip() method processing
            }
        }
        
        // get room balcony for ship
        System.out.println("Enter room balcony number: ");
        boolean check2 = true;
        String roomBalconyInput = newShipInput.nextLine();
        while (check2 == true) {
        	if (isANumber(roomBalconyInput) == true) {
        		check2 = false;
        	}
        	else {
        		System.out.println("Invalid input.  Enter a number.");
        		roomBalconyInput = newShipInput.nextLine();
        	};
        }
        int roomBalconyNum = Integer.parseInt(roomBalconyInput);
        
        // get room ocean view for ship
        System.out.println("Enter room ocean view number: ");
        boolean check3 = true;
        String roomOceanViewInput = newShipInput.nextLine();
        while (check3 == true) {
        	if (isANumber(roomOceanViewInput) == true) {
        		check3 = false;
        	}
        	else {
        		System.out.println("Invalid input.  Enter a number.");
        		roomOceanViewInput = newShipInput.nextLine();
        	};
        }
        int roomOceanViewNum = Integer.parseInt(roomOceanViewInput);
        
        // get room suite for ship
        System.out.println("Enter room suite number: ");
        boolean check4 = true;
        String roomSuiteInput = newShipInput.nextLine();
        while (check4 == true) {
        	if (isANumber(roomSuiteInput) == true) {
        		check4 = false;
        	}
        	else {
        		System.out.println("Invalid input.  Enter a number.");
        		roomSuiteInput = newShipInput.nextLine();
        	};
        }
        int roomSuiteNum = Integer.parseInt(roomSuiteInput);
        
        // get room interior for ship
        System.out.println("Enter room interior number: ");
        boolean check5 = true;
        String roomInteriorInput = newShipInput.nextLine();
        while (check5 == true) {
        	if (isANumber(roomInteriorInput) == true) {
        		check5 = false;
        	}
        	else {
        		System.out.println("Invalid input.  Enter a number.");
        		roomInteriorInput = newShipInput.nextLine();
        	};
        }
        int roomInteriorNum = Integer.parseInt(roomInteriorInput);
        
        // get service for ship
        System.out.println("Enter if in serice or not: ");
        boolean check6 = true;
        String inServiceInput = newShipInput.nextLine();
        while (check6 == true) {
        	if (inServiceInput.equalsIgnoreCase("true") || inServiceInput.equalsIgnoreCase("false")) {
        		check6 = false;
        	}
        	else {
        		System.out.println("Invalid input.  Enter true or false.");
        		inServiceInput = newShipInput.nextLine();
        	};
        }
        boolean inServiceCheck = Boolean.parseBoolean(inServiceInput);
        
		Ship newShip = new Ship(newShipName, roomBalconyNum, roomOceanViewNum, roomSuiteNum,
        						roomInteriorNum, inServiceCheck);
        shipList.add(newShip);
        System.out.println("Ship added.");
    }

    public static void editShip() {
    	// edit existing ships
    	
        Scanner editShipInput = new Scanner(System.in);
        System.out.println("Enter the ship's name or 'x' to exit.");
        printShipList("name");
        String editShipName = editShipInput.nextLine();
        // ensure ship exists
        if (!editShipName.equalsIgnoreCase("x")) {
	        while (editShipCheck == true) {
		        for (Ship eachShip: shipList) {
		            if (eachShip.getShipName().equalsIgnoreCase(editShipName)) {
		                System.out.println(editShipName + " selected.");
		                editShipCheck = false;
		            }
		        }
		        if (editShipCheck == true) {
			        System.out.println("Invalid ship selected.\nSelect a valid ship or 'x' to exit.");
			        printShipList("name");
			        editShipName = editShipInput.nextLine();
			        if (editShipName.equalsIgnoreCase("x")) {
			        	return;
			        }
		        }
	        }
        }
        else {
        	return;
        }
        editShipCheck = true;
        
        System.out.println("Enter ship's new name or 'x' to exit.");
        String newShipName = editShipInput.nextLine();
        if (newShipName.equalsIgnoreCase("x")) {
        	return;
        }
        
        System.out.println("Enter ship's new room balcony or 'x' to exit.");
        String newRoomBalcony = editShipInput.nextLine();
        boolean balconyCheck = true;
        if (!newRoomBalcony.equalsIgnoreCase("x")) {
	        while (balconyCheck == true) {
	        	if (isANumber(newRoomBalcony) == true) {
	        		balconyCheck = false;
	        	}
	        	else {
	        		System.out.println("Invalid input. Enter a number or 'x' to exit.");
	        		newRoomBalcony = editShipInput.nextLine();
	        		if (newRoomBalcony.equalsIgnoreCase("x")) {
	        			return;
	        		}
	        	}
	        }
        }
        else {
        	return;
        }
        int newRoomBalconyNum = Integer.parseInt(newRoomBalcony);
        balconyCheck = true;
        
        System.out.println("Enter ship's new room ocean view or 'x' to exit.");
        String newRoomOceanView = editShipInput.nextLine();
        boolean oceanViewCheck = true;
        if (!newRoomOceanView.equalsIgnoreCase("x")) {
	        while (oceanViewCheck == true) {
	        	if (isANumber(newRoomOceanView) == true) {
	        		oceanViewCheck = false;
	        	}
	        	else {
	        		System.out.println("Invalid input. Enter a number or 'x' to exit.");
	        		newRoomOceanView = editShipInput.nextLine();
	        		if (newRoomOceanView.equalsIgnoreCase("x")) {
	        			return;
	        		}
	        	}
	        }
        }
        else {
        	return;
        }
        int newRoomOceanViewNum = Integer.parseInt(newRoomOceanView);
        oceanViewCheck = true;
        
        System.out.println("Enter ship's new room suite or 'x' to exit.");
        String newRoomSuite = editShipInput.nextLine();
        boolean suiteCheck = true;
        if (!newRoomSuite.equalsIgnoreCase("x")) {
	        while (suiteCheck == true) {
	        	if (isANumber(newRoomSuite) == true) {
	        		suiteCheck = false;
	        	}
	        	else {
	        		System.out.println("Invalid input. Enter a number or 'x' to exit.");
	        		newRoomSuite = editShipInput.nextLine();
	        		if (newRoomSuite.equalsIgnoreCase("x")) {
	        			return;
	        		}
	        	}
	        }
        }
        else {
        	return;
        }
        int newRoomSuiteNum = Integer.parseInt(newRoomSuite);
        suiteCheck = true;
        
        System.out.println("Enter ship's new room interior or 'x' to exit.");
        String newRoomInterior = editShipInput.nextLine();
        boolean interiorCheck = true;
        if (!newRoomInterior.equalsIgnoreCase("x")) {
	        while (interiorCheck == true) {
	        	if (isANumber(newRoomInterior) == true) {
	        		interiorCheck = false;
	        	}
	        	else {
	        		System.out.println("Invalid input. Enter a number or 'x' to exit.");
	        		newRoomInterior = editShipInput.nextLine();
	        		if (newRoomInterior.equalsIgnoreCase("x")) {
	        			return;
	        		}
	        	}
	        }
        }
        else {
        	return;
        }
        int newRoomInteriorNum = Integer.parseInt(newRoomInterior);
        interiorCheck = true;
        
        System.out.println("Enter ship's service status or 'x' to exit.");
        String newInService = editShipInput.nextLine();
        boolean inServiceCheck = true;
        if (!newInService.equalsIgnoreCase("x")) {
	        while (inServiceCheck == true) {
	        	if (newInService.equalsIgnoreCase("true") || newInService.equalsIgnoreCase("false")) {
	        		inServiceCheck = false;
	        	}
	        	else {
	        		System.out.println("Invalid input. Enter true, false or 'x' to exit.");
	        		newInService = editShipInput.nextLine();
	        		if (newInService.equalsIgnoreCase("x")) {
	        			return;
	        		}
	        	}
	        }
        }
        else {
        	return;
        }
        boolean newInServiceVal = Boolean.parseBoolean(newInService);
        inServiceCheck = true;
        
        for (Ship eachShip: shipList) {
            if (eachShip.getShipName().equalsIgnoreCase(editShipName)) {
            	eachShip.setShipName(newShipName);
            	eachShip.setRoomBalcony(newRoomBalconyNum);
            	eachShip.setRoomOceanView(newRoomOceanViewNum);
            	eachShip.setRoomSuite(newRoomSuiteNum);
            	eachShip.setRoomInterior(newRoomInteriorNum);
            	eachShip.setInService(newInServiceVal);
            }
        }
        
        System.out.println("Ship updated.");

    }

    // Add a New Cruise
    public static void addCruise() {
        Scanner newCruiseInput = new Scanner(System.in);
        System.out.println("Enter the new cruise's name: ");
        printCruiseList("list");
        String newCruiseName = newCruiseInput.nextLine();
        // ensure new cruise name does not already exist
        for (Cruise eachCruise: cruiseList) {
            if (eachCruise.getCruiseName().equalsIgnoreCase(newCruiseName)) {
                System.out.println("That cruise is already in the system.\nPlease add a new cruise.");
                printCruiseList("list");
                newCruiseName = newCruiseInput.nextLine();
            }
        }
        // get cruise ship name for cruise
        
        System.out.println("Select cruise ship: ");
        printShipList("name");
        String cruiseShipNameInput = newCruiseInput.nextLine();        
        while (addCruiseShipCheck == true) {
        	for (Ship eachShip: shipList) {
            	if (eachShip.getShipName().equalsIgnoreCase(cruiseShipNameInput)) {
                    addCruiseShipCheck = false;
                    break;
            	}
        	}
        	if (addCruiseShipCheck == true) {
        		System.out.println("Selected ship not in list.\nPlease select a valid ship.");
            	printShipList("name");
            	cruiseShipNameInput = newCruiseInput.nextLine();
        	}
        	else {
        		addCruiseShipCheck = false;
        	}

        }
        
        // get departure port for cruise
        System.out.println("Enter departure port: ");
        String departurePortInput = newCruiseInput.nextLine();
        // get destination for cruise
        System.out.println("Enter destination: ");
        String destinationInput = newCruiseInput.nextLine();
        // get return port for cruise
        System.out.println("Enter return port: ");
        String returnPortInput = newCruiseInput.nextLine();
        
        Cruise newCruise = new Cruise(newCruiseName, cruiseShipNameInput, departurePortInput, destinationInput,
        						    returnPortInput);
        cruiseList.add(newCruise);  
    }

    // Edit an existing cruise
    public static void editCruise() {

        Scanner editCruiseInput = new Scanner(System.in);
        System.out.println("Enter the cruise's name or 'x' to exit.");
        printCruiseList("list");
        String editCruiseName = editCruiseInput.nextLine();
        // ensure cruise exists
        if (!editCruiseName.equalsIgnoreCase("x")) {
	        while (editCruiseCheck == true) {
		        for (Cruise eachCruise: cruiseList) {
		            if (eachCruise.getCruiseName().equalsIgnoreCase(editCruiseName)) {
		            	System.out.println(editCruiseName + " selected.");
		            	editCruiseCheck = false;
		            }
		        }
	            if (editCruiseCheck == true) {
	            	System.out.println("Cruise does not exist.\nPlease select a valid cruise or 'x' to exit.");
	                printCruiseList("list");
	                editCruiseName = editCruiseInput.nextLine();
	                if (editCruiseName.equalsIgnoreCase("x")) {
	                	return;
	                }
	            }
	        }
        }
        else {
        	return;
        }
        editCruiseCheck = true;
        
        System.out.println("Select new cruise ship or 'x' to exit.");
        printShipList("name");
        String editCruiseShipNameInput = editCruiseInput.nextLine();
        boolean editCruiseShipCheck = true;
        // ensure ship exists
        if (!editCruiseShipNameInput.equalsIgnoreCase("x")) {
	        while (editCruiseShipCheck == true) {
	        	for (Ship eachShip: shipList) {
	            	if (eachShip.getShipName().equalsIgnoreCase(editCruiseShipNameInput)) {
	                    editCruiseShipCheck = false;
	                    break;
	            	}
	        	}
	        	if (editCruiseShipCheck == true) {
	        		System.out.println("Ship does not exist.\nPlease select a valid ship or 'x' to exit.");
	            	printShipList("name");
	            	editCruiseShipNameInput = editCruiseInput.nextLine();
	            	if (editCruiseShipNameInput.equalsIgnoreCase("x")) {
	            		return;
	            	}
	        	}
	        }
        }
        else {
        	return;
        }
        editCruiseShipCheck = true;
        
        // get new departure port for cruise
        System.out.println("Enter departure port or 'x' to exit.");
        String editDeparturePortInput = editCruiseInput.nextLine();
        if (editDeparturePortInput.equalsIgnoreCase("x")) {
        	return;
        }
        
        // get new destination for cruise
        System.out.println("Enter destination or 'x' to exit.");
        String editDestinationInput = editCruiseInput.nextLine();
        if (editDestinationInput.equalsIgnoreCase("x")) {
        	return;
        }
        
        // get new return port for cruise
        System.out.println("Enter return port or 'x' to exit.");
        String editReturnPortInput = editCruiseInput.nextLine();
        if (editReturnPortInput.equalsIgnoreCase("x")) {
        	return;
        }

        for (Cruise eachCruise: cruiseList) {
            if (eachCruise.getCruiseName().equalsIgnoreCase(editCruiseName)) {
            	eachCruise.setCruiseShipName(editCruiseShipNameInput);
            	eachCruise.setDeparturePort(editDeparturePortInput);
            	eachCruise.setDestination(editDestinationInput);
            	eachCruise.setReturnPort(editReturnPortInput);
            }
        }
        
        System.out.println("Cruise updated.");
        printCruiseList("details");
        
    }

    // Add a New Passenger
    public static void addPassenger() {

        Scanner newPassengerInput = new Scanner(System.in);
        printPassengerList();
        System.out.println("\nEnter the new passenger's name: ");
        String newPassengerName = newPassengerInput.nextLine();

        // ensure new passenger name does not already exist
        while (addPassengerCheck == true) {
        	for (Passenger eachPassenger: passengerList) {
        		if (eachPassenger.getPassengerName().equalsIgnoreCase(newPassengerName)) {
        			addPassengerCheck = false;
        			break;
        			}
        	}
        	if (addPassengerCheck == false) {
        	    System.out.println("That passenger is already in the system.\nPlease add a new passenger.");
        		addPassengerCheck = true;
        	    printPassengerList();
        		newPassengerName = newPassengerInput.nextLine();
        	}
        	else {
        		addPassengerCheck = false;
        	}
        }


        // get cruise name for passenger
        System.out.println("Enter cruise name: ");
        printCruiseList("list");
        String newCruiseName = newPassengerInput.nextLine();

        // ensure cruise exists
        while (addPassengerCruiseCheck == true) {
	        for (Cruise eachCruise: cruiseList) {
	            if (eachCruise.getCruiseName().equalsIgnoreCase(newCruiseName)) {
	            	addPassengerCruiseCheck = false;
	            	break;
	            }
	        }
	        if (addPassengerCruiseCheck == true) {
	        	System.out.println("Cruise does not exist.\nPlease select an existing cruise.");
	        	printCruiseList("list");
	        	newCruiseName = newPassengerInput.nextLine();
	        }
        }
        
        // get room type
        System.out.println("Enter Room Type (BAL, OV, STE or INT) : ");
        String room = newPassengerInput.nextLine();
        // validate room type
        while (addPassengerRoomCheck == true) {
	        if ((room.equalsIgnoreCase("BAL")) || (room.equalsIgnoreCase("OV")) ||
	                (room.equalsIgnoreCase("STE")) || (room.equalsIgnoreCase("INT"))) {
	            // validation passed - add passenger
	            Passenger newPassenger = new Passenger(newPassengerName, newCruiseName, room.toUpperCase());
	            passengerList.add(newPassenger);
	            addPassengerRoomCheck = false;
	            System.out.println("Passenger added.");
	            printPassengerList();
	        } else {
	            System.out.println("Invalid input. Enter a valid room type (BAL, OV, STE or INT) : ");
	            room = newPassengerInput.nextLine();
	        }
        }
    }

    // Edit an existing passenger FIXME
    public static void editPassenger() {

    	Scanner editPassengerInput = new Scanner(System.in);
        printPassengerList();
        System.out.println("\nEnter the passenger's name or 'x' to exit.");
        String editPassengerName = editPassengerInput.nextLine();

        // ensure new passenger name does not already exist
        boolean editPassengerCheck = true;
        if (!editPassengerName.equalsIgnoreCase("x")) {
	        while (editPassengerCheck == true) {
	        	for (Passenger eachPassenger: passengerList) {
	        		if (eachPassenger.getPassengerName().equalsIgnoreCase(editPassengerName)) {
	        			editPassengerCheck = false;
	        			break;
	        			}
	        	}
	        	if (editPassengerCheck == true) {
	        	    System.out.println("That passenger was not found.\nPlease enter an existing passenger or 'x' to exit.");
	        	    printPassengerList();
	        		editPassengerName = editPassengerInput.nextLine();
	        		if (editPassengerName.equalsIgnoreCase("x")) {
	        			return;
	        		}
	        	}
	        }
        }
        else {
        	return;
        }
        editPassengerCheck = true;

        // get cruise name for passenger
        System.out.println("Enter cruise name or 'x' to exit.");
        printCruiseList("list");
        String editCruiseName = editPassengerInput.nextLine();

        // ensure cruise exists
        boolean editPassengerCruiseCheck = true;
        if (!editCruiseName.equalsIgnoreCase("x")) {
	        while (editPassengerCruiseCheck == true) {
		        for (Cruise eachCruise: cruiseList) {
		            if (eachCruise.getCruiseName().equalsIgnoreCase(editCruiseName)) {
		            	editPassengerCruiseCheck = false;
		            	break;
		            }
		        }
		        if (editPassengerCruiseCheck == true) {
		        	System.out.println("Cruise does not exist.\nPlease select an existing cruise or 'x' to exit.");
		        	printCruiseList("list");
		        	editCruiseName = editPassengerInput.nextLine();
		        	if (editCruiseName.equalsIgnoreCase("x")) {
		        		return;
		        	}
		        }
	        }
        }
        else {
        	return;
        }
        editPassengerCruiseCheck = true;
        
        // get room type
        System.out.println("Enter Room Type (BAL, OV, STE or INT) or 'x' to exit.");
        String editRoom = editPassengerInput.nextLine();
        // validate room type
        boolean editPassengerRoomCheck = true;
        if (!editRoom.equalsIgnoreCase("x")) {
	        while (editPassengerRoomCheck == true) {
		        if ((editRoom.equalsIgnoreCase("BAL")) || (editRoom.equalsIgnoreCase("OV")) ||
		                (editRoom.equalsIgnoreCase("STE")) || (editRoom.equalsIgnoreCase("INT"))) {
		            // validation passed - add passenger
		        	if (editRoom.equalsIgnoreCase("BAL")) {
		        		editRoom = "Balcony";
		        	}
		        	else if (editRoom.equalsIgnoreCase("OV")) {
		        		editRoom = "OceanView";
		        	}
		        	else if (editRoom.equalsIgnoreCase("STE")) {
		        		editRoom = "Suite";
		        	}
		        	else if (editRoom.equalsIgnoreCase("INT")) {
		        		editRoom = "Interior";
		        	}
		        	for (Passenger eachPassenger: passengerList) {
		        		if (eachPassenger.getPassengerName().equalsIgnoreCase(editPassengerName)) {
		        			eachPassenger.setPassengerCruise(editCruiseName);
		        			eachPassenger.setPassengerRoomType(editRoom);
		        			editPassengerRoomCheck = false;
		        			break;
		        		}
		        	}
		        } else {
		            System.out.println("Invalid input. Enter a valid room type (BAL, OV, STE or INT) or 'x' to exit.");
		            editRoom = editPassengerInput.nextLine();
		            if (editRoom.equalsIgnoreCase("x")) {
		            	return;
		            }
		        }
	        }
        }
        else {
        	return;
        }
        editPassengerRoomCheck = true;
        printPassengerList();
    }

    // Method to check if input is a number
    public static boolean isANumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)) == false)
                return false;
        }
        return true;
    }

}
