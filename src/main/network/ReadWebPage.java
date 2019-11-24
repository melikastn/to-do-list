package network;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


public class ReadWebPage {

    private static String quoteOfDay;

    public static void main(String[] args) throws IOException {
        URLConnection uc;
        StringBuilder parsedContentFromUrl = new StringBuilder();
        String urlString = "http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";
        URL url = new URL(urlString);
        uc = url.openConnection();
        uc.connect();
        uc = url.openConnection();
        uc.addRequestProperty("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
        uc.getInputStream();
        BufferedInputStream in = new BufferedInputStream(uc.getInputStream());
        int ch;
        while ((ch = in.read()) != -1) {
            parsedContentFromUrl.append((char) ch);
        }
        Map<String, Object> respMap = jsonToMap(parsedContentFromUrl.toString());
        System.out.println("Quote of the day: " + respMap.get("quoteText"));
        //getWeather();

    }

    private static void getWeather() throws IOException {
        String apiKey = "72acbe81f173eb0b17f12195e939e358";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=Vancouver&units=metric&APPID=" + apiKey;
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlString);
        URLConnection uc = url.openConnection();
        BufferedReader rd = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        Map<String, Object> respMap = jsonToMap(result.toString());
        Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
//      Map<String, Object> windMap = jsonToMap(mainMap.get("wind").toString());
        System.out.println("Current temperature in Vancouver: " + mainMap.get("temp") + "°C");
        System.out.println("Humidity is: " + mainMap.get("humidity") + "gm⁻³");
//      System.out.println("Current wind Speed: " + windMap.get("speed"));
    }

    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );
        return map;
    }
}