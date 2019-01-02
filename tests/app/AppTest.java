package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void main() {
        App.main(new String[]{});
    }

    @Test
    void mainPrintIndex1() {
        App.main(new String[]{"printIndex"});
    }

    @Test
    void mainPrintIndex2() {
        App.main(new String[]{"printIndex", "siedlce-konarskiego", "POŁANIEC, UL. RUSZCZAŃSKA"});
    }

    @Test
    void mainPrintValues1() {
        App.main(new String[]{"printValues", "PM25", "2018-12-23 23:00:00"});
    }

    @Test
    void mainPrintValues2() {
        App.main(new String[]{"printValues", "PM25", "2018-12-23 23:00:00", "siedlce-konarskiego", "POŁANIEC, UL. RUSZCZAŃSKA"});
    }

    @Test
    void mainPrintActualValues1() {
        App.main(new String[]{"printActualValues", "c6h6"});
    }

    @Test
    void mainPrintActualValues2() {
        App.main(new String[]{"printActualValues", "pm25", "siedlce-konarskiego", "POŁANIEC, UL. RUSZCZAŃSKA"});
    }

    @Test
    void mainPrintLowestParameter() {
        App.main(new String[]{"printLowestParameter", "2018-12-23 23:00:00"});
    }

    @Test
    void mainPrintActualHighestParameter() {
        App.main(new String[]{"printActualHighestParameter", "siedlce-konarskiego", "POŁANIEC, UL. RUSZCZAŃSKA"});
    }

    @Test
    void mainPrintLowestValue() {
        App.main(new String[]{"printLowestValue", "pm10", "2018-12-23 23:00:00"});
    }

    @Test
    void mainPrintActualHighestValue() {
        App.main(new String[]{"printActualHighestValue", "o3", "siedlce-konarskiego", "POŁANIEC, UL. RUSZCZAŃSKA"});
    }

    @Test
    void mainPrintActualHighestNValues() {
        App.main(new String[]{"printActualHighestNValues", "PM2.5", "10"});
    }

    @Test
    void mainPrintMean() {
        App.main(new String[]{"printMean", "c6h6"});
    }

    @Test
    void mainPrintActualVariance() {
        App.main(new String[]{"printActualVariance", "c6h6", "2017-12-23 23:00:00"});
    }

    @Test
    void mainPrintRangedRange() {
        App.main(new String[]{"printRangedRange", "c6h6", "2017-12-23 23:00:00", "2018-12-23 23:00:00"});
    }

    @Test
    void mainPrintRangedFigure1() {
        App.main(new String[]{"printFigure", "pm25"});
    }

    @Test
    void mainPrintRangedFigure2() {
        App.main(new String[]{"printRangedFigure", "PM25", "2017-12-23 23:00:00", "2018-12-23 23:00:00"});
    }

    @Test
    void mainPrintFigure() {
        App.main(new String[]{"printFigure", "PM25", "lublin ul. obywatelska"});
    }

}
