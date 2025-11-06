package api;

import java.net.*;
import java.io.*;
import org.json.*;

public class USGSEarthquakeAPI {
    public static JSONArray fetchEarthquakes() throws Exception {
        String url = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson";
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null)
            sb.append(line);
        reader.close();

        JSONObject data = new JSONObject(sb.toString());
        return data.getJSONArray("features");
    }
}
