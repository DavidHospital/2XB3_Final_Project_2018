package group15.a2xb3.stormware;//package group15.a2xb3.stormware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import group15.a2xb3.stormware.DisasterEvent;

/**
 Module for storing and accessing the parsed data
 */
public class Data {

    // Store data as a hashmap of String keys and List values
    // Key: A string representing the type of event
    // Value: A list of all events of that type
    // This makes for easy access of all data of a specific type
    private static final HashMap<String, ArrayList<DisasterEvent>> data;

    static {
        data = new HashMap<>();
    }

    // Clear all data
    public static void clear() {
        data.clear();
    }


    // Add a new event to the coresponging list in the hashmap
    public static void add(DisasterEvent disasterEvent) {
        if (!data.containsKey(disasterEvent.getType())) {
            data.put(disasterEvent.getType(), new ArrayList<DisasterEvent>());
        }
        data.get(disasterEvent.getType()).add(disasterEvent);
    }

    // Return the list at a given key
	public static ArrayList<DisasterEvent> getList(String type) {
		return data.get(type.trim().toUpperCase());
	}

	// Return a list of the keys
	public static Set<String> getTypes() {
        return data.keySet();
    }
	
}
