package group15.a2xb3.stormware;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Created by David on 2018-03-05.
 */

public class DisasterEvent {

    private final String type;
    private final LatLng location;
    private final Date date;
    private final float magnitude;

    public DisasterEvent(String type, LatLng location, Date date, float magnitude) {
        this.type = type;
        this.location = location;
        this.date = date;
        this.magnitude = magnitude;
    }

    public String getType() {
        return type;
    }

    public LatLng getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public float getMagnitude() {
        return magnitude;
    }

}
