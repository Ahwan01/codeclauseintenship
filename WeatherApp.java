import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApp {

    private static final String API_KEY = "7f5b00b8025cb68b441480540f10bd9c"; // 
  
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome to the Weather App!");
        System.out.print("Enter a location (city, state): ");
        String location = reader.readLine();

        String urlString = String.format(API_URL, location, API_KEY);
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != 200) {
            System.out.println("Error: " + connection.getResponseMessage());
            return;
        }

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        connection.disconnect();

        JSONObject weatherData = new JSONObject(response.toString());

        System.out.println("\nWeather for " + location + ":");
        System.out.println("Description: " + weatherData.getJSONArray("weather").getJSONObject(0).getString("description"));
        System.out.println("Temperature: " + (weatherData.getMainJSONObject().getDouble("temp") - 273.15) + "°C"); // Convert Kelvin to Celsius
        System.out.println("Feels like: " + (weatherData.getMainJSONObject().getDouble("feels_like") - 273.15) + "°C");

    }
}
