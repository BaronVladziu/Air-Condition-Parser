package app;

import gios.Sensor;
import gios.Station;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

public class CacheUpdater {

    private static final HttpGetter HTTP_GETTER = new HttpGetter();
    private static final float updateAfterMinutes = 100000;

    private final Calendar cal = Calendar.getInstance();


    public void updateStationCache() throws IOException {
        this.updateFile("cache/station-findAll.txt", "http://api.gios.gov.pl/pjp-api/rest/station/findAll");
    }

    public void updateSensorCache(Iterable<Station> stations) throws IOException {
        for (Station station : stations) {
            this.updateFile("cache/sensors-" + station.id + ".txt", "http://api.gios.gov.pl/pjp-api/rest/station/sensors/" + station.id);
        }
    }

    public void updateDataCache(Iterable<Sensor> sensors) throws IOException {
        for (Sensor sensor : sensors) {
            this.updateFile("cache/data-" + sensor.id + ".txt", "http://api.gios.gov.pl/pjp-api/rest/data/getData/" + sensor.id);
        }
    }

    public void updateIndexCache(Iterable<Station> stations) throws IOException {
        for (Station station : stations) {
            this.updateFile("cache/index-" + station.id + ".txt", "http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/" + station.id);
        }
    }

    private void updateFile(String filePath, String url) {
        if (this.cal.getTimeInMillis() -  new File(filePath).lastModified() > updateAfterMinutes * 60000) {
            try {
                System.out.println("--- Updating file: " + filePath + " ---");
                PrintWriter writer = new PrintWriter(filePath, "UTF-8");
                writer.print(HTTP_GETTER.getHTML(url));
                writer.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
