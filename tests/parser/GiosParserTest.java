package parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GiosParserTest {

    @Test
    void parseStations() {
        //TODO
    }

    @Test
    void parseSensors() {
        //TODO
    }

    @Test
    void parseData() {
        //TODO
    }

    @Test
    void parseIndex() {
        //TODO
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
