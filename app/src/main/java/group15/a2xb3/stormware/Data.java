//package group15.a2xb3.stormware;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by David on 2018-03-05.
 */

public class Data {

    private static final HashMap<String, ArrayList<DisasterEvent>> data;

    static {
        data = new HashMap<>();
    }

    public static void clear() {
        data.clear();
    }

    public static void add(DisasterEvent disasterEvent) {
        if (!data.containsKey(disasterEvent.getType())) {
            data.put(disasterEvent.getType(), new ArrayList<DisasterEvent>());
        }
        data.get(disasterEvent.getType()).add(disasterEvent);
    }
	
	public static ArrayList<DisasterEvent> getList(String type) {
		return data.get(type);
	}
	
}
