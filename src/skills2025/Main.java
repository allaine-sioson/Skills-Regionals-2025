package skills2025;


import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // lets the program run
        boolean run = true;
        // allows user input
        Scanner input = new Scanner(System.in);
        // counts the amount of IDs made
        int IDCounter = 0;

        // resources
        HashMap<Integer, Resource> resources = new HashMap<>();

        // gets the data from txt file
        IDCounter = getData(resources);


        // actual program, loops until quit
        do {
            System.out.println("\nHello! What would you like to do?");

            // menu
            System.out.println("[1] Add new resource");
            System.out.println("[2] View resources");
            System.out.println("[3] Search for resource");
            System.out.println("[4] Remove resource");
            System.out.println("[5] Check out / Return resource");
            System.out.println("[6] Top Resources");
            System.out.println("[7] HELP");
            System.out.println("[0] Quit");

            // user's choice
            System.out.print("Input choice: ");
            int choice = input.nextInt();

            // flush
            input.nextLine();

            // switch case for the choices
            switch (choice) {
                // if 0, quit
                case 0 -> run = false;
                // add new resource
                case 1 -> {
                    // makes the category
                    String category;
                    // allows user to input name
                    System.out.print("\nWhat is the name of your resource?: ");
                    String name = input.nextLine();

                    // gets category
                    do { 
                        System.out.print("What is the category of your resource?: ");
                        category = input.nextLine().toLowerCase();
                    } while (!category.equals("mobility") && !category.equals("vision") && !category.equals("hearing") && !category.equals("cognitive") && !category.equals("ergonomic"));
                    
                    // adds one to the counter
                    IDCounter++;
                    
                    // lets user know what the ID of the resource is
                    System.out.println("The ID for this resource is: " + IDCounter);

                    // adds resource to list of resources
                    resources.put(IDCounter, new Resource(name, category));
                    // updates data
                    updateData(resources);
                }
                // view resources
                case 2 -> {
                    System.out.println("\nView by?");

                    // menu
                    System.out.println("[1] All");
                    System.out.println("[2] Category");
                    System.out.println("[0] Quit");

                    // user choice
                    System.out.print("Input choice: ");
                    int viewChoice = input.nextInt();

                    // flush
                    input.nextLine();
                    System.out.print("\n");

                    // switch for choices
                    // all resources
                    if (viewChoice == 1) {
                        for (Resource resource : resources.values()) {
                            System.out.println(resource);
                        }
                    }
                    // by category
                    else if (viewChoice == 2) {
                        System.out.println("Mobility:");
                        for (Resource resource : resources.values()) {
                            if (resource.getCategory().equals("mobility")) {
                                System.out.println(resource);
                            }
                        }
                        System.out.println("\nVision:");
                        for (Resource resource : resources.values()) {
                            if (resource.getCategory().equals("vision")) {
                                System.out.println(resource);
                            }
                        }
                        System.out.println("\nHearing:");
                        for (Resource resource : resources.values()) {
                            if (resource.getCategory().equals("hearing")) {
                                System.out.println(resource);
                            }
                        }
                        System.out.println("\nCognitive:");
                        for (Resource resource : resources.values()) {
                            if (resource.getCategory().equals("cognitive")) {
                                System.out.println(resource);
                            }
                        }
                        System.out.println("\nErgonomic:");
                        for (Resource resource : resources.values()) {
                            if (resource.getCategory().equals("ergonomic")) {
                                System.out.println(resource);
                            }
                        }
                    }
                    }
                // search resource
                case 3 -> {
                    System.out.println("\nSearch by?");

                    // menu
                    System.out.println("[1] Name");
                    System.out.println("[2] Category");
                    System.out.println("[3] Availability Status");
                    System.out.println("[0] Quit");

                    System.out.print("Input choice: ");
                    int searchChoice = input.nextInt();

                    input.nextLine();
                    System.out.print("\n");

                    // search choices
                    switch (searchChoice) {
                        // by name
                        case 1 -> {
                            System.out.print("Input name: ");
                            String name = input.nextLine();
                            // go through hashmap, find the resource
                            for (Resource resource : resources.values()) {
                                if (resource.getName().equals(name)) {
                                    System.out.println(resource);
                                }
                            }
                        }

                        // by category
                        case 2 -> {
                            System.out.print("Input category: ");
                            String category = input.nextLine();
                            for (Resource resource : resources.values()) {
                                // go through hashmap, find the resource
                                if (resource.getCategory().equals(category)) {
                                    System.out.println(resource);
                                }
                            }
                        }
                        // by status
                        case 3 -> {
                            System.out.println("A for Available / N for NOT");
                            System.out.print("Input availability status: ");
                            String availabilityStatus = input.nextLine();
                            // go through hashmap, find the resource
                            for (Resource resource : resources.values()) {
                                // if checked out
                                if (resource.toString().contains("N") && availabilityStatus.equals("N")) {
                                    System.out.println(resource);
                                // if available
                                } else if (!resource.toString().contains("N") && availabilityStatus.equals("A")){
                                    System.out.println(resource);
                                }
                            }
                        }
                        // default
                        default -> throw new AssertionError();
                    }

                }
                // remove the resource
                case 4 -> removeID(input, resources);
                // check out the resource
                case 5 -> checkOutResource(input, resources);
                // top per category
                case 6 -> {
                    // mobility top resource
                    System.out.print("Mobility:");
                    int maxMobility = 0;
                    Resource topMobilityResource = new Resource("", "", true, 0);
                        // go through hashmap, find the resource
                        for (Resource resource : resources.values()) {
                            if (resource.getCategory().equals("mobility")) {
                                if (resource.getTimesCheckedOut() >= maxMobility) {
                                    maxMobility = resource.getTimesCheckedOut();
                                    topMobilityResource = resource;
                                }
        
                            }
                        }
                    System.out.println(topMobilityResource);
                        
                    // vision top resource
                    System.out.print("\nVision:");
                    int maxVision = 0;
                    Resource topVisionResource = new Resource("", "", true, 0);
                        // go through hashmap, find the resource
                        for (Resource resource : resources.values()) {
                            if (resource.getCategory().equals("vision")) {
                                if (resource.getTimesCheckedOut() >= maxVision) {
                                    maxVision = resource.getTimesCheckedOut();
                                    topVisionResource = resource;
                                }
        
                            }
                        }
                    System.out.println(topVisionResource);
                        
                    // hearing top resource
                    System.out.println("\nHearing:");
                    int maxHearing = 0;
                    Resource topHearingResource = new Resource("", "", true, 0);
                    // go through hashmap, find the resource
                        for (Resource resource : resources.values()) {
                            if (resource.getCategory().equals("hearing")) {
                                if (resource.getTimesCheckedOut() >= maxHearing) {
                                    maxHearing = resource.getTimesCheckedOut();
                                    topHearingResource = resource;
                                }
        
                            }
                        }
                    System.out.println(topHearingResource);

                    // cognitive top resource
                    System.out.println("\nCognitive:");
                    int maxCognitive = 0;
                    Resource topCognitiveResource = new Resource("", "", true, 0);
                    // go through hashmap, find the resource
                    for (Resource resource : resources.values()) {
                        if (resource.getCategory().equals("cognitive")) {
                            if (resource.getTimesCheckedOut() >= maxCognitive) {
                                maxCognitive = resource.getTimesCheckedOut();
                                topCognitiveResource = resource;
                            }
    
                        }
                    }
                    
                    System.out.println(topCognitiveResource);

                    // ergonomic top resource
                    System.out.println("\nErgonomic:");
                    int maxErgonomic = 0;
                    Resource topErgonomicResource = new Resource("", "", true, 0);
                    // go through hashmap, find the resource
                    for (Resource resource : resources.values()) {
                        if (resource.getCategory().equals("ergonomic")) {
                            if (resource.getTimesCheckedOut() >= maxErgonomic) {
                                maxErgonomic = resource.getTimesCheckedOut();
                                topErgonomicResource = resource;
                            }
    
                        }
                    }
                    System.out.println(topErgonomicResource);
                }
                case 7 -> {
                    // help info per option
                    System.out.println("\nTo use the program:");
                    System.out.println("[1] Add new resources with their name and category.");
                    System.out.println("[2] View resources by category or unordered.");
                    System.out.println("[3] Search for resources by name, category or status.");
                    System.out.println("[4] Remove resources using their ID.");
                    System.out.println("[5] Check out resource using their ID.");
                    System.out.println("[6] View top Resources per category.");
                }

            }
        // while the program is running, loop!
        } while (run);
    }

    /**
     * Allows the user to remove a resource using its ID 
     * @param input the scanner to allow user input
     * @param resources the resources that are given
     */
    public static void removeID(Scanner input, HashMap<Integer, Resource> resources) {
        // ask user for the ID
        System.out.print("\nWhat is the ID of the resource you'd like to remove?: ");
        int ID = input.nextInt();
        
        // looks for the ID
        for (int resourceID : resources.keySet()) {
            // if it exists
            if (resourceID == ID) {
                // show the user the resource
                System.out.println(resources.get(ID));
                System.out.println("Are you sure, you'd like to remove this resource?");
                
                System.out.println("[1] Yes");
                System.out.println("[0] No");

                // asks the user if they want to delete
                System.out.print("Input choice: ");
                int choice = input.nextInt();

                // confirm choice
                switch (choice) {
                    case 0 -> System.out.println("Action Cancelled.");
                    case 1 -> {
                        resources.remove(ID);
                        System.out.print("Successfully removed resource!");
                    }
                }
                // update
                updateData(resources);
                break;
            } 
        }
    }

    /**
     * Allows the user to checkout a resource using its ID 
     * @param input the scanner to allow user input
     * @param resources the resources that are given
     */
    public static void checkOutResource(Scanner input, HashMap<Integer, Resource> resources) {
        // asks for the ID of the resource
        System.out.print("\nWhat is the ID of the resource?: ");
        int ID = input.nextInt();
        
        // loops through the IDs
        for (int resourceID : resources.keySet()) {
            // if it exists
            if (resourceID == ID) {
                // shows user the resource
                System.out.println(resources.get(ID));
                System.out.println("Would you like to:");
                
                System.out.println("[1] Checkout");
                System.out.println("[0] Return");

                System.out.print("Input choice: ");
                int choice = input.nextInt();

                switch (choice) {
                    // chooses to return the resource
                    case 0 -> {
                        Resource resource = resources.get(ID);
                        resource.setAvailabilityStatus(true);
                        System.out.print("Successfully returned resource!");
                    }
                    // chooses to check out the resource
                    case 1 -> {
                        Resource resource = resources.get(ID);
                        resource.setAvailabilityStatus(false);
                        System.out.print("Successfully checked out resource!");
                    }
                }
                // update
                updateData(resources);
                break;
            } 
        }
    }

    /**
     * update the data in the data.txt file
     * @param resources resources data that will be used to update the file
     */
    public static void updateData(HashMap<Integer, Resource> resources) {
        File dataFile = new File("src/skills2025/data.txt");
        dataFile.delete();

        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error.");
        }

        try {
            try (FileWriter writer = new FileWriter("src/skills2025/data.txt")) {
                for (int ID : resources.keySet()) {
                    writer.write(ID + "," + resources.get(ID) + "\n");
                }
            }
          } catch (IOException e) {
            System.out.println("An error occurred.");
          }
    }
    /**
     * get the data from the data.txt file (READ) and return the ID count
     * @param resources place to put all resources from the data file
     * @return ID count
     */
    public static int getData(HashMap<Integer, Resource> resources) {
        int lastID = 0;
        try {
            try (Scanner reader = new Scanner(new File("src/skills2025/data.txt"))) {
                while (reader.hasNextLine()) {
                    String[] data = reader.nextLine().split(",");
                    int ID = Integer.parseInt(data[0]);
                    String[] resourceData = data[1].split(" ");
                    String[] resourceData2 = data[1].split("\\(");
                    
                    String name = resourceData[0].replace("\"", "");
                    String category = resourceData[1].replace("(", "").replace("):", "");
                    int timesCheckedOut = Integer.parseInt(resourceData2[2].replace(" time", ""));
                    boolean availabilityStatus;
                    
                    // checks for the availability
                    if (data[1].contains("NOT")) {
                        availabilityStatus = false;
                    } else {
                        availabilityStatus = true;
                    }
                    
                    // outs resources in the resources hashmap
                    resources.put(ID, new Resource(name, category, availabilityStatus, timesCheckedOut));
                    lastID = ID;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        // return the ID count
        return lastID;
    }
}
