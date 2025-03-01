package Service;

import Model.Facility;
import Model.House;
import Model.Room;
import Model.Villa;
import Repository.FacilityRepository;
import View.Validation;

import java.util.*;

public class FacilityService implements IFacilityService {
    private final FacilityRepository faciRepository;
    private LinkedHashMap<Facility, Integer> currentFacilities;
    private final String errMsg;

    public FacilityService() {
        faciRepository = new FacilityRepository();
        currentFacilities = faciRepository.readFile();
        errMsg = "-> Invalid Input, Try Again";
    }

    public LinkedHashMap<Facility, Integer> getCurrentFacilities() {
        return currentFacilities;
    }

    public void setCurrentFacilities(LinkedHashMap<Facility, Integer> currentFacilities) {
        this.currentFacilities = currentFacilities;
    }

    //hiển thị
    @Override
    public void display() {
        try {
            displayVillas();
            displayHouses();
            displayRooms();
        } catch (Exception e) {
            System.out.println("-> Error displaying facilities: " + e.getMessage());
        }
    }

    
    //Quản lý bảo trì
    public void displayMaintenance() {
        System.out.println("+-----------------+----------------------+-----------------+----------------------+");
        System.out.printf("| %-15s | %-20s | %-15s | %-20s |%n", "Facility ID", "Facility Name", "Usage Count", "Status");
        System.out.println("+-----------------+----------------------+-----------------+----------------------+");

        for (Map.Entry<Facility, Integer> entry : currentFacilities.entrySet()) {
            Facility facility = entry.getKey();
            Integer usageCount = entry.getValue();
            String usageStatus = (usageCount > 5) ? "Need Maintenance" : "Good Condition";

            System.out.printf("| %-15s | %-20s | %-15d | %-20s |%n",
                    facility.getFacilityID(),
                    facility.getFacilityName(),
                    usageCount,
                    usageStatus);
        }
        System.out.println("+-----------------+----------------------+-----------------+----------------------+");
    }
// hiển thị danh sách vila
    private void displayVillas() {
        try {
            System.out.println("+-----------------+----------------------+-----------------+-----------------+--------------+------------------+--------------------+-------------------+-----------------+");
            System.out.printf("| %-15s | %-20s | %-15s | %-15s | %-12s | %-15s | %-15s | %-15s | %-15s |%n",
                    "Villa ID", "Name", "Area", "Price", "Max People", "Rental Type", "Standard", "Number of Floors", "Pool Area");
            System.out.println("+-----------------+----------------------+-----------------+-----------------+--------------+------------------+--------------------+-------------------+-----------------+");

            for (Map.Entry<Facility, Integer> entry : currentFacilities.entrySet()) {
                Facility facility = entry.getKey();
                if (facility instanceof Villa villa) {
                    System.out.printf("| %-15s | %-20s | %-15.2f | %-15.2f | %-12d | %-15s | %-15s | %-15d | %-15.2f |%n",
                            villa.getFacilityID(), villa.getFacilityName(), villa.getArea(), villa.getRentalCost(),
                            villa.getMaxPeople(), villa.getRentalType(), villa.getRoomStandard(),
                            villa.getNumberOfFloor(), villa.getPoolArea());
                }
            }

            System.out.println("+-----------------+----------------------+-----------------+-----------------+--------------+------------------+--------------------+-------------------+-----------------+");
        } catch (Exception e) {
            System.out.println("-> Error displaying Villas: " + e.getMessage());
        }
    }
// hiển thị danh sách house
    private void displayHouses() {
        try {
            System.out.println("+-----------------+----------------------+-----------------+-----------------+--------------+------------------+-------------------+--------------------+");
            System.out.printf("| %-15s | %-20s | %-15s | %-15s | %-12s | %-15s | %-15s | %-15s |%n",
                    "House ID", "Name", "Area", "Price", "Max People", "Rental Type", "Standard", "Number of Floors");
            System.out.println("+-----------------+----------------------+-----------------+-----------------+--------------+------------------+-------------------+--------------------+");

            for (Map.Entry<Facility, Integer> entry : currentFacilities.entrySet()) {
                Facility facility = entry.getKey();
                if (facility instanceof House house) {
                    System.out.printf("| %-15s | %-20s | %-15.2f | %-15.2f | %-12d | %-15s | %-15s | %-15d |%n",
                            house.getFacilityID(), house.getFacilityName(), house.getArea(), house.getRentalCost(),
                            house.getMaxPeople(), house.getRentalType(), house.getRoomStandard(), house.getNumberOfFloor());
                }
            }

            System.out.println("+-----------------+----------------------+-----------------+-----------------+--------------+------------------+-------------------+--------------------+");
        } catch (Exception e) {
            System.out.println("-> Error displaying Houses: " + e.getMessage());
        }
    }

    // hiển thị danh sách room
    private void displayRooms() {
        try {
            System.out.println("+-----------------+----------------------+-----------------+-----------------+--------------+------------------+-------------------+");
            System.out.printf("| %-15s | %-20s | %-15s | %-15s | %-12s | %-15s | %-20s |%n",
                    "Room ID", "Name", "Area", "Price", "Max People", "Rental Type", "Free Service");
            System.out.println("+-----------------+----------------------+-----------------+-----------------+--------------+------------------+-------------------+");

            for (Map.Entry<Facility, Integer> entry : currentFacilities.entrySet()) {
                Facility facility = entry.getKey();
                if (facility instanceof Room room) {
                    System.out.printf("| %-15s | %-20s | %-15.2f | %-15.2f | %-12d | %-15s | %-20s |%n",
                            room.getFacilityID(), room.getFacilityName(), room.getArea(), room.getRentalCost(),
                            room.getMaxPeople(), room.getRentalType(), room.getFreeService());
                }
            }

            System.out.println("+-----------------+----------------------+-----------------+-----------------+--------------+------------------+-------------------+");
        } catch (Exception e) {
            System.out.println("-> Error displaying Rooms: " + e.getMessage());
        }
    }

    @Override
    public void add(Facility entity) {
        try {
            currentFacilities.put(entity, 0);
            System.out.println("-> Add Facility Successfully!!");
        } catch (Exception e) {
            System.out.println("-> Error adding facility: " + e.getMessage());
        }
    }

    public void addVilla() {
        displayVillas();
        try {
            addFacility("Villa", Villa.class);
        } catch (Exception e) {
            System.out.println("-> Error adding villa: " + e.getMessage());
        }
    }

    public void addHouse() {
        displayHouses();
        try {
            addFacility("House", House.class);
            display();
        } catch (Exception e) {
            System.out.println("-> Error adding house: " + e.getMessage());
        }
    }

    public void addRoom() {
        displayRooms();
        try {
            addFacility("Room", Room.class);
            display();
        } catch (Exception e) {
            System.out.println("-> Error adding room: " + e.getMessage());
        }
    }

    public void addFacility(String facilityType, Class<? extends Facility> facilityClass) {
        try {
            String ID;
            do {
                ID = Validation.validateID(facilityType + " ID", "ID Must Follow SVxx-xxxx", "SV(VL|HO|RO)-\\d{4}");
                if (findByID(ID) != null) {
                    System.out.println("-> ID Already Exist, Try New One");
                }
            } while (findByID(ID) != null);

            String facilityName = Validation.validateStringInput(facilityType + " Name", errMsg);
            double area = Validation.validateDouble("Area", errMsg, 30);
            double rentalCost = Validation.validateDouble("Rental Cost", errMsg, 0);
            int maxPeople;
            do {
                maxPeople = Validation.validateInteger("Max People", errMsg, 0);
            } while (maxPeople > 20);

            String rentalType;
            do {
                rentalType = Validation.validateStringInput("Rental Type", errMsg);
            } while (!rentalType.equalsIgnoreCase("day") && !rentalType.equalsIgnoreCase("week") && !rentalType.equalsIgnoreCase("month"));

            Facility newFacility = null;

            if (facilityClass == Villa.class) {
                String roomStandard = Validation.validateStringInput("Room Standard", errMsg);
                double poolArea = Validation.validateDouble("Pool Area", errMsg, 30);
                int numberOfFloor = Validation.validateInteger("Number Of Floor", errMsg, 0);
                newFacility = new Villa(ID, facilityName, area, rentalCost, maxPeople, rentalType, roomStandard, poolArea, numberOfFloor);
            } else if (facilityClass == House.class) {
                String roomStandard = Validation.validateStringInput("Room Standard", errMsg);
                int numberOfFloor = Validation.validateInteger("Number Of Floor", errMsg, 0);
                newFacility = new House(ID, facilityName, area, rentalCost, maxPeople, rentalType, roomStandard, numberOfFloor);
            } else if (facilityClass == Room.class) {
                String freeService = Validation.validateStringInput("Free Service", errMsg);
                newFacility = new Room(ID, facilityName, area, rentalCost, maxPeople, rentalType, freeService);
            }

            if (newFacility != null) {
                add(newFacility);
                incrementUsage(newFacility);
            }

            while (Validation.validateStringInput("-> Do You Want To Continue (Y/N)", "Invalid Input, Try Again").equalsIgnoreCase("Y"));

        } catch (Exception e) {
            System.out.println("-> Error while adding facility: " + e.getMessage());
        }

        if (Validation.validateStringInput("-> Do you want to save changes to file (Y/N): ", errMsg).equalsIgnoreCase("Y")) {
            save();
        }
    }


    @Override
    public void save() {
        try {
            faciRepository.writeFile(currentFacilities);
            System.out.println("-> Facilities saved to file successfully !!!");
        } catch (Exception e) {
            System.out.println("-> Error saving facilities to file: " + e.getMessage());
        }
    }

    @Override
    public void update(Facility f) {
    }

    @Override
    public Facility findByID(String ID) {
        try {
            for (Facility facility : currentFacilities.keySet()) {
                if (facility.getFacilityID().equalsIgnoreCase(ID)) {
                    return facility;
                }
            }
        } catch (Exception e) {
            System.out.println("-> Error finding facility by ID: " + e.getMessage());
        }
        return null;
    }

    public String getFacilityID() {
        String faciID;
        try {
            do {
                faciID = Validation.validateID("Facility ID", "ID Must Follow SVxx-xxxx", "SV(VL|HO|RO)-\\d{4}");
                Facility facility = findByID(faciID);
                if (facility != null) {
                    return faciID;
                } else {
                    System.out.println("-> ID Not Found, Try Again!");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("-> Error While Getting Facility ID: " + e.getMessage());
            return null;
        }
    }

    public void incrementUsage(Facility facility) {
        try {
            if (currentFacilities.containsKey(facility)) {
                currentFacilities.put(facility, currentFacilities.get(facility) + 1);
            }
        } catch (Exception e) {
            throw new RuntimeException("-> Error While Increasing Usage Count - " + e.getMessage());
        }
    }
}
