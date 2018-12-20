package app;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.StringReader;

public class App {

    public static void main(String[] args) {
        try {
            StringReader stringReader = new StringReader(HttpGetter.getHTML("http://api.gios.gov.pl/pjp-api/rest/station/findAll"));
            JsonReader jsonReader = Json.createReader(stringReader);
            JsonArray json = jsonReader.readArray();
            System.out.println(json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
