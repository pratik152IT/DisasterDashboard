package service;

import api.USGSEarthquakeAPI;
import api.EONETAPI;
import model.Disaster;
import org.json.*;
import java.util.*;

public class DisasterService {

    public static List<Disaster> getAllDisasters() throws Exception {
        List<Disaster> disasters = new ArrayList<>();

        // 🌋 Fetch Earthquakes
        JSONArray quakes = USGSEarthquakeAPI.fetchEarthquakes();
        for (int i = 0; i < Math.min(5, quakes.length()); i++) {
            JSONObject props = quakes.getJSONObject(i).getJSONObject("properties");
            String place = props.optString("place", "Unknown Location");
            String time = new java.util.Date(props.optLong("time")).toString();
            disasters.add(new Disaster("Earthquake", props.optString("title"), place, time));
        }

        // 🔥 Fetch Natural Events
        JSONArray events = EONETAPI.fetchEvents();
        for (int i = 0; i < Math.min(5, events.length()); i++) {
            JSONObject ev = events.getJSONObject(i);
            String title = ev.optString("title");
            String date = ev.getJSONArray("geometry").getJSONObject(0).optString("date");
            disasters.add(new Disaster("Natural Event", title, "Global", date));
        }

        return disasters;
    }
}
