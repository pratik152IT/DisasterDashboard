package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class EONETAPI {

    // NASA EONET (v3) endpoint
    private static final String API_URL = "https://eonet.gsfc.nasa.gov/api/v3/events";

    public static JSONArray fetchEvents() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Add headers to avoid 403 Forbidden errors
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; DisasterDashboard/1.0)");
        conn.setRequestProperty("Accept", "application/json");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException(
                "Error fetching data: Server returned HTTP response code: " 
                + responseCode + " for URL: " + API_URL
            );
        }

        // Read the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parse JSON response
        JSONObject data = new JSONObject(response.toString());
        return data.getJSONArray("events");
    }

    // For testing this class independently
    public static void main(String[] args) {
        try {
            JSONArray events = fetchEvents();
            System.out.println("Total Events: " + events.length());
            for (int i = 0; i < Math.min(events.length(), 10); i++) {
                JSONObject ev = events.getJSONObject(i);
                System.out.println("Event: " + ev.getString("title"));
                System.out.println("Link: " + ev.getString("link"));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
