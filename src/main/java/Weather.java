import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Weather {
    public static void main(String[] args) throws InterruptedException {
        while (true){
            getWeather();
            Thread.sleep(60000);
        }
    }
    public static void getWeather() {
        HttpResponse<String> response = Unirest.get("https://api.open-meteo.com/v1/forecast")
                .queryString("latitude", "23.6")
                .queryString("longitude", "58.6")
                .queryString("current_weather", "true")
                .header("Content-Type", "application/json")
                .asString();

        if (response.getStatus() == 200) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJson = gson.toJson(JsonParser.parseString(response.getBody()));
            System.out.println(prettyJson);
        } else {
            System.err.println("Request failed: " + response.getStatus());
        }
    }
}
