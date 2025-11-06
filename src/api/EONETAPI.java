package api;

import java.net.*;
import java.io.*;
import org.json.*;

public class EONETAPI {
    public static JSONArray fetchEvents() throws Exception {
        String url = "https://eonet.gsfc.nasa.gov/api/v3/events";
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null)
            sb.append(line);
        reader.close();

        JSONObject data = new JSONObject(sb.toString());
        return data.getJSONArray("events");
    }
}
