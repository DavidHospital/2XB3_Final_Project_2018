package group15.a2xb3.stormware;

import com.google.android.gms.maps.model.LatLng;

/*
Class for DisasterEvent objects.
Stores start and end location, time, and type for a single event
 */
public class DisasterEvent implements Comparable {

    private final String type;
    private final LatLng location1;
    private final LatLng location2;
    private final int year;
	private final int month;
	private final int day;


	// Basic constructor
    public DisasterEvent(String type, LatLng location1, LatLng location2, int year, int month, int day) {
        this.type = type;
        this.location1 = location1;
        this.location2 = location2;
        this.year = year;
		this.month = month;
		this.day = day;
    }

    // Compare by year (currently unused)
	@Override
	public int compareTo(Object o) {
		DisasterEvent other = (DisasterEvent) o;
		if (this.year < other.year) {
			return -1;
		} else if (this.year > other.year) {
			return 1;
		} else {
			if (this.month < other.month) {
				return -1;
			} else if (this.month > other.month) {
				return 1;
			} else {
				if (this.day < other.day) {
					return -1;
				} else if (this.day > other.day) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}

	/*
	Getters
	 */

    public String getType() {
        return type;
    }

    public LatLng getLocation1() {
        return location1;
    }
    
    public LatLng getLocation2() {
        return location2;
    }

	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}

	
	@Override
	public String toString() {
		return String.format("Type: %s, Lat: %.2f, Lng: %.2f, Year: %d, Month %d, Day %d", type, getLocation1().latitude, getLocation1().longitude, year, month, day);
	}
}