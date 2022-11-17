package sof306.ph18485.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.HttpURLConnection;
import java.net.URL;

public class Rest {

    private static ObjectMapper mapper = new ObjectMapper();
    
    public static JsonNode get(String path) {
        return request("GET", path, null);
    }
    
    public static JsonNode post(String path, Object data) {
        return request("POST", path, data);
    }
    
    public static JsonNode put(String path, Object data) {
        return request("PUT", path, data);
    }
    
    public static void delete(String path) {
        request("DELETE", path, null);
    }

    private static JsonNode request(String method, String path, Object data) {
        try {
            String uri = "https://rest-api-20613-default-rtdb.asia-southeast1.firebasedatabase.app" + path + ".json";
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod(method);
            if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
                conn.setDoOutput(true);
                mapper.writeValue(conn.getOutputStream(), data);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                return mapper.readTree(conn.getInputStream());
            }
            conn.disconnect();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
