package group15.a2xb3.stormware;//package group15.a2xb3.stormware;

//import com.google.android.gms.maps.model.LatLng;

//import java.util.Date;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by David on 2018-03-05.
 */

public class DisasterEvent implements Comparable {

    private final String type;
    private final LatLng location;
    private final int year;
	private final int month;
	private final int day;
    private final double magnitude;

    public DisasterEvent(String type, LatLng location, int year, int month, int day, double magnitude) {
        this.type = type;
        this.location = location;
        this.year = year;
		this.month = month;
		this.day = day;
        this.magnitude = magnitude;
    }

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
	
    public String getType() {
        return type;
    }

    public LatLng getLocation() {
        return location;
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
	
    public double getMagnitude() {
        return magnitude;
    }

	
//	@Override
//	public String toString() {
//		return String.format("Type: %s, Lat: %.2f, Lng: %.2f, Year: %d, Month %d, Day %d", type, location.getLat(), location.getLng(), year, month, day);
//	}
}
