package parser;

import gios.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class GiosParserTest {

    private final GiosParser giosParser = new GiosParser();
    private final String cacheDirName = "cacheTest";

    @Test
    void parse() {
        try {
            //Create cache
            File cacheDir = new File(this.cacheDirName);
            cacheDir.mkdirs();
            //station-findAll.txt
            PrintWriter writer = new PrintWriter(this.cacheDirName + "/station-findAll.txt", "UTF-8");
            writer.println("[{\"id\":0,\"stationName\":\"Dummy Station\",\"gegrLat\":\"51.115933\",\"gegrLon\":\"17.141125\",\"city\":{\"id\":0,\"name\":\"Dummy City\",\"commune\":{\"communeName\":\"Dummy Commune\",\"districtName\":\"Dummy District\",\"provinceName\":\"DUMMY PROVINCE\"}},\"addressStreet\":\"Dummy Street\"}]");
            writer.close();
            //index-0.txt
            writer = new PrintWriter(this.cacheDirName + "/index-0.txt", "UTF-8");
            writer.println("{\"id\":0," +
                    "\"stCalcDate\":\"2018-12-23 22:12:26\",\"stIndexLevel\":{\"id\":0,\"indexLevelName\":\"Bardzo dobry\"},\"stSourceDataDate\":\"2018-12-23 22:00:00\"," +
                    "\"so2CalcDate\":\"2018-12-23 22:12:26\",\"so2IndexLevel\":{\"id\":0,\"indexLevelName\":\"Bardzo dobry\"},\"so2SourceDataDate\":\"2018-12-23 22:00:00\"," +
                    "\"no2CalcDate\":1545599546000,\"no2IndexLevel\":{\"id\":0,\"indexLevelName\":\"Bardzo dobry\"},\"no2SourceDataDate\":\"2018-12-23 22:00:00\"," +
                    "\"coCalcDate\":\"2018-12-23 22:12:26\",\"coIndexLevel\":{\"id\":0,\"indexLevelName\":\"Bardzo dobry\"},\"coSourceDataDate\":\"2018-12-23 22:00:00\"," +
                    "\"pm10CalcDate\":\"2018-12-23 22:12:26\",\"pm10IndexLevel\":null,\"pm10SourceDataDate\":null," +
                    "\"pm25CalcDate\":\"2018-12-23 22:12:26\",\"pm25IndexLevel\":{\"id\":0,\"indexLevelName\":\"Bardzo dobry\"},\"pm25SourceDataDate\":\"2018-12-23 22:00:00\"," +
                    "\"o3CalcDate\":null,\"o3IndexLevel\":null,\"o3SourceDataDate\":null," +
                    "\"c6h6CalcDate\":\"2018-12-23 22:12:26\",\"c6h6IndexLevel\":{\"id\":0,\"indexLevelName\":\"Bardzo dobry\"},\"c6h6SourceDataDate\":\"2018-12-23 22:00:00\"," +
                    "\"stIndexStatus\":true,\"stIndexCrParam\":\"PYL\"}");
            writer.close();
            //sensors-0.txt
            writer = new PrintWriter(this.cacheDirName + "/sensors-0.txt", "UTF-8");
            writer.println("[{\"id\":0,\"stationId\":0,\"param\":{\"paramName\":\"dwutlenek azotu\",\"paramFormula\":\"NO2\",\"paramCode\":\"NO2\",\"idParam\":6}}," +
                    "{\"id\":1,\"stationId\":0,\"param\":{\"paramName\":\"pył zawieszony PM10\",\"paramFormula\":\"PM10\",\"paramCode\":\"PM10\",\"idParam\":3}}," +
                    "{\"id\":2,\"stationId\":0,\"param\":{\"paramName\":\"dwutlenek siarki\",\"paramFormula\":\"SO2\",\"paramCode\":\"SO2\",\"idParam\":1}}]");
            writer.close();
            //data-0.txt
            writer = new PrintWriter(this.cacheDirName + "/data-0.txt", "UTF-8");
            writer.println("{\"key\":\"NO2\",\"values\":[{\"date\":\"2018-12-23 23:00:00\",\"value\":24.5028},{\"date\":\"2018-12-23 22:00:00\",\"value\":19.3937}]}");
            writer.close();
            //data-1.txt
            writer = new PrintWriter(this.cacheDirName + "/data-1.txt", "UTF-8");
            writer.println("{\"key\":\"PM10\",\"values\":[{\"date\":\"2018-12-23 23:00:00\",\"value\":24.5028},{\"date\":\"2018-12-23 22:00:00\",\"value\":19.3937}]}");
            writer.close();
            //data-2.txt
            writer = new PrintWriter(this.cacheDirName + "/data-2.txt", "UTF-8");
            writer.println("{\"key\":\"SO2\",\"values\":[{\"date\":\"2018-12-23 23:00:00\",\"value\":24.5028},{\"date\":\"2018-12-23 22:00:00\",\"value\":19.3937}]}");
            writer.close();

            //Parsing
            this.giosParser.parseStations(this.cacheDirName);
            System.out.println("Cities: " + this.giosParser.getCities().size());
            assertEquals(1, this.giosParser.getCities().size());
            System.out.println("Stations: " + this.giosParser.getStations().size());
            assertEquals(1, this.giosParser.getStations().size());
            this.giosParser.parseIndices(this.cacheDirName);
            System.out.println("Indices: " + this.giosParser.getStations().size());
            this.giosParser.parseSensors(this.cacheDirName);
            System.out.println("Sensors: " + this.giosParser.getSensors().size());
            assertEquals(3, this.giosParser.getSensors().size());
            System.out.println("StationNames: " + this.giosParser.getStationNames2IDs().size());
            assertEquals(1, this.giosParser.getStationNames2IDs().size());
            this.giosParser.parseData(this.cacheDirName);

            //asserts
            assertEquals(new City(0, "dummy city", "dummy commune", "dummy district", "dummy province"),
                    this.giosParser.getCities().get(0));
            LocalDateTime calcDate = LocalDateTime.of(2018, 12, 23, 22, 12, 26);
            LocalDateTime sourceDate = LocalDateTime.of(2018, 12, 23, 22, 0);
            Index testIndex = new Index(new IndexValue[]{new IndexValue("bardzo dobry", calcDate, sourceDate),
                    new IndexValue("bardzo dobry", calcDate, sourceDate),
                    new IndexValue("bardzo dobry", calcDate, sourceDate),
                    new IndexValue("bardzo dobry", calcDate, sourceDate),
                    null,
                    new IndexValue("bardzo dobry", calcDate, sourceDate),
                    null,
                    new IndexValue("bardzo dobry", calcDate, sourceDate)});
            assertEquals(testIndex, this.giosParser.getStations().get(0).index);
            assertEquals(new Station(0, "dummy station", 51.115933f, 17.141125f, 0, "dummy street"),
                    this.giosParser.getStations().get(0));
            assertEquals(0, this.giosParser.getStationNames2IDs().get("dummy station"));
            assertEquals(new Sensor(0, 0, "dwutlenek azotu", "no2", "NO2", 6),
                    this.giosParser.getSensors().get(0));
            assertEquals(new Sensor(1, 0, "pył zawieszony pm10", "pm10", "PM10", 3),
                    this.giosParser.getSensors().get(1));
            assertEquals(new Sensor(2, 0, "dwutlenek siarki", "so2", "SO2", 1),
                    this.giosParser.getSensors().get(2));

        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        } finally {
            //Cleanup
            try {
                Files.walk(Paths.get(cacheDirName + "/"))
                        .map(Path::toFile)
                        .sorted((o1, o2) -> -o1.compareTo(o2))
                        .forEach(File::delete);
            } catch (IOException ex) {
                ex.printStackTrace();
                fail();
            }
        }
    }

    @Test
    void parseDate() {
        assertEquals("2018-12-23T23:00", GiosParser.parseDate("2018-12-23 23:00:00").toString());
        assertEquals("1982-12-23T23:00", GiosParser.parseDate("1982-12-23 23:00:00").toString());
        assertEquals("2018-01-23T23:00", GiosParser.parseDate("2018-01-23 23:00:00").toString());
        assertEquals("2018-12-15T23:00", GiosParser.parseDate("2018-12-15 23:00:00").toString());
        assertEquals("2018-12-23T00:00", GiosParser.parseDate("2018-12-23 00:00:00").toString());
    }

}
